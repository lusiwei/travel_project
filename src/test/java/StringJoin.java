import org.junit.Test;

import java.util.LinkedList;

/**
 * Created  by lusiwei on 2018/10/16
 */
public class StringJoin {
    @Test
    public void test(){
        LinkedList<String> linkedList=new LinkedList<>();
//        LinkedHashSet<String> linkedHashSet=new LinkedHashSet<>();
        linkedList.add("1");
        linkedList.add("3");
        linkedList.add("4");
        System.out.println(String.join("-", linkedList));
    }
}
