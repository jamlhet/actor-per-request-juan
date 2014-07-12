package co.s4n.template

import spray.routing._
import akka.actor.{ Actor, Props }

trait Api extends RouteConcatenation with Core {
  import c.s4n.template.web.TemplateExampleService

  implicit val _ = system.dispatcher
  val routes = new TemplateExampleService( ).route
    
  val rootService = system.actorOf( Props( new RoutedHttpService( routes ) ) )
}

class RoutedHttpService( route: Route ) extends HttpServiceActor {
  def receive = runRoute( route )
}