import java.io.*;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

public class Params extends HttpServlet {



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	;
		PrintWriter pw=resp.getWriter();
		
		//我试了试 把form中的name改成两个
		// 这边用map来读两个name
//		Map<String, String[]> m=req.getParameterMap();
//		System.out.println(m.get("gender"));
//		System.out.println(m.get("country"));
//		String[] s=m.get("name");
//		System.out.println(s[0]);
//		System.out.println(s[1]);
		
		// 这边直接用getParameterValues来读两个name参数的值
		
//		String[] s= req.getParameterValues("name");
//		System.out.println(s[0]);
//		System.out.println(s[1]);	
		req.setCharacterEncoding("GBK");
		resp.setCharacterEncoding("GBK");//不能正确显示中文 为什么 
		pw.println(	req.getParameter("gender"));
		pw.println(	req.getParameter("country"));
		pw.println(	req.getParameter("name")); // 这边直接用这个方法只能读到一个参数值
		System.out.println("get"); 
		
		
		//这里是servlet到jsp的转发 我先让form。jsp提交过来 然后又转发到jsp  让jsp显示form的参数  因为是用的forword 所以request保持不变
		//也可以不从form提交过来 就在url地址写/test/Params?name=gongmi&country=china
		RequestDispatcher rd=req.getRequestDispatcher("/params.jsp");
		rd.forward(req, resp);
		//不过这样显示的最后的params。jsp中的中文显示不出来了
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
		System.out.println("post");
		 
	}
	}


