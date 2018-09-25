package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constant.Define;
import model.bean.Category;
import model.bean.News;
import model.bean.Users;
import util.DatabaseConnection;

public class NewsDao {
	private Statement st;
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public ArrayList<News> getItems() {
		ArrayList<News> arNews = new ArrayList<>();
		String sql = "SELECT news.id , news.name , preview , detail , date_create ,created_by, picture , cat_list.id , cat_list.name , cat_list.parent_id , is_slide FROM news INNER JOIN cat_list ON news.cat_id = cat_list.id ORDER BY news.id";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category();
				objCat.setId(rs.getInt("cat_list.id"));
				objCat.setName(rs.getString("cat_list.name"));
				objCat.setParent_id(rs.getInt("cat_list.parent_id"));
				News objNews = new News(rs.getInt("id"), rs.getString("name"),rs.getString("preview") , rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"), rs.getInt("is_slide"), rs.getInt("created_by"), objCat);
				arNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null && st != null && conn != null){
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return arNews;
	}
	
	public ArrayList<News> getNewestItems() {
		ArrayList<News> arNews = new ArrayList<>();
		String sql = "SELECT news.id , news.name , preview , detail , date_create ,created_by, picture , cat_list.id , cat_list.name , cat_list.parent_id , is_slide FROM news INNER JOIN cat_list ON news.cat_id = cat_list.id ORDER BY news.id DESC LIMIT 3";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category();
				objCat.setId(rs.getInt("cat_list.id"));
				objCat.setName(rs.getString("cat_list.name"));
				objCat.setParent_id(rs.getInt("cat_list.parent_id"));
				News objNews = new News(rs.getInt("id"), rs.getString("name"),rs.getString("preview") , rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"), rs.getInt("is_slide"), rs.getInt("created_by"), objCat);
				arNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null && st != null && conn != null){
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return arNews;
	}

	public int addItem(News objNews) {
		int result = 0;
		String sql = "INSERT INTO news(name,preview,detail,date_create,created_by,picture,cat_id,is_slide) VALUES(?,?,?,?,?,?,?,?)";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getName());
			pst.setString(2, objNews.getPreview());
			pst.setString(3, objNews.getDetail());
			pst.setTimestamp(4, objNews.getDate_create());
			pst.setInt(5, objNews.getCreated_by());
			pst.setString(6,objNews.getPicture());
			pst.setInt(7, objNews.getCategory().getId());
			pst.setInt(8, objNews.getIsSlide());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pst != null && conn != null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public News getItemByID(int id) {
		News objNews = null;
		String sql = "SELECT news.id , news.name , preview , detail , date_create ,created_by, picture , cat_list.id , cat_list.name , cat_list.parent_id , is_slide FROM news INNER JOIN cat_list ON news.cat_id = cat_list.id WHERE news.id=?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				Category objCat = new Category();
				objCat.setId(rs.getInt("cat_list.id"));
				objCat.setName(rs.getString("cat_list.name"));
				objCat.setParent_id(rs.getInt("cat_list.parent_id"));
				objNews = new News(rs.getInt("id"), rs.getString("name"),rs.getString("preview") , rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"), rs.getInt("is_slide"), rs.getInt("created_by"), objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return objNews;
	}

	public int editNews(News objNews) {
		int result = 0;
		String sql = "UPDATE news SET name = ? , preview = ? , detail = ? , picture = ? ,cat_id=?,is_slide=? WHERE id = ?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getName());
			pst.setString(2, objNews.getPreview());
			pst.setString(3, objNews.getDetail());
			pst.setString(4, objNews.getPicture());
			pst.setInt(5, objNews.getCategory().getId());
			pst.setInt(6, objNews.getIsSlide());
			pst.setInt(7, objNews.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pst != null && conn != null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public int countNews() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS sumNews FROM news INNER JOIN cat_list ON news.cat_id = cat_list.id";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt("sumNews");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(st != null && conn != null && rs != null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public ArrayList<News> getItemsPagination(int offset) {
		ArrayList<News> arNews = new ArrayList<>();
		String sql = "SELECT news.id , news.name , preview , detail , date_create ,created_by, picture , cat_list.id , cat_list.name , cat_list.parent_id , is_slide FROM news INNER JOIN cat_list ON news.cat_id = cat_list.id ORDER BY news.id LIMIT "+offset +","+ Define.ROW_COUNT_NEWS;
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category();
				objCat.setId(rs.getInt("cat_list.id"));
				objCat.setName(rs.getString("cat_list.name"));
				objCat.setParent_id(rs.getInt("cat_list.parent_id"));
				News objNews = new News(rs.getInt("id"), rs.getString("name"),rs.getString("preview") , rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"), rs.getInt("is_slide"), rs.getInt("created_by"), objCat);
				arNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arNews;
	}

	public int deleteNews(int id) {
		int result = 0;
		String sql = "DELETE FROM news WHERE id = ?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pst != null && conn != null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public ArrayList<News> getNewsmByID(int id) {
		ArrayList<News> arNews = new ArrayList<>();
		String sql = "SELECT news.id , news.name , preview , detail , date_create ,created_by, picture , cat_list.id , cat_list.name , cat_list.parent_id , is_slide FROM news INNER JOIN cat_list ON news.cat_id = cat_list.id WHERE news.cat_id=?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Category objCat = new Category();
				objCat.setId(rs.getInt("cat_list.id"));
				objCat.setName(rs.getString("cat_list.name"));
				objCat.setParent_id(rs.getInt("cat_list.parent_id"));
				News objNews = new News(rs.getInt("id"), rs.getString("name"),rs.getString("preview") , rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"), rs.getInt("is_slide"), rs.getInt("created_by"), objCat);
				arNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return arNews;
	}

	public ArrayList<News> getNewsByIDLimit(int id, int did) {
		ArrayList<News> arNews = new ArrayList<>();
		String sql = "SELECT news.id , news.name , preview , detail , date_create ,created_by, picture , cat_list.id , cat_list.name , cat_list.parent_id , is_slide FROM news INNER JOIN cat_list ON news.cat_id = cat_list.id WHERE news.cat_id=? && news.id != ? LIMIT 2";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, did);
			rs = pst.executeQuery();
			while (rs.next()) {
				Category objCat = new Category();
				objCat.setId(rs.getInt("cat_list.id"));
				objCat.setName(rs.getString("cat_list.name"));
				objCat.setParent_id(rs.getInt("cat_list.parent_id"));
				News objNews = new News(rs.getInt("id"), rs.getString("name"),rs.getString("preview") , rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"), rs.getInt("is_slide"), rs.getInt("created_by"), objCat);
				arNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return arNews;
	}

	public ArrayList<News> searchItem(String searchNews) {
		ArrayList<News> arNews = new ArrayList<>();
		String sql = "SELECT news.id , news.name , preview , detail , date_create ,created_by, picture , cat_list.id , cat_list.name , cat_list.parent_id , is_slide FROM news INNER JOIN cat_list ON news.cat_id = cat_list.id WHERE news.name LIKE "+ "'%" + searchNews + "%'";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCat = new Category();
				objCat.setId(rs.getInt("cat_list.id"));
				objCat.setName(rs.getString("cat_list.name"));
				objCat.setParent_id(rs.getInt("cat_list.parent_id"));
				News objNews = new News(rs.getInt("id"), rs.getString("name"),rs.getString("preview") , rs.getString("detail"), rs.getTimestamp("date_create"), rs.getString("picture"), rs.getInt("is_slide"), rs.getInt("created_by"), objCat);
				arNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null && st != null && conn != null){
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return arNews;
	}
}
