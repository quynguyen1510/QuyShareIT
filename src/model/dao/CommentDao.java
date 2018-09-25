package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constant.Define;
import model.bean.Category;
import model.bean.Comment;
import model.bean.News;
import model.bean.Users;
import util.DatabaseConnection;

public class CommentDao {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public ArrayList<Comment> getItems(){
		ArrayList<Comment> arCmt = new ArrayList<>();
		String sql = "SELECT comment.id , content , fullname , comment.date_create ,parent_id , news.id , news.name FROM comment \r\n" + 
				"INNER JOIN news ON comment.news_id = news.id ORDER BY comment.id DESC";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				News objNews = new News();
				objNews.setId(rs.getInt("news.id"));
				objNews.setName(rs.getString("news.name"));
				Comment objCmt = new Comment(rs.getInt("comment.id"), rs.getString("content"),rs.getTimestamp("date_create"),rs.getString("fullname"), rs.getInt("parent_id"), objNews,rs.getInt("active"));
				arCmt.add(objCmt);
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
		
		return arCmt;
	}
	
	public ArrayList<Comment> getItemsByID(int id){
		ArrayList<Comment> arCmt = new ArrayList<>();
		String sql = "SELECT comment.id , comment.content , comment.fullname , comment.date_create ,parent_id , news.id , news.name , comment.active FROM comment INNER JOIN news ON comment.news_id = news.id WHERE comment.news_id = ?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				News objNews = new News();
				objNews.setId(rs.getInt("news.id"));
				objNews.setName(rs.getString("news.name"));
				Comment objCmt = new Comment(rs.getInt("comment.id"), rs.getString("content"),rs.getTimestamp("date_create"),rs.getString("fullname"), rs.getInt("parent_id"), objNews, rs.getInt("active"));
				arCmt.add(objCmt);
			}
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
		return arCmt;
	}
	
	
	public int deleteItem(int id) {
			int result = 0;
			String sql = "DELETE FROM comment WHERE id=?";
			try {
				conn = DatabaseConnection.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setInt(1, id);
				result = pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(Exception e) {
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

	public int addCmt(Comment objCmt) {
		int result = 0;
		String sql = "INSERT INTO comment(content,fullname,date_create,parent_id,news_id,active) VALUES(?,?,?,?,?,?)";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCmt.getContent());
			pst.setString(2, objCmt.getName());
			pst.setTimestamp(3, objCmt.getDate_create());
			pst.setInt(4, objCmt.getParent_id());
			pst.setInt(5, objCmt.getNews().getId());
			pst.setInt(6, objCmt.getActive());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pst != null && conn != null){
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
	public int editCmt(int id , int active) {
		int result = 0;
		String sql = "UPDATE comment SET active = ? WHERE id = ?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, active);
			pst.setInt(2, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pst != null && conn != null){
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

	public int countCmt() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS sumCmt FROM comment";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt("sumCmt");
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

	public ArrayList<Comment> getItemsPagination(int offset) {
		ArrayList<Comment> arCmt = new ArrayList<>();
		String sql = "SELECT comment.id , content , fullname , comment.date_create ,parent_id , news.id , news.name  , comment.active FROM comment \r\n" + 
				"INNER JOIN news ON comment.news_id = news.id ORDER BY comment.id LIMIT "+offset +","+ Define.ROW_COUNT_NEWS;
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				News objNews = new News();
				objNews.setId(rs.getInt("news.id"));
				objNews.setName(rs.getString("news.name"));
				Comment objCmt = new Comment(rs.getInt("comment.id"), rs.getString("content"),rs.getTimestamp("date_create"),rs.getString("fullname"), rs.getInt("parent_id"), objNews,rs.getInt("active"));
				arCmt.add(objCmt);
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
		return arCmt;
	}
}
