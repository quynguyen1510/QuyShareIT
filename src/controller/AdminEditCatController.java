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
 * Servlet implementation class AdminDeleteCatController
 */
public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categoryDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditCatController() {
        super();
        categoryDao = new CategoryDao();
       
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
		Category objCat = categoryDao.getItemByID(id);
		request.setAttribute("objCat", objCat);
		ArrayList<Category> arCat = categoryDao.getParentItem();
		request.setAttribute("parentCat", arCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		int parent_id = 0;
		try {
			parent_id = Integer.parseInt(request.getParameter("parent_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("".equals(name)) {
			name = categoryDao.getItemByID(id).getName();
		}
		Category objCat = new Category(id, name, parent_id);
		if(categoryDao.editCat(objCat) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/cats?msg=1");
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp?error=1");
			rd.forward(request, response);
		}
	}

}
