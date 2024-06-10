<script setup>
import router from "@/router";

defineProps({ plan: Object });
const navigateToPlanForm = () => {
  console.log(plan);
  router.push({ name: "plan-form", params: { planId: plan.planId } });
};
</script>

<template>
  <v-col col="12" md="3">
    <v-card class="mx-auto card" max-width="350" rounded>
      <div v-if="plan.firstImage !== null">
        <router-link
          :to="{ name: 'plan-form', params: { planId: plan.planId } }"
        >
          <v-img
            height="200px"
            :src="plan.firstImage"
            cover
            @click="navigateToPlanForm"
            class="cursor-pointer"
          ></v-img>
        </router-link>
      </div>
      <div v-else>
        <router-link
          :to="{ name: 'plan-form', params: { planId: plan.planId } }"
        >
          <div
            class="bg-grey-lighten-3 cursor-pointer"
            @click="navigateToPlanForm"
            style="height: 200px"
          ></div>
        </router-link>
      </div>
      <v-card-title class="font-weight-bold"> {{ plan.title }} </v-card-title>
      <v-card-text style="padding-bottom: 0px">
        <span
          >여행 기간:
          <span class="text-caption"
            >{{ plan.fromDate }} ~ {{ plan.toDate }}</span
          ></span
        >
      </v-card-text>
      <v-card-text style="padding-top: 0px">
        <span
          >최근 수정:
          <span class="text-caption">{{
            plan.updateDate.split(" ")[0]
          }}</span></span
        >
      </v-card-text>
      <v-card-actions>
        <v-btn
          class="mt-4"
          color="red-lighten-2"
          text="수정"
          :to="{ name: 'plan-form', params: { planId: plan.planId } }"
        >
          수정</v-btn
        >
      </v-card-actions>
    </v-card>
  </v-col>
</template>

<style scoped>
.card {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}
</style>
