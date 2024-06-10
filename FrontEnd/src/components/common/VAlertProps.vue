<script setup>
import { defineProps, onMounted, ref } from "vue";
const props = defineProps({
  alertType: {
    type: String,
    required: true,
  },
  title: {
    type: String,
    required: true,
  },
  text: {
    type: String,
    required: true,
  },
});
const icon = ref("");
</script>

<template>
  <div class="fix">
    <v-alert
      :text="props.text"
      :title="props.title"
      :type="props.alertType"
      :icon="`$${props.alertType}`"
      closable
    >
    </v-alert>
  </div>
</template>

<style scoped>
.fix {
  position: fixed; /* 모달을 화면에 고정 */
  top: 13%; /* 상단에서부터 20%의 위치에 배치 */
  left: 50%; /* 왼쪽에서부터 50%의 위치에 배치 */
  transform: translate(
    -50%,
    -20%
  ); /* 자신의 크기의 반만큼 왼쪽과 위로 이동하여 가운데 정렬 */
  z-index: 1000; /* 다른 요소들 위에 모달이 위치하도록 z-index 설정 */
  width: 30%;
}
</style>
