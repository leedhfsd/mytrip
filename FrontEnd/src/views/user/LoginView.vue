<script setup>
	import { ref } from "vue";
	import { mdiEyeOff, mdiEye, mdiLockOutline, mdiEmailOutline, mdiChevronRight, mdiAccountOutline } from "@mdi/js";
	import { useMemberStore } from "@/stores/member";
	import { storeToRefs } from "pinia";
	import { useRouter } from "vue-router";
	import Logo from "@/assets/Logo.vue";
	const router = useRouter();
	const store = useMemberStore();
	const { isLogin, isLoginError } = storeToRefs(store);
	const loginUser = ref({
		userId: "",
		userPw: "",
	});
	const loginErrorMessage = ref("");
	const login = async () => {
		loginErrorMessage.value = ""; // 로그인 시도 전에 메시지 초기화
		if (loginUser.value.userId === "" || loginUser.value.userPw === "") {
			loginErrorMessage.value = "아이디와 비밀번호를 모두 입력해주세요.";
			return;
		}
		await store.userLogin(loginUser.value);
		if (isLoginError.value) {
			loginErrorMessage.value = "아이디 또는 비밀번호가 올바르지 않습니다.";
			return;
		}
		if (isLogin.value) {
			let token = sessionStorage.getItem("accessToken");
			console.log(token);
			console.log(loginUser);
			store.getUserInfo(token);
			router.go(-1);
		}
	};

	const visible = ref(false);
</script>

<template>
	<div class="" style="min-height: 100vh">
		<div class="d-flex justify-center">
			<Logo width="250" height="150" />
		</div>
		<v-card class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg">
			<div class="text-subtitle-1 text-medium-emphasis mb-1">아이디</div>

			<v-text-field
				density="compact"
				v-model="loginUser.userId"
				color="primary"
				placeholder="아이디를 입력해주세요."
				:prepend-inner-icon="mdiAccountOutline"
				variant="outlined"
				autofocus=""
			></v-text-field>

			<div class="mb-1 text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
				비밀번호
			</div>

			<v-text-field
				v-model="loginUser.userPw"
				color="primary"
				:append-inner-icon="visible ? mdiEyeOff : mdiEye"
				:type="visible ? 'text' : 'password'"
				density="compact"
				placeholder="비밀번호를 입력해주세요."
				:prepend-inner-icon="mdiLockOutline"
				variant="outlined"
				@click:append-inner="visible = !visible"
				@keyup.enter="login"
			></v-text-field>
			<p v-show="loginErrorMessage !== ''" style="font-size: 12px; color: #b00020" class="mb-2">
				{{ loginErrorMessage }}
			</p>
			<v-card class="mb-8" color="surface-variant" variant="tonal">
				<v-card-text class="text-medium-emphasis text-caption">
					안녕하세요! 여행 정보 사이트 MyTrip입니다.
					<br />
					여행 일정 작성, 좋아요, 후기 남기기 기능으로
					<br />
					편리하고 즐거운 여행을 도와드립니다.
					<br />
					MyTrip과 함께 국내 곳곳을 탐험해 보세요!
					<br />
					회원이 되신다면 더 많은 혜택을 누리실 수 있습니다.
				</v-card-text>
			</v-card>

			<v-btn class="mb-4" color="primary" size="large" variant="elevated" block @click="login">로그인</v-btn>

			<v-card-text class="text-center">
				<v-btn class="text-blue text-decoration-none" to="/join">
					회원가입 <v-icon :icon="mdiChevronRight"></v-icon>
				</v-btn>
			</v-card-text>
		</v-card>
	</div>
</template>
<style scoped></style>
