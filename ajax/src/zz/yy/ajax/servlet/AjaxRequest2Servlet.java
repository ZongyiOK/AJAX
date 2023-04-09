package zz.yy.ajax.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajaxrequest2")
public class AjaxRequest2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();//获取响应流
        //out.print("<font color='red'>this is a ajax request</font>");//响应，无论此处响应什么，如果前端以response对象接收，都是以字符串的形式接收的
        //获取ajax get请求提交的数据
        String usercode = request.getParameter("usercode");
        String username = request.getParameter("username");
        out.print("usercode="+usercode+",");
        out.print("username="+username);
    }
}
