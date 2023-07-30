package User.createUser;

import User.UserBaseTest;
import dto.UserDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends UserBaseTest {

//  1. Тест на добавление юзера
    @Test
    public void createUser() {

        UserDTO userDTO = UserDTO.builder()
            .id(100500L)
            .username("Frank")
            .lastName("Frank")
            .firstName("Frank")
            .email("frank@yy.yy")
            .password("12345")
            .phone("12345")
            .userStatus(1L)
                .build();
        userApi.createUser(userDTO);

        userApi.createUser(userDTO)
            .statusCode(200)
            .body("code", equalTo(200))
            .body("type", equalTo("unknown"))
            .body("message", equalTo("100500"))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));

    }
//  1. Тест на добавление юзера
    @Test
    public void createUser2() {

        UserDTO userDTO = UserDTO.builder()
            .id(3000L)
            .username("Frank3")
            .lastName("FrankLastName")
            .firstName("FrankFirstName")
            .email("frank@yy.yy")
            .password("12345pass")
            .phone("12345")
            .userStatus(1L)
            .build();
        userApi.createUser(userDTO);

        userApi.createUser(userDTO)
            .statusCode(200)
            .body("code", equalTo(200))
            .body("type", equalTo("unknown"))
            .body("message", equalTo("3000"))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));

    }

//  2. Тест на добавление юзера пустые поля
    @Test
    public void createUserEmpty() {

        UserDTO userDTO = UserDTO.builder()
            .id(10L)
            .username("")
            .lastName("")
            .firstName("")
            .email("")
            .password("")
            .phone("")
            .userStatus(100L)
            .build();
        userApi.createUser(userDTO);

        userApi.createUser(userDTO)
            .statusCode(200)
            .body("code", equalTo(200))
            .body("type", equalTo("unknown"))
            .body("message", equalTo("10"))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));

    }
}
