<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="com.bjsxt.shopping.client.*, com.bjsxt.shopping.product.*" %>

<%
Cart c = (Cart)session.getAttribute("cart");
if(c == null) {
	c = new Cart();
	session.setAttribute("cart", c);//看看当前的这个回话（也可以用cookie）里面有没有购物车 没有就造一个 有的话就用原来的
}//cookie 不占服务器内存 而且保存得久  session只能保存一会儿

%>


<%
request.setCharacterEncoding("GBK");
String action = request.getParameter("action");

if(action != null && action.trim().equals("add")) {//showproducts那个页面传add过来
	int id = Integer.parseInt(request.getParameter("id"));
	Product p = ProductMgr.getInstance().loadById(id);
	CartItem ci = new CartItem();
	ci.setProduct(p);
	ci.setCount(1);
	c.add(ci);
}

if(action != null && action.trim().equals("delete")) {//自身页面传delete过来
	int id = Integer.parseInt(request.getParameter("id"));
	c.deleteItemById(id);
}

if(action != null && action.trim().equals("update")) {//用了JavaScript
	for(int i=0; i<c.getItems().size(); i++) {
		CartItem ci = c.getItems().get(i);
		int count = Integer.parseInt(request.getParameter("p" + ci.getProduct().getId()));
		ci.setCount(count);
	}
}
 %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
List<CartItem> items = c.getItems();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>购物车</title>

</head>
<body>


<form action="Buy.jsp" method="get">
<input type="hidden" name="action" value="update"/>   <!--如果点击修改  则页面提交到当前页面 并且action=update -->
<table align="center" border="1">
	<tr>
		<td>产品ID</td>
		<td>产品名称</td>
		<td>购买数量</td>
		<td>单价</td>
		<td>总价</td>
		<td>处理</td>
	</tr>
	<%
	for(Iterator<CartItem> it = items.iterator(); it.hasNext(); ) {
		CartItem ci = it.next();
		%>
		<tr>
			<td><%=ci.getProduct().getId() %></td>
			<td><%=ci.getProduct().getName() %></td>
			<td>
				<input type="text" size=3 name="<%="p" + ci.getProduct().getId() %>" value="<%=ci.getCount() %>">				
			</td><!--产品数量可以修改 -->
			<td><%=ci.getProduct().getNormalPrice() %></td>
			<td><%=ci.getProduct().getNormalPrice()*ci.getCount() %></td>
			<td>
				
				<a href="Buy.jsp?action=delete&id=<%=ci.getProduct().getId() %>">删除</a>
				
			</td>
		</tr>
		<%
	}
	%>
	<tr>
		<td colspan=6>
			<a href="Confirm.jsp">下单</a>
			<a href="javascript:document.forms[0].submit()">修改</a>
			<!-- 怎么样知道更新的是哪个产品呢？不能知道 只能全都重置一遍哦~~把整个 form提交 submit()通过input type="hidden" name="action" value="update" 达成目的-->
			<!--点击修改之后 url地址是：Buy.jsp?action=update&p3=7&p1=2-->
		</td>
	</tr>
</table>
</form>
</body>
</html>
