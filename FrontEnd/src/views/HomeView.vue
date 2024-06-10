<script setup>
import { ref } from "vue";
import { mdiMagnify } from "@mdi/js";
import { useAttractionStore } from "@/stores/attraction";
import router from "@/router";
const word = ref("");
const navigateToSearch = () => {
  if (word.value.length == 0) return;
  router.push(`/search/${word.value}`);
  word.value = "";
};
const model = ref(null);
const location = ref([
  "빈칸",
  "서울",
  "부산",
  "제주도",
  "강원",
  "경주",
  "전주",
  "인천",
]);
const handleCardClick = (location) => {
  router.push(`/search/${location}`);
};

// 홈에서 attraction 미리 받아 놓기
const store = useAttractionStore();
</script>

<template>
  <v-container>
    <p class="text-h2 text-center my-16" style="font-weight: bold">
      마이트립과 함께하는 국내 여행
    </p>
    <div class="d-flex justify-center">
      <v-text-field
        max-width="700"
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
      <v-btn
        class="mx-4"
        color="primary"
        variant="tonal"
        rounded
        @click="navigateToSearch"
        >검색</v-btn
      >
    </div>

    <div style="margin-top: 150px; margin-bottom: 100px">
      <v-card height="400">
        <v-img
          :src="`/src/assets/image/seoul.jpg`"
          cover
          rounded
          class="opacity-70"
        >
          <v-card-text
            class="text-h3 text-white ml-12 sd"
            style="margin-top: 100px"
          >
            사용자의 후기로 만들어가는 마이트립
            <br />
            <v-btn
              size="large"
              color="primary"
              variant="elevated"
              rounded
              to="/join"
              >회원가입</v-btn
            >
          </v-card-text>
        </v-img>
      </v-card>
    </div>

    <!-- 시트 -->
    <div style="margin: 100px 0px">
      <p class="text-h5 my-4" style="font-weight: bold">인기 여행지 둘러보기</p>
      <v-sheet class="mx-auto" style="background-color: transparent">
        <v-slide-group v-model="model" class="pa-4" show-arrows>
          <v-slide-group-item v-for="i in 7" :key="i">
            <v-card
              class="ma-4 rounded-lg bright"
              color="grey-lighten-1"
              height="230"
              width="175"
            >
              <div class="cursor-pointer" @click="handleCardClick(location[i])">
                <v-img
                  :src="`/src/assets/image/${location[i]}.jpg`"
                  height="230"
                  width="175"
                  cover
                  class="opacity-80"
                >
                  <span
                    class="d-flex fill-height align-end justify-start text-white pa-2 opacity-100 font-weight-bold sd"
                  >
                    {{ location[i] }}</span
                  ></v-img
                >
              </div>
            </v-card>
          </v-slide-group-item>
        </v-slide-group>
      </v-sheet>
    </div>

    <!-- 카드 -->
    <div style="margin-top: 150px; margin-bottom: 100px">
      <v-container style="padding: 0px 0px">
        <v-row>
          <v-col col="12" md="4">
            <v-card max-height="400" class="rounded-xl">
              <v-img
                :src="`/src/assets/image/card1.jpg`"
                cover
                rounded
                class="opacity-80"
              >
                <v-card-text
                  class="text-h4 text-white sd"
                  style="margin-top: 100px"
                >
                  요즘 뜨는 관광지
                  <br />
                  <v-btn
                    color="primary"
                    variant="elevated"
                    rounded
                    href="https://www.instagram.com/kto9suk9suk/"
                    target="_blank"
                    rel="noopener noreferrer"
                    class="mt-4"
                    >이동하기</v-btn
                  >
                </v-card-text>
              </v-img>
            </v-card>
          </v-col>
          <v-col col="12" md="4">
            <v-card height="400" class="rounded-xl">
              <v-img
                :src="`/src/assets/image/card2.jpg`"
                cover
                rounded
                class="opacity-80"
              >
                <v-card-text
                  class="text-h4 text-white sd"
                  style="margin-top: 100px"
                >
                  내게 필요한 여행정보
                  <br />
                  <v-btn
                    color="primary"
                    variant="elevated"
                    rounded
                    href="https://korean.visitkorea.or.kr/main/main.do"
                    target="_blank"
                    rel="noopener noreferrer"
                    class="mt-4"
                    >이동하기</v-btn
                  >
                </v-card-text>
              </v-img>
            </v-card>
          </v-col>
          <v-col col="12" md="4">
            <v-card height="400" class="rounded-xl">
              <v-img
                :src="`/src/assets/image/card3.jpg`"
                cover
                rounded
                class="opacity-80"
              >
                <v-card-text
                  class="text-h4 text-white sd"
                  style="margin-top: 100px"
                >
                  여행 관련 뉴스
                  <br />
                  <v-btn
                    color="primary"
                    variant="elevated"
                    rounded
                    href="https://www.korea.kr/totalSearch.do?pWise=main&pWiseMain=S1&where=ALL&multiFlag=Y&dateRange=all&range=ALL&multiFlag=Y&topSearchKeyword=%EA%B4%80%EA%B4%91"
                    target="_blank"
                    rel="noopener noreferrer"
                    class="mt-4"
                    >이동하기</v-btn
                  >
                </v-card-text>
              </v-img>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </v-container>
</template>

<style scoped>
.sd {
  /* text-shadow: 3px 4px 7px rgba(81, 67, 21, 0.8); */
  text-shadow: 3px 4px 7px rgba(0, 0, 0, 0.6);
}
.bright {
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  filter: brightness(80%);
}

.bright:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  background-color: #f5f5f5; /* 배경색을 약간 밝게 변경 */
  opacity: 100%;
  filter: brightness(100%);
}
</style>
