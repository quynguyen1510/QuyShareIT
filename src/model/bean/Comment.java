package model.bean;

import java.sql.Timestamp;

public class Comment {
	private int id;
	private String content;
	private Timestamp date_create;
	private String name;
	private int parent_id;
	private News news;
	private int active;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public Comment(int id, String content, Timestamp date_create, String name, int parent_id, News news,int active) {
		super();
		this.id = id;
		this.content = content;
		this.date_create = date_create;
		this.name = name;
		this.parent_id = parent_id;
		this.news = news;
		this.active = active;
	}
	
}
