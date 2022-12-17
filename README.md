# Flight Booking AWS Application
This repoistory shows how to deploy a Spring Boot application on AWS. The application is an imaginary Flight Booking application and only supports the back-end API.

## Requirements
The application has been tested locally with the following:
* Java 17
* Maven 3.8.4
* MySQL 5.7.x

## Local testing
To test the application locally, you need to update application.properties similar to this:

    # Only for testing purposes - comment this in production
    # spring.jpa.hibernate.ddl-auto=create
    spring.datasource.url=jdbc:mysql://localhost:3306/bookflight
    spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
    spring.datasource.username=<a-privileged-user-name>
    spring.datasource.password=<a-privileged-user-password>

    # Hibernate SQL dialec
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
    
Then you can use your IDE or maven to run the application using:

    mvnw spring-boot:run

The flights added with bootstrapping can be seen at http://localhost:8080/api/v1/flights

The following endpoints can be tested with curl or Postman:

    [POST] api/v1/flights
    
    [PUT] api/v1/flights/reserve/{flight-id}

    [PUT] api/v1/flights/release/{flight-id}

    [POST] api/v1/bookings

    [PUT] api/v1/confirm/{bookingId}

    [PUT] api/v1/cancel/{bookingId}

## Deploy on AWS
The application has beed succesfully deployed on AWS using the following services:
- AWS Elastic Beanstalk
- AWS RDS

### Set up an Elastic Beanstalk application environment:

    aws elasticbeanstalk create-application --application-name flightbooking

### Create an application version
    aws elasticbeanstalk create-application-version --application-name flightbooking --version-label v1

### Make sure your application version exists
    aws elasticbeanstalk describe-application-versions --application-name flightbooking --version-label v1

### Create a configuration template for the application
    aws elasticbeanstalk create-configuration-template --application-name flightbooking --template-name v1 \
      --solution-stack-name "64bit Amazon Linux 2 v3.4.2 running Corretto 17"

### Create environment
    aws elasticbeanstalk create-environment --cname-prefix flightbooking-cname \
      --application-name flightbooking --template-name v1 --version-label v1 \
      --environment-name v1clone --option-settings file://options.txt

### Determine if the new environment is Green and Ready
    aws elasticbeanstalk describe-environments --environment-names v1clone

### Create an S3 bucket for storing application versions
    aws s3 mb s3://flightbooking-files

### Create an RDS database:
    aws rds create-db-instance \
      --db-name bookflight \
      --db-instance-identifier flightbooking \
      --db-instance-class db.t2.micro \
      --allocated-storage 200 \
      --engine mysql\
      --master-username root \
      --master-user-password a_strong_password

### Update Elastic Beanstalk environment variables with database connection settings:
    aws elasticbeanstalk update-environment --environment-name v1clone \
      --option-settings Namespace=aws:elasticbeanstalk:application:environment,OptionName=MYSQL_HOST,Value=flightbooking.coyo72ntjmdh.us-west-1.rds.amazonaws.com

## Future Improvements
* Add authentication/authorization to backend
* Use AWS Lambda to make some API serverless
