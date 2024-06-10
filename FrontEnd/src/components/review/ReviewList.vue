<script setup>
import { ref, onMounted, watch } from "vue";
import { ratingAttraction } from "@/api/att.js";
import ReviewListItem from "@/components/review/ReviewListItem.vue";
import {
  mdiMapMarkerOutline,
  mdiMapMarker,
  mdiPhone,
  mdiStarOutline,
  mdiStar,
  mdiStarHalfFull,
  mdiShapePlus,
  mdiPencil,
  mdiHeartOutline,
  mdiArrowUp,
  mdiArrowDown,
} from "@mdi/js";
const props = defineProps({ contentId: String });

const ratings = ref([]);
const ratingCnt = ref({
  rateCnt: 0,
});
const ratingArray = ref([]);
watch(
  () => ({
    rate1: ratingCnt.value.rate1,
    rate2: ratingCnt.value.rate2,
    rate3: ratingCnt.value.rate3,
    rate4: ratingCnt.value.rate4,
    rate5: ratingCnt.value.rate5,
  }),
  (newVal) => {
    ratingArray.value = [
      newVal.rate1,
      newVal.rate2,
      newVal.rate3,
      newVal.rate4,
      newVal.rate5,
    ];
  },
  { deep: true }
);

const filter = ref({
  order: "A.update_date desc",
  rate: 0,
});

const filter_order = ref([
  { title: "작성일자(최신순)", order: "A.update_date desc" },
  { title: "작성일자(오래된 순)", order: "A.update_date asc" },
  { title: "방문일자(최신순)", order: "A.visit_date desc" },
  { title: "방문일자(오래된 순)", order: "A.visit_date asc" },
  { title: "좋아요(높은 순)", order: "islike desc" },
  { title: "평점(높은 순)", order: "A.rating desc" },
  { title: "평점(낮은 순)", order: "A.rating asc" },
]);

const filter_sort = ref([
  { value: 1 },
  { value: 2 },
  { value: 3 },
  { value: 4 },
  { value: 5 },
]);

const getReviews = async () => {
  const data = await ratingAttraction(props.contentId, filter.value);
  ratings.value = data.ratings;
  ratingCnt.value = data.ratingCnt;
};

onMounted(() => {
  getReviews();
});

const sortingReviewByFilter = async (value) => {
  filter.value.order = value;
  const data = await ratingAttraction(props.contentId, filter.value);
  ratings.value = data.ratings;
};
const selectedChip = ref(null);
const chips = ref([
  { text: "작성일자 ↓", value: "A.update_date desc" },
  { text: "작성일자 ↑", value: "A.update_date asc" },
  { text: "방문일자 ↓", value: "A.visit_date desc" },
  { text: "방문일자 ↑", value: "A.visit_date asc" },
  { text: "좋아요 순", value: "islike desc" },
  { text: "평점↑", value: "A.rating desc" },
  { text: "평점↓", value: "A.rating asc" },
]);
const onChipClick = (value) => {
  selectedChip.value = value;
};
watch(selectedChip, (newValue) => {
  if (newValue) {
    sortingReviewByFilter(newValue);
  }
});
</script>

<template>
  <!-- 레이팅 -->
  <v-container style="padding: 0px 0px; margin-top: 50px">
    <v-card class="elevation-3">
      <v-row>
        <v-col class="" style="margin-top: 64px" cols="12" md="5">
          <v-card
            class="d-flex flex-column mx-auto py-8 elevation-3"
            height="500"
            width="350"
          >
            <div class="d-flex justify-center mt-auto text-h5">사용자 평점</div>

            <div class="d-flex align-center flex-column my-auto">
              <div class="text-h2 mt-5">
                {{ ratingCnt.avgRate }}
                <span class="text-h6 ml-n3">/ 5</span>
              </div>

              <v-rating
                :model-value="ratingCnt.avgRate"
                color="#51ABF3"
                half-increments
                readonly
              ></v-rating>
              <div class="px-3">{{ ratingCnt.rateCnt }}건의 후기</div>
            </div>

            <v-list
              bg-color="transparent"
              class="d-flex flex-column-reverse"
              density="compact"
            >
              <v-list-item v-for="(rating, i) in 5" :key="i">
                <v-progress-linear
                  :model-value="ratingArray[i] * 5"
                  class="mx-n5"
                  color="#51ABF3"
                  height="20"
                ></v-progress-linear>

                <template v-slot:prepend>
                  <span>{{ rating }}</span>
                  <v-icon class="mx-3" :icon="mdiStar"></v-icon>
                </template>

                <template v-slot:append>
                  <div class="rating-values">
                    <span class="d-flex justify-end">
                      {{ ratingArray[i] }}
                    </span>
                  </div>
                </template>
              </v-list-item>
            </v-list>
          </v-card>
        </v-col>
        <v-col>
          <v-card-text class="mt-10">
            <p class="font-weight-bold text-h6 mb-4">리뷰 정렬하기</p>
            <v-chip-group
              v-model:selected="selectedChip"
              active-class="primary--text"
            >
              <v-chip
                v-for="chip in chips"
                :text="chip.text"
                :key="chip.value"
                :value="chip.value"
                @click="onChipClick(chip.value)"
              >
                {{ chip.text }}
              </v-chip>
            </v-chip-group>
          </v-card-text>

          <p v-if="ratingCnt.rateCnt <= 0">작성된 리뷰가 없습니다.</p>
          <template v-else>
            <review-list-item
              v-for="rating in ratings"
              :key="rating.ratingId"
              :review="rating"
            />
          </template>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<style scoped></style>
