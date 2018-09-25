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
import model.bean.News;
import model.dao.NewsDao;
import util.AuthUtil;

/**
 * Servlet implementation class AdminIndexNewsController
 */
public class AdminIndexNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDao newsDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexNewsController() {
        super();
        newsDao = new NewsDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			return;
		}
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
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
