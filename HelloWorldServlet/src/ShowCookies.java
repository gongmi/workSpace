import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCookies extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		PrintWriter out = resp.getWriter();
		Cookie[] cookies = req.getCookies();
		// 遍历客户端上的每个Cookie
		for (Cookie c : cookies) {
			// 如果Cookie的名为username，表明该Cookie是我们需要访问的Cookie

			out.println(c.getValue());
			out.println(c.getName());

		}
	}
}
