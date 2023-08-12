package User.createUser;

import dto.UserDTO;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.*;
import services.UserApi;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {
    protected UserApi userApi = new UserApi();
    private final List<String> createdUsers = new ArrayList<>();

    @BeforeEach
    public void clearUser() {
        createdUsers.clear();
    }
    @AfterEach
    public void deleteUser() {
        createdUsers.forEach(username -> userApi.deleteUser(username).statusCode(200));
    }

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

        userApi.createUser(userDTO)
            .statusCode(200)
            .body("code", equalTo(200))
            .body("type", equalTo("unknown"))
            .body("message", equalTo("100500"))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));

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

        createdUsers.add(username);

    }
//  2. Тест на добавление юзера  кириллицей
    @Test
    public void createUserCyrillic() {

        UserDTO userDTO = UserDTO.builder()
            .id(3000L)
            .username("Франк")
            .lastName("Синатра")
            .firstName("Фрэнк")
            .email("frank123@yy.yy")
            .password("12345pass;%:")
            .phone("12345")
            .userStatus(1L)
            .build();

        userApi.createUser(userDTO)
            .statusCode(200)
            .body("code", equalTo(200))
            .body("type", equalTo("unknown"))
            .body("message", equalTo("3000"))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));

        String username = "Франк";

        userApi.getUser(username)
            .statusCode(200)
            .body("id", equalTo(3000))
            .body("username", equalTo("Франк"))
            .body("firstName", equalTo("Фрэнк"))
            .body("lastName", equalTo("Синатра"))
            .body("email", equalTo("frank123@yy.yy"))
            .body("password", equalTo("12345pass;%:"))
            .body("phone", equalTo("12345"))
            .body("userStatus", equalTo(1))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/GetUser.json"));

        createdUsers.add(username);
    }

//  3. Тест на добавление юзера пустые поля
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
