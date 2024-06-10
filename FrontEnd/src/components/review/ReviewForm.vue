<script setup>
import { attractionCode, detailAttraction } from "@/api/att";
import { defineProps, ref, onMounted } from "vue";
import { mdiCamera } from "@mdi/js";
import { postRating } from "@/api/att";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import router from "@/router";
const store = useMemberStore();
const { userId } = storeToRefs(store);
const props = defineProps({
  id: {
    type: String,
    required: true,
  },
});

const attraction = ref({
  contentId: "",
  title: "",
  addr1: "",
  firstImage: "",
});
const getDetail = async () => {
  const data = await detailAttraction(props.id);
  attraction.value = data;
};
onMounted(() => {
  getDetail();
});
const formData = ref({
  contentId: props.id,
  userId: userId.value,
  rating: -1,
  title: "",
  description: "",
  visitDate: "",
  files: null,
});

const handleSubmit = async () => {
  if (formData.value.title.length === 0) return;
  if (formData.value.description.length === 0) return;
  if (formData.value.rating === -1) return;
  if (formData.value.visitDate.length === 0) return;
  const res = await postRating(formData.value);
  if (res.status === 201) {
    router.replace(`/attraction/${props.id}`);
  }
};
</script>
<template>
  <v-container class="pa-4">
    <v-row>
      <v-col cols="12" md="4">
        <v-card>
          <v-card-text class="mt-4" style="font-size: 24px; font-weight: bold">
            방문하신 시설의 후기를 남겨주세요.
          </v-card-text>
          <v-card-subtitle
            >{{ attraction.title }} ·
            {{ attractionCode[attraction.contentTypeId] }}</v-card-subtitle
          >
          <v-card-text>{{ attraction.addr1 }}</v-card-text>
          <v-img :src="attraction.firstImage" aspect-ratio="1" cover></v-img>
        </v-card>
      </v-col>

      <v-col cols="12" md="8">
        <v-card class="px-16 py-16">
          <div class="my-4 font-weight-bold text-h4">평점</div>
          <v-rating
            background-color="grey lighten-3"
            color="#DADCE0"
            active-color="#51ABF3"
            v-model="formData.rating"
          ></v-rating>
          <div class="mt-4 mb-8 font-weight-bold text-h4">방문 날짜</div>
          <v-text-field
            type="date"
            variant="outlined"
            v-model="formData.visitDate"
          ></v-text-field>

          <div class="mt-8 mb-4 font-weight-bold text-h4">제목</div>
          <v-textarea
            label="제목"
            row-height="25"
            rows="1"
            variant="outlined"
            color="primary"
            v-model="formData.title"
            auto-grow
            shaped
          ></v-textarea>

          <div class="my-4 font-weight-bold text-h4">상세설명</div>
          <v-textarea
            label="상세설명"
            row-height="25"
            rows="3"
            color="primary"
            variant="outlined"
            v-model="formData.description"
            auto-grow
            shaped
          ></v-textarea>
          <div class="my-4 font-weight-bold text-h4">사진 추가하기</div>
          <v-file-input
            label="사진 추가하기"
            placeholder="선택 사항"
            :prepend-icon="mdiCamera"
            outlined
          ></v-file-input>
          <v-btn
            class="mt-4"
            color="primary"
            variant="elevated"
            type="submit"
            @click="handleSubmit"
            >리뷰 제출</v-btn
          >
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style>
.v-card {
  margin-bottom: 20px;
}
</style>
