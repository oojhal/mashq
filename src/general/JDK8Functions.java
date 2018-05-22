package general;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssiddiqu on 3/1/18.
 */
public class JDK8Functions {
    public static void mapFunctions() {
        Map<String,String> testMap = new HashMap<String,String>() {{
            put("a","one");
            put("b","two");
            put("e",null);
        }};
        testMap.compute("a",(k,v) -> v.concat("123"));
        System.out.println(testMap.compute("c",(k,v) -> "value of c"));
        System.out.println(testMap.compute("d",(k,v) -> null));
        System.out.println(testMap);

    }
    public static void testRegex() {
        System.out.println("some component here".replaceAll("\\bcom\\.[a-zA-Z|$|0-9|.]*|\\borg.[a-zA-Z|$|0-9|.]*",
            "xxx").trim());
        System.out.println("some com.oracle.what here".replaceAll("\\bcom\\.[a-zA-Z|$|0-9|.]*|\\borg.[a-zA-Z|$|0-9|.]*",
            "xxx").trim());
    }
    public static void main(String[] args) {
        testRegex();
        mapFunctions();
    }
}
