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
    public static void main(String[] args) {
        mapFunctions();
    }
}
