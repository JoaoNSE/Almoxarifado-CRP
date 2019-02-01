package br.ce.qxd.crp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession s = request.getSession(false);
		String path =  request.getRequestURI();
		
		if(path.endsWith("login")) {
			if(s != null && s.getAttribute("usuarioLogado") != null) {
				response.sendRedirect("/produtos");
				return false;
			}
			return true;
			
		} else if(s != null && s.getAttribute("usuarioLogado") != null) {
			return true;
			
		} else {
			response.sendRedirect("/login");
			return false;
		}
		
	}
	
}
