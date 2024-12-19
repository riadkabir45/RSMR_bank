CREATE USER IF NOT EXISTS 'rsmr'@'localhost' IDENTIFIED BY 'spring';
CREATE DATABASE IF NOT EXISTS `rsmr`;
GRANT ALL PRIVILEGES ON `rsmr`.* TO 'rsmr'@'localhost' IDENTIFIED BY 'spring';
