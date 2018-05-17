import com.github.kevinsawicki.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.*;
import com.library.server.Constant;
import com.library.client.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Test
 */
public class Test {
    // HttpRequest request = HttpRequest.get("http://www.baidu.com");
    public static void main(String[] args) {
        Map map1 = new HashMap();
        map1.put("name", "12345");
        map1.put("sex", 0);
        map1.put("phone", "17300000010");
        map1.put("password", "123456");
        String resp1 = new HttpRequest("http://127.0.0.1:8080/user/add", HttpRequest.METHOD_POST).form(map1).connectTimeout(60000).readTimeout(60000).body();

        String resp2 = HttpRequest.get("http://127.0.0.1:8080/book/all").body();


        Map map2 = new HashMap();
        map2.put("keyword", "978-7-115-37799-2");
        map2.put("page", 1);
        String resp3 = new HttpRequest("http://localhost:8080/book/getfuzzy", HttpRequest.METHOD_POST).form(map2).body();

        System.out.println(resp1);
        System.out.println("\n\n" + resp2);
        System.out.println("\n\n" + resp3);

        JSONObject jsobj = new JSONObject(resp3);
        try {
            int status = jsobj.getInt(Constant.Status);
            System.out.println("\n\nstatus\n"+(status==Constant.HTTP_OK));
            JSONObject body = jsobj.getJSONObject(Constant.Body);
            System.out.println("\n\nbody\n"+body);
            JSONArray content = body.getJSONArray("content");
            System.out.println("\n\ncontent\n"+content.getJSONObject(0));
        } catch (JSONException e) {
            System.err.println("error"+e);
        }

        String s = URL.getURL(URL.HTTP_User, URL.User_Add);

        System.out.println(s);

                JFrame jf=new JFrame("Frame");
                JPanel jpanel=new JPanel();
                jpanel.setLayout(null);
                
                Container c=jf.getContentPane();
                //c.add(jpanel,BorderLayout.CENTER);
                jf.setLayout(null);
                c.add(jpanel);
                
                JButton bt1,bt2,bt3;
                bt1=new JButton("Button1");
                bt2=new JButton("Button2");
                bt3=new JButton("Button3");
                
                bt1.setBounds(100,100,80,20);
                bt2.setBounds(100,500,80,20);
                bt3.setBounds(100,900,80,20);
                
                jpanel.add(bt1);
                jpanel.add(bt2);
                jpanel.add(bt3);
                
                jpanel.setBackground(Color.blue);
                //jpanel.setLocation(70,70);
                
                jf.setBounds(0,0,800,600);
                
                jpanel.setBounds(50,0,750,600);
                    
                jf.setVisible(true);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }