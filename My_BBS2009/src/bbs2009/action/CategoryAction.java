package bbs2009.action;

import java.sql.SQLException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bbs2009.model.*;
import bbs2009.service.*;

public class CategoryAction extends ActionSupport {
	//������������Щfield��getter��setter ����Ҫ  �Ժ�Ҫע�룡��
	
	private List<Category> Categories;
	
	// û���õ���  �Ժ���Spring ע��
	private CategoryService categoryservice = new CategoryService();
	
	// domain model ����add ���� updateʱ  struts�Զ���field�������
	private Category category;
	
	private int Id;

	
	

	
	public String list() throws SQLException {
		Categories = categoryservice.list();
		return SUCCESS;
	}

	public String delete() throws SQLException {
		categoryservice.deleteId(Id);
		return SUCCESS;
	}

	public String update() throws SQLException  {
		 
		categoryservice.update(category);
		return SUCCESS;
	}

	public String add() throws SQLException {
		categoryservice.add(category);
		return SUCCESS;
	}

	public String updateinput() throws SQLException {
		this.category=categoryservice.loadById(Id);
		return INPUT;
	}

	public String addinput() {
		return INPUT;
	}

	public List<Category> getCategories() {
		return Categories;
	}

	public void setCategories(List<Category> Categories) {
		this.Categories = Categories;
	}

	public CategoryService getCategoryservice() {
		return categoryservice;
	}

	public void setCategoryservice(CategoryService categoryservice) {
		this.categoryservice = categoryservice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
}
