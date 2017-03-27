import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SSession extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {

		HttpSession s = req.getSession(true);

		PrintWriter out = resp.getWriter();
		out.println(s.isNew());
		out.println(s.getId());
		out.println(new Date(s.getCreationTime()));
		out.println(new Date(s.getLastAccessedTime()));

		out.println(req.getRequestedSessionId());
		out.println(req.isRequestedSessionIdFromCookie());
		out.println(req.isRequestedSessionIdFromURL());
		out.println(req.isRequestedSessionIdValid());
	}
}
