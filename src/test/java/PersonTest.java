import java.util.*;
import java.util.stream.*;

import net.jqwik.api.*;
import org.assertj.core.api.Assertions;

class PersonTest {
    @Property
    void validPeopleHaveIDs(@ForAll("validPeople") Person aPerson) {
        Assertions.assertThat(aPerson.getID()).contains("-");
    }

    @Provide
    Arbitrary<Person> validPeople() {
        Arbitrary<String> names = Arbitraries.strings().withCharRange('a', 'z')
                .ofMinLength(3).ofMaxLength(15);
        Arbitrary<Integer> ages = Arbitraries.integers().between(0, 130);
        return Combinators.combine(names, ages)
                .as((name, age) -> new Person(name, age));
    }


    class Pet {
        private final Person owner;
        public Pet(Person owner) {
            this.owner = owner;
        }
    }
    class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getID() {
            return name + "-" + age;
        }

        @Override
        public String toString() {
            return String.format("%s:%s", name, age);
        }
    }
}
