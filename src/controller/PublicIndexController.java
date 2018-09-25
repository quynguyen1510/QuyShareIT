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
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categoryDao;
	private NewsDao newsDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexController() {
        super();
        categoryDao = new CategoryDao();
        newsDao = new NewsDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<Category> arParentCat = categoryDao.getParentItem();
		request.setAttribute("arParentCat", arParentCat);
		ArrayList<Category> arChildCat = categoryDao.getChildItem();
		request.setAttribute("arChildCat", arChildCat);
//		ArrayList<News> arNews = newsDao.getItems();
//		request.setAttribute("arNews", arNews);
		
		int sumNews = newsDao.countNews();
		int sumPage = (int) Math.ceil((float)sumNews/Define.ROW_COUNT_NEWS);
		request.setAttribute("sumPage", sumPage);
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));	
		}
		int offset = (page-1)*Define.ROW_COUNT_NEWS;
		request.setAttribute("currentPage", page);
		ArrayList<News> arNews = newsDao.getItemsPagination(offset);
		request.setAttribute("arNews", arNews);
		
		ArrayList<News> arHotNew = newsDao.getNewestItems();
		request.setAttribute("arHotNew", arHotNew);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
