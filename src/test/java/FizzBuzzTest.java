import java.util.*;
import java.util.stream.*;

import net.jqwik.api.*;

class FizzBuzzTest {
    @Property
    boolean every_third_element_starts_with_Fizz(@ForAll("divisibleBy3") int i) {
        return fizzBuzz().get(i - 1).startsWith("Fizz");
    }

    @Property
    boolean any_element_less_than_or_equal_to_two_returns_itself_stringified(@ForAll("eitherOneOrTwo") int i) {
        return fizzBuzz().get(i - 1).equals(String.valueOf(i));
    }

    @Provide
    Arbitrary<Integer> divisibleBy3() {
        return Arbitraries.integers().between(1, 100).filter(i -> i % 3 == 0);
    }

    @Provide
    Arbitrary<Integer> eitherOneOrTwo() {
        return Arbitraries.integers().between(1, 2);
    }

    private List<String> fizzBuzz() {
        return IntStream.range(1, 100).mapToObj((int i) -> {
            boolean divBy3 = i % 3 == 0;
            boolean divBy5 = i % 5 == 0;

            return divBy3 && divBy5 ? "FizzBuzz"
                    : divBy3 ? "Fizz"
                    : divBy5 ? "Buzz"
                    : String.valueOf(i);
        }).collect(Collectors.toList());
    }
}