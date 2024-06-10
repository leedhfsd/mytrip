<script setup>
import { ref, onMounted } from "vue";
import { mdiMagnify } from "@mdi/js";
import { useRouter } from "vue-router";
import { useAttractionStore } from "@/stores/attraction";
import { storeToRefs } from "pinia";

const word = ref("");
const router = useRouter();
const store = useAttractionStore();
const { foodAttractions, hotelAttractions, shoppingAttractions } =
  storeToRefs(store);
const isAttractionListExist = ref(false);
const attractionList = ref([]);
const tab = ref(null);
onMounted(() => {
  if (
    foodAttractions.value.length > 0 ||
    hotelAttractions.value.length > 0 ||
    shoppingAttractions.value.length > 0
  ) {
    isAttractionListExist.value = true;
    let count = 0;
    for (let i = 0; i < 12; i++) {
      if (foodAttractions.value[i] === undefined) break;
      if (foodAttractions.value[i].firstImage !== "") {
        attractionList.value.push(foodAttractions.value[i]);
        count += 1;
        if (count == 6) break;
      }
    }
    count = 0;
    for (let i = 0; i < 12; i++) {
      if (hotelAttractions.value[i] === undefined) break;
      if (hotelAttractions.value[i].firstImage !== "") {
        attractionList.value.push(hotelAttractions.value[i]);
        count += 1;
        if (count == 6) break;
      }
    }
    count = 0;
    for (let i = 0; i < 12; i++) {
      if (shoppingAttractions.value[i] === undefined) break;
      if (shoppingAttractions.value[i].firstImage !== "") {
        attractionList.value.push(shoppingAttractions.value[i]);
        count += 1;
        if (count == 6) break;
      }
    }
  }
});
const goSearchPage = () => {
  router.replace(`/search/${word.value}`);
};
const goAttractionDetail = (contentId) => {
  if (contentId === undefined) return;
  router.replace(`/attraction/${contentId}`);
};
</script>

<template>
  <v-container style="min-height: 100vh">
    <div class="centered-content my-4">
      <div style="min-height: 200px">
        <div class="flex-column align-center">
          <p class="text-h4 font-weight-bold my-8 mx-auto">
            다양한 여행지를 검색해보세요!
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
              @click:prepend-inner="goSearchPage"
              v-model="word"
            ></v-text-field>
            <v-btn
              class="mx-4"
              color="primary"
              variant="tonal"
              rounded
              @click="goSearchPage"
              >검색</v-btn
            >
          </div>
        </div>
      </div>
    </div>

    <!-- 분류 -->
    <v-card v-if="attractionList.length !== 0">
      <v-tabs v-model="tab" align-tabs="center" color="primary">
        <v-tab :value="1">명소</v-tab>
        <v-tab :value="2">음식점</v-tab>
        <v-tab :value="3">축제</v-tab>
      </v-tabs>

      <v-tabs-window v-model="tab">
        <v-tabs-window-item v-for="n in 3" :key="n" :value="n">
          <v-container fluid>
            <v-row>
              <v-col v-for="i in 6" :key="i" cols="12" md="4">
                <v-card
                  max-height="400"
                  class="cursor-pointer"
                  @click="
                    goAttractionDetail(
                      attractionList[(n - 1) * 6 + (i - 1)].contentId
                    )
                  "
                >
                  <v-img
                    :src="attractionList[(n - 1) * 6 + (i - 1)].firstImage"
                    cover
                    rounded
                    height="205"
                    class="opacity-80"
                  >
                    <v-card-text class="text-white sd" style="font-size: 18px">
                      {{ attractionList[(n - 1) * 6 + (i - 1)].title }}
                      <br />
                      {{ attractionList[(n - 1) * 6 + (i - 1)].addr1 }}
                    </v-card-text>
                  </v-img>
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-tabs-window-item>
      </v-tabs-window>
    </v-card>
  </v-container>
</template>

<style scoped>
.centered-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}
.sd {
  text-shadow: 3px 4px 7px rgba(0, 0, 0, 0.6);
  backdrop-filter: blur(1px);
  background-color: rgba(255, 255, 255, 0.05);
}
</style>
