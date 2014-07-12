import sbt._
import Keys._

object Common {

	autoScalaLibrary := false

	lazy val akkaVersion = "2.2.3"
	lazy val sprayVersion = "1.2-RC4"

	/**
	 * Scala version
	 **/
	def commonScalaVersion = "2.10.3"

	/**
	 * scalac arguments
	 **/
	def commonScalacOptions = Seq(
		"-unchecked",
		"-deprecation",
		"-Xlint",
		"-Ywarn-dead-code",
		"-language:_",
		"-target:jvm-1.7",
		"-encoding", "UTF-8"
	)

	/**
	 * Maven repositories
	 **/
	def commonResolvers = Seq(
		"Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",		
		"Sonatype Releases"  at "http://oss.sonatype.org/content/repositories/releases",
		"Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
		"Spray.io repository" at "http://repo.spray.io"
	)

	/**
	 * Scala basic libraries
	 **/
	def commonScalaLibraries = Seq (
		"org.scala-lang" 					% "scala-library" 	% "2.10.3",
		"org.scala-lang" 					% "scala-compiler" 	% "2.10.3",
		// See http://jesseeichar.github.io/scala-io-doc/0.4.2/index.html#!/getting-started/index
		"com.github.scala-incubator.io" 	%% "scala-io-core" 	% "0.4.2",		
		"com.github.scala-incubator.io" 	%% "scala-io-file" 	% "0.4.2" 

	)

	def mongoLibraries = Seq(
		"org.reactivemongo" 		%% 	"play2-reactivemongo" 	% "0.10.0-SNAPSHOT" withSources(),
		"org.reactivemongo" 		%% 	"reactivemongo" 		% "0.10.0-SNAPSHOT" withSources(),
		"com.typesafe.play" 		%% 	"play-json" 			% "2.2.0" withSources(),
		"joda-time" 		 		%	"joda-time" 			% "1.5.2",
		"org.slf4j" 				% 	"slf4j-api"				% "1.6.6"
	)

	def functionalProgrammingLibraries = Seq(
		"org.scalaz"				%% "scalaz-core" 			% "7.0.2"
	)

	def akkaLibraries = Seq(
		"com.typesafe.akka" 		%% "akka-actor" 			% akkaVersion withSources(),
	  	"com.typesafe.akka" 		%% "akka-slf4j"       		% akkaVersion withSources(),
	  	"com.typesafe.akka" 		%% "akka-testkit"       	% akkaVersion withSources(),
	  	"com.typesafe.akka" 		%% "akka-remote" 			% akkaVersion withSources(),
	  	"ch.qos.logback"     		%  "logback-classic"  		% "1.0.13" withSources()
  	)

  	def sprayLibraries = Seq(
	    "io.spray"           		% 	"spray-can"    			% sprayVersion withSources(),
	    "io.spray"           		% 	"spray-routing"    		% sprayVersion withSources(),
	    "io.spray"           		% 	"spray-client"    		% sprayVersion withSources(),
	    "io.spray"           		% 	"spray-http"    		% sprayVersion withSources(),
	    "io.spray"           		% 	"spray-httpx"    		% sprayVersion withSources(),
		"io.spray" 					%% 	"spray-json" 			% "1.2.5" withSources(),
		"com.chuusai"				%% 	"shapeless" 			% "1.2.4" withSources()
  	)

  	def testingLibraries = Seq(
		"junit" 			 		%	"junit"				%	"4.10"		% "test",
		"com.jayway.restassured"	%	"rest-assured" 		%	"1.8.1"		% "test",
		"org.scalatest" 	 		% 	"scalatest_2.10" 	%	"1.9.1" 	% "test",
		"org.scalacheck" 			%%	"scalacheck" 		%	"1.11.1"	% "test"		
	)

	def apacheCommonsLibraries = Seq( 
		"commons-logging" 			% 	"commons-logging" 	% "1.1.3",
		"org.apache.commons" 		% 	"commons-lang3" 	% "3.1",
		"commons-codec" 			% 	"commons-codec" 	% "1.8"
	)

  	def commonLibraries = functionalProgrammingLibraries ++ testingLibraries ++ commonScalaLibraries ++ apacheCommonsLibraries
  	def reactiveLibraries = akkaLibraries ++ sprayLibraries

}
