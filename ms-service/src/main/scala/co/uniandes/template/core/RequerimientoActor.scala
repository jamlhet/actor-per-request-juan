package co.uniandes.template.core

import akka.actor.Actor
import akka.actor.Actor.Receive
import akka.event.LoggingReceive
import org.slf4j.LoggerFactory
import spray.http.StatusCodes._
import spray.routing.RequestContext

/**
  * Created by juan.rubiano on 17/10/2016.
  */
class RequerimientoActor(requestContext: RequestContext) extends Actor {

  private val logger = LoggerFactory.getLogger(classOf[RequerimientoActor])

  def receive = requerimientoReceive()

  def requerimientoReceive(): Receive = {
    case message: String =>
      logger.info("Llega petición")
      requestContext.complete(OK -> s"$message Lina")
      context.stop(self)
    case _ =>
      logger.info("Llega otra petición")
      requestContext.complete(OK -> s"${OK.reason} Marcela")
      context.stop(self)
  }
}
