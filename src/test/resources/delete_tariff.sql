# INSERT INTO `countries` (`id`, `currency`,`unit_of_speed`, `name`)
# VALUES ('1', 'UAH', 'KPH', 'Ukraine');

DELETE FROM `orders` WHERE (`id` = '1');
DELETE FROM `cities_tariffs` WHERE (`city_id` = '1') and (`tariff_id` = '1');
DELETE FROM `cities_tariffs` WHERE (`city_id` = '2') and (`tariff_id` = '1');
DELETE FROM `cities_tariffs` WHERE (`city_id` = '3') and (`tariff_id` = '1');
DELETE FROM `tariffs` WHERE (`id` = '1');
