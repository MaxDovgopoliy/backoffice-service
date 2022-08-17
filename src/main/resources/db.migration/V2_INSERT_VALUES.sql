INSERT INTO `backoffice`.`tariffs` (`id`, `car_type`, `description`, `name`, `rate_per_hour`) VALUES ('1', 'sedan', 'for family', 'tarif1', '120');
INSERT INTO `backoffice`.`tariffs` (`id`, `car_type`, `description`, `name`, `rate_per_hour`) VALUES ('2', 'moto', 'good one', 'tarif2', '130');
INSERT INTO `backoffice`.`tariffs` (`id`, `car_type`, `description`, `name`, `rate_per_hour`) VALUES ('3', 'hatchback', 'No description', 'tarif3', '140');


INSERT INTO `backoffice`.`orders` (`id`, `car_id`, `car_type`, `end_date`, `prise`, `start_date`, `user_id`, `tariff_id`) VALUES ('1', '1', 'sedan', '20021-01-01 22:00:00', '120', '20021-01-01 21:00:00', '1', '1');
INSERT INTO `backoffice`.`orders` (`id`, `car_id`, `car_type`, `end_date`, `prise`, `start_date`, `user_id`, `tariff_id`) VALUES ('2', '2', 'moto', '20021-02-01 22:00:00', '130', '20021-02-01 21:00:00', '3', '2');
INSERT INTO `backoffice`.`orders` (`id`, `car_id`, `car_type`, `end_date`, `prise`, `start_date`, `user_id`, `tariff_id`) VALUES ('3', '5', 'sedan', '20021-01-07 22:00:00', '120', '20021-01-07 21:00:00', '2', '1');


INSERT INTO `backoffice`.`areas` (`id`, `city`, `country`) VALUES ('1', 'Lviv', 'Ukraine');
INSERT INTO `backoffice`.`areas` (`id`, `city`, `country`) VALUES ('2', 'Kyiv', 'Ukraine');
INSERT INTO `backoffice`.`areas` (`id`, `city`, `country`) VALUES ('3', 'Dnipro', 'Ukraine');


INSERT INTO `backoffice`.`coordinates` (`id`, `latitude`, `longitude`, `area_id`) VALUES ('1', '123.32', '423.43', '1');
INSERT INTO `backoffice`.`coordinates` (`id`, `latitude`, `longitude`, `area_id`) VALUES ('2', '456.65', '435.56', '1');
INSERT INTO `backoffice`.`coordinates` (`id`, `latitude`, `longitude`, `area_id`) VALUES ('3', '876.54', '548.44', '1');
INSERT INTO `backoffice`.`coordinates` (`id`, `latitude`, `longitude`, `area_id`) VALUES ('4', '112.33', '332.32', '2');
INSERT INTO `backoffice`.`coordinates` (`id`, `latitude`, `longitude`, `area_id`) VALUES ('5', '334.55', '423.43', '2');
INSERT INTO `backoffice`.`coordinates` (`id`, `latitude`, `longitude`, `area_id`) VALUES ('6', '346.56', '546.43', '2');
INSERT INTO `backoffice`.`coordinates` (`id`, `latitude`, `longitude`, `area_id`) VALUES ('7', '123.32', '998.77', '3');
INSERT INTO `backoffice`.`coordinates` (`id`, `latitude`, `longitude`, `area_id`) VALUES ('8', '838.55', '546.88', '3');
INSERT INTO `backoffice`.`coordinates` (`id`, `latitude`, `longitude`, `area_id`) VALUES ('9', '930.44', '435.77', '3');
