start
cd authentication
mvn clean compile package -Dmaven.test.skip
cd ..

start
cd city
mvn clean compile package -Dmaven.test.skip
cd ..

start
cd eureka
mvn clean compile package -Dmaven.test.skip
cd ..

start
cd gateway
mvn clean compile package -Dmaven.test.skip
cd ..

start
cd movie
mvn clean compile package -Dmaven.test.skip
cd ..

start
cd theatre
mvn clean compile package -Dmaven.test.skip
cd ..

start
cd ticket
mvn clean compile package -Dmaven.test.skip
cd ..