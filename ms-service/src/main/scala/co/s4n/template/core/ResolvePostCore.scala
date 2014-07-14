package co.s4n.template.core

import akka.actor.{Actor, ActorLogging, ActorRef, OneForOneStrategy}
import akka.actor.SupervisorStrategy.Restart
import akka.event.LoggingReceive
import co.s4n.template.{ResponseMessage, RestMessage}

/**
 *
 * @param actorClient
 */
class ResolvePostCore(actorClient: ActorRef) extends Actor with ActorLogging {
  println("In class ResolvePostCore")

  def receive = LoggingReceive {
    case message: RestMessage =>
      log.info("RestMessage :D -> " + message.toString)
      actorClient ! message
      context.become(waitResponse)
  }

  def waitResponse: Receive = {
    case message: ResponseMessage =>
      log.info("ResponseMessage :D -> " + message)
      context.parent ! message
  }

  override val supervisorStrategy = OneForOneStrategy() {
    case exception: Exception =>
      exception.printStackTrace()
      log.error(exception, exception.getMessage())
      Restart
  }

}
