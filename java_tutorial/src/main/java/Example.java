import SubDirectory.FunctionalInterface;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Example {
    private static FunctionalInterface functionalInterface;

    static {
        functionalInterface = new FunctionalInterface();
    }

    public static void main(String[] args) {
        functionalInterface.doAll();
        // Chain Function
//        System.out.println(function1.andThen(function2).apply("sony tulung"));
//        List<String> list = Arrays.asList("senin", "selasa", "rabu", "kamis", "jumat", "sabtu", "minggu");
//
//
//        // For Each
//        list.forEach(System.out::println);
//
//        // Filter
//        List<String> result = list.stream()
//                .filter(str -> str.equals("senin") || str.equals("selasa"))
//                .collect(Collectors.toList());
//        System.out.println(result);
//
//        String resultStr = list.stream()
//                .filter(str -> str.equals("kamis"))
//                .findAny()
//                .orElse(null);
//        System.out.println(resultStr);

        // Map
    }
}
