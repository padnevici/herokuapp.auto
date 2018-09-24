package com.herokuapp.auto.testDataUtils;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class RandomDataHelper {
    private Logger logger = LoggerFactory.getLogger(RandomDataHelper.class);

    private static Faker faker = new Faker();

    public static String getRandomDataFor(String forWhat) {
        switch (forWhat.toLowerCase()) {
            case "[random-fname]":
                return getRandomFirstname();
            case "[random-lname]":
                return getRandomLastname();
            case "[random-date]":
                return getRandomStartDate();
            case "[random-email]":
                return getRandomEmail();
        }
        return forWhat;
    }

    public static String getRandomFirstname() {
        return faker.name().firstName() + "-" + RandomStringUtils.randomAlphabetic(5);
    }

    public static String getRandomLastname() {
        return faker.name().lastName() + "-" + RandomStringUtils.randomAlphabetic(5);
    }

    public static String getRandomStartDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().plusMonths(faker.random().nextInt(2, 10)));
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }
}
