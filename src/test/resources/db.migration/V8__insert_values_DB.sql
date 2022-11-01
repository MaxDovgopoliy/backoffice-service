INSERT INTO `tariffs` (`id`, `car_type`, `description`, `name`, `rate_per_hour`) VALUES ('1', 'medium', 'good for family trips', 'family', '200');
INSERT INTO `tariffs` (`id`, `car_type`, `description`, `name`, `rate_per_hour`) VALUES ('2', 'premium', 'convenient and prestigious', 'lakshery', '300');
INSERT INTO `tariffs` (`id`, `car_type`, `description`, `name`, `rate_per_hour`) VALUES ('3', 'economy', 'good for fast and regular trips', 'standart', '100');
INSERT INTO `tariffs` (`id`, `car_type`, `description`, `name`, `rate_per_hour`) VALUES ('4', 'business', 'good for work trips', 'work', '250');

INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`) VALUES ('2', '1');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`) VALUES ('3', '1');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`) VALUES ('4', '1');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`) VALUES ('1', '2');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`) VALUES ('2', '2');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`) VALUES ('3', '2');
INSERT INTO `cities_tariffs` (`tariff_id`, `city_id`) VALUES ('4', '2');

INSERT INTO `orders` (`id`, `car_id`, `car_type`, `end_date_time`, `price`, `start_date_time`, `user_id`, `tariff_id`) VALUES ('1', '1', 'medium', '2021-01-01 08:00:00.000000', '200', '2021-01-01 07:00:00.000000', '7', '1');
INSERT INTO `orders` (`id`, `car_id`, `car_type`, `end_date_time`, `price`, `start_date_time`, `user_id`, `tariff_id`) VALUES ('2', '13', 'premium', '2021-01-01 08:00:00.000000', '300', '2021-01-01 07:00:00.000000', '9', '2');
INSERT INTO `orders` (`id`, `car_id`, `car_type`, `end_date_time`, `price`, `start_date_time`, `user_id`, `tariff_id`) VALUES ('3', '16', 'premium', '2021-02-01 08:00:00.000000', '300', '2021-02-01 07:00:00.000000', '71', '2');