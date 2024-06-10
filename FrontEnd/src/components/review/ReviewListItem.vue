<script setup>
	import { ref } from "vue";

	const props = defineProps({
		review: {
			type: Object,
			required: true,
		},
	});

	function formatDate(dateString) {
		const [year, month, day] = dateString.split("-");
		const shortYear = year.slice(2);
		const formattedDate = `${shortYear}년 ${month}월`;
		return formattedDate;
	}
</script>

<template>
	<v-card class="mx-auto my-12" elevation="3" width="500">
		<div class="d-flex align-center mx-4">
			<v-avatar>
				<v-img src="https://cdn.vuetifyjs.com/images/cards/halcyon.png"></v-img>
			</v-avatar>
			<v-card-text class="">
				<div class="font-weight-bold" style="font-size: 18px">
					{{ review.userId }}
				</div>
				<div class="d-flex">
					<div class="mr-2"><strong>방문 날짜:</strong> {{ formatDate(review.visitDate) }} /</div>
					<div><strong>작성 날짜:</strong> {{ review.updateDate.split(" ")[0] }}</div>
				</div>
				<div class="d-flex">
					<v-rating
						v-model="review.rating"
						active-color="#51ABF3"
						color="#DADCE0"
						size="18"
						half-increments
						hover
						readonly
					></v-rating>
					<span class="text-grey-darken-1 text-caption me-2 mt-1"> ({{ review.rating }}) </span>
				</div>
			</v-card-text>
		</div>
		<v-divider></v-divider>
		<v-card-text>
			{{ review.description }}
		</v-card-text>
	</v-card>
	<div class="mx-auto" style="width: 500px">
		<v-divider></v-divider>
	</div>
</template>

<style scoped></style>
