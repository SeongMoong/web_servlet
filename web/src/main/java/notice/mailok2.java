package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.m_dbinfo;

public class mailok2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con = null;
    PreparedStatement ps =null;
    PrintWriter pw = null;
	ResultSet rs = null; //select로 가져오기 위함
	String sql; //SQL Query
    m_dbinfo db = new m_dbinfo();
	ArrayList<String> data = null; //데이터를 배열로 가져옴, 각 컬럼별 값을 저장
	ArrayList<ArrayList<String>> alldata = null; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.con = db.getConnection();
			this.sql = "select nidx, to_name, to_mail, subject, context , ndate from work";
			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery(); //select
			this.alldata = new ArrayList<ArrayList<String>>();
			while(this.rs.next()) {
				this.data = new ArrayList<String>();
				this.data.add(this.rs.getString("nidx"));
				this.data.add(this.rs.getString("to_name"));
				this.data.add(this.rs.getString("to_mail"));
				this.data.add(this.rs.getString("subject"));
				this.data.add(this.rs.getString("context"));
				this.data.add(this.rs.getString("ndate"));
				this.alldata.add(this.data);
			}
			
		//m_noticelist nl = new m_noticelist();
		ArrayList<ArrayList<String>> result = this.alldata;
		System.out.println(result);
		
		request.setAttribute("result", result);
		
		RequestDispatcher rd = request.getRequestDispatcher("./milkok.jsp");
		rd.forward(request, response);
		}
		
		catch(Exception e) {

		}finally {
			try {
				this.ps.close();
				this.con.close();
				
			}catch(Exception e) {
				
			}
		}
	}

}
