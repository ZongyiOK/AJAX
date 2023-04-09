package zz.yy.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {
    public static void main(String[] args) {
        User user = new User("111","张三",20);

        //使用fastjson将以上的User对象转换成json格式的字符串
        //fastjson中有一个类，叫做JSON，全部大写
        //JSON类中的方法都是静态的方法，。直接调用即可
        String jsonStr = JSON.toJSONString(user);
        System.out.println(jsonStr);

        //尝试list集合是否可用转换成数组
        List<User> userList = new ArrayList<>();
        User user1 = new User("120","李四",30);
        User user2 = new User("130","jackson",36);
        userList.add(user1);
        userList.add(user2);
        String jsonStr2 = JSON.toJSONString(userList);
        System.out.println(jsonStr2);
    }
}
