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
		
		//�������� ��form�е�name�ĳ�����
		// �����map��������name
//		Map<String, String[]> m=req.getParameterMap();
//		System.out.println(m.get("gender"));
//		System.out.println(m.get("country"));
//		String[] s=m.get("name");
//		System.out.println(s[0]);
//		System.out.println(s[1]);
		
		// ���ֱ����getParameterValues��������name������ֵ
		
//		String[] s= req.getParameterValues("name");
//		System.out.println(s[0]);
//		System.out.println(s[1]);	
		req.setCharacterEncoding("GBK");
		resp.setCharacterEncoding("GBK");//������ȷ��ʾ���� Ϊʲô 
		pw.println(	req.getParameter("gender"));
		pw.println(	req.getParameter("country"));
		pw.println(	req.getParameter("name")); // ���ֱ�����������ֻ�ܶ���һ������ֵ
		System.out.println("get"); 
		
		
		//������servlet��jsp��ת�� ������form��jsp�ύ���� Ȼ����ת����jsp  ��jsp��ʾform�Ĳ���  ��Ϊ���õ�forword ����request���ֲ���
		//Ҳ���Բ���form�ύ���� ����url��ַд/test/Params?name=gongmi&country=china
		RequestDispatcher rd=req.getRequestDispatcher("/params.jsp");
		rd.forward(req, resp);
		//����������ʾ������params��jsp�е�������ʾ��������
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
		System.out.println("post");
		 
	}
	}


