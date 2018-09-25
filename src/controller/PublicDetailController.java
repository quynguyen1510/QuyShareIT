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
import model.bean.Comment;
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.CommentDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicDetailController
 */
public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDao newsDao;
	private CategoryDao categoryDao;
	private CommentDao commentDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicDetailController() {
        super();
        newsDao = new NewsDao();
        categoryDao = new CategoryDao();
        commentDao = new CommentDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int did = 0;
		try {
			did = Integer.parseInt(request.getParameter("did"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		News objNews = newsDao.getItemByID(did);
		request.setAttribute("objNews", objNews);
		ArrayList<Category> arParentCat = categoryDao.getParentItem();
		request.setAttribute("arParentCat", arParentCat);
		
		ArrayList<News> arNewsLimit = newsDao.getNewsByIDLimit(objNews.getCategory().getId(),did);
		request.setAttribute("arNewsLimit", arNewsLimit);
		
		ArrayList<Category> arChildCat = categoryDao.getChildItem();
		request.setAttribute("arChildCat", arChildCat);

		ArrayList<Comment> arComments = commentDao.getItemsByID(did);
		request.setAttribute("arComments", arComments);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
