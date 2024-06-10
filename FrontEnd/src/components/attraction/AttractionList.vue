<script setup>
	import AttractionListItem from "./AttractionListItem.vue";
	import { useAttractionStore } from "@/stores/attraction";
	const store = useAttractionStore();
	const attractions = store.attractions;
</script>

<template>
	<attraction-list-item v-for="attraction in attractions" :key="attraction.id" :attraction="attraction" />
</template>

<style scoped></style>
