<script setup>
import { ref, onMounted, watch, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { listAttraction, listLocations, listContent, planAttractionList } from "@/api/att.js";
import { confirmPlan, userPlan, updatePlan } from "@/api/plan.js";
import { KakaoMap, KakaoMapMarker } from "vue3-kakao-maps";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { mdiFlag } from "@mdi/js";
//import SearchListItem from "@/components/attraction/SearchListItem.vue";

const route = useRoute();
const router = useRouter();

const map = ref();
const onLoadKakaoMap = (mapRef) => {
  map.value = mapRef;
};

const { planId } = route.params;
const store = useMemberStore();
const { userId } = storeToRefs(store);

const fromDate = ref("");
const toDate = ref("");
const onDay = ref("");
const days = ref(0);
const planTitle = ref("");

const planAttraction = ref([]);
const planDetails = computed(() => {
  return {
    userId: store.userId,
    title: planTitle.value,
    fromDate: fromDate.value,
    toDate: toDate.value,
    planDetails: planAttraction.value.map((item, index) => ({
      contentId: item.contentId,
      planDay: 1,
      planIndex: index,
    })),
  };
});
watch(planAttraction.value, () => {
  //console.log("watch planAtt");
  if (map.value && planAttraction.value.length > 0) {
    console.log("map");
    tempAttraction.value.latitude = planAttraction.value[planAttraction.value.length - 1].latitude;
    tempAttraction.value.longitude =
      planAttraction.value[planAttraction.value.length - 1].longitude;
  }
});

const filterDays = computed(() => {
  return days.value > 0 ? days.value : 0;
});

const filterAttraction = ref({
  sido: null,
  gugun: null,
  contentTypeId: null,
});

const attractions = ref([]);
const totalCnt = ref(0);

const selectedChip = ref(null);
const chips = ref([]);

const locations = ref({
  sidoCode: 37,
  gugunCode: 12,
  siguName: "전주",
});
const selected = ref(null);
const selectedIndex = ref(null);
const tempAttraction = ref({
  latitude: 37.566826,
  longitude: 126.9786567,
  firstImage: "",
});

watch([fromDate, toDate], () => {
  console.log("get Day");
  getDays();
});

watch(selectedChip, () => {
  filterAttraction.value.sido = locations.value.sidoCode;
  filterAttraction.value.gugun = locations.value.gugunCode;
  filterAttraction.value.contentTypeId = selectedChip.value;
  console.log(filterAttraction.value);
  getAttractionList();
});

const dateToday = () => {
  const today = new Date();
  const year = today.getFullYear();
  const month = String(today.getMonth() + 1).padStart(2, "0");
  const day = String(today.getDate()).padStart(2, "0");
  return `${year}-${month}-${day}`;
};

const addPlan = (temp) => {
  planAttraction.value.push(temp);
  tempAttraction.value.latitude = planAttraction.value[planAttraction.value.length - 1].latitude;
  tempAttraction.value.longitude = planAttraction.value[planAttraction.value.length - 1].longitude;
  //console.log(planAttraction.value);
  //console.log(planDetails.value);
};

const subPlan = (contentId) => {
  const index = planAttraction.value.findIndex((item) => item.contentId === contentId);
  if (index !== -1) {
    planAttraction.value.splice(index, 1);
  }
};

const getAttractionList = async () => {
  //console.log(filterAttraction.value);
  const data = await listAttraction(filterAttraction.value);
  attractions.value = data.attractions;
  totalCnt.value = data.totalAttractionCount;
};

const getDays = () => {
  const d1 = new Date(fromDate.value);
  const d2 = new Date(toDate.value);
  days.value = Math.floor((d2 - d1) / (1000 * 60 * 60 * 24)) + 1;
  //console.log(filterDays);
  //console.log(days.value);
};

const planInsert = async () => {
  if (!planTitle.value) {
    console.log("title이 비었음");
    return;
  }
  if (planId === "new") {
    await confirmPlan(planDetails.value);
  } else {
    await updatePlan(planId, planDetails.value);
  }
  router.push({
    name: "planning",
  });
};

const getLocations = async () => {
  const data = await listLocations();
  locations.value = data;
  //console.log(locations.value);
};

const onChipClick = (value) => {
  selectedChip.value = value;
};

const getContentType = async () => {
  const data = await listContent();
  chips.value = data;
};

const updateSelected = (index) => {
  //console.log("change");
  selected.value = locations.value[index];
  //console.log(selected);
};

const getSavedAttraction = async () => {
  const data = await planAttractionList(planId);
  planAttraction.value = data;
  tempAttraction.value.latitude = planAttraction.value[planAttraction.value.length - 1].latitude;
  tempAttraction.value.longitude = planAttraction.value[planAttraction.value.length - 1].longitude;
  console.log(planAttraction.value);
};

const getUserPlan = async () => {
  const data = await userPlan(planId);
  planTitle.value = data.title;
  fromDate.value = data.fromDate;
  toDate.value = data.toDate;
};

onMounted(() => {
  onDay.value = dateToday();
  //getLocations();
  getContentType();
  if (planId === "new") {
    fromDate.value = onDay.value;
    toDate.value = onDay.value;
    getDays();
  } else {
    console.log(planId);
    getUserPlan();
    getSavedAttraction();
  }
});
</script>

<template>
  <div>
    <v-container class="mt-8">
      <v-row>
        <v-col col="12" md="4">
          <v-card class="pa-2 elevation-3 mr-4">
            <v-sheet rounded="lg">
              <v-list rounded="lg">
                <v-list-item color="grey-lighten-6">
                  <div class="text-h5 mt-4 mb-4 font-weight-bold">일정</div>
                  <v-text-field
                    label="제목"
                    v-model="planTitle"
                    placeholder="제목을 입력해주세요"
                    color="primary"
                    variant="underlined"
                  ></v-text-field>
                </v-list-item>
                <v-divider class="my-2"></v-divider>
                <v-list-item v-for="index in filterDays" :key="index">{{ index }}일차</v-list-item>
                <v-btn
                  color="primary"
                  size="large"
                  @click="planInsert()"
                  variant="elevated"
                  class="mt-8 px-8"
                  >저장하기</v-btn
                >
              </v-list>
            </v-sheet>
          </v-card>
        </v-col>

        <v-col col="12" md="8">
          <v-card class="px-4 elevation-3">
            <v-sheet rounded="lg">
              <div class="text-h5 mb-8 mt-8 font-weight-bold" v-if="planId === 'new'">
                계획 생성
              </div>
              <div v-else class="text-h5 mb-8 mt-8 font-weight-bold">계획 수정</div>
              <input type="date" id="fromDate" v-model="fromDate" :min="onDay" />
              -
              <input type="date" id="toDate" v-model="toDate" :min="fromDate" />
              <div v-if="filterDays > 0">
                <div class="text-h5 mt-12 mb-4 font-weight-bold">여행지</div>
                <v-text-field
                  label="전주 고정"
                  value="전주"
                  placeholder="제목을 입력해주세요"
                  color="primary"
                  variant="underlined"
                  class="mt-4"
                ></v-text-field>
                <v-chip-group v-model:selected="selectedChip" active-class="primary--text">
                  <v-chip
                    v-for="chip in chips"
                    :text="chip.contentName"
                    :key="chip.contentTypeId"
                    :value="chip.contentTypeId"
                    @click="onChipClick(chip.contentTypeId)"
                  >
                    {{ chip.contentName }}
                  </v-chip>
                </v-chip-group>
              </div>
              <div class="text-h5 my-12 font-weight-bold">여행지 리스트</div>
              <template v-if="totalCnt > 0">
                <v-list lines="two">
                  <v-list-item
                    v-for="attraction in attractions"
                    :key="attraction.contentId"
                    :title="attraction.title"
                    :subtitle="attraction.rating"
                    :prepend-avatar="attraction.firstImage"
                    @click="addPlan(attraction)"
                  >
                  </v-list-item>
                </v-list>
              </template>
            </v-sheet>
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <v-col col="12" md="9">
          <v-card class="px-2">
            <h1>여행 계획</h1>
            <v-timeline>
              <v-timeline-item
                v-for="pAttraction in planAttraction"
                :key="pAttraction.contentId"
                color="primary"
                :icon="mdiFlag"
                dot-color="primary"
              >
                <v-card
                  variant="outlined"
                  class="elevation-1"
                  max-width="350"
                  color="primary"
                  @click="subPlan(pAttraction.contentId)"
                >
                  <v-card-title>{{ pAttraction.title }}</v-card-title>
                  <v-card-subtitle class="text-caption">{{ pAttraction.addr1 }}</v-card-subtitle>
                  <v-card-text>평점: {{ pAttraction.rating }}점</v-card-text>
                </v-card>
              </v-timeline-item>
            </v-timeline>
          </v-card>
        </v-col>
        <v-col col="12" md="3">
          <div class="text-h4 font-weight-bold">지도</div>
          <KakaoMap
            :lat="tempAttraction.latitude"
            :lng="tempAttraction.longitude"
            :draggable="true"
            style="margin-top: 16px"
            @onLoadKakaoMap="onLoadKakaoMap"
            width="350px"
          >
            <KakaoMapMarker
              v-for="pAttraction in planAttraction"
              :key="pAttraction.contentId"
              :lat="pAttraction.latitude"
              :lng="pAttraction.longitude"
            >
            </KakaoMapMarker>
          </KakaoMap>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style scoped></style>
