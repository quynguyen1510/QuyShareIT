package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Users;
import model.dao.UsersDao;
import util.StringLibrary;

/**
 * Servlet implementation class AuthLoginController
 */
public class AuthLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDao usersDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthLoginController() {
        super();
        usersDao = new UsersDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users login = (Users) session.getAttribute("login");
		if(login != null) {
			response.sendRedirect(request.getContextPath()+"/admin/cats");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if("".equals(username) || "".equals(password)) {
			RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp?error=12");
			rd.forward(request, response);
			return;
		}
		password = StringLibrary.md5(password);
		Users login = usersDao.checkLogin(username,password);
		if(login != null) {
			session.setAttribute("login", login);
			response.sendRedirect(request.getContextPath()+"/admin/cats");
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp?error=10");
			rd.forward(request, response);
			return;
		}
	}

}
