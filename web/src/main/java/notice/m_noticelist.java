package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.m_dbinfo;

//DB에 있는 모든 데이터를 가져오는 역할 (Model)
public class m_noticelist {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null; //select로 가져오기 위함
	String sql; //SQL Query
	
	m_dbinfo db = new m_dbinfo();
	
	ArrayList<String> data = null; //데이터를 배열로 가져옴, 각 컬럼별 값을 저장
	ArrayList<ArrayList<String>> alldata = null; //Database전체 데이터를 저장, 그룹별로 가져오기 위해 2차배열 사용
	
	int spage = 0; //첫번째 노드 (배열값)
	int ea = 3; //한페이지당 계시물을 3개씩 출력
	
	public m_noticelist(int s) { //게시판 밑에 번호 로직
		if(s>0) { //1번 페이지 번호 외에 번호를 클릭 했을 경우
			//(페이지번호 - 1) * 한 페이지당 출력할 갯수
			this.spage = (s - 1) * ea ;
		}
		else {
			this.spage = s; //spl쿼리문 limit을 사용하기 위한 값			
		}
	}
	
	public ArrayList<ArrayList<String>> db_data(){
		
		try {
			this.con = db.getConnection();
			this.sql = "select nidx, subject, writer, nview, ndate, (select count(*) from notice) as total from notice " 
					+ "order by nidx desc limit ?,?"; //띄어쓰기 안하면 noticeorder로 인식
			//this.sql = "SELECT nidx, subject, writer, nview, ndate, "
			//         + "(SELECT COUNT(*) FROM notice) AS total "
			//         + "FROM notice ORDER BY nidx DESC LIMIT ?, ?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setInt(1, this.spage); // 첫 번째 ?에 적용
			this.ps.setInt(2, this.ea); 
			this.rs = this.ps.executeQuery(); //select
			
			//반복문으로 2차 배열 만드는 과정
			this.alldata = new ArrayList<ArrayList<String>>();
			while(this.rs.next()) {
				this.data = new ArrayList<String>();
				this.data.add(this.rs.getString("nidx"));
				this.data.add(this.rs.getString("subject"));
				this.data.add(this.rs.getString("writer"));
				this.data.add(this.rs.getString("nview"));
				this.data.add(this.rs.getString("ndate"));
				this.data.add(this.rs.getString("total")); //게시물 전체 계수를 저장한 배열값
				this.alldata.add(this.data);
			}

		}catch(Exception e) {
			this.alldata = null;
		}finally {
			try {
				this.ps.close();
				this.con.close();
				
			}catch(Exception e) {
				this.alldata = null;
			}
		
		}
		//Model에서 Controller로 데이터를 회신함
		return this.alldata;
	}
}
