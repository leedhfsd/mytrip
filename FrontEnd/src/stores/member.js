import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore } from "pinia";
import { jwtDecode } from "jwt-decode";

import { login, logout, findById, tokenRegeneration, join } from "@/api/user";

export const useMemberStore = defineStore(
  "member",
  () => {
    const router = useRouter();
    const isLogin = ref(false);
    const isLoginError = ref(false);
    const userInfo = ref(null);
    const userId = ref(null);
    const isValidToken = ref(false);
    const isAuthenticated = ref(false);

    const userLogin = async (loginUser) => {
      await login(
        loginUser,
        (response) => {
          if (response.status === 201) {
            console.log("로그인 성공!!!!");
            let { data } = response;
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            isLogin.value = true;
            isLoginError.value = false;
            isValidToken.value = true;
            sessionStorage.setItem("accessToken", accessToken);
            sessionStorage.setItem("refreshToken", refreshToken);
            console.log(isAuthenticated);
            console.log(userInfo.value);
            isAuthenticated.value = true;
          }
        },
        (error) => {
          console.log("로그인 실패!!!!");
          isLogin.value = false;
          isLoginError.value = true;
          isValidToken.value = false;
          isAuthenticated.value = false;
          console.error(error);
        }
      );
    };

    const getUserInfo = async (token) => {
      let decodeToken = jwtDecode(token);
      console.log(decodeToken);
      await findById(
        decodeToken.userId,
        (response) => {
          if (response.status === 200) {
            //console.log(response);
            userInfo.value = response.data.userInfo;
            userId.value = userInfo.value.userId;
            //console.log(userId.value);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.error(
            "[토큰 만료되어 사용 불가능.] : ",
            error.response.status,
            error.response.statusText
          );
          isValidToken.value = false;

          await tokenRegenerate();
        }
      );
    };

    const tokenRegenerate = async () => {
      await tokenRegeneration(
        JSON.stringify(userInfo.value),
        (response) => {
          if (response.status === 201) {
            let accessToken = response.data["access-token"];
            sessionStorage.setItem("accessToken", accessToken);
            isValidToken.value = true;
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              userInfo.value.userid,
              (response) => {
                if (response.status === 200) {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                isLogin.value = false;
                userInfo.value = null;
                isValidToken.value = false;
                router.push({ name: "user-login" });
              },
              (error) => {
                console.error(error);
                isLogin.value = false;
                userInfo.value = null;
              }
            );
          }
        }
      );
    };

    const userLogout = async () => {
      console.log("로그아웃 아이디 : " + userInfo.value.userId);
      await logout(
        userInfo.value.userId,
        (response) => {
          if (response.status === 200) {
            isLogin.value = false;
            isAuthenticated.value = false;
            userInfo.value = null;
            userId.value = null;
            isValidToken.value = false;
            sessionStorage.removeItem("accessToken");
            sessionStorage.removeItem("refreshToken");
          } else {
            console.error("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    };

    const userJoin = async (data) => {
      const res = await join(data);
      return res.status;
    };

    return {
      isLogin,
      isLoginError,
      userInfo,
      isValidToken,
      userLogin,
      getUserInfo,
      tokenRegenerate,
      userLogout,
      userJoin,
      isAuthenticated,
      userId,
    };
  },
  { persist: true }
);
