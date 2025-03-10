package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.m_dbinfo;
import shop.m_md5;

public class notice_delete extends HttpServlet {
	//Database 게시물 삭제 처리 Controller
	private static final long serialVersionUID = 1L;
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    int result = 0;
    m_dbinfo db = new m_dbinfo();
    m_md5 md5 = new m_md5();
    PrintWriter pw = null;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String nidx = request.getParameter("nidx");
		String ori_pw = request.getParameter("ori_pw");
		String npw = request.getParameter("npw");
		this.pw = response.getWriter();
		try {
			//사용자가 입력한 패스워드랑 자동증가값이 올바르게 post전송이 안되면 접근 해제
			if(nidx.equals(null)||npw.equals(null)) {
				this.pw.write("<script>"
						+ "alert('올바른접근이 아닙니다');"
						+ "history.go(-1);"
						+ "</script>");
			}
			else {
				npw = md5.md5_code(npw); //db에 저장된 pw 와 비교, ori_pw와 비교하기위해
				if(npw.equals(ori_pw)) {
					this.con = this.db.getConnection();//조건이 맞는경우에 connection사용, 처음부터 걸어버리면 공격당함
					this.sql = "delete from notice where nidx=?";
					this.con.prepareStatement(sql);
					this.ps.setString(1, nidx);
					this.result = this.ps.executeUpdate();
					if(this.result>0) {
						this.pw.write("<script>"
								+ "alert('게시물 삭제');"
								+ "location.href='./notice/list.do';"
								+ "</script>");
					}
				}
				else {
					this.pw.write("<script>"
							+ "alert('패스워드가 틀렸습니다.');"
							+ "history.go(-1);"
							+ "</script>");
				}
			}
		}catch(Exception e) {
			this.pw.write("<script>"
					+ "alert('db삭제오류');"
					+ "history.go(-1);"
					+ "</script>");
		}finally {
			try {
				this.pw.close();
				this.con.close();
				this.pw.close();
			}catch(Exception e) {
				System.out.println("DB 접속에 따른 해제권한 오류 발생~");
			}
		}
	}

}
