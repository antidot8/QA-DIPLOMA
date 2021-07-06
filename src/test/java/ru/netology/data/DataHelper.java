package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.Year;
import java.util.Locale;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class DataHelper {
    private static Faker faker = new Faker();
    private static Faker fakerRu = new Faker(new Locale("ru"));

    private static String approvedCard = "4444 4444 4444 4441";
    private static String declinedCard = "4444 4444 4444 4442";

    private static String generateMonth() {
        Random random = new Random();
        int num = random.nextInt(12) + 1;
        if (num < 10) {
            return String.format("%02d", num);
        } else {
            return String.valueOf(num);
        }
    }
        private static String generateYear() {
            Random random = new Random();
            int fullYear = Year.now().getValue();
            String stringFullYear = String.valueOf(fullYear).substring(2,4);
            int year = parseInt(stringFullYear, 10) + random.nextInt(4) + 1;
            return String.valueOf(year);
        }

//1.
        public static Card validCardData() {
            return new Card(approvedCard,
                    generateMonth(),
                    generateYear(),
                    faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                    faker.numerify("###"));
        }
//2.
        public static Card emptyFieldsCardData() {
            return new Card("",
                    "",
                    "",
                    "",
                    "");
        }


//3.
        public static Card nonexistentCardNumberCardData() {
            return new Card("3333444455558888",
                    generateMonth(),
                    generateYear(),
                    faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                    faker.numerify("###"));
        }
//4.
        public static Card shortCardNumberCardData() {
            return new Card("444444444444444",
                    generateMonth(),
                    generateYear(),
                    faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                    faker.numerify("###"));
        }
//5.
        public static Card nonexistentMonthCardData() {
            return new Card(approvedCard,
                    "20",
                    generateYear(),
                    faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                    faker.numerify("###"));
        }
 //6.
        public static Card expiredCardData() {
            return new Card(approvedCard,
             generateMonth(),
             "19",
             faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
             faker.numerify("###"));
 }
//7.
        public static Card specialCharactersInCardholderData() {
            return new Card(approvedCard,
                    generateMonth(),
                    generateYear(),
                    faker.name().firstName().toUpperCase() + " " + "~!@#$%^&*()_-+=,./?><|" + " " + faker.name().lastName().toUpperCase(),
                    faker.numerify("###"));
        }
//8.
        public static Card numbersInCardholderData() {
            return new Card(approvedCard,
                    generateMonth(),
                    generateYear(),
                    faker.name().firstName().toUpperCase() + "1122334455" + " " + faker.name().lastName().toUpperCase() + "67890",
                    faker.numerify("###"));
        }
//9.
        public static Card oneNumberCVVCardData(){
            return new Card(approvedCard,
                    generateMonth(),
                    generateYear(),
                    faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                    faker.numerify("#"));
        }
//10.
        public static Card twoNumbersCVVCardData(){
            return new Card(approvedCard,
                    generateMonth(),
                    generateYear(),
                    faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(),
                    faker.numerify("##"));
        }
    }