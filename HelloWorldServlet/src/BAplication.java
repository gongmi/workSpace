import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BAplication extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {

		ServletContext app = getServletConfig().getServletContext(); // 这样来拿到application

		PrintWriter out = resp.getWriter();

		Integer n = (Integer) app.getAttribute("haha");
		if (n == null) {
			n = 0;
			out.println("hello");
		} else {
			n = n+1;
			out.println("SB");
		}
		app.setAttribute("haha", n);
		out.println(n);

	}
}
