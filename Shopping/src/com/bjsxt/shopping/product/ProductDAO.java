package com.bjsxt.shopping.product;

import java.util.List;

public interface ProductDAO {
	public List<Product> getProducts();

	public int getProducts(List<Product> products, int pageNo, int pageSize,
			boolean lazy);

	public void add(Product p);

	public void delete(int id);

	public void update(Product p);

	public Product loadById(int id);

	public void delete(String conditionStr);

	public int find(List<Product> products, int pageNo, int pageSize,
			String queryStr);
}

// UserDAO
// MySQL Oracle
// CategoryDAO
// MySQL Oracle
// ProductDAO
// MySQL Oracle


/*

//simple factory 简单工厂
class DAOFactory { //由工厂去产生各种 dao 工厂帮我创建各种对象 通过配置文件 或者pool 
	UserDAO getUserDAO() 
	{ return UsermysqlDao/UseroracleDao }

	ProductDAO getProductDAO() 
	{ return ProductMYSQLDAO/ProductOracleDAO }
由工厂来产生（implement了某一个接口的）具体的实现类
由工厂来产生（implement了ProductDAO的）具体的实现类ProductMYSQLDAO或ProductOracleDAO
}

// abstract factory

interface DAOFactory {
	UserDAO getUserDAO();

	CategoryDAO getCategoryDAO();
}

class MySQLDAOFactory implements DAOFactory {
	UserDAO getUserDAO() { return mysqlUserDao };

	CategoryDAO getCategoryDAO(){ return mysqlCategoryDao };
}
qq的皮肤
interface SkinFactory {
	MenuBar getMenuBar();
	ToolBar getToolBar();}
	
	class oceanSkinFactory{
	}
}*/