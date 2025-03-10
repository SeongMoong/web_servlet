package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class paylist extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String[] product = request.getParameterValues("product");
        String totalprice = request.getParameter("totalprice");

        if (product == null) {
            product = new String[] {}; 
        }
        if (totalprice == null) {
            totalprice = "0";  
        }

        System.out.println("선택한 상품: " + Arrays.toString(product));
        System.out.println("총 결제 금액: " + totalprice);

        request.setAttribute("product", product);
        request.setAttribute("totalprice", totalprice);

        RequestDispatcher rd = request.getRequestDispatcher("./payokok.jsp");
        rd.forward(request, response);
    }
}
