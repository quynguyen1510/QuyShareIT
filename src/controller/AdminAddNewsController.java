package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.NewsDao;
import util.AuthUtil;
import util.FileUtil;

/**
 * Servlet implementation class AdminEditNewsController
 */
@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categoryDao;
	private NewsDao newsDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddNewsController() {
        super();
        categoryDao = new CategoryDao();
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
		ArrayList<Category> arCat = categoryDao.getItems();
		request.setAttribute("arCat", arCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int category = 0;
		try {
			category = Integer.parseInt(request.getParameter("category"));
		}catch(Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp?error=6");
			rd.forward(request, response);
			return;
		}
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		int isSlide = Integer.parseInt(request.getParameter("isslide"));
		Part file = request.getPart("picture");
		String fileName = FileUtil.getFileName(file);

		String dirPath = request.getServletContext().getRealPath("images"); // đưa ra đường dẫn lưu hình
		System.out.println(dirPath);
		File dirFile = new File(dirPath);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String [] newName = fileName.split("\\.");
		String fileHead = newName[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String nameNew = fileHead + sdf.format(new Date()) + "." + newName[1];
		String filePath = dirPath + File.separator + nameNew;
		System.out.println(filePath);
		Date date = new Date();
		Timestamp date_create =  new Timestamp(date.getTime());
		Category objCat = new Category(category, null, 0);
		News objNews = new News(0, name, preview, detail, date_create, nameNew, isSlide, 0, objCat);
		if(newsDao.addItem(objNews) > 0) {
			file.write(filePath); //ghi file đường dẫn của nó filePath là tên đường dẫn
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=6");
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/addNews.jsp?error=6");
			rd.forward(request, response);
			return;
		}
	}

}
