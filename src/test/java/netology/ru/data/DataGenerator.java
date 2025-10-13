package netology.ru.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;


import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static final Faker FAKER = new Faker(new Locale("en"));

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private DataGenerator() {

    }

    public static void sendRegistrationRequestAndVerifyResponse(DataGenerator.RegistrationDto user) {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when().log().all() // "когда"
                .post("/api/system/users") // на какой путь относительно BaseUri отправляем запрос
                .then().log().all() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    public static String getRandomLogin() {
        return FAKER.name().username();
    }

    public static String getRandomPassword() {
        return FAKER.internet().password();
    }

    public static class Registration {
        private Registration() {
        }
        // Случайный пользователь
        public static RegistrationDto getUser(String status) {
            return new RegistrationDto(getRandomLogin(), getRandomPassword(), status);
        }

        // Зарегистрированный пользователь
        public static RegistrationDto getRegisteredUser(String status) {
            var user = getUser(status);
            sendRegistrationRequestAndVerifyResponse(user);
            return user;
        }
    }

    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }

    /*public static RegistrationInfo generateUser(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();
        sendRegistrationRequestAndVerifyResponse(new RegistrationInfo(login, password, "active"));
        return new RegistrationInfo(login, password, "active");
    }

    public static RegistrationInfo generateUserNoAuth(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();
        return new RegistrationInfo(login, password, "active");
    }

    public static RegistrationInfo generateBlockedUser(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();
        sendRegistrationRequestAndVerifyResponse(new RegistrationInfo(login, password, "blocked"));
        return new RegistrationInfo(login, password, "blocked");
    }

    public static RegistrationInfo generateInvalidLoginUser(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();
        sendRegistrationRequestAndVerifyResponse(new RegistrationInfo(login, password, "active"));
        return new RegistrationInfo(faker.name().username(), password, "active");
    }

    public static RegistrationInfo generateInvalidPasswordUser(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String login = faker.name().username();
        String password = faker.internet().password();
        sendRegistrationRequestAndVerifyResponse(new RegistrationInfo(login, password, "active"));
        return new RegistrationInfo(login, faker.internet().password(), "active");
    }*/



}
