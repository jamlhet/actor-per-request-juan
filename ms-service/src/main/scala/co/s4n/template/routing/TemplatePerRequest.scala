package co.s4n.template.routing

import scala.concurrent.duration._

import akka.actor._
import akka.actor.SupervisorStrategy.Restart
import akka.event.LoggingReceive
import co.s4n.template.{ResponseMessage, RestMessage}
import co.s4n.template.routing.PerRequest.WithProps
import spray.http.{StatusCode, StatusCodes}
import spray.routing.RequestContext

trait PerRequest extends Actor with ActorLogging {

  import context._

  setReceiveTimeout(2.seconds)

  def r: RequestContext

  def target: ActorRef

  def message: RestMessage

  target ! message

  def receive = LoggingReceive {
    case message: ResponseMessage =>
      completeRequestContext(StatusCodes.OK, "****** OK")
    case _ =>
      completeRequestContext(StatusCodes.BadGateway, "****** Error")
  }

  override val supervisorStrategy = OneForOneStrategy() {
    case exception: Exception =>
      exception.printStackTrace()
      log.error(exception, exception.getMessage())
      Restart
  }

  def completeRequestContext(code: StatusCode, message: String) = {
    r.complete(code -> message)
    stop(self)
  }

}

object PerRequest {

  case class WithProps(r: RequestContext, props: Props, message: RestMessage) extends PerRequest {
    lazy val target = context.actorOf(props)
  }

}

trait TemplatePerRequest {
  this: Actor =>
  def perRequest(r: RequestContext, props: Props, message: RestMessage) = {
    context.actorOf(Props(new WithProps(r, props, message)))
  }
}