package User.createUser;

import dto.UserDTO;
import dto.UserResponseDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.*;

public class CreateUserTest extends UserBaseTest{

    @Test
    public void createUser() {

        UserDTO userDTO = UserDTO.builder()
            .id(100500L)
            .username("Frank")
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
            .body("message", equalTo("100500"))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));

//        ValidatableResponse response = userApi.createUser(userDTO);
//        UserResponseDTO actualUser = response.extract().body().as(UserResponseDTO.class);
//
//        Assertions.assertEquals(200, actualUser.getCode(), "Incorrect code");
//        Assertions.assertEquals("unknown", actualUser.getType(), "Incorrect type");
//        Assertions.assertEquals("100500", actualUser.getMessage(), "Incorrect message");

    }
}
