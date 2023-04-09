package zz.yy.ajax.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajaxrequest1")
public class AjaxRequest1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //向浏览器响应一段数据
        PrintWriter out = response.getWriter();
        //out对象向浏览器输出信息
        //浏览器的XMLHttpRequest对象会接收到这个响应信息
        out.print("<font color='red'>welcome to ajax!!!</font>");
    }
}
