#!/bin/bash
docker stop jpmedier-template&& docker rm jpmedier-template

docker run --name jpmedier-template -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql:5.6.27

sleep 20
#create database
docker exec -i jpmedier-template /bin/bash -c "mysql -u root '-ppassword' -e 'CREATE DATABASE jpmedier-template CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;' "
docker exec -i jpmedier-template /bin/bash -c "mysql -u root '-ppassword' -e 'CREATE DATABASE jpmedier-templateTest CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;' "
docker exec -i jpmedier-template /bin/bash -c "mysql -u root '-ppassword' -e 'set global max_connections= 550'; "
