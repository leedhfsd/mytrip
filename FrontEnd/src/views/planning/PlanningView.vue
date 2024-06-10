<script setup>
import { usePlanningStore } from "@/stores/planning";
const store = usePlanningStore();
const plans = store.plans;
</script>

<template>
  <!-- <h1>계획 페이지</h1> -->
  <router-view></router-view>
</template>

<style scoped></style>
