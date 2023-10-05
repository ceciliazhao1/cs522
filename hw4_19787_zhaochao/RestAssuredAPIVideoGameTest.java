import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredAPIVideoGameTest {
    // get All VideoGame
    @Test(priority = 1)
    public void test_getAllVideoGames() {

        given()

                .when()
                .get("http://localhost:8080/app/videogames")
                .then()
                .statusCode(200);

        // Get all the headers and then iterate over allHeaders to print each header
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");
        Headers allHeaders = response.headers();
        // Iterate over all the Headers
        for (Header header : allHeaders) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }
    }
    // add a new VideoGame
    @Test(priority = 2)
    public void test_addNewVideoGame() {

        HashMap data = new HashMap();
        data.put("id", "11");
        data.put("name", "chaozhao 19787");
        data.put("releaseDate", "2023-10-05T07:54:39.518Z");
        data.put("reviewScore", "5");
        data.put("category", "Adventure");
        data.put("rating", "Universal");

        Response res =
                given()
                        .contentType("application/json")
                        .body(data)
                        .when()
                        .post("http://localhost:8080/app/videogames")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);
    }

    // get particular VideoGame
    @Test(priority = 3)
    public void test_getVideoGame() {
        given()

                .when()
                .get("http://localhost:8080/app/videogames/11")

                .then()
                .statusCode(200)
                .log().body()
                .body("videoGame.id", equalTo("11"))
                .body("videoGame.name", equalTo("chaozhao 19787"));
    }

    // update existing VideoGame
    @Test(priority = 4)
    public void test_updatedVideoGame() {
        HashMap data = new HashMap();
        data.put("id", "11");
        data.put("name", "cecilia zhao");
        data.put("releaseDate", "2023-10-05T07:54:39.518Z");
        data.put("reviewScore", "4");
        data.put("category", "Adventure");
        data.put("rating", "Universal");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("http://localhost:8080/app/videogames/11")

                .then()
                .statusCode(200)
                .log().body()
                .body("videoGame.id", equalTo("11"))
                .body("videoGame.name", equalTo("cecilia zhao"));
    }

    // delete a VideoGame
    @Test(priority = 5)
    public void test_DeleteVideoGame() {
        Response res =
                given()
                        .when()
                        .delete("http://localhost:8080/app/videogames/11")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();
        String jsonString = res.asString();
        Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);

    }

}