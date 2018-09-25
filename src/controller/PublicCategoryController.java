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
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexCategory
 */
public class PublicCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao;
    private NewsDao newsDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicCategoryController() {
        super();
        categoryDao = new CategoryDao();
        newsDao = new NewsDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0 ; 
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		ArrayList<News> arNewsByID = newsDao.getNewsmByID(id);
		request.setAttribute("arNewsByID", arNewsByID);
		ArrayList<Category> arParentCat = categoryDao.getParentItem();
		request.setAttribute("arParentCat", arParentCat);
		ArrayList<Category> arChildCat = categoryDao.getChildItem();
		request.setAttribute("arChildCat", arChildCat);
		ArrayList<News> arHotNew = newsDao.getNewestItems();
		request.setAttribute("arHotNew", arHotNew);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/category.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
