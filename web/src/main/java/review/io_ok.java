package review;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@MultipartConfig(
		fileSizeThreshold = 1024*1024*5
)


public class io_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
      String url = null; //웹 경로 저장
      String filenm = null; //파일명 저장
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * request.setCharacterEncoding("utf-8");
		 * response.setContentType("text/html;charset=utf-8"); try { Part mfile =
		 * request.getPart("mfile"); this.filenm = mfile.getSubmittedFileName();
		 * System.out.println(filenm);
		 * 
		 * 
		 * }catch(Exception e) { System.out.println(e); }
		 */
	}

}
