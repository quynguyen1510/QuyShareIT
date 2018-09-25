package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDao;
import util.AuthUtil;

/**
 * Servlet implementation class AdminAddCatController
 */
public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categoryDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAddCatController() {
		super();
		categoryDao = new CategoryDao();
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
		ArrayList<Category> arCatParent = categoryDao.getParentItem();
		request.setAttribute("arCatParent", arCatParent);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int parent_id = 0;
		try {
			parent_id = Integer.parseInt(request.getParameter("parent_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		if ("".equals(name)) {
			response.sendRedirect(request.getContextPath() + "/admin/cats/add?error=0");
			return;
		}
		Category objCat = new Category(0, name, parent_id);
		if (categoryDao.addItem(objCat) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=0");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cats/add.jsp?error=0");
			rd.forward(request, response);
			return;
		}
	}

}
