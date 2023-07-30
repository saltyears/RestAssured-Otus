package User.getUser;

import User.UserBaseTest;
import dto.GetUserDTO;
import dto.UserDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetUserTest extends UserBaseTest {

//  1. Поиск юзера, созданного ранее
    @Test
    public void getUser() {

        String username = "Frank";

        userApi.getUser(username)
            .statusCode(200)
            .body("id", equalTo(100500))
            .body("username", equalTo("Frank"))
            .body("firstName", equalTo("Frank"))
            .body("lastName", equalTo("Frank"))
            .body("email", equalTo("frank@yy.yy"))
            .body("password", equalTo("12345"))
            .body("phone", equalTo("12345"))
            .body("userStatus", equalTo(1))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/GetUser.json"));
    }


//  2. Тест на поиск с невалидным значением. Негативный результат
    @Test
    public void getUserNegative() {
    String username = "Frankle";

    userApi.getUser(username)
        .statusCode(404)
        .body("code", equalTo(1))
        .body("type", equalTo("error"))
        .body("message", equalTo("User not found"))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));
}

//  3. Поиск юзера, созданного ранее
    @Test
    public void getUser3() {

        String username = "Frank3";

        userApi.getUser(username)
            .statusCode(200)
            .body("id", equalTo(3000))
            .body("username", equalTo("Frank3"))
            .body("firstName", equalTo("FrankFirstName"))
            .body("lastName", equalTo("FrankLastName"))
            .body("email", equalTo("frank@yy.yy"))
            .body("password", equalTo("12345pass"))
            .body("phone", equalTo("12345"))
            .body("userStatus", equalTo(1))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/GetUser.json"));
    }

}
