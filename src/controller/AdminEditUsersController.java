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
 * Servlet implementation class AdminEditUsersController
 */
public class AdminEditUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UsersDao usersDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditUsersController() {
        super();
        usersDao = new UsersDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		Users objUsers = usersDao.getItemByID(id);
		request.setAttribute("objUsers", objUsers);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/users/edit.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		int active = 1;
		try {
			active = Integer.parseInt(request.getParameter("quyenhan"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		if ("".equals(password)) {
			password = usersDao.getItemByID(id).getPassword();
		} else {
			password = StringLibrary.md5(request.getParameter("password"));
		}
		Users objUser = new Users(id, "", password, fullname, active);
		if (usersDao.editUser(objUser) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=4");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?error=4");
			rd.forward(request, response);
			return;
		}
	}

}
