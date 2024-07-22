-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Ven 30 Août 2019 à 07:41
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `pharmabase`
--

-- --------------------------------------------------------

--
-- Structure de la table `combocode`
--

CREATE TABLE IF NOT EXISTS `combocode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=25 ;

--
-- Contenu de la table `combocode`
--

INSERT INTO `combocode` (`id`, `code`) VALUES
(12, 'PARA'),
(13, 'QUIN'),
(14, 'BACT'),
(19, 'IBU'),
(20, 'INDO'),
(23, 'EFF'),
(24, 'COLD');

-- --------------------------------------------------------

--
-- Structure de la table `combodate`
--

CREATE TABLE IF NOT EXISTS `combodate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `combodate`
--

INSERT INTO `combodate` (`id`, `date`) VALUES
(2, '2019-08-10'),
(3, '2020-11-23'),
(5, '2021-09-30'),
(6, '2018-09-06'),
(7, '2019-07-30'),
(8, '2019-09-01');

-- --------------------------------------------------------

--
-- Structure de la table `combojour`
--

CREATE TABLE IF NOT EXISTS `combojour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `combojour`
--

INSERT INTO `combojour` (`id`, `date`) VALUES
(1, '2019-08-28'),
(2, '2019-08-29'),
(3, '2012-12-22'),
(4, '2022-12-20'),
(5, '');

-- --------------------------------------------------------

--
-- Structure de la table `combonom`
--

CREATE TABLE IF NOT EXISTS `combonom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `combonom`
--

INSERT INTO `combonom` (`id`, `nom`) VALUES
(1, 'PARACETAMOL'),
(2, 'QUININE'),
(3, 'IBUPROFEN'),
(4, 'BACTRIME'),
(6, 'INDOCIDE'),
(7, 'EFFERALGANT'),
(8, 'COLD TIME');

-- --------------------------------------------------------

--
-- Structure de la table `mouv`
--

CREATE TABLE IF NOT EXISTS `mouv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) DEFAULT NULL,
  `nom` varchar(70) DEFAULT NULL,
  `datexp` date DEFAULT NULL,
  `nature` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `mouv`
--

INSERT INTO `mouv` (`id`, `code`, `nom`, `datexp`, `nature`) VALUES
(1, 'PARA', 'PARACETAMOL', '2019-08-20', 'Depot'),
(2, 'IBU', 'IBUPROFEN', '2019-08-21', 'Retrait'),
(3, 'QUIN', 'QUININE', '2019-08-22', 'Depot'),
(4, 'VERM', 'VERMOX', '2019-08-23', 'Retrait'),
(5, 'ARTH', 'ARTHEMETER', '2019-08-24', 'Depot');

-- --------------------------------------------------------

--
-- Structure de la table `mouvement`
--

CREATE TABLE IF NOT EXISTS `mouvement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prix` int(11) DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `datexp` date DEFAULT NULL,
  `nature` varchar(10) DEFAULT NULL,
  `jour` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=28 ;

--
-- Contenu de la table `mouvement`
--

INSERT INTO `mouvement` (`id`, `code`, `nom`, `prix`, `quantite`, `datexp`, `nature`, `jour`) VALUES
(9, 'QUIN', 'QUININE', 100, 25, '2021-09-30', 'Depot', '2019-08-28'),
(10, 'QUIN', 'QUININE', 100, 30, '2021-09-30', 'Depot', '2019-08-29'),
(11, 'QUIN', 'QUININE', 100, 60, '2020-11-23', 'Depot', '2019-08-29'),
(12, 'QUIN', 'QUININE', 100, 8, '2018-09-06', 'Depot', '2019-08-28'),
(13, 'QUIN', 'QUININE', 100, 20, '2021-09-30', 'Retrait', '2019-08-28'),
(14, 'QUIN', 'QUININE', 100, 5, '2019-07-30', 'Depot', '2019-08-28'),
(15, 'QUIN', 'QUININE', 100, 1, '2019-09-01', 'Depot', '2019-08-29'),
(16, 'QUIN', 'QUININE', 100, 1, '2019-09-01', 'Retrait', '2019-08-28'),
(17, 'QUIN', 'QUININE', 100, 3, '2019-07-30', 'Retrait', '2019-08-29'),
(18, 'QUIN', 'QUININE', 100, 2, '2019-07-30', 'Retrait', '2019-08-28'),
(19, 'QUIN', 'QUININE', 100, 10, '2020-11-23', 'Retrait', '2019-08-28'),
(20, 'PARA', 'PARACETAMOL', 100, 15, '2019-08-10', 'Depot', '2019-08-28'),
(21, 'BACT', 'BACTRIME', 400, 30, '2021-09-30', 'Depot', '2019-08-29'),
(22, 'PARA', 'PARACETAMOL', 100, 5, '2019-08-10', 'Retrait', '2019-08-28'),
(23, 'QUIN', 'QUININE', 100, 14, '2021-09-30', 'Retrait', '2019-08-28'),
(24, 'QUIN', 'QUININE', 100, 9, '2021-09-30', 'Retrait', '2012-12-22'),
(25, 'IBU', 'IBUPROFEN', 200, 85, '2020-11-23', 'Depot', '2019-08-28'),
(26, 'IBU', 'IBUPROFEN', 200, 6, '2020-11-23', 'Retrait', '2019-08-28'),
(27, 'IBU', 'IBUPROFEN', 200, 6, '2020-11-23', 'Retrait', '2019-08-28');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vue1`
--
CREATE TABLE IF NOT EXISTS `vue1` (
`id` int(11)
,`code` varchar(40)
,`nom` varchar(100)
,`prix` int(11)
,`quantite` bigint(20)
,`datexp` date
,`nature` varchar(10)
,`jour` date
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vuedate`
--
CREATE TABLE IF NOT EXISTS `vuedate` (
`code` varchar(40)
,`nom` varchar(100)
,`prix` int(11)
,`stock` decimal(41,0)
,`datexp` date
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vuemontant`
--
CREATE TABLE IF NOT EXISTS `vuemontant` (
`code` varchar(40)
,`nom` varchar(100)
,`prix` int(11)
,`stock` decimal(41,0)
,`montant` decimal(51,0)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vuevente`
--
CREATE TABLE IF NOT EXISTS `vuevente` (
`code` varchar(40)
,`nom` varchar(100)
,`prix` int(11)
,`quantite` decimal(32,0)
,`montant` decimal(42,0)
,`jour` date
);
-- --------------------------------------------------------

--
-- Structure de la vue `vue1`
--
DROP TABLE IF EXISTS `vue1`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vue1` AS select `mouvement`.`id` AS `id`,`mouvement`.`code` AS `code`,`mouvement`.`nom` AS `nom`,`mouvement`.`prix` AS `prix`,`mouvement`.`quantite` AS `quantite`,`mouvement`.`datexp` AS `datexp`,`mouvement`.`nature` AS `nature`,`mouvement`.`jour` AS `jour` from `mouvement` where (`mouvement`.`nature` = 'Depot') union select `mouvement`.`id` AS `id`,`mouvement`.`code` AS `code`,`mouvement`.`nom` AS `nom`,`mouvement`.`prix` AS `prix`,-(`mouvement`.`quantite`) AS `quantite`,`mouvement`.`datexp` AS `datexp`,`mouvement`.`nature` AS `nature`,`mouvement`.`jour` AS `jour` from `mouvement` where (`mouvement`.`nature` = 'Retrait');

-- --------------------------------------------------------

--
-- Structure de la vue `vuedate`
--
DROP TABLE IF EXISTS `vuedate`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vuedate` AS select `vue1`.`code` AS `code`,`vue1`.`nom` AS `nom`,`vue1`.`prix` AS `prix`,sum(`vue1`.`quantite`) AS `stock`,`vue1`.`datexp` AS `datexp` from `vue1` group by `vue1`.`nom`,`vue1`.`datexp`;

-- --------------------------------------------------------

--
-- Structure de la vue `vuemontant`
--
DROP TABLE IF EXISTS `vuemontant`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vuemontant` AS select `vue1`.`code` AS `code`,`vue1`.`nom` AS `nom`,`vue1`.`prix` AS `prix`,sum(`vue1`.`quantite`) AS `stock`,(`vue1`.`prix` * sum(`vue1`.`quantite`)) AS `montant` from `vue1` group by `vue1`.`nom`;

-- --------------------------------------------------------

--
-- Structure de la vue `vuevente`
--
DROP TABLE IF EXISTS `vuevente`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vuevente` AS select `mouvement`.`code` AS `code`,`mouvement`.`nom` AS `nom`,`mouvement`.`prix` AS `prix`,sum(`mouvement`.`quantite`) AS `quantite`,(`mouvement`.`prix` * sum(`mouvement`.`quantite`)) AS `montant`,`mouvement`.`jour` AS `jour` from `mouvement` where (`mouvement`.`nature` = 'Retrait') group by `mouvement`.`nom`,`mouvement`.`jour`;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
