<script setup>
defineProps({ attraction: Object });
</script>

<template>
  <v-list-item
    :title="attraction.title"
    :subtitle="attraction.overview"
    :prepend-avatar="attraction.firstImage"
  >
  </v-list-item>
</template>

<style scoped></style>
