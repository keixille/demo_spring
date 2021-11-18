import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Filter {
    private static Function<String, Integer> function1 = str -> str.length();
    private static Function<Integer, Integer> function2 = val -> {
        if(val == 11) return 99;
        else return 101;
    };
    private static BiFunction<Integer, Integer, Double> biFunction1 = (x1, x2) -> Math.pow(x1, x2);

    private static UnaryOperator<Integer> unary1 = val -> val * val;
    private static BinaryOperator<Integer> binary1 = (x1, x2) -> x1 + x2;

    private static Supplier<LocalDateTime> supplier = () -> LocalDateTime.now();

    public static void main(String args[]) {
        // Chain Function
        System.out.println(function1.andThen(function2).apply("sony tulung"));
        List<String> list = Arrays.asList("senin", "selasa", "rabu", "kamis", "jumat", "sabtu", "minggu");


        // For Each
        list.forEach(System.out::println);

        // Filter
        List<String> result = list.stream()
                .filter(str -> str.equals("senin") || str.equals("selasa"))
                .collect(Collectors.toList());
        System.out.println(result);

        String resultStr = list.stream()
                .filter(str -> str.equals("kamis"))
                .findAny()
                .orElse(null);
        System.out.println(resultStr);

        // Map
    }
}
