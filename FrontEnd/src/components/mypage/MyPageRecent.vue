<script setup>
import { ref, onMounted } from "vue";
//import { attractionCode } from "@/api/mypage";

const recents = ref([]);

onMounted(() => {});
</script>
<template>
  <v-col>
    <v-sheet min-height="70vh" rounded="lg">
      <h1>최근 활동내역</h1>
    </v-sheet>
  </v-col>
</template>

<style scoped></style>
