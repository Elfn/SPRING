## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE db_dev;
CREATE DATABASE db_prod;

#Create database service accounts
CREATE USER 'db_dev_user'@'localhost' IDENTIFIED BY 'mydb';
CREATE USER 'db_prod_user'@'localhost' IDENTIFIED BY 'mydb';
CREATE USER 'db_dev_user'@'%' IDENTIFIED BY 'mydb';
CREATE USER 'db_prod_user'@'%' IDENTIFIED BY 'mydb';

#Database grants
GRANT SELECT ON db_dev.* to 'db_dev_user'@'localhost';
GRANT INSERT ON db_dev.* to 'db_dev_user'@'localhost';
GRANT DELETE ON db_dev.* to 'db_dev_user'@'localhost';
GRANT UPDATE ON db_dev.* to 'db_dev_user'@'localhost';
GRANT SELECT ON db_prod.* to 'db_prod_user'@'localhost';
GRANT INSERT ON db_prod.* to 'db_prod_user'@'localhost';
GRANT DELETE ON db_prod.* to 'db_prod_user'@'localhost';
GRANT UPDATE ON db_prod.* to 'db_prod_user'@'localhost';
GRANT SELECT ON db_dev.* to 'db_dev_user'@'%';
GRANT INSERT ON db_dev.* to 'db_dev_user'@'%';
GRANT DELETE ON db_dev.* to 'db_dev_user'@'%';
GRANT UPDATE ON db_dev.* to 'db_dev_user'@'%';
GRANT SELECT ON db_prod.* to 'db_prod_user'@'%';
GRANT INSERT ON db_prod.* to 'db_prod_user'@'%';
GRANT DELETE ON db_prod.* to 'db_prod_user'@'%';
GRANT UPDATE ON db_prod.* to 'db_prod_user'@'%';
