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
			resp.addCookie(c);// ����cookie�ǻỰcookie ��sessionһ�� ��������Ĺرն��Զ���ʧ
			c = new Cookie("Persistent-cookie-name" + i, "Persistent-cookie-value" + i);
			c.setMaxAge(3600);
			resp.addCookie(c);// ����cookie���ᱣ����Ӳ��3600s ��һ��Сʱ

		}
		PrintWriter out = resp.getWriter();
		out.println("hello");
		System.out.println("hello");
	}

}
