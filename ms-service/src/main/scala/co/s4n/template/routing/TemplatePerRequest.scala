package co.s4n.template.routing

import akka.actor._
import co.s4n.template.RestMessage
import co.s4n.template.routing.PerRequest.WithProps
import spray.http.StatusCodes
import spray.routing.RequestContext

trait PerRequest extends Actor with ActorLogging {

  def r: RequestContext

  def target: ActorRef

  def message: RestMessage

  target ! message

  def receive = {
    case RestMessage =>
      r.complete(StatusCodes.OK)
      log.info("************Ok")
    case _ =>
      r.complete(StatusCodes.NotAcceptable)
      log.error("************Error")
  }

}

object PerRequest {

  case class WithProps(r: RequestContext, props: Props, message: RestMessage) extends PerRequest {
    lazy val target = context.actorOf(props)
  }

}

trait TemplatePerRequest {
  this: Actor =>
  def perRequest(r: RequestContext, props: Props, message: RestMessage) =
    context.actorOf(Props(new WithProps(r, props, message)))
}