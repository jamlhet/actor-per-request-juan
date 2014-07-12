package co.s4n.template

import akka.actor.{ Props, ActorSystem }
import co.s4n.template.aggregate.TemplateSupervisor

/**
 * Method override for the unique ActorSystem instance
 */
trait Core {
  implicit def system: ActorSystem
}

/**
 * Definition of the ActorSystem and the ExecutionContext
 */
trait BootedCore extends Core {
  import scala.concurrent.ExecutionContext

  implicit lazy val system = ActorSystem( "ms-service-executor" )
  implicit lazy val ex: ExecutionContext = system.dispatcher
  sys.addShutdownHook( system.shutdown( ) )
}

///**
// * Template project actors instantiation
// */
//trait CoreActors { this: Core =>
//  val templateSupervisor = system.actorOf( Props[ TemplateSupervisor ], "TemplateSupervisor" )
//}
//
///**
// * Template actor references
// */
//object TemplateActors extends BootedCore with CoreActors
