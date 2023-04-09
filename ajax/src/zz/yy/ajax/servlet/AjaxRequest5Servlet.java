package zz.yy.ajax.servlet;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zz.yy.ajax.beans.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//发送ajax请求，动态展示学员列表
@WebServlet("/ajaxrequest5")
public class AjaxRequest5Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        //连接数据库，查询学员信息，拼接html代码，响应到浏览器
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //连接数据库，查询学员信息，拼接html代码
        //响应到浏览器
//        StringBuilder html = new StringBuilder();
//
//        //目前存在的缺点：在后端的java代码中拼接了html代码，这样不好维护
//        //能不能直接将数据返回，给WEB前段数据就行了，让WEB前段能够拿到数据就行，然后页面展示的功能交给WEB前段来处理
//        //后端能不能只返回数据？可以，返回数据可以采用JSON的格式，也可以采用XML的格式
//        html.append("<tr>");
//        html.append("<td>1</td>");
//        html.append("<td>张三</td>");
//        html.append("<td>20</td>");
//        html.append("<td>北京</td>");
//        html.append("</tr>");
//
//        html.append("<tr>");
//        html.append("<td>2</td>");
//        html.append("<td>李四</td>");
//        html.append("<td>22</td>");
//        html.append("<td>南京</td>");
//        html.append("</tr>");
//
//        out.print(html.toString());

        //将以上拼接html程序，换成拼接JSON格式的字符串
        //String jsonStr = "[{\"name\":\"张三\",\"age\":20,\"addr\":\"北京\"},{\"name\":\"李四\",\"age\":22,\"addr\":\"南京\"}]";


        //准备StringBuilder对象，用以json字符串拼接
        //StringBuilder json = new StringBuilder();
        String jsonStr = "";
        //连接数据库，查询所有的学生，拼接json字符串
        //连接数据库，验证用户名是否存在
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
            //3.获取预编译的数据库操作对象
            String sql = "select name,age,addr from t_student_ajax";
            ps = conn.prepareStatement(sql);
            //4.执行sql语句
            rs = ps.executeQuery();

            //先拼接“[”
            /*json.append("[");
            //5.处理结果集
            while(rs.next()) {
                //获取每个学生的信息
                String name = rs.getString("name");
                String age = rs.getString("age");
                String addr = rs.getString("addr");
                //拼接json格式的字符串:{"name":"   张三  ","age":   20   ,"addr":"   北京  "},
                json.append("{\"name\":\"");
                json.append(name);
                json.append("\",\"age\":");
                json.append(age);
                json.append(",\"addr\":\"");
                json.append(addr);
                json.append("\"},");
            }
            //去掉最后一个逗号并加上右中括号,获得最终的json格式的数据
            jsonStr = json.substring(0,json.length()-1)+"]";*/

            List<Student> studentList = new ArrayList<>();
            while (rs.next()){
                //取数据
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String addr = rs.getString("addr");
                //将以上数据封装成Student对象
                Student s = new Student(name,age,addr);
                //将Student对象了放到List集合当中
                studentList.add(s);
            }

            //将List集合转换成JSON字符创
            jsonStr = JSON.toJSONString(studentList);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //6.释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //响应JSON格式的字符串给前端
        out.print(jsonStr);
    }
}
