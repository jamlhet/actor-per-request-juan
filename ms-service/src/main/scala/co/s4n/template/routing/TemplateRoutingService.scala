package c.s4n.template.web

import akka.actor.{Actor, ActorLogging, Props}
import akka.event.LoggingReceive
import co.s4n.template.RestMessage
import co.s4n.template.client.TemplateActorClient
import co.s4n.template.core.TemplateActorCore
import co.s4n.template.routing.TemplatePerRequest
import spray.routing.{Directives, HttpService, Route}

/**
 * TemplateRoutingService
 * @author S4N
 */
class TemplateRoutingService extends Directives with HttpService with Actor with TemplatePerRequest with ActorLogging {

  implicit def actorRefFactory = context

  val actorClient = context.actorOf(Props[TemplateActorClient], "TemplateActorClient")

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
      resolvePost {
        new RestMessage("Hello per request")
      }
    }
  }

  def resolvePost(message: RestMessage): Route =
    ctx => perRequest(ctx, Props(new TemplateActorCore(actorClient)), message)

}