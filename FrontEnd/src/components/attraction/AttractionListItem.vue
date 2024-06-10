<script setup>
import { defineProps, onMounted, watch } from "vue";
import {
  mdiHeart,
  mdiStarOutline,
  mdiStar,
  mdiStarHalfFull,
  mdiHeartOutline,
} from "@mdi/js";
import { useRoute, useRouter } from "vue-router";

const props = defineProps({
  attraction: {
    type: Object,
    required: true,
    contentId: {
      type: Number,
    },
    title: {
      type: String,
    },
    description: {
      type: String,
    },
    contentTypeId: {
      type: Number,
    },
    firstImage: {
      type: String,
    },
    addr1: {
      type: String,
    },
    favorite: {
      type: String,
    },
    rating: {
      type: String,
    },
    islike: {
      type: Number,
    },
  },
});

const router = useRouter();

const goAttractionDetail = () => {
  router.push({
    name: "attractionDetail",
    params: { id: props.attraction.contentId },
    replace: true,
  });
};
</script>

<template>
  <div
    class="hover cursor-pointer text-center"
    style="text-align: center"
    @click="goAttractionDetail"
  >
    <v-img
      v-if="attraction.firstImage.length > 0"
      height="270"
      width="270"
      :src="attraction.firstImage"
      cover
      class="rounded-lg"
    >
      <v-btn
        icon
        class="position-absolute top-0 right-0 bg-white mr-2 mt-2 opacity-100"
        width="36"
        height="36"
      >
        <v-icon
          v-if="attraction.isLike == 0"
          color="#ff4081"
          :icon="mdiHeartOutline"
        ></v-icon>
        <v-icon v-else color="#ff4081" :icon="mdiHeart"></v-icon>
      </v-btn>
    </v-img>
    <v-img
      v-if="attraction.firstImage.length === 0"
      height="270"
      width="270"
      src="https://japan.ybmclass.com/common_css/img/noimage.gif"
      :aspect-ratio="1"
      cover
      class="rounded-lg"
    >
      <v-btn
        icon
        class="position-absolute top-0 right-0 bg-white mr-2 mt-2 opacity-100"
        width="36"
        height="36"
      >
        <v-icon color="#ff4081" :icon="mdiHeartOutline"></v-icon>
      </v-btn>
    </v-img>
    <div class="flex-column ml-1">
      <p style="font-size: 18px; font-weight: bold" class="title ml-1 mt-1">
        {{ attraction.title }}
      </p>
      <div class="d-flex align-center justify-center" style="height: 17px">
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
        <span class="ml-2" style="font-size: 14px; font-weight: bold"
          >{{ attraction.rating.toFixed(1) }} / 5</span
        >
      </div>
      <div class="ml-1 mt-1" style="font-size: 14px">
        <span style="font-size: 14px; font-weight: bold">
          {{ attraction.ratingCount }} </span
        >개의 이용 후기
      </div>
      <span class="ml-1 title line-clamp" style="font-size: 14px">{{
        attraction.addr1
      }}</span>
    </div>
  </div>
</template>

<style scoped>
.title {
  line-height: 1.4em;
  height: 2.8em;
}

.line-clamp {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: normal;
  word-break: break-all;
  display: block;
  display: -moz-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  flex-direction: column;
  max-height: 3em; /* 2줄 높이 (라인 높이 * 2) */
}
.hover:hover {
  opacity: 0.8;
  transition: transform 0.3s, opacity 0.5s;
}
</style>
