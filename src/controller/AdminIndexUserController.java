package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Users;
import model.dao.UsersDao;
import util.AuthUtil;

/**
 * Servlet implementation class AdminIndexUserController
 */
public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDao usersDao;
       
    public AdminIndexUserController() {
        super();
        usersDao = new UsersDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			return;
		}
		ArrayList<Users> arrUsers = usersDao.getItems();
		request.setAttribute("arrUsers", arrUsers);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/users/index.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
