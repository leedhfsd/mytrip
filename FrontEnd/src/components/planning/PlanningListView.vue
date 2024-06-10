<script setup>
import { ref, onMounted } from "vue";
import { listPlan } from "@/api/plan.js";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import PlanningListItem from "@/components/planning/item/PlanningListItem.vue";
import { mdiCalendar } from "@mdi/js";

const store = useMemberStore();
const { userId } = storeToRefs(store);

const planList = ref([]);

const getPlanList = async () => {
  const data = await listPlan(store.userId);
  planList.value = data;
  //console.log(planList);
  //console.log(planList.value);
};

onMounted(() => {
  //console.log("plan : " + store.userId);
  getPlanList();
  console.log(planList);
});
</script>

<template>
  <v-container style="height: 100vh">
    <div class="mt-1">
      <div class="my-4 text-h5">
        {{ store.userId }}님의 일정관리 <v-icon :icon="mdiCalendar"></v-icon>
      </div>
      <div class="my-1">
        <v-btn
          style="color: white; background-color: tomato; margin-bottom: 16px"
          :to="{ name: 'plan-form', params: { planId: 'new' } }"
        >
          새 계획 만들기
        </v-btn>
      </div>
    </div>
    <v-divider></v-divider>
    <v-row class="mt-12">
      <planning-list-item
        v-for="list in planList"
        :key="list.planId"
        :plan="list"
      >
      </planning-list-item>
    </v-row>
  </v-container>
</template>

<style scoped></style>
