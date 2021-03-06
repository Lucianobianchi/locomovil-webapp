# Locomovil API
A Jersey-based API, part of a research project to enable indoor positioning of mobile devices based on its sensor data.
Right now, the main positioning method is via Wi-Fi fingerprinting.

## Getting Started

### Installation
Import the app with Maven, which should automatically setup the five modules (webapp, service, persistence, model and interfaces) for you.

### Running the app
In development, use [Jetty Runner](http://www.eclipse.org/jetty/documentation/9.3.x/runner.html), which is easy to setup and quick to load for testing purposes, and also has great plugins for Eclipse and IntelliJ.

The app works with a PostgreSQL database, and the SQL needed to create the appropriate tables is also provided (see [createTablesWeb.sql](https://github.com/Lucianobianchi/locomovil-webapp/blob/master/webapp/src/main/resources/createTablesWeb.sql)). Make sure you configure a correct datasource in the [web.xml file](https://github.com/Lucianobianchi/locomovil-webapp/blob/master/webapp/src/main/webapp/WEB-INF/web.xml).

### Availability
Currently, an instance of this app is running and can be accessed freely, with no authentication at [https://locomovil-heroku.herokuapp.com](https://locomovil-heroku.herokuapp.com)
