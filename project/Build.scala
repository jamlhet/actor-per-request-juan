import sbt._
import Keys._

object Build extends Build {
	lazy val msCommons = Project( "ms-commons", file( "ms-commons" ) )	
	lazy val msService = Project( "ms-service", file( "ms-service" ) ).dependsOn( msCommons )
}
