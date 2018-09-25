package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Users;
import model.dao.UsersDao;
import util.AuthUtil;
import util.StringLibrary;

/**
 * Servlet implementation class AdminAddUserController
 */
public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDao usersDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAddUserController() {
		super();
		usersDao = new UsersDao();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/users/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String fullname = request.getParameter("fullname");
		int active = 0;
		try {
			active = Integer.parseInt(request.getParameter("quyenhan"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (usersDao.checkUser(username) != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/users/add.jsp?error=3");
			rd.forward(request, response);
		} else if (!password.equals(repassword)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/users/add.jsp?error=3.1");
			rd.forward(request, response);
		} 
			password = StringLibrary.md5(password);
			Users objUser = new Users(0, username, password, fullname, active);
			if (usersDao.addUser(objUser) > 0) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/users?msg=3");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/users/add.jsp?error=3");
				rd.forward(request, response);
			}
		
	}

}
