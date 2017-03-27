package cn.edu.nju.dan;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.edu.nju.dan.algo.Algo;

@WebServlet("/getpath")
public class BOP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		JSONArray result;
		try {
			long input1 = Long.parseLong(request.getParameter("id1"));
			long input2 = Long.parseLong(request.getParameter("id2"));
			result = Algo.exec(input1, input2);

		} catch (Exception e1) {
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "error");
			errorInfo.put("msg", e1.getMessage());

			result = new JSONArray();
			result.put(errorInfo);
		}

		try {

			response.getWriter().write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
