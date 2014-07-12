import org.junit.runner.RunWith
import org.scalatest.FunSuite
import com.jayway.restassured.RestAssured._
import com.jayway.restassured.matcher.RestAssuredMatchers._
import com.jayway.restassured.RestAssured
import com.jayway.restassured.http.ContentType
import org.hamcrest.Matchers._
import org.scalatest.junit.JUnitRunner

@RunWith( classOf[ JUnitRunner ] )
class TemplatePostTest extends FunSuite {

  RestAssured.baseURI = "http://192.168.0.200"
  RestAssured.port = 9400
  
  test( "POST" ) {
    val response =
      given( )
        .contentType( ContentType.JSON )
      .expect( )
        .statusCode( 201 )
      .when( ) 
        .post( "/templates" )
        
    println( response.prettyPrint( ) )
  }
}