package c.s4n.template.web

import akka.actor.{Actor, ActorLogging, Props}
import akka.event.{LoggingReceive, Logging}
import co.s4n.template.RestMessage
import co.s4n.template.client.TemplateClient
import co.s4n.template.core.ResolvePostCore
import co.s4n.template.routing.TemplatePerRequest
import spray.routing.{Directives, HttpService, Route}

/**
 * TemplateRoutingService
 * @author S4N
 */
class TemplateRoutingService extends Directives with HttpService with Actor with TemplatePerRequest with ActorLogging {

  import akka.util.Timeout

import scala.concurrent.duration._

  implicit def actorRefFactory = context

  val logAkka = Logging(context.system, this)

  val actorClient = context.actorOf(Props[TemplateClient],"TemplateClient")
  log.info("actorClient path :D :" + actorClient.path)

  implicit val timeout = Timeout(120.seconds)

  def receive = LoggingReceive {
    log.info("JUST DO IT -> :D")
    runRoute(route)
  }

  val stringMessage = "Hello request"

  val route = path("templates") {
    delete {
      complete("Resource deleted")
    }
    put {
      complete("PUT executed")
    }
    options {
      complete("OPTIONS executed")
    }
    head {
      complete("HEAD executed")
    }
    get {
      complete("")
    }
    post {
      log.info("Hello per request :D")
      resolvePost{new RestMessage("Hello per request")}
    }
  }

  def resolvePost(message: RestMessage): Route =
    ctx => perRequest(ctx, Props(new ResolvePostCore(actorClient)), message)

}