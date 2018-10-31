package com.buzz.test.core.optional.car;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        run();
    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public static Insurance findCheapestInsurance(Person person, Car car) {
        // just mock a test
        return null;
    }

    public static Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {

        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        }

        return Optional.empty();
    }

    public static void run() {
        Optional<Car> car = Optional.empty();
        System.out.println(car);

        Car benz = new Car();
        Optional<Car> carOptional = Optional.of(benz);
        carOptional = Optional.ofNullable(benz);
        System.out.println(carOptional.get());

        Insurance insurance = new Insurance();
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optionalInsurance.map(Insurance::getName);
        System.out.println(name.orElse("default"));

        Map<String, String> map = new HashMap<>();
        Optional<Object> value = Optional.ofNullable(map.get("key"));
        System.out.println(value.orElse("defaultValue"));
    }
}
