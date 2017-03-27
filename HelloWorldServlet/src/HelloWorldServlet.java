import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		System.out.println("doget");
		PrintStream out = new PrintStream(resp.getOutputStream());
//		都不用输出html的 头标签什么的
		out.println("<a href='http://www.baidu.com'>hahayuanqixiang</a>");
		// resp.getWriter().write("<a href='http://www.baidu.com'>haha</a>");

	}

}
