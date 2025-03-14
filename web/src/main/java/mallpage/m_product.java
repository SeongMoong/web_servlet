package mallpage;
//상품 리스트 Database연결 Model (select)

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.m_dbinfo;

public class m_product {
	//DTO(setter)
	dto_product pd = new dto_product(); //(DTO)
	ArrayList<ArrayList<String>> all = null;
	ArrayList<String> al = null; 
	
	//DB정보 및 연결
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null; //select값 사용할때 씀
	
	String sql = ""; //db 쿼리문
	m_dbinfo db = new m_dbinfo(); //db 접속 정보
	
	//상품하나의 정보만 가져오는 모델
	public void oneproduct(dto_product p) {  //Controller에서 보내준 DTO값을 받음
		//System.out.println(p.getMidx()); //dto_product pd = new dto_product();pd라는 객체명이 같으면안됨
		try {
			this.con = this.db.getConnection(); //Connection으로 db연결
			this.sql = "select * from mall_product where midx=?";
			this.ps = con.prepareStatement(this.sql);
			this.ps.setInt(1, p.getMidx());
			this.rs = this.ps.executeQuery();
			if(this.rs.next() == false) { //resultset은 false나 true
				this.pd.setMidx(0); //데이터가 없다 autoincrement는 0이없다.
			}
			else { //필드에 있는 DTO 모델에 setter로 DB값을 이관함
				//System.out.println(this.rs.getString("pnm"));
				this.pd.setMidx(this.rs.getInt("midx"));
				this.pd.setPcode(this.rs.getString("pcode"));
				this.pd.setPdate(this.rs.getString("pdate"));
				this.pd.setPimg(this.rs.getString("pimg"));;
				this.pd.setPmoney(this.rs.getString("pmoney"));
				this.pd.setPsale(this.rs.getString("psale"));
				this.pd.setPnm(this.rs.getString("pnm"));
				this.pd.setPsale(this.rs.getString("psale"));
				this.pd.setPsmoney(this.rs.getString("psmoney"));
			}
		}catch(Exception e) {
			this.pd.setMidx(0);
		}finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
			}catch(Exception e) {
				this.pd.setMidx(0);
			}
		}
	}
	
	//전체 리스트를 가져오는 모델 (SELECT)
	public ArrayList<ArrayList<String>> product_all (){
		
		try {
			this.con = this.db.getConnection(); //Connection으로 db연결
			this.sql = "select * from mall_product order by midx desc";
			this.ps = this.con.prepareStatement(sql);
			this.rs = ps.executeQuery();
			this.all = new ArrayList<ArrayList<String>>();
			while(this.rs.next()) {
				this.al = new ArrayList<String>();
				this.al.add(String.valueOf(this.rs.getInt("midx")));
				this.al.add(this.rs.getString("pcode"));
				this.al.add(this.rs.getString("pnm"));
				this.al.add(this.rs.getString("pmoney"));
				this.al.add(this.rs.getString("psale"));
				this.al.add(this.rs.getString("psmoney"));
				this.al.add(this.rs.getString("pimg"));
				this.al.add(this.rs.getString("pdate"));
				this.all.add(al);
			}
		}catch(Exception e) {
			this.all = null;
		}finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
			}catch(Exception e) {
				
			}
		}
		
		return this.all;
	}
}
