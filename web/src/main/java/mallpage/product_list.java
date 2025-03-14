package mallpage;
//controller
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mallpage/product_list.do")
public class product_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //DTO 값 가져와야함
	dto_product pd = new dto_product();  //void 메소드 일 경우 Controller에서 가져가는 객체
	//select Model
	m_product mp = new m_product();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//request.getParaeter("midx")는 String값이고 midx는 int값인데 
		//Integer midx= Integer valudOf(request.getParameter("midx"));
		//상품을 한가지를 클릭했을 경우 Front-end에서 GET 전송값
		String midx= request.getParameter("midx");
		String pagename =""; //하나의 컨트롤 => 두개의 View호출
		
		if(midx == null) { //상품 전체 리스트
		ArrayList<ArrayList<String>> all = this.mp.product_all();
		request.setAttribute("all", all);
		
		//jsp로드하여 View역할로 데이터 출력
		//원래 RequestDispatcher을 여러번 사용하지 않음 swich문 사용해서 spa 이용
		pagename ="./product_list.jsp";
		}
		else { //하나의 상품 리스트
		this.pd.setMidx(Integer.parseInt(midx)); //프론트에서 받은 값을 DTO로 전달
		this.mp.oneproduct(this.pd); //DTO의 객체를 모델로 전달 (실행값)
		
		this.pd = this.mp.pd; //Controller에서 model에 있는 DTO 객체 가져오기
		//System.out.println(this.pd.getPnm());
		
		//DTO model을 JSP 전달하기 위해 Attribute
		request.setAttribute("dto", this.pd);
		
		pagename = "./product_view.jsp";
		}
		//RequestDispatcher rd = request.getRequestDispatcher("./product_xxx.jsp");
		RequestDispatcher rd = request.getRequestDispatcher(pagename);
		rd.forward(request, response);
	}

}
