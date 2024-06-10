<script setup>
import { ref } from "vue";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

const store = useMemberStore();

const { userId } = storeToRefs(store);

const items = ref([
  {
    title: "최근 활동내역",
    link: "recent",
  },
  {
    title: "나의 관광지",
    link: "favorite",
  },
  {
    title: "나의 리뷰",
    link: "review",
  },
]);
</script>
<template>
  <div>
    <v-container>
      <v-row>
        <v-col cols="2">
          <v-sheet rounded="lg">
            <v-list rounded="lg">
              <v-list-item
                color="grey-lighten-4"
                :title="`${store.userId}님의 페이지`"
              ></v-list-item>
              <v-divider class="my-2"></v-divider>
              <v-list-item
                v-for="item in items"
                :key="item.link"
                :title="`${item.title}`"
                link
                :to="item.link"
              ></v-list-item>
            </v-list>
          </v-sheet>
        </v-col>

        <router-view></router-view>
      </v-row>
    </v-container>
  </div>
</template>

<style scoped></style>
