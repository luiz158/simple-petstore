SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE items;
TRUNCATE products;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `products` (`id`, `name`, `description`, `photo_file_name`, `number`) VALUES
(21, 'Reptiles', 'Cold-blooded friends', 'iguana.png', '60090989'),
(22, 'Labrador Retriever', 'Your best friend', 'dog.png', '70080944'),
(23, 'Husky', 'Perfect winter jogging partner', 'husky.jpg', '45454545'),
(24, 'Chef', 'Chef a vendre', 'chef.jpg', 66666666),
(25, 'Bougon', 'Raleur marrant', 'bougon.jpg', 66666667),
(26, 'Boucle d\'or', 'Experte reveil', 'boucle.jpg', 66666668);



INSERT INTO `items` (`id`, `number`, `product_id`, `description`, `price`) VALUES
(8, '12345678', 21, 'Green adult lizard', 18.50),
(9, '12345679', 21, 'Madagascar iguana', 37.95),
(10, '12345680', 21, 'Spiny tailed iguana', 49.99),
(11, '98765432', 22, 'Golden Retriever', 79.99),
(12, '98765433', 22, 'Labrador Retriever black male', 69.99),
(13, '98765434', 23, '3 months Husky', 69.99),
(14, '98765435', 24, 'Fournisseur de bonbons', 10),
(15, '98765436', 24, 'Developpeur fou', 11),
(16, '98765437', 25, 'Prix a débattre', 1),
(17, '98765438', 26, 'Travail en decale', 99);
