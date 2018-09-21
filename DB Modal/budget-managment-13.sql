-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema MOU
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema MOU
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MOU` DEFAULT CHARACTER SET utf8 ;
USE `MOU` ;

-- -----------------------------------------------------
-- Table `MOU`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`USER` (
  `USER_ID` INT NOT NULL AUTO_INCREMENT,
  `USER_NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`USER_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOU`.`MOU_CUSTOMER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`MOU_CUSTOMER` (
  `CUSTOMER_ID` INT NOT NULL AUTO_INCREMENT,
  `CREATED_SYS_USER` INT NOT NULL,
  `ID_TYPE` VARCHAR(10) NOT NULL,
  `ID_NUMBER` VARCHAR(50) NOT NULL,
  `CX_NAME` VARCHAR(100) NULL,
  `CREATED_USER` VARCHAR(50) NOT NULL,
  `CREATED_DATE` DATETIME NOT NULL,
  `AD_LINE3` VARCHAR(100) NULL,
  `AD_LINE2` VARCHAR(100) NULL,
  `AD_LINE1` VARCHAR(100) NULL,
  `SEGMENT` VARCHAR(100) NULL,
  `SECTOR` VARCHAR(45) NULL,
  PRIMARY KEY (`CUSTOMER_ID`),
  INDEX `fk_customer_user1_idx` (`CREATED_SYS_USER` ASC),
  CONSTRAINT `fk_customer_user1`
    FOREIGN KEY (`CREATED_SYS_USER`)
    REFERENCES `MOU`.`USER` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOU`.`MOU_AGREEMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`MOU_AGREEMENT` (
  `AGREEMENT_KEY` INT NOT NULL AUTO_INCREMENT,
  `CREATED_SYS_USER` INT NOT NULL,
  `AGREEMENT_ID` VARCHAR(30) NOT NULL,
  `ID_TYPE` VARCHAR(10) NOT NULL,
  `CREATED_DATE` DATETIME NOT NULL,
  `CREATED_USER` VARCHAR(50) NOT NULL,
  `END_DATE` DATETIME NOT NULL,
  `START_DATE` DATETIME NOT NULL,
  `ID_NUMBER` VARCHAR(50) NOT NULL,
  `DESCRIPTION` VARCHAR(200) NULL,
  `ACTIVE` TINYINT(1) NULL,
  `AMOUNT` DOUBLE NULL,
  PRIMARY KEY (`AGREEMENT_KEY`),
  UNIQUE INDEX `AGREEMENT_ID_UNIQUE` (`AGREEMENT_KEY` ASC),
  INDEX `fk_MOU_AGREEMENT_user1_idx` (`CREATED_SYS_USER` ASC),
  CONSTRAINT `fk_MOU_AGREEMENT_user1`
    FOREIGN KEY (`CREATED_SYS_USER`)
    REFERENCES `MOU`.`USER` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOU`.`CUSTOMER_MOU_AGREEMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`CUSTOMER_MOU_AGREEMENT` (
  `CUSTOMER_MOU_AGREEMENT_ID` INT NOT NULL AUTO_INCREMENT,
  `MOU_CUSTOMER_ID` INT NOT NULL,
  `MOU_AGREEMENT_KEY` INT NOT NULL,
  PRIMARY KEY (`CUSTOMER_MOU_AGREEMENT_ID`),
  INDEX `fk_CUSTOMER_MOU_AGREEMENT_MOU_CUSTOMER1_idx` (`MOU_CUSTOMER_ID` ASC),
  INDEX `fk_CUSTOMER_MOU_AGREEMENT_MOU_AGREEMENT1_idx` (`MOU_AGREEMENT_KEY` ASC),
  CONSTRAINT `fk_CUSTOMER_MOU_AGREEMENT_MOU_CUSTOMER1`
    FOREIGN KEY (`MOU_CUSTOMER_ID`)
    REFERENCES `MOU`.`MOU_CUSTOMER` (`CUSTOMER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CUSTOMER_MOU_AGREEMENT_MOU_AGREEMENT1`
    FOREIGN KEY (`MOU_AGREEMENT_KEY`)
    REFERENCES `MOU`.`MOU_AGREEMENT` (`AGREEMENT_KEY`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOU`.`BUDGET_TYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`BUDGET_TYPE` (
  `BUDGET_TYPE_ID` INT NOT NULL AUTO_INCREMENT,
  `BUDGET_NAME` VARCHAR(45) NULL,
  `DESCRIPTION` VARCHAR(45) NULL,
  PRIMARY KEY (`BUDGET_TYPE_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOU`.`RELEASE_FREQUENCY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`RELEASE_FREQUENCY` (
  `RELEASE_FREQUENCY_ID` INT NOT NULL AUTO_INCREMENT,
  `FREQUENCY_NAME` VARCHAR(45) NULL,
  `DESCRIPTION` VARCHAR(45) NULL,
  PRIMARY KEY (`RELEASE_FREQUENCY_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOU`.`MOU_AGREEMENT_BUDGET_TYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`MOU_AGREEMENT_BUDGET_TYPE` (
  `MOU_AGREEMENT_BUDGET_TYPE_ID` INT NOT NULL AUTO_INCREMENT,
  `CUSTOMER_AGREEMENT_ID` INT NOT NULL,
  `BUDGET_TYPE_ID` INT NOT NULL,
  `MOU_AGREEMENT_ID` INT NOT NULL,
  `RELEASE_FREQUENCY_ID` INT NOT NULL,
  `CUSTOMER_ID` INT NOT NULL,
  `AMOUNT` DOUBLE NULL,
  PRIMARY KEY (`MOU_AGREEMENT_BUDGET_TYPE_ID`),
  INDEX `fk_mou_agreement_budget_type_budget_type1_idx` (`BUDGET_TYPE_ID` ASC),
  INDEX `fk_mou_agreement_budget_type_mou_agreement1_idx` (`MOU_AGREEMENT_ID` ASC),
  INDEX `fk_mou_agreement_budget_type_release_freequency1_idx` (`RELEASE_FREQUENCY_ID` ASC),
  INDEX `fk_MOU_AGREEMENT_BUDGET_TYPE_MOU_CUSTOMER1_idx` (`CUSTOMER_ID` ASC),
  INDEX `fk_MOU_AGREEMENT_BUDGET_TYPE_CUSTOMER_MOU_AGREEMENT1_idx` (`CUSTOMER_AGREEMENT_ID` ASC),
  CONSTRAINT `fk_mou_agreement_budget_type_budget_type1`
    FOREIGN KEY (`BUDGET_TYPE_ID`)
    REFERENCES `MOU`.`BUDGET_TYPE` (`BUDGET_TYPE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mou_agreement_budget_type_mou_agreement1`
    FOREIGN KEY (`MOU_AGREEMENT_ID`)
    REFERENCES `MOU`.`MOU_AGREEMENT` (`AGREEMENT_KEY`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mou_agreement_budget_type_release_freequency1`
    FOREIGN KEY (`RELEASE_FREQUENCY_ID`)
    REFERENCES `MOU`.`RELEASE_FREQUENCY` (`RELEASE_FREQUENCY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOU_AGREEMENT_BUDGET_TYPE_MOU_CUSTOMER1`
    FOREIGN KEY (`CUSTOMER_ID`)
    REFERENCES `MOU`.`MOU_CUSTOMER` (`CUSTOMER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOU_AGREEMENT_BUDGET_TYPE_CUSTOMER_MOU_AGREEMENT1`
    FOREIGN KEY (`CUSTOMER_AGREEMENT_ID`)
    REFERENCES `MOU`.`CUSTOMER_MOU_AGREEMENT` (`CUSTOMER_MOU_AGREEMENT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOU`.`BUDGET`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`BUDGET` (
  `BUDGET_ID` INT NOT NULL AUTO_INCREMENT,
  `MOU_AGREEMENT_BUDGET_TYPE_ID` INT NOT NULL,
  `MOU_CUSTOMER_ID` INT NOT NULL,
  `BUDGET_AMOUNT` DOUBLE NULL,
  `BUDGET_START_DATE` DATETIME NULL,
  `SUB_PERIOD_CODE` VARCHAR(45) NULL,
  `SUB_PERIOD_CODE_DESC` VARCHAR(45) NULL,
  `BUDGET_END_DATE` DATETIME NULL,
  `USAGE_AMOUNT` DOUBLE NULL,
  `AVAILABLE_AMOUNT` DOUBLE NULL,
  `ACTIVE` TINYINT(1) NULL,
  PRIMARY KEY (`BUDGET_ID`),
  INDEX `fk_budget_mou_agreement_budget_type1_idx` (`MOU_AGREEMENT_BUDGET_TYPE_ID` ASC),
  INDEX `fk_budget_MOU_CUSTOMER1_idx` (`MOU_CUSTOMER_ID` ASC),
  CONSTRAINT `fk_budget_mou_agreement_budget_type1`
    FOREIGN KEY (`MOU_AGREEMENT_BUDGET_TYPE_ID`)
    REFERENCES `MOU`.`MOU_AGREEMENT_BUDGET_TYPE` (`MOU_AGREEMENT_BUDGET_TYPE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_budget_MOU_CUSTOMER1`
    FOREIGN KEY (`MOU_CUSTOMER_ID`)
    REFERENCES `MOU`.`MOU_CUSTOMER` (`CUSTOMER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOU`.`transaction_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`transaction_status` (
  `transaction_status_id` INT NOT NULL,
  `transaction_status` VARCHAR(45) NULL,
  `transaction_status_desc` VARCHAR(45) NULL,
  `transaction_statuscol` VARCHAR(45) NULL,
  PRIMARY KEY (`transaction_status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOU`.`mou_transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`mou_transaction` (
  `mou_transaction_id` INT NOT NULL,
  `mou_transaction_amount` DOUBLE NULL,
  `mou_transaction_date` DATETIME NULL,
  `budget_budget_id` INT NOT NULL,
  `created_user_id` INT NOT NULL,
  `transaction_status_transaction_status_id` INT NOT NULL,
  PRIMARY KEY (`mou_transaction_id`),
  INDEX `fk_mou_transaction_budget1_idx` (`budget_budget_id` ASC),
  INDEX `fk_mou_transaction_user1_idx` (`created_user_id` ASC),
  INDEX `fk_mou_transaction_transaction_status1_idx` (`transaction_status_transaction_status_id` ASC),
  CONSTRAINT `fk_mou_transaction_budget1`
    FOREIGN KEY (`budget_budget_id`)
    REFERENCES `MOU`.`BUDGET` (`BUDGET_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mou_transaction_user1`
    FOREIGN KEY (`created_user_id`)
    REFERENCES `MOU`.`USER` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mou_transaction_transaction_status1`
    FOREIGN KEY (`transaction_status_transaction_status_id`)
    REFERENCES `MOU`.`transaction_status` (`transaction_status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOU`.`segment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`segment` (
  `segment_id` INT NOT NULL,
  `segment_type` VARCHAR(45) NULL,
  `segment_desc` VARCHAR(45) NULL,
  PRIMARY KEY (`segment_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MOU`.`customer_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOU`.`customer_users` (
  `idcustomer_users` INT NOT NULL,
  `user_user_id` INT NOT NULL,
  `MOU_CUSTOMER_CUSTOMER_ID` INT NOT NULL,
  PRIMARY KEY (`idcustomer_users`),
  INDEX `fk_customer_users_user1_idx` (`user_user_id` ASC),
  INDEX `fk_customer_users_MOU_CUSTOMER1_idx` (`MOU_CUSTOMER_CUSTOMER_ID` ASC),
  CONSTRAINT `fk_customer_users_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `MOU`.`USER` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_users_MOU_CUSTOMER1`
    FOREIGN KEY (`MOU_CUSTOMER_CUSTOMER_ID`)
    REFERENCES `MOU`.`MOU_CUSTOMER` (`CUSTOMER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
