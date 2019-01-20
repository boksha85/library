# LIBRARY
Simple project for Convey for storing and showing books.


# Prerequisites:
- java 1.8.x
- maven
- appache maven
- postgresql with database library for user/password boksha/boksha (or change in config.yml name of the database and user/password)

	# install guide for  postgresql on linnux ubuntu 18.04:
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

# Clone project from github
git clone https://github.com/boksha85/library.git

# Go in project folder and  install dependencies
mvn clean install

# Check status of database:
java -jar target/library-0.0.1-SNAPSHOT.jar db status config.yml 

# Tagging Your Schema
java -jar target/library-0.0.1-SNAPSHOT.jar db tag config.yml convey-test

# Migrate schema to add 10 books
java -jar target/library-0.0.1-SNAPSHOT.jar db migrate config.yml

# IF NEEDED Rolling Back Your Schema 
java -jar target/library-0.0.1-SNAPSHOT.jar db rollback config.yml  convey-test

# Start application with
java -jar target/library-0.0.1-SNAPSHOT.jar server config.yml

# Run in browser 
http://localhost:8080

# Exposed APIs:
- GET  /api/books 			– returns all books from database

- GET  /api/books/lastfive 	– returns last five inserted records

- GET  /api/books/get/{title} – returns all books that contains search parameter sent as title

- POST /api/books/add/		– store new record in database, in body we should send Book as json object.

# Test APIs with postman

Open postman app and import collection "library-convey.postman_collection.json" from postman folder
