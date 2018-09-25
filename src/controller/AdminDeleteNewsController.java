package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.NewsDao;
import util.AuthUtil;

/**
 * Servlet implementation class AdminDeleteNewsController
 */
public class AdminDeleteNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDao newsDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteNewsController() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		String dirPath = request.getServletContext().getRealPath("images"); //đường dẫn lưu file
		File dirFile = new File(dirPath);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String urlFileDel = dirPath + File.separator + newsDao.getItemByID(id).getPicture();
		File delFile = new File(urlFileDel);
		delFile.delete();
		if(newsDao.deleteNews(id) > 0 ) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news?msg=9");
			rd.forward(request, response);
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news?error=9");
			rd.forward(request, response);
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
