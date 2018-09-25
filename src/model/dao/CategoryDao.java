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
import util.DatabaseConnection;

public class CategoryDao {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public ArrayList<Category> getItems() {
		ArrayList<Category> items = new ArrayList<>();
		String sql = "SELECT id, name, parent_id FROM cat_list";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int parent_id = rs.getInt("parent_id");
				Category item = new Category(id, name, parent_id);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		return items;
	}
	
	
	
	public ArrayList<Category> getParentItem(){
		ArrayList<Category> items = new ArrayList<>();
		String sql = "SELECT id, name, parent_id FROM cat_list WHERE parent_id = 0";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int parent_id = rs.getInt("parent_id");
				Category item = new Category(id, name, parent_id);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		return items;
	}
	
	
	public ArrayList<Category> getChildItem(){
		ArrayList<Category> items = new ArrayList<>();
		String sql = "SELECT id, name, parent_id FROM cat_list WHERE parent_id != 0";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int parent_id = rs.getInt("parent_id");
				Category item = new Category(id, name, parent_id);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		return items;
	}

	public int addItem(Category objCat) {
		int result = 0;
		String sql = "INSERT INTO cat_list(name,parent_id) VALUES(?,?)";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getParent_id());
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
	
	public Category getItemByID(int id) {
		Category objCat = null;
		String sql = "SELECT id,name,parent_id FROM cat_list WHERE id=?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				objCat = new Category(id, rs.getString("name"), rs.getInt("parent_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objCat;
	}

	public int editCat(Category objCat) {
		int result = 0;
		String sql = "UPDATE cat_list SET name = ? , parent_id=? WHERE id= ?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getParent_id());
			pst.setInt(3, objCat.getId());
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

	public int deleteCat(int id) {
		int result = 0;
		String sql = "DELETE FROM cat_list WHERE id=?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
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



	public int countCats() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS sumCats FROM cat_list";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt("sumCats");
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



	public ArrayList<Category> getItemsPagination(int offset) {
		ArrayList<Category> arCat = new ArrayList<>();
		String sql = "SELECT id, name, parent_id FROM cat_list";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int parent_id = rs.getInt("parent_id");
				Category item = new Category(id, name, parent_id);
				arCat.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arCat;
	}
	
}
