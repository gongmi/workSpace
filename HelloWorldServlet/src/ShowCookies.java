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
		// �����ͻ����ϵ�ÿ��Cookie
		for (Cookie c : cookies) {
			// ���Cookie����Ϊusername��������Cookie��������Ҫ���ʵ�Cookie

			out.println(c.getValue());
			out.println(c.getName());

		}
	}
}
