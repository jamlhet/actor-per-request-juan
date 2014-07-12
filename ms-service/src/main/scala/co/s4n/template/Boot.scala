package co.s4n.template

object Boot extends App with Api with BootedCore with HostBinding  {
  import akka.io.IO
  import spray.can.Http

  IO( Http )( system ) ! Http.Bind( rootService, interface = machineIp( ), port = portNumber( args ) )
}

trait HostBinding {
	import collection.JavaConversions.enumerationAsScalaIterator
	import java.net.{ InetAddress, NetworkInterface }
	
  def portNumber( args: Array[ String ] ): Int =
    if ( args.length != 0 ) args( 0 ).toInt else 9400

  def machineIp(): String =
    NetworkInterface.getByName( s"eth0" ).getInetAddresses.map( matchIp ).flatten.mkString

  private def matchIp( address: InetAddress ): Option[ String ] =
    """\b(?:\d{1,3}\.){3}\d{1,3}\b""".r.findFirstIn( address.getHostAddress( ) )
}
