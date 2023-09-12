package services;

import dto.GetUserDTO;
import dto.UserDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = System.getProperty("base.url", "https://petstore.swagger.io/v2");
    private static final String BASE_PATH = "/user";
    private RequestSpecification spec;



    public UserApi(){
        spec = given()
            .baseUri(BASE_URL)
            .basePath(BASE_PATH)
            .contentType(ContentType.JSON)
            .log().all();
    }

    public ValidatableResponse createUser (UserDTO userDTO){

        return given(spec)
                    .body(userDTO)
                    .log().all()
               .when()
                    .post()
               .then()
               .log().all();
    }
    public ValidatableResponse getUser(String username) {
        return given(spec)
            .pathParam("username", username)
            .log().all()
            .when()
            .get("/{username}")
            .then()
            .log().all();
    }

    public ValidatableResponse deleteUser (String username) {

        return given(spec)
            .pathParam("username", username)
            .log().all()
            .when()
            .delete("/{username}")
            .then()
            .log().all();
    }
}
