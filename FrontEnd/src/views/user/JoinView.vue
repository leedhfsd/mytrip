<script setup>
	import { ref, watch } from "vue";
	import { useMemberStore } from "@/stores/member";
	import UserJoinSuccess from "@/components/user/UserJoinSuccess.vue";
	import Logo from "@/assets/Logo.vue";
	import { mdiEyeOff, mdiEye, mdiLockOutline, mdiEmailOutline, mdiChevronRight, mdiAccountOutline } from "@mdi/js";
	const store = useMemberStore();
	const enteredEmail = ref("");
	const checkPassword = ref("");
	const userInfo = ref({
		userId: "",
		userPw: "",
		emailId: "",
		emailDomain: "",
	});
	watch(enteredEmail, (newValue) => {
		const [emailId, emailDomain] = newValue.split("@");
		userInfo.value.emailId = emailId;
		userInfo.value.emailDomain = emailDomain;
	});

	const joinFormRule = [
		(value) => {
			const idRegex = /^[a-z0-9]*[a-z][a-z0-9]*$/;
			if (idRegex.test(value) && value.length >= 8 && value.length < 13) {
				return true;
			}
			return "영소문자와 숫자만 사용 가능합니다.(8-12자)";
		},
	];

	const joinFormEmailRule = [
		(value) => {
			const idRegex = /[a-z0-9]+@[a-z]+\.[a-z]{2,3}/;
			if (idRegex.test(value)) {
				return true;
			}
			return "올바른 이메일을 입력해주세요.";
		},
	];

	const checkPasswordRule = [
		(value) => {
			if (userInfo.value.userPw === value) {
				return true;
			}
			return "동일한 비밀번호를 입력해주세요.";
		},
	];

	const form = ref(null);
	async function validate() {
		const { valid } = await form.value.validate();
		if (valid) {
			const status = await store.userJoin(userInfo.value);
			if (status === 200) {
				dialog.value = true;
				console.log(dialog.value);
				console.log("!$@#$!@#$!@");
			}
		}
	}
	const dialog = ref(false);
	const handleSubmit = () => {
		validate();
	};
	const visible = ref(false);
</script>

<template>
	<user-join-success :dialog="dialog" />
	<div class="" style="min-height: 100vh">
		<div class="d-flex justify-center">
			<Logo width="250" height="150" />
		</div>

		<v-form ref="form" lazy-validation @submit.prevent="handleSubmit">
			<v-card class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg">
				<div class="text-subtitle-1 text-medium-emphasis mb-1">아이디</div>

				<v-text-field
					density="compact"
					v-model="userInfo.userId"
					color="primary"
					placeholder="아이디를 입력해주세요."
					:prepend-inner-icon="mdiAccountOutline"
					variant="outlined"
					autofocus
					:rules="joinFormRule"
					class="mb-2"
				></v-text-field>

				<div class="text-subtitle-1 text-medium-emphasis mb-1">이메일</div>

				<v-text-field
					density="compact"
					v-model="enteredEmail"
					color="primary"
					placeholder="이메일을 입력해주세요."
					:prepend-inner-icon="mdiEmailOutline"
					variant="outlined"
					:rules="joinFormEmailRule"
					class="mb-2"
				></v-text-field>

				<div class="mb-1 text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
					비밀번호
				</div>

				<v-text-field
					v-model="userInfo.userPw"
					color="primary"
					:append-inner-icon="visible ? mdiEyeOff : mdiEye"
					:type="visible ? 'text' : 'password'"
					density="compact"
					placeholder="비밀번호를 입력해주세요."
					:prepend-inner-icon="mdiLockOutline"
					variant="outlined"
					@click:append-inner="visible = !visible"
					:rules="joinFormRule"
					class="mb-2"
				></v-text-field>

				<div class="mb-1 text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
					비밀번호 확인
				</div>

				<v-text-field
					:v-model="userInfo.checkPassword"
					color="primary"
					:append-inner-icon="visible ? mdiEyeOff : mdiEye"
					:type="visible ? 'text' : 'password'"
					density="compact"
					placeholder="동일한 비밀번호를 입력해주세요."
					:prepend-inner-icon="mdiLockOutline"
					variant="outlined"
					@click:append-inner="visible = !visible"
					:rules="checkPasswordRule"
					class="mb-2"
				></v-text-field>

				<v-btn class="mt-8" color="primary" type="submit" size="large" variant="elevated" block>회원가입</v-btn>
			</v-card>
		</v-form>
	</div>
</template>
<style scoped></style>
