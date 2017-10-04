name := """play-java-starter-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies += guice

// Test Database
libraryDependencies += "com.h2database" % "h2" % "1.4.194"

libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final" // replace by your jpa implementation
)

libraryDependencies ++= Seq(
  ehcache
)

// https://mvnrepository.com/artifact/postgresql/postgresql
// https://mvnrepository.com/artifact/org.postgresql/postgresql
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1200-jdbc41"

// https://mvnrepository.com/artifact/com.vaadin/vaadin-server
libraryDependencies += "com.vaadin" % "vaadin-server" % "8.1.0"


// https://mvnrepository.com/artifact/javax.servlet/servlet-api
libraryDependencies += "javax.servlet" % "servlet-api" % "2.5" % "provided"



// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
