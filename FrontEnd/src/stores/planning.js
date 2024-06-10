import { defineStore } from "pinia";
import { reactive } from "vue";

// 서버에서 사용자의 플랜 내용을 전부 가져온다.

// data{
//     plan_id: "서버에서 처리함"
//     user_id: 유저명(비회원은 저장 불가)
//     title : title
//     update_date : now()
//     plan_detail: [
//         {
//             plan_id: 서버에서 처리
//             plan_day: 여행날짜(n일차?)
//             plan_index : 여행 순서(시작, 끝 시간으로 대체 가능)
//             content_id : 여행지
//             start_time : time
//             end_time : time
//             memo : String
//         }
//     ]
// }

export const usePlanningStore = defineStore("planning", () => {
  //서버에서 plan 배열 받아왔다고 가정
  const plans = reactive([
    {
      data: {
        plan_id: "1",
        user_id: "test1",
        title: "여행 일정입니다.",
        update_date: "2024-05-15",
        plan_detail: [
          {
            plan_day: 1,
            plan_index: 0,
            content_id: 125266,
            start_time: "",
            end_time: "",
            memo: "1일차 첫번째입니다..",
          },
          {
            plan_day: 1,
            plan_index: 1,
            content_id: 125405,
            start_time: "",
            end_time: "",
            memo: "1일차 두번째입니다..",
          },
          {
            plan_day: 2,
            plan_index: 0,
            content_id: 125419,
            start_time: "",
            end_time: "",
            memo: "2일차 1번째입니다..",
          },
          {
            plan_day: 2,
            plan_index: 1,
            content_id: 125417,
            start_time: "",
            end_time: "",
            memo: "2일차 2번째입니다..",
          },
          {
            plan_day: 2,
            plan_index: 2,
            content_id: 125418,
            start_time: "",
            end_time: "",
            memo: "2일차 3번째입니다..",
          },
        ],
      },
    },
  ]);

  const addPlan = () => {};

  const deletePlan = () => {};

  return { plans, addPlan, deletePlan };
});
