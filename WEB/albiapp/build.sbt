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

// https://mvnrepository.com/artifact/com.vaadin/vaadin-themes
libraryDependencies += "com.vaadin" % "vaadin-themes" % "8.1.0"

// https://mvnrepository.com/artifact/com.vaadin/vaadin-client-compiled
libraryDependencies += "com.vaadin" % "vaadin-client-compiled" % "8.1.0"

// https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api
libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.1.0"

// https://mvnrepository.com/artifact/io.swagger/swagger-play2_2.12
libraryDependencies += "io.swagger" % "swagger-play2_2.12" % "1.6.0"

// https://mvnrepository.com/artifact/org.glassfish/javax.el
libraryDependencies += "org.glassfish" % "javax.el" % "3.0.0"

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

routesGenerator := InjectedRoutesGenerator