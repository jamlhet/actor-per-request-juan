package c.s4n.template.web

import spray.json.DefaultJsonProtocol
import spray.routing.Directives

/**
 * TemplateExampleService
 * @author S4N
 */
class TemplateExampleService extends Directives with DefaultJsonProtocol {
  import akka.util.Timeout
  import spray.http._

import scala.concurrent.duration._

  implicit val timeout = Timeout( 120.seconds )

  val route = path( "templates" ) {
    delete {
      complete( "Resource deleted" )
    }
    put {
      complete( "PUT executed" )
    }
    options {
      complete( "OPTIONS executed" )
    }
    head {
      complete( "HEAD executed" )
    }
    get {
      complete( "" )
    }
    post {
      complete(StatusCodes.Created -> "POST OK")
//      detach( ) {
//        val response = ( TemplateActors.templateSupervisor ? new TemplateMessage( "hello, akka world", 1 ) )
//        onSuccess( response ) {
//          case str: String => complete( StatusCodes.Created -> "POST executed " )
//        }
//      }
    }
  }

}