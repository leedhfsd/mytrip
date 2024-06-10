import { backendAxios } from "@/api/axios";
import axios from "axios";

const instance = backendAxios();

async function listAttraction(param) {
  const response = await instance.get(`/attraction`, { params: param });
  return response.data;
}

async function myRating(param) {
  const response = await instance.get(`/attraction`, { params: param });
  return response.data;
}

export { listAttraction };
