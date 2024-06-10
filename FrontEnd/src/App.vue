<script setup>
import { ref, computed } from "vue";
import { mdiMagnify } from "@mdi/js";
import router from "./router";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { mdiFacebook, mdiTwitter, mdiLinkedin, mdiInstagram } from "@mdi/js";
import Logo from "@/assets/Logo.vue";
import { useDisplay } from "vuetify";

const { lgAndDown } = useDisplay;
const store = useMemberStore();
const { isAuthenticated } = storeToRefs(store);
const word = ref("");
const links = ref([
  {
    menu: "추천 여행지",
    link: "/recommend",
  },
  {
    menu: "일정",
    link: "/planning",
  },
  {
    menu: "검색",
    link: "/searching",
  },
]);

const navigateToSearch = () => {
  if (word.value.length == 0) return;
  router.push(`/search/${word.value}`);
  word.value = "";
};

const logout = () => {
  store.userLogout();
  router.push(`/`);
};
</script>

<template>
  <div class="box">
    <v-app>
      <v-app-bar flat>
        <v-container class="mx-auto d-flex align-center justify-center box">
          <router-link to="/">
            <Logo width="150" height="300" />
          </router-link>
          <v-responsive max-width="350" class="ml-8">
            <v-text-field
              width="350"
              density="compact"
              label="여행지 검색.."
              rounded="lg"
              variant="outlined"
              flat
              hide-details
              single-line
              :prepend-inner-icon="mdiMagnify"
              @click:prepend-inner="navigateToSearch"
              v-model="word"
            ></v-text-field>
          </v-responsive>

          <v-spacer></v-spacer>
          <v-btn v-for="link in links" :key="link.menu" :to="link.link" text variant="text">
            {{ link.menu }}
          </v-btn>
          <v-btn v-if="!isAuthenticated" to="/login">로그인</v-btn>
          <v-btn v-if="isAuthenticated" @click="logout()">로그아웃</v-btn>
          <v-btn to="/mypage">
            <v-avatar class="me-4" color="grey-darken-1" size="32">
              <v-img
                v-if="isAuthenticated"
                src="https://i.namu.wiki/i/rnfTnl-VuplqgYpoa6nWkk8pUd0jY_q0u4x7QG0Owh-kSVkNC__pYGydF4OXiQ3g4DHb6Lg815rdnKqHx40J391VDS2f7R8yuc906c7tXUtKhEKpSsab9ppKnFIPfUFBcN-F0XcI-FP2AtSkYtjwrg.webp"
              >
              </v-img>
            </v-avatar>
          </v-btn>
        </v-container>
      </v-app-bar>
      <v-main class="bg-white">
        <router-view />
      </v-main>
      <v-footer class="text-center d-flex flex-column border-t-sm" style="color: #666d75">
        <div>
          <v-btn :icon="mdiFacebook" class="mx-4" variant="text"></v-btn>
          <v-btn :icon="mdiTwitter" class="mx-4" variant="text"></v-btn>
          <v-btn :icon="mdiLinkedin" class="mx-4" variant="text"></v-btn>
          <v-btn :icon="mdiInstagram" class="mx-4" variant="text"></v-btn>
        </div>

        <div class="pt-0">
          <span class="text-caption"
            >마이트립과 함께 국내 여행을 계획해보세요. 앞으로 다양한 기능들을 추가할 예정이니</span
          >
          <br />
          <span class="text-caption">많은 관심 부탁드립니다! 감사합니다.</span>
        </div>
        <v-divider></v-divider>
        <div class="text-caption">{{ new Date().getFullYear() }} — <strong>MyTrip</strong></div>
      </v-footer>
    </v-app>
  </div>
</template>

<style scoped>
.v-btn {
  background-color: transparent !important;
  box-shadow: none !important;
  transition: background-color 0.3s ease !important;
}
@media (min-width: 1920px) {
  .box {
    margin-left: 359px !important;
    margin-right: 359px !important;
  }
}
</style>
