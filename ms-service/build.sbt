import AssemblyKeys._
import Common._

assemblySettings

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case x => old(x)
  }
}

test in assembly := {}

outputPath in assembly := file( "../ms-service.jar" )

name := "ms-service-juan"

version := "0.1"

scalaVersion := commonScalaVersion

scalacOptions ++= commonScalacOptions

unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil

unmanagedSourceDirectories in Test := (scalaSource in Test).value :: Nil

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

EclipseKeys.projectFlavor := EclipseProjectFlavor.Scala

resolvers ++= commonResolvers

libraryDependencies ++= commonLibraries ++ reactiveLibraries

seq( Revolver.settings: _* )

mainClass in Revolver.reStart := Some( "co.s4n.template.Boot" )
