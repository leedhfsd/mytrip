<script setup>
	import { ref } from "vue";
	import AttractionCarousel from "@/components/attraction/AttractionCarousel.vue";

	const heading = ref(["가장 인기 있는 장소", "최고의 요리 명소", "나를 위한 최고의 숙소", "쇼핑은 여기에서!!"]);
	const infos = ref([
		{ sort: "favorite", contentTypeId: null, attractionTitle: "rating" },
		{ sort: "rating", contentTypeId: "39", attractionTitle: "food" },
		{ sort: "rating", contentTypeId: "32", attractionTitle: "hotel" },
		{ sort: "favorite", contentTypeId: "38", attractionTitle: "shopping" },
	]);
</script>

<template>
	<v-container style="min-height: 100vh">
		<attraction-carousel
			v-for="(headingTitle, index) in heading"
			:key="index"
			:title="heading[index]"
			:info="infos[index]"
		/>
	</v-container>
</template>

<style scoped></style>
