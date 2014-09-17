INSERT INTO `products` (`id`, `name`, `description`, `photo_file_name`, `number`)
VALUES (24, 'Chef', 'Chef a vendre', 'chef.jpg', 66666666);

INSERT INTO `items` (`id`, `number`, `product_id`, `description`, `price`) VALUES
(14, '98765435', 24, 'Fournisseur de bonbons', 10),
(15, '98765436', 24, 'Développeur fou', 11);