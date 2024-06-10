import "./assets/main.css";
import { createApp } from "vue";
import { createPinia } from "pinia";

import "vuetify/styles";
import { createVuetify } from "vuetify";
import { aliases, mdi } from "vuetify/iconsets/mdi-svg";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";

import App from "./App.vue";
import { useKakao } from "vue3-kakao-maps/@utils";
useKakao(import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY);

import router from "./router";
import { createPersistedState } from "pinia-plugin-persistedstate";

const vuetify = createVuetify({
	components,
	directives,
	icons: {
		defaultSet: "mdi",
		aliases,
		sets: {
			mdi,
		},
	},
	defaults: {
		VBtn: {
			variant: "plain",
		},
	},
	breakpoints: {
		thresholds: {
			lg: 0,
		},
	},
	breakpoint: {
		thresholds: {
			xs: 600,
			sm: 960,
			md: 1366,
			lg: 1924,
		},
	},
});
const app = createApp(App);
const pinia = createPinia();
pinia.use(
	createPersistedState({
		storage: sessionStorage,
	})
);

app.use(pinia);
app.use(router);
app.use(vuetify);
app.mount("#app");
