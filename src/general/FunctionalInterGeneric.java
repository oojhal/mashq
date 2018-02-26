package general;

import java.util.Objects;

/**
 * Created by ssiddiqu on 2/19/18.
 */
public class FunctionalInterGeneric<M> {
    @FunctionalInterface
    public interface BiConsumer<T, U> {

        /**
         * Performs this operation on the given arguments.
         *
         * @param t the first input argument
         * @param u the second input argument
         */
        void accept(T t, U u);

        /**
         * Returns a composed {@code BiConsumer} that performs, in sequence, this
         * operation followed by the {@code after} operation. If performing either
         * operation throws an exception, it is relayed to the caller of the
         * composed operation.  If performing this operation throws an exception,
         * the {@code after} operation will not be performed.
         *
         * @param after the operation to perform after this operation
         * @return a composed {@code BiConsumer} that performs in sequence this
         * operation followed by the {@code after} operation
         * @throws NullPointerException if {@code after} is null
         */
        default BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> after) {
            Objects.requireNonNull(after);

            return (l, r) -> {
                accept(l, r);
                after.accept(l, r);
            };
        }
    }
        public static void main(String[] args) {
            BiConsumer printer = (s1, s2) -> System.out.println(s1.getClass() + " " + s2.getClass());
            printer.accept("Hello!","Functional Interface API");
        }
}
