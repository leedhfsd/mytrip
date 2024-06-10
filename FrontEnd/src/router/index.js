import HomeView from "@/views/HomeView.vue";
import RecommendView from "@/views/RecommendView.vue";
import LoginView from "@/views/user/LoginView.vue";
import JoinView from "@/views/user/JoinView.vue";
import ReviewView from "@/views/review/ReviewView.vue";
import ReviewForm from "@/components/review/ReviewForm.vue";
import TestView from "@/views/TestView.vue";
import AttractionDetail from "@/components/attraction/AttractionDetail.vue";
import SearchView from "@/views/SearchView.vue";
import SearchHomeView from "@/views/SearchHomeView.vue";
import { useMemberStore } from "@/stores/member";
import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: "/test", name: "test", component: TestView },
    { path: "/", name: "home", component: HomeView },
    { path: "/join", name: "join", component: JoinView },
    { path: "/recommend", name: "recommend", component: RecommendView },
    {
      path: "/review",
      name: "review",
      component: ReviewView,
      props: (route) => ({ word: route.params.word }),
      children: [
        {
          path: ":word",
          name: "review-search",
          component: () =>
            import("../components/attraction/SearchListItem.vue"),
        },
      ],
    },
    { path: "/login", name: "login", component: LoginView },
    {
      path: "/planning",
      name: "planning",
      component: () => import("../views/planning/PlanningView.vue"),
      beforeEnter: (to, from) => {
        const store = useMemberStore();
        const isAuthenticated = store.isAuthenticated;
        if (!isAuthenticated && to.name !== "login") {
          return { name: "login" };
        }
      },
      redirect: { name: "plan-list" },
      children: [
        {
          path: "list",
          name: "plan-list",
          component: () =>
            import("../components/planning/PlanningListView.vue"),
        },
        {
          path: "form/:planId",
          name: "plan-form",
          component: () => import("../components/planning/PlanningForm.vue"),
        },
      ],
    },
    {
      path: "/mypage",
      name: "mypage",
      component: () => import("../views/MyPageView.vue"),
      beforeEnter: (to, from) => {
        const store = useMemberStore();
        const isAuthenticated = store.isAuthenticated;
        if (!isAuthenticated && to.name !== "login") {
          return { name: "login" };
        }
      },
      redirect: { name: "mypage-recent" },
      children: [
        {
          path: "recent",
          name: "mypage-recent",
          component: () => import("../components/mypage/MyPageRecent.vue"),
        },
        {
          path: "favorite",
          name: "mypage-favorite",
          component: () => import("../components/mypage/MyPageFavorite.vue"),
        },
        {
          path: "review",
          name: "mypage-review",
          component: () => import("../components/mypage/MyPageReview.vue"),
        },
      ],
    },
    {
      path: "/attraction/:id",
      name: "attractionDetail",
      component: AttractionDetail,
      props: (route) => ({
        id: route.params.id,
        attraction: route.state?.attraction,
      }),
    },
    {
      path: "/attraction/:id/rating",
      name: "reviewRating",
      component: ReviewForm,
      props: true,
      beforeEnter: (to, from) => {
        const store = useMemberStore();
        const isAuthenticated = store.isAuthenticated;
        if (!isAuthenticated && to.name !== "login") {
          return { name: "login" };
        }
      },
    },
    {
      path: "/searching",
      name: "searchPage",
      component: SearchHomeView,
    },
    {
      path: "/search/:word",
      name: "search",
      component: SearchView,
      props: (route) => ({ word: route.params.word }),
    },
  ],
  scrollBehavior(to, from, savedPosition) {
    // always scroll to top
    return { top: 0 };
  },
});

export default router;
