DROP TABLE if exists schema_final_project.admin;
CREATE TABLE `schema_final_project`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) UNIQUE NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
);
DROP TABLE if exists schema_final_project.reader;
CREATE TABLE `schema_final_project`.`reader` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `surname` VARCHAR(100) NOT NULL,
  `login` DATE NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `email` VARCHAR(20) NOT NULL,
  `account` INT NOT NULL ,
  PRIMARY KEY (`id`)
);
DROP TABLE if exists schema_final_project.journal;
CREATE TABLE `schema_final_project`.`journal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `topic` VARCHAR(100) NOT NULL,
  `publisher` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
);
DROP TABLE if exists schema_final_project.journal_reader;
CREATE TABLE `schema_final_project`.`journal_reader` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_reader` INT NOT NULL ,
  `id_journal` INT NOT NULL ,
  FOREIGN KEY (`id_reader`) REFERENCES schema_final_project.reader(id),
  FOREIGN KEY (`id_journal`) REFERENCES schema_final_project.journal(id),
  UNIQUE (id_journal,id_reader),
PRIMARY KEY ( `id`)
)
  CHARSET utf8 COLLATE utf8_general_ci;