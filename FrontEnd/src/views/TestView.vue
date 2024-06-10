<script setup>
import AttractionCarousel from "@/components/attraction/AttractionCarousel.vue";
import VAlertProps from "@/components/common/VAlertProps.vue";
</script>

<template>
  <div>
    <!-- <attraction-carousel title="보고 계신 장소의 다른 주변 관광지" contentTypeId="12" word="강남구" /> -->
    <!-- <p>afsdfasdf</p>
    <v-img src="@/assets/image/서울.jpg" height="200"></v-img>
    <v-img src="/src/assets/image/강원.jpg"></v-img> -->
    <VAlertProps
      title="제목"
      alertType="success"
      text="성공 alert 테스트입니다. 성공 alert 테스트입니다. 성공 alert 테스트입니다."
    />
    <VAlertProps
      title="제목"
      alertType="error"
      text="실패 alert 테스트입니다. 실패 alert 테스트입니다."
    />
  </div>
</template>

<style scoped></style>
