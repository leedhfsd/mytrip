-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafytrip` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `ssafytrip`.`members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafytrip`.`members` ;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`members` (
  `user_id` VARCHAR(20) NOT NULL,
  `user_pw` VARCHAR(200) NOT NULL,
  `email_id` VARCHAR(20) NOT NULL,
  `email_domain` VARCHAR(20) NOT NULL,
  `user_salt` VARCHAR(200) NOT NULL,
  `address` VARCHAR(200) NULL,
  `age` INT NULL,
  `user_sexual` VARCHAR(1) NULL,
  `phone_number` VARCHAR(20) NULL,
  `grade` VARCHAR(1) NULL,
  `fav_sido` VARCHAR(3) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`attraction_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafytrip`.`attraction_rating` ;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`attraction_rating` (
  `content_id` INT NOT NULL,
  `user_id` VARCHAR(20) NOT NULL,
  `rating` INT NOT NULL,
  `memo` VARCHAR(200) NULL,
  `insert_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`content_id`, `user_id`),
  INDEX `fk_attraction_rating_attraction_info_idx` (`content_id` ASC) VISIBLE,
  INDEX `fk_attraction_rating_members1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_attraction_rating_attraction_info`
    FOREIGN KEY (`content_id`)
    REFERENCES `ssafytrip`.`attraction_info` (`content_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_attraction_rating_members1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafytrip`.`members` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ssafytrip`.`member_plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafytrip`.`member_plan` ;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`member_plan` (
  `plan_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `update_date` DATE NOT NULL,
  PRIMARY KEY (`plan_id`),
  CONSTRAINT `fk_member_plan_members1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafytrip`.`members` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafytrip`.`plan_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafytrip`.`plan_detail` ;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`plan_detail` (
  `plan_id` INT NOT NULL,
  `plan_day` INT NOT NULL,
  `plan_index` INT NOT NULL,
  `content_id` INT NOT NULL,
  `start_time` TIMESTAMP NULL,
  `end_time` TIMESTAMP NULL,
  `memo` VARCHAR(100) NULL,
  PRIMARY KEY (`plan_id`, `plan_day`, `plan_index`),
  INDEX `fk_plan_detail_attraction_info1_idx` (`content_id` ASC) VISIBLE,
  CONSTRAINT `fk_plan_detail_member_plan1`
    FOREIGN KEY (`plan_id`)
    REFERENCES `ssafytrip`.`member_plan` (`plan_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plan_detail_attraction_info1`
    FOREIGN KEY (`content_id`)
    REFERENCES `ssafytrip`.`attraction_info` (`content_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
