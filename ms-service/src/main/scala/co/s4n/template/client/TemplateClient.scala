package co.s4n.template.client

import akka.actor.{Actor, ActorLogging}
import co.s4n.template.{ActorClientMessage, RestMessage}

/**
 * Created by juan on 12/07/14.
 */
class TemplateClient extends Actor with ActorLogging {
  def receive = {
    case RestMessage => log.info("Client OK")
  }
}
