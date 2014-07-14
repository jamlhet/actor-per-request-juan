package co.s4n.template.core

import akka.actor.{Actor, ActorLogging, ActorRef, OneForOneStrategy}
import akka.actor.SupervisorStrategy.Restart
import akka.event.LoggingReceive
import co.s4n.template.{ResponseMessage, RestMessage}

/**
 *
 * @param actorClient
 */
class TemplateActorCore(actorClient: ActorRef) extends Actor with ActorLogging {

  def receive = LoggingReceive {
    case message: RestMessage =>
      actorClient ! message
      context.become(waitResponse)
  }

  def waitResponse: Receive = {
    case message: ResponseMessage =>
      context.parent ! message
  }

  override val supervisorStrategy = OneForOneStrategy() {
    case exception: Exception =>
      exception.printStackTrace()
      log.error(exception, exception.getMessage())
      Restart
  }

}
