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
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao;
    private NewsDao newsDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditNewsController() {
        super();
        categoryDao = new CategoryDao();
        newsDao = new NewsDao();
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
		ArrayList<Category> arCat = categoryDao.getItems();
		request.setAttribute("arCat", arCat);
		News objNews = newsDao.getItemByID(id);
		request.setAttribute("objNews", objNews);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		int cid = 0;
		int id = 0;
		int isSlide = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			cid = Integer.parseInt(request.getParameter("category"));
		}catch(Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp?error=7");
			rd.forward(request, response);
			return;
		}
		try {
			isSlide = Integer.parseInt(request.getParameter("quyenhan"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		String preview = request.getParameter("preview");
		String detail= request.getParameter("detail");
		
		String filePath = "";
		String nameNew = "";
		Part file = request.getPart("picture");
		String fileName = FileUtil.getFileName(file);
			//đổi tên file
		String dirPath = request.getServletContext().getRealPath("images"); //đường dẫn lưu file
		File dirFile = new File(dirPath);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		if(!"".equals(fileName)) {
			//có sửa hình
			if(!"".equals(newsDao.getItemByID(id).getPicture())) {
				String urlFileDel = dirPath + File.separator + newsDao.getItemByID(id).getPicture();
				File delFile = new File(urlFileDel);
				delFile.delete();
			}
			String [] newName = fileName.split("\\.");
			String fileHead = newName[0];
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			nameNew = fileHead + sdf.format(new Date()) + "." + newName[1];
			filePath = dirPath + File.separator + nameNew;
		}else {
			// không sửa hình
			nameNew = newsDao.getItemByID(id).getPicture();
		}
		Date date = new Date();
		Timestamp date_create =  new Timestamp(date.getTime());
		Category objCat = new Category(cid,null,newsDao.getItemByID(id).getCategory().getParent_id());// tạo đổi tượng để truyền vào objNews
		News objNews = new News(id, name, preview, detail, date_create, nameNew, isSlide,0 , objCat);
		if(newsDao.editNews(objNews) > 0) {
			if(!"".equals(fileName)) {
				file.write(filePath);
				System.out.println(filePath);
				response.sendRedirect(request.getContextPath()+"/admin/news?msg=7");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/news?msg=7");
				return;
			}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp?error=7");
			rd.forward(request, response);
			return;
		}
	}

}
