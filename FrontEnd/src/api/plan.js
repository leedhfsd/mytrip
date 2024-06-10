import { backendAxios } from "@/api/axios";
import axios from "axios";

const instance = backendAxios();

async function listPlan(userId) {
  const response = await instance.get(`/plan/${userId}`);
  return response.data;
}

async function userPlan(planId) {
  const response = await instance.get(`/plan/user/${planId}`);
  return response.data;
}

async function confirmPlan(param) {
  console.log(param);
  await instance.post(`/plan`, param);
}

async function updatePlan(planId, param) {
  console.log(param);
  await instance.put(`/plan/${planId}`, param);
}

export { listPlan, userPlan, confirmPlan, updatePlan };
