import com.jayway.restassured.RestAssured
import com.jayway.restassured.RestAssured._
import com.jayway.restassured.http.ContentType
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TemplateRoutingServiceTest extends FlatSpec {

  RestAssured.baseURI = "http://192.168.0.200"
  RestAssured.port = 38080

  "POST method " should " retrieve StatusCode 200 -> OK" in {
    val response =
      given()
        .contentType(ContentType.JSON)
        .expect()
        .statusCode(200)
        .when()
        .post("/templates")

    println(response.prettyPrint())
  }
}