<script setup>
	import { mdiCheckCircle } from "@mdi/js";
	import router from "@/router";
	const props = defineProps({
		dialog: {
			type: Boolean,
			required: true,
		},
	});
	const goHome = () => {
		router.replace({
			name: "home",
		});
	};
</script>

<template>
	<v-dialog v-model="props.dialog">
		<v-sheet class="pa-4 text-center mx-auto" elevation="12" max-width="600" rounded="lg" width="100%">
			<v-icon class="mb-5" color="success" :icon="mdiCheckCircle" size="112"></v-icon>

			<h2 class="text-h5 mb-6">회원가입이 완료되었습니다.</h2>

			<p class="mb-4 text-medium-emphasis text-body-2">
				대한민국의 다양한 여행지를 살펴보세요.
				<br />
				<span class="text-medium-emphasis"
					>여행지 정보와 후기를 보며 <span class="text-blue-lighten-1">나만의 여행계획</span>을 세울 수
					있습니다!</span
				>
			</p>

			<v-divider class="mb-4"></v-divider>

			<div class="text-center">
				<v-btn class="text-none" color="success" variant="flat" width="90" rounded @click="goHome">
					완료
				</v-btn>
			</div>
		</v-sheet>
	</v-dialog>
</template>
<style scoped></style>
