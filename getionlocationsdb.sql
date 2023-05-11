-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 11 mai 2023 à 02:53
-- Version du serveur : 5.7.36
-- Version de PHP : 7.2.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `getionlocationsdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `num_cni` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `adresse`, `nom`, `num_cni`) VALUES
(1, 'Kara', 'ALOU Johns', '0236-8974-6957'),
(2, 'Lomé', 'POLI', '2152-5865-8956');

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `mdp` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `poste` varchar(255) DEFAULT NULL,
  `prenoms` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`id`, `adresse`, `login`, `mdp`, `nom`, `poste`, `prenoms`) VALUES
(1, 'Lomé', 'ajavon', 'marc', 'AJAVON', 'DIRECTEUR', 'Marc'),
(2, 'Lomé', 'bona', 'bona', 'BONA', 'RESPONSABLE', 'Emile'),
(3, 'Lomé', 'pepe', 'pepe', 'PEPY', 'RESPONSABLE', 'Jean'),
(4, 'Lomé', 'admin', 'admin', 'SUPER', 'DIRECTEUR', 'Admin'),
(5, 'Kara', 'respo', 'respo', 'RESPONSABLE', 'RESPONSABLE', 'Respo'),
(6, 'Lomé', 'subor', 'subor', 'SUBORDONNE', 'SUBORDONNE', 'Subor');

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `id` bigint(20) NOT NULL,
  `date_location` datetime(6) DEFAULT NULL,
  `dure_location` int(11) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `employe_id` bigint(20) DEFAULT NULL,
  `voiture_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtlwklm3ks6wa03sfliibb7o2x` (`client_id`),
  KEY `FKtotmid6slle5xu5diyt6k9gbq` (`employe_id`),
  KEY `FKei6x977yqtpvkdkncbi5cq3kr` (`voiture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`id`, `date_location`, `dure_location`, `client_id`, `employe_id`, `voiture_id`) VALUES
(1, '2023-05-02 14:32:31.000000', 5, 1, 1, 5),
(2, '2023-05-02 14:32:31.000000', 7, 2, 2, 4),
(3, '2023-04-04 14:32:31.000000', 5, 1, 1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

DROP TABLE IF EXISTS `voiture`;
CREATE TABLE IF NOT EXISTS `voiture` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_aquisition` datetime(6) DEFAULT NULL,
  `marque` varchar(255) DEFAULT NULL,
  `num_mat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `voiture`
--

INSERT INTO `voiture` (`id`, `date_aquisition`, `marque`, `num_mat`) VALUES
(1, '2023-04-27 00:00:00.000000', 'Mazda', 'BU-5264'),
(2, '2023-04-29 00:00:00.000000', 'Benz', 'BJ-2516'),
(3, '2023-04-29 00:00:00.000000', 'Toyota', 'BJ-8785'),
(4, '2023-05-07 00:00:00.000000', 'Patrol', 'BD-4589'),
(5, '2023-04-28 00:00:00.000000', 'Honda', 'BI-2020'),
(6, '2023-04-26 00:00:00.000000', 'Patrol', 'BT-6542');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `FKei6x977yqtpvkdkncbi5cq3kr` FOREIGN KEY (`voiture_id`) REFERENCES `voiture` (`id`),
  ADD CONSTRAINT `FKtlwklm3ks6wa03sfliibb7o2x` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKtotmid6slle5xu5diyt6k9gbq` FOREIGN KEY (`employe_id`) REFERENCES `employe` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
