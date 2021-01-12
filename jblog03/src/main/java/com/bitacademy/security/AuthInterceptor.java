package com.bitacademy.security;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bitacademy.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. handler 종류 확인
		if (handler instanceof HandlerMethod == false) {
			// DefaultServletHanlder가 처리하는 경우(보통, assets의 정적 자원 접근)
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Handler Method에 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Method에 @Auth가 안붙어 있는 경우, Type(Class)에 붙어 있는 지 확인한다.(과제)
		if (auth == null) {

			auth = handler.getClass().getAnnotation(Auth.class);
		}

		// 5. Method나 Type(Class)에 @Auth 가 없는 경우
		if (auth == null) {
			return true;
		}

		// 6. @Auth가 붙어 있기 때문에 인증(Authentification) 여부 확인
		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}


		// 사용자 명과 경로가 다를 경우 // TODO 올바른 방법으로 수정
		@SuppressWarnings("unchecked")
		final Map<String, String> variableValueMap = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		if(!authUser.getId().equals(variableValueMap.get("id"))) {
			return false;
		}
		
		
		
		// 7. 권한 (Authorizaton) 체크를 위해서 @Auth의 role 가져오기 ("USER", "ADMIN")
		String role = auth.value();
		System.out.println(role);

		// 8. @Auth의 role이 "USER"인 경우에는 authUSer의 role이 "USER", "ADMIN" 상관 없음
		if ("USER".equals(role)) {
			return true;
		}

		
		
		
		
		

		// 9. @Auth의 role이 "ADMIN"인 경우에는 authUser가 "ADMIN" 이어야 한다.
//		if (!"ADMIN".equals(authUser.getRole())) {
//			response.sendRedirect(request.getContextPath());
//			return false;
//		}

		// @Auth의 role => "ADMIN"
		// authUser의 role => "ADMIN"
		// 관리자 권한이 확인
		return true;

	}

	public boolean hello(@PathVariable String id) {
		boolean isOwner = false;
		System.out.println(id);
		System.out.println(id);
		System.out.println(id);
		System.out.println(id);
		return isOwner;
	}
}
