package admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10,
		maxFileSize = 1024 * 1024 * 50,
		maxRequestSize = 1024 * 1024 * 100
)


public class mobileok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset:utf-8");
		String se = request.getParameter("security"); //hidden값
		if(!se.equals("m")) { //hidden에 있는 값이 다를 경우
			PrintWriter pw = response.getWriter();
			pw.write("<script>"
					+ "alert('Error!!')"
					+ "history.go(-1)"
					+ "</script>");
		}else { //hidden이 맞을경우
			
			new reviews(request,response);//데이터를 핸들링함
		}
	}
}
class reviews{ //외부 클래스 사용시 한글 깨짐 처리는 메인 doPost, doGet에서 처리함
	PrintWriter pw =null;
	public reviews(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		this.pw = response.getWriter();
		this.pw.write("<script>"
				+ "alert('정상')"
				+ "</script");
		String mname = request.getParameter("mname");
		String pname = request.getParameter("pname");
		String star = request.getParameter("star");
		String mtext = request.getParameter("mtext");
		Part p = request.getPart("mfile");
		String filename = p.getSubmittedFileName();
		
		/*//review2디렉토리 생성
		String ori = request.getServletContext().getRealPath("/review2");
		File f = new File(ori);
		f.mkdir();
		*/
		
		if(filename != "") { //이미지 첨부 되었을 경우
			String url = request.getServletContext().getRealPath("/review/");
			p.write(url + filename);
		}
		this.pw.write("<script>"
				+ "alert('정상적으로 리뷰 등록');"
				+ "location.href='./mobile.html';"
				+ "</script>");
	}
}