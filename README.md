# library
Simple project for Convey
=======
# Library

# Prerequisites:
- java 1.8.x
- maven
- appache maven
- postgresql with database library for user/password boksha/boksha

	# install postgresql on linnux ubuntu 18.04:
		sudo apt update
		sudo apt install postgresql postgresql-contrib
		sudo -i -u postgres
		createuser --interactive
			Enter name of role to add: boksha
			Shall the new role be a superuser? (y/n) y
		createdb library
		psql
			alter user boksha with encrypted password 'boksha';
			GRANT ALL PRIVILEGES ON DATABASE library to boksha;

# How to start the Library application
mvn clean install

#  check status of database:
java -jar target/library-0.0.1-SNAPSHOT.jar db status config.yml 

#  Tagging Your Schema
java -jar target/library-0.0.1-SNAPSHOT.jar db tag config.yml convey-test

#  migrate schema
java -jar target/library-0.0.1-SNAPSHOT.jar db migrate config.yml 

#  Rolling Back Your Schema if needed
java -jar target/library-0.0.1-SNAPSHOT.jar db rollback config.yml  convey-test

#  Start application with
java -jar target/library-0.0.1-SNAPSHOT.jar server config.yml

# To check that your application is running enter url 
http://localhost:8080


Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`