package notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class notice_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자가 페이지 번호를 클릭시 해당 값을 받는 역할
		String pageno = request.getParameter("pageno");
		
		if(pageno == null || pageno.equals("1")) { //Integer는 "ex"문자열을 알아먹지 못함 또한 .equals는 문자에서만 사용
			pageno = "0";	//최초 해당 게시판에 접속시 페이지 배열번호를 0으로 처리
		}
		
		//list를 출력하기 위한 Database Table을 로드하는 Model
		m_noticelist nl = new m_noticelist(Integer.parseInt(pageno));
		//2차 클래스 배열로 저장된 Table에 모든 데이터를 셋팅함 (m_noticelist에서 받아옴)
		ArrayList<ArrayList<String>> result = nl.db_data();
		//System.out.println(result);
		
		//JSP로 2차 클래스 배열 값을 전달(View)
		request.setAttribute("result", result);
		
		RequestDispatcher rd = request.getRequestDispatcher("./notice_list.jsp");
		rd.forward(request, response);
	}

}
