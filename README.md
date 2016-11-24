JPMedier template scala, play and slick
=================================

This project is based on our current knowledge of how to best make a json-estful api on scala, play and slick.  
To create a new project, copy the contents of this project, delete the .git directory and create a new git repo.  
For now this is the best way to start a new project, maybe in the future, we might create an activator template.  

Database Access
===============
If you have an existing database, you can generate dataaccess classes by running `sbt gen-tables`. The classes will be generated under `target/src_managed`  
It is recommended to copy the generated files under `app/models`and delete the content in `target`  
A docker container, with mysql, can be started by running `./start_db.sh`, this can be used for dev and test, you should probably rename the container and the database. This can be done by replacing the names in `./start_db.sh` and the corresponding in `application.conf` and `application.test.conf`.  
The database is created with user "root" and password "password", this is fine for dev and test and of course not for prod.

Running the app
===============

To run the app: `sbt run`, this assumes that the environment variables in `application.conf` are set.
To run the tests run `sbt test` sbt can run tests when files changes this can be done by entering a sbt session and issuing the command `~test`.   

Controllers
===========

This template contains only one controller: The HealthController.
In this template you will find that is simply always returns ok, it is recommended to have the health endpoint also check that the services needed by the app are up and runing.


Components
==========

- Module.scala:

  Shows how to use Guice to bind all the components needed by your application.

Filters
=======
The LoggingFilter will log all requests.
 
Best practices
===============
 - Unless you have a very good reason, you should always make your controller actions async.
 - The only place in your codebase where you should allow async.await is in tests/
 
