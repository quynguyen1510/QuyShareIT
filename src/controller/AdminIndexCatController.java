package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Define;
import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;
import util.AuthUtil;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexCatController() {
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
		ArrayList<Category> arParentCat = categoryDao.getParentItem();
		request.setAttribute("arParentCat", arParentCat);
		ArrayList<Category> arChildCat = categoryDao.getChildItem();
		request.setAttribute("arChildCat", arChildCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/index.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
