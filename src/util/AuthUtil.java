package util;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Users;

public class AuthUtil {

	public static boolean checkLogin(HttpServletRequest request , HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Users login = (Users) session.getAttribute("login");
		if(login != null) {
			return true;
		}
		response.sendRedirect(request.getContextPath()+"/login");
		return false;
	}
	
}
