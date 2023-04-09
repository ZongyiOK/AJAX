package zz.yy.ajax.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajaxrequest6")
public class AjaxRequest6Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //注意响应的内容类型是xml
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();

        /*
        <students>
            <student>
                <name>zhangsan</name>
                <age>20</age>
            </student>

            <student>
                <name>lisi</name>
                <age>22</age>
            </student>
        </students>
        * */
        StringBuilder xml = new StringBuilder();
        xml.append("<students>");
        xml.append("<student>");
        xml.append("<name>zhangsan</name>");
        xml.append("<age>20</age>");
        xml.append("</student>");
        xml.append("<student>");
        xml.append("<name>lisi</name>");
        xml.append("<age>22</age>");
        xml.append("</student>");
        xml.append("</students>");

        out.print(xml);
    }
}
