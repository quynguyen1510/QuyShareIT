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
import model.bean.Comment;
import model.bean.News;
import model.dao.CommentDao;
import util.AuthUtil;

/**
 * Servlet implementation class AdminIndexCommentController
 */
public class AdminIndexCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDao commentDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexCommentController() {
        super();
        commentDao = new CommentDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			return;
		}
		

		int sumCmt = commentDao.countCmt();
		int sumPage = (int) Math.ceil((float)sumCmt/Define.ROW_COUNT_NEWS);
		request.setAttribute("sumPage", sumPage);
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));	
		}
		int offset = (page-1)*Define.ROW_COUNT_NEWS;
		request.setAttribute("currentPage", page);
		ArrayList<Comment> arCmt = commentDao.getItemsPagination(offset);
		request.setAttribute("arCmt", arCmt);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int active = Integer.parseInt(request.getParameter("activeidc"));
		int id = Integer.parseInt(request.getParameter("id"));
		if(active == 0) {
			active = 1;
		   	commentDao.editCmt(id, active);
		   	request.setAttribute("active", active);
		   	request.setAttribute("id", id);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/active.jsp");
			rd.forward(request, response);
		}else {
			active = 0;
		   	commentDao.editCmt(id, active);
		   	request.setAttribute("active", active);
		   	request.setAttribute("id", id);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/active.jsp");
			rd.forward(request, response);
		}
	}

}
