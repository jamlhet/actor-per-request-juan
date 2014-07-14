package co.s4n.template.client

import akka.actor.SupervisorStrategy.Restart
import akka.actor.{Actor, ActorLogging, OneForOneStrategy}
import akka.event.LoggingReceive
import co.s4n.template.{ResponseMessage, RestMessage}

/**
 * Created by juan on 12/07/14.
 */
class TemplateActorClient extends Actor with ActorLogging {
  def receive = LoggingReceive {
    case message: RestMessage =>
      log.info("RestMessage :D -> " + message)
      sender ! new ResponseMessage("Client OK")
    case _ =>
      log.info("default")
      sender ! "X"
  }

  override val supervisorStrategy = OneForOneStrategy() {
    case exception: Exception =>
      exception.printStackTrace()
      log.error(exception, exception.getMessage())
      Restart
  }

}
