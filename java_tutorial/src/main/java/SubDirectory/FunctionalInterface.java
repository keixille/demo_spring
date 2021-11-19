package SubDirectory;

import java.time.LocalDateTime;
import java.util.function.*;
import java.util.logging.Logger;

public class FunctionalInterface {

    private Logger logger = Logger.getLogger(FunctionalInterface.class.getName());

    private Function<String, Integer> chainFunction1 = String::length;
    private Function<Integer, Integer> chainFunction2 = val -> {
        if(val == 11) return 99;
        else return 101;
    };
    private BiFunction<Integer, Integer, Double> biFunction1 = Math::pow;

    private UnaryOperator<Integer> unary1 = val -> val * val;
    private BinaryOperator<Integer> binary1 = (x1, x2) -> x1 + (x1 * x2);

    private Predicate<String> predicate1 = name -> name.equals("Jackson");
    private BiPredicate<String, Integer> biPredicate1 = (name, len) -> name.length() == len;

    private Consumer<String> consumer1 = name -> {};
    private BiConsumer<String, Integer> biConsumer1 = (name, val) -> {};
    private Supplier<LocalDateTime> supplier1 = LocalDateTime::now;

    public void doFunction() {
        Integer chainResult = chainFunction1.andThen(chainFunction2).apply("sony tulung");
        Double biFunctionResult = biFunction1.apply(3, 4);
    }

    public void doUnaryOperator() {
        Integer unaryOperatorResult = unary1.apply(12);
        Integer binaryOperatorResult = binary1.apply(2, 3);
    }

    public void doPredicate() {
        Boolean predicateResult = predicate1.test("Jackson");          // true
        Boolean biPredicateResult = biPredicate1.test("asd", 3);    // true

        Predicate<String> isNameRoza = name -> name.equals("Roza");
        Predicate<String> isLengthEqual4 = name -> name.length() == 4;
        Predicate<String> isLengthEqual8 = name -> name.length() == 8;

        Predicate<String> andPredicate = isNameRoza.and(isLengthEqual4);
        Boolean andResult = andPredicate.test("Roza");

        Predicate<String> orPredicate = isNameRoza.or(isLengthEqual8);
        Boolean orResult = orPredicate.test("Roza");

        Predicate<String> negatePredicate = isNameRoza.negate();
        Boolean negateResult = negatePredicate.test("Roza");
    }

    public void doConsumerSupplier() {
        consumer1.accept("Eaten");
        biConsumer1.accept("EatenNumber", 12);
        LocalDateTime dateTime = supplier1.get();
    }

    public void doAll() {
        logger.info("Start");
        doFunction();
        doUnaryOperator();
        doPredicate();
        doConsumerSupplier();
    }
}
