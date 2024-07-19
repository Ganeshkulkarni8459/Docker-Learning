# Build docker image for auth-service
        docker build -t dnyanyog.org/auth-service:latest -f dockerfile-auth-svc .

# Connect two containers over Bridge(default) network using IP
        
        -- Run My SQL container on default network
        docker run --rm -dit -p 3306:3306 --name mysql-auth-container -e MYSQL_ROOT_PASSWORD=Test@123 mysql-auth-sample:latest
        
        -- Find the ip of container using inspect command
        docker container inspect mysql-auth-sample:latest
        
        -- Run My SQL container on default networn
        docker run -it --rm -e DB_HOST=172.17.0.2 dnyanyog.org/auth-service:latest

# Connect two containers over Bridge(default) network using container name (--link)
    To connect the containers each other using domain name (container name), we need to link the containers with each other
    It's useful for default network but not needed or available for other network
    Internally it updates the /etc/host file with container name and ip adress

        -- docker run --rm -dit -p 3306:3306 --name mysql_directory -e MYSQL_ROOT_PASSWORD=Test@123 mysql:latest

        -- docker run -it --rm -e DB_HOST=mysql --link mysql_directory:mysql --name directory-svc dnyanyog.org/auth-service:latest (DB_HOST=mysql databas image name, --link  mysql-container-name:mysql-databas-image -name)

# Connect two containers over Bridge(default) network using container name (--network container:<container_name>)
    To connect the containers each other using domain name (container name), we need to link the containers with each other
    It's useful for default network but not needed or available for other network
    Internally it updates the /etc/host file with container name and ip adress

        -- docker run --rm -dit -p 3306:3306 --name mysql_directory -e MYSQL_ROOT_PASSWORD=Test@123 mysql:latest

        -- docker run -it --rm -e DB_HOST=mysql --network container:mysql_directory --name directory-svc dnyanyog.org/auth-service:latest

# Run two containers in named network (User defined container)
    1. Create network
        docker network create services
    2. Then run DB using the network 
        -- docker run --rm -dit -p 3306:3306 --name mysql --network services -e MYSQL_ROOT_PASSWORD=Test@123 mysql:latest
    3. Run the service using the network and container name as host name
        -- docker run -it --rm -e DB_HOST=mysql --network services --name directory-svc dnyanyog.org/auth-service:latest

# Assignment

    1. Create user defined network and use it for all containers

    2. Craete docker image of 
            - directory-service
            - patient-service
            - case-service
            - appointment-service
            - api-gateway

    3. Run mysql inside docker container (do not expose port)
            - directory-service-db
            - patient-service-db
            - case-service-db
            - appointment-service-db
            - Mount volume for mysql

    4. Run below services as container
            - directory-service
            - patient-service
            - case-service
            - appointment-service
            - api-gateway (Export 8080 port)

    5. Connect hospital-console to api-gateway from container and test all features

