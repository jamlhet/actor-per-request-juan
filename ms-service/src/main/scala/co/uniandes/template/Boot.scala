package co.uniandes.template

import akka.actor.{ActorSystem, Props}
import co.uniandes.template.core.RequerimientoActor
import co.uniandes.template.routing.TemplateRoutingService

object Boot extends App with HostBinding {

  import akka.io.IO
  import spray.can.Http

  import akka.io.IO
  import spray.can.Http

  /* Actor system */
  implicit val system = ActorSystem("ActorSystem-requerimiento")
  implicit val ec: scala.concurrent.ExecutionContext = system.dispatcher

  val routeActor = system.actorOf(Props(new TemplateRoutingService), "RouteActor")
  //val requerimientoActor = system.actorOf(Props[RequerimientoActor], "RequerimientoActor")

  /* Begin spray-can */
  IO(Http) ! Http.Bind(routeActor, interface = "0.0.0.0", port = portNumber(args))
}

trait HostBinding {

  def portNumber(args: Array[String]): Int =
    if (args.length != 0) args(0).toInt else 9100

}
