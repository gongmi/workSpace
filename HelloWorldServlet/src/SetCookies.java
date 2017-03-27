import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCookies extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		for (int i = 0; i < 3; i++) {
			Cookie c = new Cookie("Session-cookie-name" + i, "Session-cookie-value" + i);
			resp.addCookie(c);// 这种cookie是会话cookie 像session一样 随浏览器的关闭而自动消失
			c = new Cookie("Persistent-cookie-name" + i, "Persistent-cookie-value" + i);
			c.setMaxAge(3600);
			resp.addCookie(c);// 这种cookie将会保存在硬盘3600s 即一个小时

		}
		PrintWriter out = resp.getWriter();
		out.println("hello");
		System.out.println("hello");
	}

}
