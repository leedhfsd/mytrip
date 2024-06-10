<script setup>
import { ref, onMounted, watch, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { listAttraction } from "@/api/att.js";
import SearchListItem from "@/components/attraction/SearchListItem.vue";
import VPageNavigation from "@/components/common/VPageNavigation.vue";
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";
import { mdiMagnify } from "@mdi/js";

const route = useRoute();
const word = ref("");
const tempWord = ref("");

watch(
  () => route.params.word,
  (newWord, oldWord) => {
    if (newWord !== oldWord) {
      word.value = newWord;
      changeWord();
    }
  }
);

const currentPage = ref(1);
const totalPage = ref(0);
const totalCnt = ref(0);

const attractions = ref([]);

const filter = computed(() => {
  return {
    word: word.value,
    pgno: currentPage.value,
    spp: 20,
  };
});

onMounted(() => {
  word.value = route.params.word;
  getSearchList();
});

const getSearchList = async () => {
  tempWord.value = filter.value.word;
  const data = await listAttraction(filter.value);
  attractions.value = data.attractions;
  currentPage.value = data.currentPage;
  totalPage.value = data.totalPageCount;
  totalCnt.value = data.totalAttractionCount;
};

const onPageChange = (val) => {
  currentPage.value = val;
  getSearchList();
};

const changeWord = () => {
  //console.log(word);
  currentPage.value = 1;
  //filter.value.pgno = 1;
  //filter.value.word = word.value;
  getSearchList();
};
</script>

<template>
  <v-container style="min-height: 100vh">
    <div class="centered-content my-12">
      <div style="min-height: 300px">
        <div class="d-flex align-center">
          <p
            v-if="attractions.length == 0"
            class="text-h4 font-weight-bold my-4 mx-auto"
          >
            국내 여행지는 어떠세요?
          </p>
          <p v-else class="text-h4 font-weight-bold my-4 mx-auto">
            '{{ tempWord }}'으로 검색한 결과입니다.
          </p>
          <span
            v-if="totalCnt > 0"
            class="text-caption mt-4 ml-4 font-weight-bold"
            style="text-decoration: underline"
            >{{ totalCnt }}건</span
          >
        </div>
        <v-text-field
          density="compact"
          label="여행지 검색.."
          rounded="lg"
          variant="outlined"
          width="600"
          flat
          single-line
          :prepend-inner-icon="mdiMagnify"
          v-model="word"
          @click:prepend-inner="changeWord()"
        >
        </v-text-field>
        <v-btn
          class="mx-4"
          color="primary"
          variant="tonal"
          rounded
          @click="changeWord()"
          >검색</v-btn
        >
      </div>
      <template v-if="totalCnt > 0">
        <v-row>
          <v-col
            v-for="attraction in attractions"
            :key="attraction.contentId"
            cols="3"
          >
            <attraction-list-item :attraction="attraction" />
          </v-col>
        </v-row>
      </template>
    </div>
    <VPageNavigation
      :currentPage="currentPage"
      :totalPage="totalPage"
      @pageChange="onPageChange"
    ></VPageNavigation>
  </v-container>
</template>

<style scoped>
.centered-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}
</style>
