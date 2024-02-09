package consumerTest.utils;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator {

    public static Faker faker = new Faker();

    public static String getRandomDataFor(RandomDataTypeName dataTypesNames) {

        switch (dataTypesNames) {
            case LOREM:
                return faker.lorem().word();
            case BRAND:
                return faker.company().name();
            case MODELNUMBER:
                return faker.regexify("[A-Z0-9]{5}-[A-Z0-9]{5}");
            default:
                return "Data type name not available";
        }
    }

        public static String getRandomAlphabets(int count){
            return RandomStringUtils.randomAlphabetic(count);
        }

        public static int getRandomNumber(int min, int max){
        return faker.number().numberBetween(min, max);
         }

        public static String getRandomNumber(int count){
            return faker.number().digits(count);
        }

    }

