-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 28, 2021 at 10:44 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbtokobuku`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_tokobuku`
--

CREATE TABLE `tbl_tokobuku` (
  `id` int(11) NOT NULL,
  `judulbuku` varchar(100) NOT NULL,
  `penerbitbuku` varchar(100) NOT NULL,
  `genrebuku` varchar(100) NOT NULL,
  `hargabuku` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_tokobuku`
--

INSERT INTO `tbl_tokobuku` (`id`, `judulbuku`, `penerbitbuku`, `genrebuku`, `hargabuku`) VALUES
(17, 'Soal UNBK', 'Moh Ridho Rahanyamtel', 'Pendidikan', 'Rp.50.000'),
(18, 'Kunci Kesuksesan', 'M Nur Windarko', 'Pendidikan', 'Rp.100.000'),
(22, 'Belajar Java', 'Erik Masarrang', 'Pendidikan', 'Rp.100.000'),
(23, 'Majalah Mobil Sport', 'Enrico Zulian Ersada Ginting', 'Sport', 'Rp.100.000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_tokobuku`
--
ALTER TABLE `tbl_tokobuku`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_tokobuku`
--
ALTER TABLE `tbl_tokobuku`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
