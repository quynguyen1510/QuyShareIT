package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDao;
import util.AuthUtil;

/**
 * Servlet implementation class AdminDeleteCatController
 */
public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteCatController() {
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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(categoryDao.deleteCat(id) > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cats?msg=2");
			rd.forward(request, response);
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cats?error=2");
			rd.forward(request, response);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
