package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.Comment;
import model.bean.News;
import model.bean.Users;
import model.dao.CommentDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminAddCommentController
 */
@WebServlet("/AdminAddCommentController")
public class AdminAddCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDao commentDao;
	private NewsDao newsDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCommentController() {
        super();
        commentDao = new CommentDao();
        newsDao = new NewsDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String content = request.getParameter("message");
		String website = request.getParameter("website");
		String email = request.getParameter("email");
		System.out.println(name);
		System.out.println(content);
		System.out.println(website);
		System.out.println(email);
		int idnews = 0;
		try {
			idnews = Integer.parseInt(request.getParameter("id"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		News objNews = newsDao.getItemByID(idnews);
		Date date = new Date();
		Timestamp date_create =  new Timestamp(date.getTime());
		Comment objCmt = new Comment(0, content, date_create, name, objNews.getCategory().getParent_id(), objNews,0);
		commentDao.addCmt(objCmt);
		request.setAttribute("objCmt", objCmt);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showcmt.jsp");
		rd.forward(request, response);
	}

}
