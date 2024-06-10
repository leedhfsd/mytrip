<script setup>
	import { useAttractionStore } from "@/stores/attraction";
	import { useMemberStore } from "@/stores/member";
	import { storeToRefs } from "pinia";
	import { ref, onMounted, computed } from "vue";
	import { listAttraction } from "@/api/att.js";
	import AttractionListItem from "@/components/attraction/AttractionListItem.vue";
	const props = defineProps({
		title: {
			type: String,
			required: true,
		},
		info: {
			type: Object,
			required: false,
			sort: {
				type: String,
				required: false,
			},
			contentTypeId: {
				type: String,
				required: false,
			},
			attractionTitle: {
				type: String,
				required: false,
			},
		},
		contentTypeId: {
			type: String,
			required: false,
		},
		word: {
			type: String,
			required: false,
		},
	});
	const isLoading = ref(true);

	const filter = ref({
		pgno: 1,
		spp: 12,
		contentTypeId: props.info?.contentTypeId,
		sort: props.info?.sort,
		userId: null,
	});

	const newFilter = ref({
		pgno: 1,
		spp: 12,
		contentTypeId: "",
		word: "",
	});

	const store = useAttractionStore();
	const user_store = useMemberStore();
	const res = ref([]);
	const { ratingAttractions, foodAttractions, hotelAttractions, shoppingAttractions, userId } = storeToRefs(
		store,
		user_store
	);

	const getAttractionList = async () => {
		const data = await listAttraction(filter.value);
		return data;
	};

	const fetchData = async () => {
		const response = await getAttractionList();
		//const data = response.data;
		const data = response;
		if (props.info !== undefined) {
			if (props.info.attractionTitle === "rating") {
				store.ratingAttractions = data.attractions;
				res.value = data.attractions;
			} else if (props.info.attractionTitle === "food") {
				store.foodAttractions = data.attractions;
				res.value = data.attractions;
			} else if (props.info.attractionTitle === "hotel") {
				store.hotelAttractions = data.attractions;
				res.value = data.attractions;
			} else if (props.info.attractionTitle === "shopping") {
				store.shoppingAttractions = data.attractions;
				res.value = data.attractions;
			}
		} else {
			// title,  contentid, word로 캐러셀 만들기
			newFilter.value.contentTypeId = props.contentTypeId;
			newFilter.value.word = props.word;
			const response = await listAttraction(newFilter.value);
			//res.value = response.data.attractions;
			res.value = response.attractions;
		}
		isLoading.value = false;
	};

	onMounted(() => {
		filter.value.userId = user_store.userId;
		fetchData();
	});

	const attractionGroups = computed(() => {
		const groupSize = 4;
		return res.value.reduce((acc, attraction, index) => {
			const groupIndex = Math.floor(index / groupSize);
			if (!acc[groupIndex]) {
				acc[groupIndex] = [];
			}
			acc[groupIndex].push(attraction);
			return acc;
		}, []);
	});
</script>

<template>
	<div v-if="isLoading" class="text-center py-8" style="min-height: 300px">
		<v-progress-circular indeterminate size="64" color="primary"></v-progress-circular>
	</div>
	<v-carousel v-if="!isLoading" hide-delimiters>
		<p class="text-h5 mb-3 font-weight-bold">{{ props.title }}</p>
		<v-carousel-item v-for="(group, index) in attractionGroups" :key="index">
			<v-row>
				<v-col v-for="attraction in group" :key="attraction.contentId" cols="3">
					<attraction-list-item :attraction="attraction" />
				</v-col>
			</v-row>
		</v-carousel-item>
	</v-carousel>
</template>

<style lang="scss" scoped></style>
