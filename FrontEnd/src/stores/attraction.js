import { defineStore } from "pinia";
import { ref } from "vue";

export const useAttractionStore = defineStore("attraction", () => {
	const ratingAttractions = ref([]);
	const foodAttractions = ref([]);
	const hotelAttractions = ref([]);
	const shoppingAttractions = ref([]);

	return { ratingAttractions, foodAttractions, hotelAttractions, shoppingAttractions };
});
