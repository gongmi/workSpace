import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowSession extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {

		HttpSession s = req.getSession(true);

		PrintWriter out = resp.getWriter();
		out.println(s.isNew());
		out.println(s.getId());
		out.println(new Date(s.getCreationTime()));
		out.println(new Date(s.getLastAccessedTime()));
		
		
		Integer n = (Integer) s.getAttribute("haha");// 因为session的值是object类型的
		if (n == null) {
			n = new Integer(0);
			out.println("hello");
		} else {
			n = n+1;
			out.println("SB");
		}
		s.setAttribute("haha", n);
		out.println(n);

	}
}
