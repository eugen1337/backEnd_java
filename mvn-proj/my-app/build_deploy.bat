call mvn compile
call mvn package
call asadmin deploy --force=true ./target/my-app-1.0.war