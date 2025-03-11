package ajax;

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
	fileSizeThreshold = 1024*1024*5,
	maxFileSize = 1024*1024*50,
	maxRequestSize = 1024*1024*100
)


@WebServlet("/ajax/ajax_fileok.do")
public class ajax_fileok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PrintWriter pw = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin","*"); //* 모든ip 도메인을 다받겠다~
		response.addHeader("Access-Control-Allow-Credentials","true");
		request.setCharacterEncoding("utf-8");
		
		this.pw = response.getWriter();
		
		Part mfile = request.getPart("mfile");	//전송된 파일 데이터를 받는 역할
		//System.out.println(mfile.getSubmittedFileName());
		String filenm = mfile.getSubmittedFileName(); 	//전송된 파일명
		
		String url = request.getServletContext().getRealPath("/upload/");
		mfile.write(url + filenm);
		//System.out.println(url);
		
		this.pw.print("ok");
	}

}
