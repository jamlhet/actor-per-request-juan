package co.s4n.template.core

import akka.actor.Actor.Receive
import akka.actor.{ActorLogging, Actor, ActorRef}
import co.s4n.template.{ActorClientMessage, RestMessage}

/**
 * Created by juan on 12/07/14.
 */
class ResolvePostCore( actorClient: ActorRef ) extends Actor with ActorLogging{
  def receive = {
    case RestMessage => actorClient !  new RestMessage("Hello client")
  }
}
