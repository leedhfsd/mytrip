<script setup>
import { ref, onMounted, watch } from "vue";
import {
  detailAttraction,
  likeAttraction,
  dislikeAttraction,
} from "@/api/att.js";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { useRoute, useRouter } from "vue-router";
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
  mdiHeart,
  mdiCommentTextMultipleOutline,
  mdiInformationOutline,
} from "@mdi/js";
import { KakaoMap, KakaoMapMarker } from "vue3-kakao-maps";
import ReviewList from "@/components/review/ReviewList.vue";
import AttractionCarousel from "@/components/attraction/AttractionCarousel.vue";
import VAlertProps from "@/components/common/VAlertProps.vue";
import { attractionCode } from "@/api/att.js";

const route = useRoute();
const router = useRouter();

const store = useMemberStore();
const { userId } = storeToRefs(store);

const loginId = ref({ userId: null });

//const { id } = route.params;
const id = ref(route.params.id);
const attraction = ref({
  latitude: 37.566826,
  longitude: 126.9786567,
  firstImage: "",
});
function getSplitValue(str) {
  const splitValues = str.split(" ");
  return splitValues.length > 1 ? splitValues[1] : splitValues[0];
}
const isLoading = ref(true);
const word = ref("");
const successRef = ref(false);
const errorRef = ref(false);
const dislikeRef = ref(false);

watch(
  () => route.params,
  (newId, oldId) => {
    if (newId !== oldId) {
      id.value = newId.id;
      getDetail();
      window.scrollTo(0, 0);
    }
  }
);

const updateRefValue = (ref) => {
  setTimeout(() => {
    ref.value = false;
  }, 800);
};

const getDetail = async () => {
  console.log(id);
  isLoading.value = true;
  loginId.value.userId = store.userId;
  const data = await detailAttraction(id.value, loginId.value);
  word.value = getSplitValue(data.addr1);
  attraction.value = data;
  isLoading.value = false;
};

const pressLike = async (item) => {
  if (loginId.value.userId == null) {
    console.log("not login");
    errorRef.value = true;
    updateRefValue(errorRef);
    return;
  }
  if (item == 0) {
    await likeAttraction(id.value, loginId.value);
    successRef.value = true;
    updateRefValue(successRef);
  } else {
    await dislikeAttraction(id.value, loginId.value.userId);
    dislikeRef.value = true;
    updateRefValue(dislikeRef);
  }
  getDetail();
};

const goReviewForm = () => {
  router.push({
    name: "reviewRating",
    params: {
      id: route.params.id,
    },
  });
};
const scrollToReview = () => {
  const element = document.getElementById("review");
  if (element) {
    element.scrollIntoView({ behavior: "smooth" });
  }
};

onMounted(() => {
  getDetail();
});
</script>
<template>
  <!-- 카드 컴포넌트 -->
  <v-container class="py-16">
    <div class="bg-white elevation-2 pa-2">
      <div class="d-flex justify-space-between ml-1 align-center">
        <h1>{{ attraction.title }}</h1>
        <div>
          <span>
            <v-btn color="" class="border-e-sm" @click="scrollToReview">
              <v-icon :icon="mdiPencil"></v-icon>
              리뷰
            </v-btn>
            <v-btn color="#ff4081" @click="pressLike(attraction.isLike)">
              <v-icon
                v-if="attraction.isLike == 0"
                :icon="mdiHeartOutline"
              ></v-icon>
              <v-icon v-else :icon="mdiHeart"></v-icon>
              좋아요
            </v-btn>
          </span>
        </div>
      </div>

      <div class="d-flex align-center my-4 ml-1" style="height: 17px">
        <v-rating
          v-model="attraction.rating"
          :empty-icon="mdiStarOutline"
          :full-icon="mdiStar"
          :half-icon="mdiStarHalfFull"
          color="#DADCE0"
          active-color="#51ABF3"
          half-increments
          size="small"
          density="compact"
          readonly
        >
        </v-rating>
        <span class="ml-2 font-weight-bold" style="font-size: 14px"
          >{{ attraction.ratingCount }}건의 리뷰</span
        >
      </div>
      <div class="d-flex my-4 d-flex align-center" style="height: 17px">
        <div class="border-e-sm pr-2">
          <v-icon :icon="mdiMapMarker"></v-icon>
          <span class="ml-2">{{ attraction.addr1 }}</span>
        </div>
        <div class="d-flex px-2 border-e-sm" v-if="attraction.tel !== ''">
          <v-icon :icon="mdiPhone"></v-icon>
          <span class="ml-2">{{ attraction.tel }}</span>
        </div>
        <div class="d-flex px-2">
          <v-icon :icon="mdiShapePlus"></v-icon>
          <span class="ml-2">{{
            attractionCode[attraction.contentTypeId]
          }}</span>
        </div>
      </div>
      <!-- 경고 띄우기 -->
      <v-alert-props
        v-if="successRef"
        title="성공"
        text="정상적으로 등록되었습니다."
        alertType="success"
      />
      <v-alert-props
        v-if="errorRef"
        title="실패"
        text="등록에 실패하였습니다. 로그인을 해주세요"
        alertType="error"
      />
      <v-alert-props
        v-if="dislikeRef"
        title="취소"
        text="좋아요를 취소하였습니다."
        alertType="info"
      />
    </div>

    <!-- 사진 컴포넌트-->
    <div class="my-16">
      <v-container style="padding: 0px 0px">
        <v-card rounded>
          <v-row>
            <v-col col="12" md="6">
              <v-img
                :src="attraction.firstImage"
                object-fit="cover"
                :aspect-ratio="1"
              />
            </v-col>
            <v-col col="12" md="6">
              <KakaoMap
                :lat="attraction.latitude"
                :lng="attraction.longitude"
                :draggable="true"
                style="margin-top: 16px"
              >
                <KakaoMapMarker
                  :lat="attraction.latitude"
                  :lng="attraction.longitude"
                >
                  <template v-slot:infoWindow>
                    <div style="padding: 10px">
                      {{ attraction.title }}
                      <v-img
                        height="30"
                        width="30"
                        :src="attraction.firstImage"
                        cover
                        class="rounded-lg"
                      >
                      </v-img>
                    </div>
                  </template>
                </KakaoMapMarker>
              </KakaoMap>
            </v-col>
          </v-row>
        </v-card>
      </v-container>
    </div>

    <v-container style="padding: 0px 0px; margin-top: 100px">
      <v-row style="height: 500px">
        <v-col cols="12" md="4" class="full">
          <v-card class="rounded-md full elevation-3">
            <div class="card-top">
              <div class="d-flex align-center">
                <v-icon :icon="mdiCommentTextMultipleOutline"></v-icon>
                <v-card-title class="font-weight-bold"
                  >평가 및 리뷰</v-card-title
                >
              </div>
              <div class="d-flex align-center my-2" style="height: 17px">
                <v-rating
                  v-model="attraction.rating"
                  :empty-icon="mdiStarOutline"
                  :full-icon="mdiStar"
                  :half-icon="mdiStarHalfFull"
                  color="#DADCE0"
                  active-color="#51ABF3"
                  half-increments
                  size="small"
                  density="compact"
                  readonly
                >
                </v-rating>
                <span class="ml-2 font-weight-bold" style="font-size: 14px"
                  >{{ attraction.ratingCount }}건의 리뷰</span
                >
              </div>
            </div>
            <v-divider></v-divider>
            <div class="d-flex justify-center align-center" style="height: 65%">
              <v-btn
                size="large"
                rounded="lg"
                color="primary"
                variant="elevated"
                @click="goReviewForm"
                >리뷰 쓰러가기</v-btn
              >
            </div>
          </v-card>
        </v-col>
        <v-col cols="12" md="4" class="full">
          <v-card class="rounded-md full elevation-3">
            <div class="card-top">
              <div class="d-flex align-center">
                <v-icon :icon="mdiInformationOutline"></v-icon>
                <v-card-title class="font-weight-bold">상세 정보</v-card-title>
              </div>
            </div>
            <v-divider style="padding: "></v-divider>
            <v-card-text class="pa-4">
              {{ attraction.overview }}
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="12" md="4">
          <v-card class="rounded-md full elevation-3">
            <div class="card-top">
              <div class="d-flex align-center">
                <v-icon :icon="mdiMapMarkerOutline"></v-icon>
                <v-card-title class="font-weight-bold">위치 정보</v-card-title>
              </div>
              <div class="text-caption">{{ attraction.addr1 }}</div>
            </div>
            <v-divider></v-divider>
            <!-- 맵 부분 -->
            <div class="d-flex justify-center align-center">
              <KakaoMap
                :lat="attraction.latitude"
                :lng="attraction.longitude"
                :draggable="true"
                height="200"
                width="300"
                style="margin-top: 16px"
              >
                <KakaoMapMarker
                  :lat="attraction.latitude"
                  :lng="attraction.longitude"
                >
                  <template v-slot:infoWindow>
                    <div style="padding: 10px">
                      {{ attraction.title }}
                      <v-img
                        height="30"
                        width="30"
                        :src="attraction.firstImage"
                        cover
                        class="rounded-lg"
                      >
                      </v-img>
                    </div>
                  </template>
                </KakaoMapMarker>
              </KakaoMap>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <div v-if="!isLoading" class="mt-16">
      <attraction-carousel
        title="보고 계신 장소의 다른 주변 관광지"
        contentTypeId="12"
        :word="word"
      />
    </div>
    <!-- 리뷰리스트 -->
    <div id="review">
      <review-list :contentId="id" />
    </div>
  </v-container>
</template>

<style scoped>
.full {
  width: 100%;
  height: 100%;
}
.card-top {
  height: 130px;
  padding: 25px 40px;
}
</style>
