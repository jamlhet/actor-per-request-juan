package co.s4n.template.aggregate

import akka.actor.{ Actor, Props, ActorLogging }

/**
 * The message to be sent between actors
 */
case class TemplateMessage( val id: String, val value: Int )

/**
 * Actor supervisor
 */
class TemplateSupervisor extends Actor with ActorLogging {
  import scala.concurrent.duration._
  import akka.actor.SupervisorStrategy._
  import akka.actor.OneForOneStrategy
  
  val templateActor = context.actorOf( Props[ TemplateActor ], "TemplateActor" )

  def receive = {
    case message: TemplateMessage => templateActor forward message
  }

  override val supervisorStrategy = OneForOneStrategy( ) {
    case exception: Exception =>
      exception.printStackTrace( )
      log.error( exception, exception.getMessage(  ) ) 
      Restart
  }

}

/**
 * TemplateActor
 */
class TemplateActor extends Actor with ActorLogging {
  import akka.pattern._
  import scala.concurrent.{ Future, ExecutionContext }
  
  implicit val _: ExecutionContext = context.dispatcher
  
  def receive = {
    case message: TemplateMessage => 
      log.debug( "The message: " + message )
      Future { 
        Thread.sleep( 10000 )
        "Hello World"
      } pipeTo sender
  }

}