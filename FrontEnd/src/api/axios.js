import axios from "axios";

const { VITE_BACKEND_API_URL } = import.meta.env;

function backendAxios() {
  const instance = axios.create({
    baseURL: VITE_BACKEND_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  instance.defaults.headers.common["Authorization"] = "";
  instance.defaults.headers.post["Content-Type"] = "application/json";
  instance.defaults.headers.put["Content-Type"] = "application/json";
  return instance;
}

export { backendAxios };
