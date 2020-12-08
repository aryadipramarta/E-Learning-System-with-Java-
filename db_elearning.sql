-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2019 at 01:43 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_elearning`
--

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `kodekelas` varchar(30) NOT NULL,
  `namakelas` varchar(30) NOT NULL,
  `matapelajaran` varchar(30) NOT NULL,
  `jam` varchar(30) NOT NULL,
  `tentor_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`kodekelas`, `namakelas`, `matapelajaran`, `jam`, `tentor_id`) VALUES
('F12345', 'IF4206', 'FISIKAMENENGAH', '11.00', 'Tentor_4'),
('K012345', 'IF4012', 'F1', '08.00', 'Tentor_3'),
('KUG022', 'IF4209', 'F1', '11.00', 'Tentor_3'),
('KUGH012', 'IF4204', 'F1', '11.00', 'Tentor_3');

-- --------------------------------------------------------

--
-- Table structure for table `kelas_student`
--

CREATE TABLE `kelas_student` (
  `id` int(11) NOT NULL,
  `nim` varchar(15) NOT NULL,
  `kodekelas` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelas_student`
--

INSERT INTO `kelas_student` (`id`, `nim`, `kodekelas`) VALUES
(9, '1301184175', 'KUG022'),
(10, '1301184175', 'KUGH012');

-- --------------------------------------------------------

--
-- Table structure for table `matapelajaran`
--

CREATE TABLE `matapelajaran` (
  `kodemapel` varchar(30) NOT NULL,
  `namamapel` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `matapelajaran`
--

INSERT INTO `matapelajaran` (`kodemapel`, `namamapel`) VALUES
('F1', 'FISIKADASAR'),
('F2', 'FISIKAMENENGAH');

-- --------------------------------------------------------

--
-- Table structure for table `materi`
--

CREATE TABLE `materi` (
  `id` int(11) NOT NULL,
  `kodemateri` varchar(30) NOT NULL,
  `namamateri` varchar(30) NOT NULL,
  `bagian` int(30) NOT NULL,
  `ID_Tentor` varchar(10) NOT NULL,
  `kodekelas` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `materi`
--

INSERT INTO `materi` (`id`, `kodemateri`, `namamateri`, `bagian`, `ID_Tentor`, `kodekelas`) VALUES
(13, 'TF0912', 'Gerak Parabola', 3, 'Tentor_3', 'KUG022'),
(14, 'TF0912', 'Gerak Parabola', 3, 'Tentor_3', 'KUGH012');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `nim` varchar(15) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `kelamin` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `noHP` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`nim`, `nama`, `kelamin`, `email`, `noHP`, `username`, `password`) VALUES
('1301112312', 'Yanti', 'Perempuan', 'Yanti@gmail.com', '08921312333', 'yanti123', 'yanti123'),
('13011841111', 'Yantoo', 'Laki - Laki', 'Andi@gmail', '081232222', 'andi123', 'andi123'),
('1301184175', 'Aryadi Pramarta', 'Laki - Laki', 'aryadipramarta9@gmail.com', '081353389699', 'aryadip', 'aryadip'),
('13011901231', 'Bambang', 'Laki - Laki', 'bambang@gmail', '0819021312', 'bambang123', 'bambang123'),
('13012345901', 'Komang Triolascarya', 'Laki - Laki', 'komangrio@gmail.com', '081902111', 'rio123', 'rio123'),
('1307283901', 'Anya', 'Perempuan', 'anya@gmail.com', '0891222111', 'anya123', 'anya123');

-- --------------------------------------------------------

--
-- Table structure for table `tentortable`
--

CREATE TABLE `tentortable` (
  `ID_Tentor` varchar(10) NOT NULL,
  `Nama` varchar(150) NOT NULL,
  `Jenis_Kelamin` varchar(30) NOT NULL,
  `Email` varchar(60) NOT NULL,
  `No_HP` varchar(15) NOT NULL,
  `Lama_Kerja` int(11) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tentortable`
--

INSERT INTO `tentortable` (`ID_Tentor`, `Nama`, `Jenis_Kelamin`, `Email`, `No_HP`, `Lama_Kerja`, `Username`, `Password`) VALUES
('Tentor_0', 'kadek', 'laki-laki', 'kadek', '123123', 1, 'kadek', 'kadek'),
('Tentor_1', 'aryadi', 'laki-laki', 'aryadi', '081238', 1, 'aryadi', 'aryadi'),
('Tentor_2', 'sheva', 'laki-laki', 'sheva', 'sheva', 1, 'sheva', 'sheva'),
('Tentor_3', 'ShintaCantik', 'perempuan', 'shinta123@gmail.coms', '08123451929', 3, 'shinta123', 'shinta123'),
('Tentor_4', 'Krisna', 'laki-laki', 'krisnabram@gmail.com', '0812345111', 3, 'KrisnaB', 'KrisnaB');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`kodekelas`);

--
-- Indexes for table `kelas_student`
--
ALTER TABLE `kelas_student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `nim` (`nim`),
  ADD KEY `kodekelas` (`kodekelas`),
  ADD KEY `kodekelas_2` (`kodekelas`);

--
-- Indexes for table `matapelajaran`
--
ALTER TABLE `matapelajaran`
  ADD PRIMARY KEY (`kodemapel`);

--
-- Indexes for table `materi`
--
ALTER TABLE `materi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ID_Tentor` (`ID_Tentor`),
  ADD KEY `kodekelas` (`kodekelas`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `tentortable`
--
ALTER TABLE `tentortable`
  ADD PRIMARY KEY (`ID_Tentor`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kelas_student`
--
ALTER TABLE `kelas_student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `materi`
--
ALTER TABLE `materi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kelas_student`
--
ALTER TABLE `kelas_student`
  ADD CONSTRAINT `fk_student` FOREIGN KEY (`nim`) REFERENCES `student` (`nim`),
  ADD CONSTRAINT `kelas_student_ibfk_1` FOREIGN KEY (`kodekelas`) REFERENCES `kelas` (`kodekelas`);

--
-- Constraints for table `materi`
--
ALTER TABLE `materi`
  ADD CONSTRAINT `materi_ibfk_1` FOREIGN KEY (`ID_Tentor`) REFERENCES `tentortable` (`ID_Tentor`),
  ADD CONSTRAINT `materi_ibfk_2` FOREIGN KEY (`kodekelas`) REFERENCES `kelas` (`kodekelas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
