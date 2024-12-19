CREATE USER IF NOT EXISTS 'rsmr'@'localhost' IDENTIFIED BY 'spring';
CREATE DATABASE IF NOT EXISTS `rsmr`;
GRANT ALL PRIVILEGES ON `rsmr`.* TO 'rsmr'@'your_hostname' IDENTIFIED BY 'spring';
