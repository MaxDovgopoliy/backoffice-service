INSERT INTO `countries` (`id`, `currency`,`unit_of_speed`, `name`)
VALUES ('1', 'UAH', 'KPH', 'Ukraine');

INSERT INTO `cities` (`id`, `coefficient_for_tariff`, `name`, `country_id`)
VALUES ('1', '1.1', 'Lviv', '1');

INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('1', '45', '30', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('2', '45', '20', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('3', '55', '20', '1');
INSERT INTO `coordinates` (`id`, `latitude`, `longitude`, `city_id`)
VALUES ('4', '55', '30', '1');

INSERT INTO `tariffs` (`id`, `car_type`, `currency`, `description`, `name`, `rate_per_hour`)
VALUES ('1', 'medium', 'UAH', 'good for family trips', 'family', '200');

INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`)
VALUES ('1', '1');