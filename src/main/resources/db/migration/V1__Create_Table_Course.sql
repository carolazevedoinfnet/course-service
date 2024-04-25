CREATE TABLE `course` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `prof` longtext,
  `price` decimal(65,2) NOT NULL,
  `title` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
