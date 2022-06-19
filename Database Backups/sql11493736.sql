-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql11.freesqldatabase.com
-- Generation Time: Jun 01, 2022 at 02:10 PM
-- Server version: 5.5.62-0ubuntu0.14.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sql11493736`
--

-- --------------------------------------------------------

--
-- Table structure for table `Matches`
--

CREATE TABLE `Matches` (
  `Id` int(11) NOT NULL,
  `Team1` varchar(250) NOT NULL,
  `Team2` varchar(250) NOT NULL,
  `Importance` int(11) NOT NULL,
  `Competition_type` varchar(250) NOT NULL,
  `Round` varchar(250) NOT NULL,
  `Team1_Score` int(11) NOT NULL,
  `Team2_Score` int(11) NOT NULL,
  `PSO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Matches`
--

INSERT INTO `Matches` (`Id`, `Team1`, `Team2`, `Importance`, `Competition_type`, `Round`, `Team1_Score`, `Team2_Score`, `PSO`) VALUES
(8, 'Egypt', 'France', 10, 'Friendly Match', 'Group Stage', 2, 4, 1),
(9, 'Egypt', 'France', 10, 'Friendly Match', 'Group Stage', 2, 4, 1),
(12, 'Germany', 'Brazil', 40, 'Confederations Cup', 'Quarter Final', 2, 3, 0),
(13, 'England', 'United States', 40, 'Confederation final competition', 'Semi Final', 2, 0, 1),
(14, 'Morocco', 'Algeria', 25, 'Qualifier for World Cup', 'Final', 0, 4, 1),
(15, 'France', 'Austria', 25, 'Nations League', 'Round of 16', 0, 1, 0),
(16, 'Sudan', 'Libya', 10, 'Friendly Match', 'Group Stage', 2, 4, 0),
(17, 'Egypt', 'France', 10, 'Friendly Match', 'Quarter Final', 2, 4, 1),
(18, 'Algeria', 'Austria', 30, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(19, 'Algeria', 'Austria', 30, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(20, 'Algeria', 'Austria', 30, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(21, 'Libya', 'France', 30, 'Nations League', 'Quarter Final', 3, 2, 1),
(22, 'Egypt', 'United States', 40, 'Confedrations final competition', 'Semi Final', 0, 1, 0),
(23, 'Libya', 'France', 15, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(24, 'South Africa', 'Austria', 30, 'Friendly Match Group Stage', 'Round of 16', 0, 0, 1),
(25, 'Bulgaria', 'Brazil', 25, 'Qualifier for World Cup', 'Round of 16', 0, 0, 1),
(26, 'Algeria', 'Bulgaria', 30, 'Nations League', 'Final', 4, 1, 0),
(27, 'Algeria', 'Austria', 30, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(28, 'Libya', 'France', 30, 'Nations League', 'Quarter Final', 3, 2, 1),
(29, 'Egypt', 'United States', 40, 'Confedrations final competition', 'Semi Final', 0, 1, 0),
(30, 'Libya', 'France', 15, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(31, 'South Africa', 'Austria', 30, 'Friendly Match Group Stage', 'Round of 16', 0, 0, 1),
(32, 'Bulgaria', 'Brazil', 25, 'Qualifier for World Cup', 'Round of 16', 0, 0, 1),
(33, 'Algeria', 'Bulgaria', 30, 'Nations League', 'Final', 4, 1, 0),
(34, 'Algeria', 'Austria', 30, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(35, 'Libya', 'France', 30, 'Nations League', 'Quarter Final', 3, 2, 1),
(36, 'Egypt', 'United States', 40, 'Confedrations final competition', 'Semi Final', 0, 1, 0),
(37, 'Libya', 'France', 15, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(38, 'South Africa', 'Austria', 30, 'Friendly Match Group Stage', 'Round of 16', 0, 0, 1),
(39, 'Bulgaria', 'Brazil', 25, 'Qualifier for World Cup', 'Round of 16', 0, 0, 1),
(40, 'Algeria', 'Bulgaria', 30, 'Nations League', 'Final', 4, 1, 0),
(41, 'Algeria', 'Austria', 30, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(42, 'Libya', 'France', 30, 'Nations League', 'Quarter Final', 3, 2, 1),
(43, 'Egypt', 'United States', 40, 'Confedrations final competition', 'Semi Final', 0, 1, 0),
(44, 'Libya', 'France', 15, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(45, 'South Africa', 'Austria', 30, 'Friendly Match Group Stage', 'Round of 16', 0, 0, 1),
(46, 'Bulgaria', 'Brazil', 25, 'Qualifier for World Cup', 'Round of 16', 0, 0, 1),
(47, 'Algeria', 'Bulgaria', 30, 'Nations League', 'Final', 4, 1, 0),
(48, 'Algeria', 'Austria', 30, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(49, 'Libya', 'France', 30, 'Nations League', 'Quarter Final', 3, 2, 1),
(50, 'Egypt', 'United States', 40, 'Confedrations final competition', 'Semi Final', 0, 1, 0),
(51, 'Libya', 'France', 15, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(52, 'South Africa', 'Austria', 30, 'Friendly Match Group Stage', 'Round of 16', 0, 0, 1),
(53, 'Bulgaria', 'Brazil', 25, 'Qualifier for World Cup', 'Round of 16', 0, 0, 1),
(54, 'Algeria', 'Bulgaria', 30, 'Nations League', 'Final', 4, 1, 0),
(55, 'Algeria', 'Austria', 30, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(56, 'Libya', 'France', 30, 'Nations League', 'Quarter Final', 3, 2, 1),
(57, 'Egypt', 'United States', 40, 'Confedrations final competition', 'Semi Final', 0, 1, 0),
(58, 'Libya', 'France', 15, 'Qualifier for World Cup', 'Final', 3, 2, 0),
(59, 'South Africa', 'Austria', 30, 'Friendly Match Group Stage', 'Round of 16', 0, 0, 1),
(60, 'Bulgaria', 'Brazil', 25, 'Qualifier for World Cup', 'Round of 16', 0, 0, 1),
(61, 'Algeria', 'Bulgaria', 30, 'Nations League', 'Final', 4, 1, 0),
(62, 'Algeria', 'Austria', 25, 'Qualifier for Confederation final competition', 'Semi Final', 0, 1, 0),
(63, 'Algeria', 'Austria', 25, 'Qualifier for Confederation final competition', 'Quarter Final', 1, 0, 0),
(64, 'Demark', 'Estonia', 10, 'Friendly Match', 'Group Stage', 4, 1, 1),
(65, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(66, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(67, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(68, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(69, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(70, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(71, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(72, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(73, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(74, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(75, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(76, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(77, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(78, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(79, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(80, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(81, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(82, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(83, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(84, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(85, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(86, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(87, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(88, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(89, 'Egypt', 'Egypt', 60, 'World Cup', 'Final', 3, 2, 0),
(90, 'Egypt', 'England', 10, 'Friendly Match', 'Group Stage', 5, 4, 1),
(91, 'Egypt', 'England', 10, 'Friendly Match', 'Group Stage', 5, 4, 1),
(92, 'Italy', 'Egypt', 25, 'Nations League', 'Quarter Final', 5, 3, 1),
(93, 'England', 'Italy', 5, 'Friendly Match', 'Group Stage', 3, 2, 1),
(94, 'England', 'Egypt', 3, 'Friendl_Match', 'Final', 2, 0, 0),
(96, 'Brazil', 'Algeria', 60, 'World Cup', 'Final', 5, 0, 0),
(97, 'Brazil', 'Algeria', 60, 'World Cup', 'Final', 5, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Matches_Test`
--

CREATE TABLE `Matches_Test` (
  `Id` int(11) NOT NULL,
  `Team1` varchar(250) NOT NULL,
  `Team2` varchar(250) NOT NULL,
  `Importance` int(11) NOT NULL,
  `Competition_type` varchar(250) NOT NULL,
  `Round` varchar(250) NOT NULL,
  `Team1_Score` int(11) NOT NULL,
  `Team2_Score` int(11) NOT NULL,
  `PSO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Matches_Test`
--

INSERT INTO `Matches_Test` (`Id`, `Team1`, `Team2`, `Importance`, `Competition_type`, `Round`, `Team1_Score`, `Team2_Score`, `PSO`) VALUES
(3, 'Egypt', 'France', 10, 'Friendly Match', 'Round of 16', 1, 0, 0),
(4, 'Egypt', 'France', 10, 'Friendly Match', 'Round of 16', 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `score_history`
--

CREATE TABLE `score_history` (
  `TeamName` varchar(11) NOT NULL,
  `MatchID` int(11) NOT NULL,
  `Month` int(11) NOT NULL,
  `Year` int(11) NOT NULL,
  `Score` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `score_history`
--

INSERT INTO `score_history` (`TeamName`, `MatchID`, `Month`, `Year`, `Score`) VALUES
('Algeria', 62, 5, 2022, 1420.53122434),
('Algeria', 63, 5, 2022, 1100.3441241),
('Algeria', 96, 6, 2022, 22.364557370857483),
('Algeria', 97, 4, 2022, 22.364557370857483),
('Austria', 63, 5, 2022, 1123.0745756579913),
('Brazil', 96, 6, 2022, 31.286617706124055),
('Brazil', 97, 4, 2022, 60.77307279839158),
('Demark', 64, 5, 2022, 1395.933399262844),
('Egypt', 2, 1, 2022, 1492.22431),
('Egypt', 3, 1, 2021, 1295.2),
('Egypt', 4, 4, 2021, 1500.5),
('Egypt', 65, 1, 2022, 1530),
('Egypt', 66, 1, 2021, 1150.2234),
('Egypt', 67, 1, 2022, 1336.875),
('Egypt', 68, 1, 2021, 1511.98),
('Egypt', 70, 1, 2022, 1470),
('Egypt', 71, 1, 2001, 1530),
('Egypt', 72, 1, 2001, 1530),
('Egypt', 73, 1, 2001, 1530),
('Egypt', 74, 1, 2018, 1530),
('Egypt', 75, 1, 2001, 1530),
('Egypt', 76, 1, 2001, 1530),
('Egypt', 77, 1, 2001, 1530),
('Egypt', 78, 1, 2001, 1530),
('Egypt', 79, 1, 2001, 1530),
('Egypt', 81, 1, 2001, 1530),
('Egypt', 83, 1, 2001, 1530),
('Egypt', 84, 1, 2001, 1530),
('Egypt', 85, 1, 2001, 1530),
('Egypt', 86, 1, 2001, 1530),
('Egypt', 87, 1, 2001, 1530),
('Egypt', 88, 1, 2001, 1530),
('Egypt', 89, 1, 2001, 1530),
('Egypt', 90, 5, 2022, 1530.0281046197322),
('Egypt', 91, 3, 2022, 1530.0562031946774),
('England', 90, 5, 2022, -0.028104619732101915),
('England', 91, 3, 2022, -0.056203194677270245),
('Estonia', 64, 5, 2022, 31.37660073715594),
('Germany', 82, 1, 2001, 1530),
('Libya', 69, 1, 2021, 1530),
('Sudan', 80, 1, 2022, 1530);

-- --------------------------------------------------------

--
-- Table structure for table `score_history_test`
--

CREATE TABLE `score_history_test` (
  `TeamName` varchar(11) NOT NULL,
  `MatchID` int(11) NOT NULL,
  `Month` int(11) NOT NULL,
  `Year` int(11) NOT NULL,
  `Score` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Teams`
--

CREATE TABLE `Teams` (
  `Name` varchar(250) NOT NULL,
  `Score` double NOT NULL,
  `Rank` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Teams`
--

INSERT INTO `Teams` (`Name`, `Score`, `Rank`) VALUES
('Algeria', 22.364557370857483, 2),
('Austria', 23.074575657991286, 3),
('Brazil', 60.77307279839158, 4),
('Bulgaria', 162.5, 15),
('Canada', 139.5, 16),
('Chad', 98.32, 6),
('China', 47.2, 14),
('Colombia', 98.04, 16),
('Croatia', 102.3, 18),
('Demark', 195.93339926284406, 17),
('Egypt', 1530.0562031946774, 7),
('England', -0.056203194677270245, 8),
('Estonia', 31.37660073715594, 13),
('Finland', 47.2, 14),
('France', 0, 0),
('Germany', 20.5, 1),
('Ghana', 34.91, 13),
('Greece', 98.04, 16),
('Hong Kong', 102.3, 18),
('India', 192.4, 17),
('Italy', 0, 11),
('Libya', 5.3, 4),
('Morocco', 10.5, 6),
('South Africa', 30.54, 2),
('Sudan', 5, 10),
('Sweden', 0, 0),
('United States', 2.5, 9);

-- --------------------------------------------------------

--
-- Table structure for table `Teams_test`
--

CREATE TABLE `Teams_test` (
  `Name` varchar(250) NOT NULL,
  `Score` double NOT NULL,
  `Rank` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Matches`
--
ALTER TABLE `Matches`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Team1` (`Team1`),
  ADD KEY `Team2` (`Team2`);

--
-- Indexes for table `Matches_Test`
--
ALTER TABLE `Matches_Test`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Team1` (`Team1`),
  ADD KEY `Team2` (`Team2`);

--
-- Indexes for table `score_history`
--
ALTER TABLE `score_history`
  ADD PRIMARY KEY (`TeamName`,`MatchID`);

--
-- Indexes for table `score_history_test`
--
ALTER TABLE `score_history_test`
  ADD PRIMARY KEY (`TeamName`,`MatchID`);

--
-- Indexes for table `Teams`
--
ALTER TABLE `Teams`
  ADD PRIMARY KEY (`Name`);

--
-- Indexes for table `Teams_test`
--
ALTER TABLE `Teams_test`
  ADD PRIMARY KEY (`Name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Matches`
--
ALTER TABLE `Matches`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;
--
-- AUTO_INCREMENT for table `Matches_Test`
--
ALTER TABLE `Matches_Test`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Matches`
--
ALTER TABLE `Matches`
  ADD CONSTRAINT `Matches_ibfk_4` FOREIGN KEY (`Team2`) REFERENCES `Teams` (`Name`) ON DELETE CASCADE,
  ADD CONSTRAINT `Matches_ibfk_1` FOREIGN KEY (`Team1`) REFERENCES `Teams` (`Name`),
  ADD CONSTRAINT `Matches_ibfk_2` FOREIGN KEY (`Team2`) REFERENCES `Teams` (`Name`),
  ADD CONSTRAINT `Matches_ibfk_3` FOREIGN KEY (`Team1`) REFERENCES `Teams` (`Name`) ON DELETE CASCADE;

--
-- Constraints for table `score_history`
--
ALTER TABLE `score_history`
  ADD CONSTRAINT `score_history_ibfk_3` FOREIGN KEY (`TeamName`) REFERENCES `Teams` (`Name`) ON DELETE CASCADE,
  ADD CONSTRAINT `score_history_ibfk_1` FOREIGN KEY (`TeamName`) REFERENCES `Teams` (`Name`),
  ADD CONSTRAINT `score_history_ibfk_2` FOREIGN KEY (`TeamName`) REFERENCES `Teams` (`Name`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
