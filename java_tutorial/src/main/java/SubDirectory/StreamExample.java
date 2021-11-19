package SubDirectory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    private class Staff {
        String name;
        BigDecimal salary;

        Staff(String name, BigDecimal salary) {
            this.name = name;
            this.salary = salary;
        }
    }

    private class StaffLot {
        String name;
        BigDecimal salary;
        int lotNumber;

        StaffLot(String name, BigDecimal salary, int lotNumber) {
            this.name = name;
            this.salary = salary;
            this.lotNumber = lotNumber;
        }
    }

    private Logger logger = Logger.getLogger(StreamExample.class.getName());

    private List<String> dayInWeek = Arrays.asList("senin", "selasa", "rabu", "kamis", "jumat", "sabtu", "minggu");
    private List<Staff> staffList = Arrays.asList(
            new Staff("mkyong", new BigDecimal(10000)),
            new Staff("jack", new BigDecimal(20000)),
            new Staff("lawrence", new BigDecimal(30000))
    );

    private void doForEach() {
        dayInWeek.forEach(logger::info);
    }

    private void doFilter() {
        List<String> resultList = dayInWeek.stream()
                .filter(str -> str.equals("senin") || str.equals("selasa"))
                .collect(Collectors.toList());
        resultList.forEach(logger::info);

        Optional<String> resultFindAnyStr = dayInWeek.stream()
                .filter(str -> str.length() < 7)
                .findAny();
//                .orElse(null);
        logger.info(resultFindAnyStr.get());

        Optional<String> resultFindFirstStr = dayInWeek.stream()
                .filter(str -> str.length() < 7)
                .findFirst();
        logger.info(resultFindFirstStr.get());
    }

    private void doMap() {
        List<StaffLot> staffLotList = staffList.stream()
                .map((staff) -> {
                    if (staff.salary.compareTo(BigDecimal.valueOf(10000)) <= 0) {
                        return new StaffLot(staff.name, staff.salary, 10);
                    } else if (staff.salary.compareTo(BigDecimal.valueOf(20000)) <= 0) {
                        return new StaffLot(staff.name, staff.salary, 20);
                    } else {
                        return new StaffLot(staff.name, staff.salary, 30);
                    }
                })
                .collect(Collectors.toList());
        staffLotList.forEach(staffLot -> logger.info(String.valueOf(staffLot.lotNumber)));
    }

    private void doReduce() {
        BigDecimal staffSalarySum = staffList.stream()
                .map(staff -> staff.salary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        logger.info(staffSalarySum.toString());
    }

    private void doFlatMap() {
        String[][] array = new String[][]{{"afk", "bamboo"}, {"chat", "deluxe"}, {"errata", "finish"}};
        Stream<String[]> stream1 = Arrays.stream(array);
        Stream<String[]> stream2 = Stream.of(array);

        stream1.flatMap(Stream::of)
                .collect(Collectors.toList())
                .forEach(logger::info);
    }

    private void doIterate() {
        Stream.iterate(0, x -> x + 2)
                .filter(x -> x % 3 != 0) //odd
                .limit(10)
                .forEach(val -> logger.info(val.toString()));
    }

    private void doParallelStream() {
        IntStream range1 = IntStream.rangeClosed(1, 10);
        range1.parallel().forEach(val -> logger.info(Thread.currentThread() + " - " + val));
    }

    public void doAll() {
        logger.info("Start");
        doForEach();
        doFilter();
        doMap();
        doReduce();
        doFlatMap();
        doIterate();
        doParallelStream();
    }
}
