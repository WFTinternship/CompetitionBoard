-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema competition_board
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema competition_board
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `competition_board` DEFAULT CHARACTER SET latin1 ;
USE `competition_board` ;

-- -----------------------------------------------------
-- Table `competition_board`.`manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `competition_board`.`manager` (
  `manager_id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `avatar` VARCHAR(255) NULL,
  PRIMARY KEY (`manager_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 559
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `competition_board`.`tournament_format`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `competition_board`.`tournament_format` (
  `format_id` INT(11) NOT NULL AUTO_INCREMENT,
  `format_type` ENUM('ROUND_ROBBIN', 'OLYMPIC') NOT NULL,
  PRIMARY KEY (`format_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `competition_board`.`tournament`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `competition_board`.`tournament` (
  `tournament_id` INT(11) NOT NULL AUTO_INCREMENT,
  `tournament_name` VARCHAR(45) NULL DEFAULT NULL,
  `start_date` DATETIME NULL DEFAULT NULL,
  `end_date` DATETIME NULL DEFAULT NULL,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  `tournament_description` TEXT NULL DEFAULT NULL,
  `tournament_format_id` INT(11) NULL DEFAULT NULL,
  `manager_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`tournament_id`),
  INDEX `manager_id_idx` (`manager_id` ASC),
  INDEX `fk_tournament_1_idx` (`tournament_format_id` ASC),
  CONSTRAINT `FK_manager_id`
    FOREIGN KEY (`manager_id`)
    REFERENCES `competition_board`.`manager` (`manager_id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tournament_1`
    FOREIGN KEY (`tournament_format_id`)
    REFERENCES `competition_board`.`tournament_format` (`format_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 435
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `competition_board`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `competition_board`.`group` (
  `group_id` INT(11) NOT NULL AUTO_INCREMENT,
  `participants_count` INT(11) NULL DEFAULT NULL,
  `round` INT(11) NULL DEFAULT NULL,
  `next_round_participants` INT(11) NULL DEFAULT NULL,
  `tournament_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  INDEX `tournam_id_idx` (`tournament_id` ASC),
  CONSTRAINT `tournam_id`
    FOREIGN KEY (`tournament_id`)
    REFERENCES `competition_board`.`tournament` (`tournament_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 238
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `competition_board`.`participant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `competition_board`.`participant` (
  `participant_id` INT(11) NOT NULL AUTO_INCREMENT,
  `is_team` TINYINT(1) NOT NULL DEFAULT '1',
  `avatar` VARCHAR(255) NULL DEFAULT NULL,
  `participant_info` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`participant_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 55
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `competition_board`.`group_participant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `competition_board`.`group_participant` (
  `group_id` INT(11) NOT NULL,
  `participant_id` INT(11) NOT NULL,
  PRIMARY KEY (`group_id`, `participant_id`),
  INDEX `fk_group_participant_1_idx` (`participant_id` ASC),
  CONSTRAINT `fk_group_participant_1`
    FOREIGN KEY (`participant_id`)
    REFERENCES `competition_board`.`participant` (`participant_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_participant_2`
    FOREIGN KEY (`group_id`)
    REFERENCES `competition_board`.`group` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `competition_board`.`match`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `competition_board`.`match` (
  `match_id` INT(11) NOT NULL AUTO_INCREMENT,
  `group_id` INT(11) NULL DEFAULT NULL,
  `participant_1_id` INT(11) NULL DEFAULT NULL,
  `participant_2_id` INT(11) NULL DEFAULT NULL,
  `score_participant_1` INT(11) NULL DEFAULT NULL,
  `score_participant_2` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`match_id`),
  INDEX `match_group_id_idx` (`group_id` ASC),
  CONSTRAINT `match_group_id`
    FOREIGN KEY (`group_id`)
    REFERENCES `competition_board`.`group` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 96
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `competition_board`.`media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `competition_board`.`media` (
  `media_id` INT(11) NOT NULL AUTO_INCREMENT,
  `photo` VARCHAR(255) NULL DEFAULT NULL,
  `video` VARCHAR(255) NULL DEFAULT NULL,
  `tournament_id` INT(11) NULL DEFAULT NULL,
  `manager_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`media_id`),
  INDEX `media_id_idx` (`tournament_id` ASC),
  INDEX `fk_media_idx` (`manager_id` ASC),
  CONSTRAINT `fk_media`
    FOREIGN KEY (`manager_id`)
    REFERENCES `competition_board`.`manager` (`manager_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `media_id`
    FOREIGN KEY (`tournament_id`)
    REFERENCES `competition_board`.`tournament` (`tournament_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 130
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `competition_board`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `competition_board`.`member` (
  `member_id` INT(11) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `surname` VARCHAR(20) NOT NULL,
  `position` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  UNIQUE INDEX `member_id_UNIQUE` (`member_id` ASC),
  CONSTRAINT `fk_member_2`
    FOREIGN KEY (`member_id`)
    REFERENCES `competition_board`.`participant` (`participant_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `competition_board`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `competition_board`.`team` (
  `team_id` INT(11) NOT NULL,
  `team_name` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `team_id_UNIQUE` (`team_id` ASC),
  CONSTRAINT `fk_team_1`
    FOREIGN KEY (`team_id`)
    REFERENCES `competition_board`.`participant` (`participant_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
