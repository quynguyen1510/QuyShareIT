package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentDao;

/**
 * Servlet implementation class AdminDeleteCommentController
 */
public class AdminDeleteCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDao commentDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteCommentController() {
        super();
        commentDao = new CommentDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0 ;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(Exception e ) {
			e.printStackTrace();
		}
		if(commentDao.deleteItem(id) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/comment?msg=11");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/comment?error=11");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
