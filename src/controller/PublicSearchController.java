package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicSearchController
 */
public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categoryDao;
	private NewsDao newsDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicSearchController() {
		super();
		categoryDao = new CategoryDao();
		newsDao = new NewsDao();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String searchNews = request.getParameter("searchnews");
		ArrayList<Category> arParentCat = categoryDao.getParentItem();
		request.setAttribute("arParentCat", arParentCat);
		ArrayList<Category> arChildCat = categoryDao.getChildItem();
		request.setAttribute("arChildCat", arChildCat);
		ArrayList<News> arHotNew = newsDao.getNewestItems();
		request.setAttribute("arHotNew", arHotNew);
		ArrayList<News> arSearching = newsDao.searchItem(searchNews);
		request.setAttribute("arSearching", arSearching);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/search.jsp");
		rd.forward(request, response);
	}

}
