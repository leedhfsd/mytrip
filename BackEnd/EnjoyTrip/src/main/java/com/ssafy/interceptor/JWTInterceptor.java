package com.ssafy.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.exception.UnAuthorizedException;
import com.ssafy.util.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j

// 우선 인터셉터를 사용하기 위해서는 
// WebMvcConfigurer 인터페이스를 구현한 설정 클래스에서 addInterceptors() 메서드를 오버라이드하여 인터셉터 등록해야함.
public class JWTInterceptor implements HandlerInterceptor {
	private final String HEADER_AUTH = "Authorization";
	private JWTUtil jwtUtil;

	public JWTInterceptor(JWTUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String token = request.getHeader(HEADER_AUTH);

		if (token != null && jwtUtil.checkToken(token)) {
			log.info("토큰 사용 가능 : {}", token);
			return true;
		} else {
			log.info("토큰 사용 불가능 : {}", token);
			throw new UnAuthorizedException();
		}
	}

}
