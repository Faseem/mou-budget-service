-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `user_id` INT NOT NULL,
  `user_name` VARCHAR(45) NULL,
  `user_password` VARCHAR(45) NULL,
  `user_nic` VARCHAR(45) NULL,
  `user_mobile` VARCHAR(45) NULL,
  `user_type` VARCHAR(45) NULL,
  `user_email` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`segment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`segment` (
  `segment_id` INT NOT NULL,
  `segment_type` VARCHAR(45) NULL,
  `segment_desc` VARCHAR(45) NULL,
  PRIMARY KEY (`segment_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`customer` (
  `customer_id_number` INT NOT NULL,
  `customer_id_type` VARCHAR(45) NULL,
  `customer_name` VARCHAR(45) NULL,
  `customer_address_line_one` VARCHAR(45) NULL,
  `customer_address_line_two` VARCHAR(45) NULL,
  `customer_address_line_three` VARCHAR(45) NULL,
  `customer_created_data` DATETIME NULL,
  `customer_phone_number` VARCHAR(45) NULL,
  `created_user` VARCHAR(45) NULL,
  `created_user_id` INT NOT NULL,
  `segment_segment_id` INT NOT NULL,
  PRIMARY KEY (`customer_id_number`),
  INDEX `fk_customer_user1_idx` (`created_user_id` ASC),
  INDEX `fk_customer_segment1_idx` (`segment_segment_id` ASC),
  CONSTRAINT `fk_customer_user1`
    FOREIGN KEY (`created_user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_segment1`
    FOREIGN KEY (`segment_segment_id`)
    REFERENCES `mydb`.`segment` (`segment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`mou_agreement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`mou_agreement` (
  `mou_agreement_id` INT NOT NULL,
  `mou_agreement_start_date` DATETIME NULL,
  `mou_agreement_end_date` DATETIME NULL,
  `mou_agreement_amount` DOUBLE NULL,
  `mou_agreement_description` VARCHAR(45) NULL,
  PRIMARY KEY (`mou_agreement_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`customer_mou_agreement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`customer_mou_agreement` (
  `customer_mou_agreement_id` INT NOT NULL,
  `customer_mou_agreement_created_date` DATETIME NULL,
  `customer_customer_id_number` INT NOT NULL,
  `mou_agreement_mou_agreement_id` INT NOT NULL,
  `created_user_id` INT NOT NULL,
  PRIMARY KEY (`customer_mou_agreement_id`),
  INDEX `fk_customer_mou_agreement_customer_idx` (`customer_customer_id_number` ASC),
  INDEX `fk_customer_mou_agreement_mou_agreement1_idx` (`mou_agreement_mou_agreement_id` ASC),
  INDEX `fk_customer_mou_agreement_user1_idx` (`created_user_id` ASC),
  CONSTRAINT `fk_customer_mou_agreement_customer`
    FOREIGN KEY (`customer_customer_id_number`)
    REFERENCES `mydb`.`customer` (`customer_id_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_mou_agreement_mou_agreement1`
    FOREIGN KEY (`mou_agreement_mou_agreement_id`)
    REFERENCES `mydb`.`mou_agreement` (`mou_agreement_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_mou_agreement_user1`
    FOREIGN KEY (`created_user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`budget_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`budget_type` (
  `budget_type_id` INT NOT NULL,
  `budget_type_name` VARCHAR(45) NULL,
  `budget_type_desc` VARCHAR(45) NULL,
  PRIMARY KEY (`budget_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`release_freequency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`release_freequency` (
  `release_freequency_id` INT NOT NULL,
  `release_freequency_type` VARCHAR(45) NULL,
  `release_freequency_desc` VARCHAR(45) NULL,
  PRIMARY KEY (`release_freequency_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`mou_agreement_budget_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`mou_agreement_budget_type` (
  `mou_agreement_budget_type_id` INT NOT NULL,
  `budget_type_budget_type_id` INT NOT NULL,
  `mou_agreement_mou_agreement_id` INT NOT NULL,
  `release_freequency_release_freequency_id` INT NOT NULL,
  PRIMARY KEY (`mou_agreement_budget_type_id`),
  INDEX `fk_mou_agreement_budget_type_budget_type1_idx` (`budget_type_budget_type_id` ASC),
  INDEX `fk_mou_agreement_budget_type_mou_agreement1_idx` (`mou_agreement_mou_agreement_id` ASC),
  INDEX `fk_mou_agreement_budget_type_release_freequency1_idx` (`release_freequency_release_freequency_id` ASC),
  CONSTRAINT `fk_mou_agreement_budget_type_budget_type1`
    FOREIGN KEY (`budget_type_budget_type_id`)
    REFERENCES `mydb`.`budget_type` (`budget_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mou_agreement_budget_type_mou_agreement1`
    FOREIGN KEY (`mou_agreement_mou_agreement_id`)
    REFERENCES `mydb`.`mou_agreement` (`mou_agreement_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mou_agreement_budget_type_release_freequency1`
    FOREIGN KEY (`release_freequency_release_freequency_id`)
    REFERENCES `mydb`.`release_freequency` (`release_freequency_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`budget`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`budget` (
  `budget_id` INT NOT NULL,
  `customer_customer_id_number` INT NOT NULL,
  `mou_agreement_budget_type_mou_agreement_budget_type_id` INT NOT NULL,
  `budget_amount` DOUBLE NULL,
  `budget_start_date` DATETIME NULL,
  `budget_sub_period_code` VARCHAR(45) NULL,
  `budget_sub_period_desc` VARCHAR(45) NULL,
  `budget_end_date` VARCHAR(45) NULL,
  `budget_usage` DOUBLE NULL,
  `budget_available` DOUBLE NULL,
  `budget_status` TINYINT(1) NULL,
  PRIMARY KEY (`budget_id`),
  INDEX `fk_budget_customer1_idx` (`customer_customer_id_number` ASC),
  INDEX `fk_budget_mou_agreement_budget_type1_idx` (`mou_agreement_budget_type_mou_agreement_budget_type_id` ASC),
  CONSTRAINT `fk_budget_customer1`
    FOREIGN KEY (`customer_customer_id_number`)
    REFERENCES `mydb`.`customer` (`customer_id_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_budget_mou_agreement_budget_type1`
    FOREIGN KEY (`mou_agreement_budget_type_mou_agreement_budget_type_id`)
    REFERENCES `mydb`.`mou_agreement_budget_type` (`mou_agreement_budget_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`transaction_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transaction_status` (
  `transaction_status_id` INT NOT NULL,
  `transaction_status` VARCHAR(45) NULL,
  `transaction_status_desc` VARCHAR(45) NULL,
  `transaction_statuscol` VARCHAR(45) NULL,
  PRIMARY KEY (`transaction_status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`mou_transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`mou_transaction` (
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
    REFERENCES `mydb`.`budget` (`budget_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mou_transaction_user1`
    FOREIGN KEY (`created_user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mou_transaction_transaction_status1`
    FOREIGN KEY (`transaction_status_transaction_status_id`)
    REFERENCES `mydb`.`transaction_status` (`transaction_status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`customer_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`customer_users` (
  `idcustomer_users` INT NOT NULL,
  `customer_customer_id_number` INT NOT NULL,
  `user_user_id` INT NOT NULL,
  PRIMARY KEY (`idcustomer_users`),
  INDEX `fk_customer_users_customer1_idx` (`customer_customer_id_number` ASC),
  INDEX `fk_customer_users_user1_idx` (`user_user_id` ASC),
  CONSTRAINT `fk_customer_users_customer1`
    FOREIGN KEY (`customer_customer_id_number`)
    REFERENCES `mydb`.`customer` (`customer_id_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_users_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
