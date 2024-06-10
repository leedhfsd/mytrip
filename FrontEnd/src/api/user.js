import { backendAxios } from "@/api/axios";

const instance = backendAxios();

async function login(param, success, fail) {
	await instance.post(`/user/login`, param).then(success).catch(fail);
}

async function logout(userid, success, fail) {
	await instance.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function findById(userid, success, fail) {
	instance.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
	await instance.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
	instance.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
	await instance.post(`/user/refresh`, user).then(success).catch(fail);
}

async function listAttraction(param) {
	const response = await instance.get(`/attraction`, { params: param });
	return response.data;
}

async function join(userInfo, success, fail) {
	const response = await instance.post(`/user/join`, userInfo).then(success).catch(fail);
	return response;
}

export { login, logout, findById, tokenRegeneration, join, listAttraction };
