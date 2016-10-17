package co.uniandes.template.routing

import akka.actor.{Actor, ActorLogging, Props}
import akka.event.LoggingReceive
import co.uniandes.template.core.RequerimientoActor
import spray.json.DefaultJsonProtocol
import spray.routing.{RequestContext, _}
import spray.util.LoggingContext

import scala.concurrent.ExecutionContext

/**
 * TemplateRoutingService
 * @author S4N
 */
class TemplateRoutingService extends Actor with requerimientoService with ActorLogging{
  def actorRefFactory = context

  def receive = LoggingReceive {
    runRoute({
        requerimientoRoute
      })(
      ExceptionHandler.default,
      RejectionHandler.Default,
      context,
      RoutingSettings.default,
      LoggingContext.fromActorRefFactory
    )
  }
}

trait requerimientoService  extends HttpService with Directives with DefaultJsonProtocol {
  import spray.httpx.SprayJsonSupport._

  implicit val requerimientoEC: ExecutionContext = actorRefFactory.dispatcher

  val requerimientoRoute = {
    pathPrefix("requerimiento"/"crear") {
      pathEndOrSingleSlash {
        post {
          ctx: RequestContext =>
            val requerimientoActor = actorRefFactory.actorOf(Props(new RequerimientoActor(ctx)))
            requerimientoActor ! "Hola Mundo"
        }
      }
    }
  }
}