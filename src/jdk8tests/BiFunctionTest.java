package jdk8tests;
import java.util.function.BiFunction;
import java.util.function.Function;
/**
 * Created by ssiddiqu on 3/1/18.
 */

/**
 * @FunctionalInterface
public interface BiFunction<T,U,R>
 * default <V> BiFunction<T,U,V>
 *  andThen(Function<? super R,? extends V> after)
 Returns a composed function that first applies this function to its input, and then applies the after function to the result.
R	apply(T t, U u)
Applies this function to the given arguments.
 */
public class BiFunctionTest {
    public static void biFunctionTest() {
        BiFunction<String, String,String> bi = (x, y) -> {
            return x + y;
        };
        Function<String,String> f = x-> x+" new";

        System.out.println(bi.andThen(f).andThen(f).apply("java2s.com", " tutorial"));
    }
    public static void main(String[] args) {
        biFunctionTest();
    }
}
