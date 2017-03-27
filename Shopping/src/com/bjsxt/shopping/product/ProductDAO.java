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

//simple factory �򵥹���
class DAOFactory { //�ɹ���ȥ�������� dao �������Ҵ������ֶ��� ͨ�������ļ� ����pool 
	UserDAO getUserDAO() 
	{ return UsermysqlDao/UseroracleDao }

	ProductDAO getProductDAO() 
	{ return ProductMYSQLDAO/ProductOracleDAO }
�ɹ�����������implement��ĳһ���ӿڵģ������ʵ����
�ɹ�����������implement��ProductDAO�ģ������ʵ����ProductMYSQLDAO��ProductOracleDAO
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
qq��Ƥ��
interface SkinFactory {
	MenuBar getMenuBar();
	ToolBar getToolBar();}
	
	class oceanSkinFactory{
	}
}*/