import { backendAxios } from "@/api/axios";
import axios from "axios";

const instance = backendAxios();

async function listAttraction(param) {
	const response = await instance.get(`/attraction`, { params: param });
	//return response;
	return response.data;
}

async function detailAttraction(contentId, param) {
	const response = await instance.get(`/attraction/${contentId}`, { params: param });
	return response.data;
}

async function ratingAttraction(contentId, param) {
	const response = await instance.get(`/rating/${contentId}`, { params: param });
	return response.data;
}

async function planAttractionList(planId) {
	const response = await instance.get(`/attraction/plan/${planId}`);
 	return response.data;
}

async function likeAttraction(contentId, param) {
	console.log(param);
	await instance.post(`/attraction/like/${contentId}`, param);
}

async function dislikeAttraction(contentId, userId) {
	console.log(userId);
	await instance.delete(`/attraction/dislike/${contentId}/${userId}`);
}

async function postRating(ratingContent) {
	const response = await instance.post(`/rating`, ratingContent);
	return response;
}

async function listLocations() {
	const response = await instance.get(`/attraction/location`);
	return response.data;
}

async function listContent() {
	const response = await instance.get(`/attraction/contentType`);
	return response.data;
}

const attractionCode = {
	12: "관광지",
	14: "문화시설",
	15: "축제공연행사",
	25: "여행코스",
	28: "레포츠",
	32: "숙박",
	38: "쇼핑",
	39: "음식점",
};

export {
	listAttraction,
	detailAttraction,
	ratingAttraction,
	attractionCode,
	likeAttraction,
	dislikeAttraction,
	listLocations,
	listContent,
	postRating,
	planAttractionList,
};
