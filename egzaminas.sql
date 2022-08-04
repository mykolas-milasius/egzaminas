-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 04, 2022 at 12:12 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `egzaminas`
--

-- --------------------------------------------------------

--
-- Table structure for table `films`
--

CREATE TABLE `films` (
  `id` int(10) UNSIGNED NOT NULL,
  `pavadinimas` varchar(255) NOT NULL,
  `aprasas` varchar(255) NOT NULL,
  `reitingas` varchar(255) NOT NULL,
  `kategorija` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `films`
--

INSERT INTO `films` (`id`, `pavadinimas`, `aprasas`, `reitingas`, `kategorija`) VALUES
(11, 'Žiedų valdovas', 'LOTR', '100', 'Fantastika'),
(12, 'Star wars', 'test', '100', 'Fantastika'),
(13, 'Betmenas', 'Betmeno filmas gi', '100', 'Veiksmo');

-- --------------------------------------------------------

--
-- Table structure for table `films_kategorija`
--

CREATE TABLE `films_kategorija` (
  `id` int(11) UNSIGNED NOT NULL,
  `id_films` int(11) UNSIGNED NOT NULL,
  `id_kategorijos` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `films_kategorija`
--

INSERT INTO `films_kategorija` (`id`, `id_films`, `id_kategorijos`) VALUES
(1, 12, 1),
(2, 11, 1);

-- --------------------------------------------------------

--
-- Table structure for table `kategorija`
--

CREATE TABLE `kategorija` (
  `id` int(10) UNSIGNED NOT NULL,
  `pavadinimas` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategorija`
--

INSERT INTO `kategorija` (`id`, `pavadinimas`) VALUES
(1, 'Fantastika'),
(2, 'Dokumentika'),
(10, 'Biografija'),
(13, 'Veiksmo');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`) VALUES
(1, 'admin@admin.lt', 'admin', 'admin', '$2a$10$S6tnN3XTQuWFu3XlTgaUYuaZgL2RNKhmbmFfSNB/lkNdgjXVH.4/S'),
(3, 'test@test.lt', 'test', 'test', '$2a$10$OJAe39fRUSqDdzwNONY6D.a0C/maamxYYSxR5eX7gKSgXrns3bXCO'),
(4, 'AAAAAA@AAAAAA', 'aa', 'aa', '$2a$10$SEAx72HbruHG2.CkeZcY7ub0TaN5fNGNzlRRTvIJv3UyNURN/AUxC'),
(5, 'Jonas.Jonaitis@gmail.com', 'Jonas', 'Jonaitis', '$2a$10$qSwwGUVJJbCF00rBCa2EneqEwHU2xIqY/31flYwitItO1z3iIwI0u');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `roles_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`id`, `user_id`, `roles_id`) VALUES
(1, 1, 1),
(2, 3, 2),
(3, 4, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `films`
--
ALTER TABLE `films`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `films_kategorija`
--
ALTER TABLE `films_kategorija`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_films` (`id_films`),
  ADD KEY `id_kategorijos` (`id_kategorijos`);

--
-- Indexes for table `kategorija`
--
ALTER TABLE `kategorija`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `roles_id` (`roles_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `films`
--
ALTER TABLE `films`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `films_kategorija`
--
ALTER TABLE `films_kategorija`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `kategorija`
--
ALTER TABLE `kategorija`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `films_kategorija`
--
ALTER TABLE `films_kategorija`
  ADD CONSTRAINT `films_kategorija_ibfk_1` FOREIGN KEY (`id_films`) REFERENCES `films` (`id`),
  ADD CONSTRAINT `films_kategorija_ibfk_2` FOREIGN KEY (`id_kategorijos`) REFERENCES `kategorija` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
