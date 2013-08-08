import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-jodamoney-example"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.joda" % "joda-money" % "0.9",
    "com.typesafe.play" %% "play-slick" % "0.4.0"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
  )

}
