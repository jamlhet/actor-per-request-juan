package co.s4n.template

import akka.actor.{ActorSystem, Props}
import c.s4n.template.web.TemplateRoutingService

object Boot extends App with HostBinding {

  import akka.io.IO
  import spray.can.Http

  IO(Http)(system) ! Http.Bind(rootService, interface, port = portNumber(args))
}

trait HostBinding {

  import java.net.{InetAddress, NetworkInterface}

import scala.collection.JavaConversions.enumerationAsScalaIterator

  val system = ActorSystem("ms-service-juan-executor")
  val rootService = ActorSystem("ms-service-juan-executor").actorOf(Props(new TemplateRoutingService()))
  val interface = machineIp()

  def portNumber(args: Array[String]): Int =
    if (args.length != 0) args(0).toInt else 38080

  private def machineIp(): String =
    NetworkInterface.getByName(s"eth0").getInetAddresses.map(matchIp).flatten.mkString

  private def matchIp(address: InetAddress): Option[String] =
    """\b(?:\d{1,3}\.){3}\d{1,3}\b""".r.findFirstIn(address.getHostAddress())
}
