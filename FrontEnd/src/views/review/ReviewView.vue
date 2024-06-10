<script setup>
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { listAttraction } from "@/api/att.js";
import { mdiConsoleNetworkOutline, mdiMagnify } from "@mdi/js";

const route = useRoute();
const tab = ref(null);
const word = ref("");
const isLoading = ref(true);

const attractions = ref([]);
const filter = ref({
  word: "",
  pgno: 1,
  spp: 12,
  contentTypeId: null,
});

// const getAttractions = async () => {
//   filter.value.word = word.value;
//   const data = await listAttraction(filter.value);
//   //console.log("getAttractions");
//   //console.log(filter.value);
// };

const getAttractions = async () => {
  isLoading.value = true;
  filter.value.word = word.value;
  const data = await listAttraction(filter.value);

  isLoading.value = false;
};

onMounted(() => {
  if (route.params.word != null) {
    word.value = route.params.word;
    filter.value.word = word.value;
    getAttractions();
  }
  isLoading.value = false;
  //console.log(word.value);
  //console.log(attractions.value.length);
});
</script>

<template>
  <v-container>
    <div class="centered-content my-12" style="min-height: 100vh">
      <p v-if="attractions.length == 0" class="text-h4 font-weight-bold my-4">
        다른 여행지는 어떠세요?
      </p>
      <p v-else class="text-h4 font-weight-bold my-4">
        '{{ word }}'로 검색한 결과입니다.
      </p>
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
        @click:prepend-inner="getAttractions()"
      >
      </v-text-field>
    </div>
    <v-card v-if="attractions.length == 0">
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
                <v-img
                  :lazy-src="`https://picsum.photos/10/6?image=${
                    i * n * 5 + 10
                  }`"
                  :src="`https://picsum.photos/500/300?image=${i * n * 5 + 10}`"
                  height="205"
                  cover
                ></v-img>
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
}
</style>
