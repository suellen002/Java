-- MySQL Script generated by MySQL Workbench
-- Wed Sep  6 02:52:24 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`usuario` ;

CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `idusuario` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `senha` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `nome` VARCHAR(100) NULL,
  `admin` INT UNSIGNED NULL,
  PRIMARY KEY (`idusuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`solicitacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`solicitacao` ;

CREATE TABLE IF NOT EXISTS `mydb`.`solicitacao` (
  `idsol` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` INT NOT NULL,
  `idusuario` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idsol`, `idusuario`),
  INDEX `fk_solicitacao_usuaio_idx` (`idusuario` ASC),
  CONSTRAINT `fk_solicitacao_usuaio`
    FOREIGN KEY (`idusuario`)
    REFERENCES `mydb`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`item` ;

CREATE TABLE IF NOT EXISTS `mydb`.`item` (
  `iditem` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `categoria` VARCHAR(45) NULL,
  `valor` DECIMAL NULL,
  `qtd` INT UNSIGNED NULL,
  PRIMARY KEY (`iditem`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`solicitacao_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`solicitacao_item` ;

CREATE TABLE IF NOT EXISTS `mydb`.`solicitacao_item` (
  `idsol` INT UNSIGNED NOT NULL,
  `iditem` INT UNSIGNED NOT NULL,
  `qtd` INT UNSIGNED NOT NULL,
  INDEX `fk_solicitacao_item_item1_idx` (`iditem` ASC),
  INDEX `fk_solicitacao_item_solicitacao1_idx` (`idsol` ASC),
  CONSTRAINT `fk_solicitacao_item_item1`
    FOREIGN KEY (`iditem`)
    REFERENCES `mydb`.`item` (`iditem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_solicitacao_item_solicitacao1`
    FOREIGN KEY (`idsol`)
    REFERENCES `mydb`.`solicitacao` (`idsol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT IGNORE INTO usuario (idusuario,senha,email,nome,admin) VALUES (1,'admin','admin','admin',1);