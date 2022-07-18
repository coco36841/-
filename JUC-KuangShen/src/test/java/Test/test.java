package Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ClassName:test
 * Description:
 *
 * @Date:2022/6/7 15:13
 * @Author:cmt
 */
public class test {


    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("1","value1");
        map.put("2","value2");
        map.put("3","value3");
        map.put("4","value4");
        map.put("5","value5");
        map.put("6","value6");

        System.out.println("*********第一种遍历方式*********");
        for (String key : map.keySet()) {
            System.out.println("key: " + key + " value: " + map.get(key));
        }

        System.out.println("*********第三种遍历方式*********");
        for(Map.Entry<String, String> mp: map.entrySet()) {
            System.out.println("Key: "+ mp.getKey()+ " Value: "+mp.getValue());
        }

        System.out.println("*********第二种遍历方式*********");
        Iterator iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, String> mp = (Map.Entry<String, String>) iterator.next();
            System.out.println("Key: "+mp.getKey()+" Value: "+mp.getValue());
        }
    }
}
