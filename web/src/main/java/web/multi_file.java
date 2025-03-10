package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
//여러개의 파일을 전송하는 방식

@MultipartConfig( //첨부파일 기능이 있을 때 사용 //첨부파일 환경설정
		fileSizeThreshold = 1024 * 1024 *2, //한개의 파일 전송 크기 : 2MB
		maxFileSize = 1024* 1024 * 5,	//해당 max 파일 용량이 5MB이상 될 경우 Server Down 되어버림//파일 최대 크기 용량 : 5MB
		maxRequestSize = 1024 * 1024 * 100 //여러개의 첨부파일 전체의 용량 : 100MB
) 
public class multi_file extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PrintWriter pw = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			this.pw = response.getWriter();
			String mname = request.getParameter("mname");
			System.out.println(mname);
			String url = request.getServletContext().getRealPath("/upload/");
			//name을 따로 설정 하지않으며, 배열 클래스를 이용하여 IO값을 핸들링함
			//Collection : 모든 name 다 받을 수 있음
			Collection<Part> p = request.getParts(); //name=files 가져옴 //모든 name값을 가져옴 주의
			for(Part f : p) {
				long size = f.getSize();
				String filename = f.getSubmittedFileName();
				//if(!filename.equals("")&& size < 2097152) { //파일명이 있을 경우
				if(filename != null && size < 2097152) {
					f.write(url + filename); //해당 조건이 맞을 경우 저장
						
					}
				//System.out.println(f.getSubmittedFileName());
				}
			
		}catch(Exception e) {
			this.pw.write("<script>"
					+ "alert('올바른 값이 전달 되지 않았습니다.')"
					+ "history.go(-1)"
					+ "</script>");
		}finally {
			this.pw.close();
		}
	}

}
