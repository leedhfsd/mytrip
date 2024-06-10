<script setup>
	import { ref, computed, defineProps, defineEmits, onMounted, watch } from "vue";

	const props = defineProps({
		currentPage: {
			type: Number,
			required: true,
		},
		totalPage: {
			type: Number,
			required: true,
		},
	});

	const emit = defineEmits(["pageChange"]);
	const page = ref(1);
	const totalPages = computed(() => props.totalPage);
	watch(page, (newValue) => {
		emit("pageChange", newValue);
	});
</script>

<template>
	<div class="text-center">
		<v-container>
			<v-row justify="center">
				<v-col cols="8">
					<v-container class="max-width">
						<v-pagination
							:length="totalPages"
							:total-visible="8"
							class="my-4"
							v-model="page"
						></v-pagination>
					</v-container>
				</v-col>
			</v-row>
		</v-container>
	</div>
</template>

<style scoped>
	a {
		cursor: pointer;
	}
</style>
