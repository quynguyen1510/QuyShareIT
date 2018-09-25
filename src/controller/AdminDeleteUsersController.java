package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UsersDao;
import util.AuthUtil;

/**
 * Servlet implementation class AdminDeleteUsersController
 */
public class AdminDeleteUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDao usersDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteUsersController() {
        super();
        usersDao = new UsersDao();
        // TODO Auto-generated constructor stub
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
		if (usersDao.deleteUser(id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=5");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/users?error=5");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
