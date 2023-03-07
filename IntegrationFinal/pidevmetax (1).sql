-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 07 mars 2023 à 02:29
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pidevmetax`
--

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `id` int(11) NOT NULL,
  `valeur` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `avis`
--

INSERT INTO `avis` (`id`, `valeur`, `id_user`) VALUES
(1, 1, 1),
(2, 1, 1),
(4, 3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `avis_produit`
--

CREATE TABLE `avis_produit` (
  `id` int(11) NOT NULL,
  `valeur` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `avis_produit`
--

INSERT INTO `avis_produit` (`id`, `valeur`, `id_user`) VALUES
(1, 1, 1),
(2, 1, 1),
(4, 3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `blog`
--

CREATE TABLE `blog` (
  `id_blg` int(11) NOT NULL,
  `titre_blg` varchar(255) NOT NULL,
  `email_blg` varchar(255) NOT NULL,
  `contenu_blg` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `blog`
--

INSERT INTO `blog` (`id_blg`, `titre_blg`, `email_blg`, `contenu_blg`) VALUES
(4, 'remerciement', 'amine.khalfaoui@esprit.tn', 'merci'),
(4, 'remerciement', 'amine.khalfaoui@esprit.tn', 'merci');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `nom`) VALUES
(1, 'boisson'),
(2, 'popCorn'),
(3, 'chocolat'),
(4, 'biscuit'),
(5, 'zeyda'),
(6, 'kkk'),
(10, '55');

-- --------------------------------------------------------

--
-- Structure de la table `categorie_event`
--

CREATE TABLE `categorie_event` (
  `id_categori` int(11) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie_event`
--

INSERT INTO `categorie_event` (`id_categori`, `description`) VALUES
(3, 'nininini'),
(9, '1111'),
(10, 'vgcdkekzk');

-- --------------------------------------------------------

--
-- Structure de la table `categorie_produit`
--

CREATE TABLE `categorie_produit` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie_produit`
--

INSERT INTO `categorie_produit` (`id`, `nom`) VALUES
(1, 'boisson'),
(2, 'popCorn'),
(3, 'chocolat'),
(4, 'biscuit'),
(5, 'zeyda'),
(6, 'kkk'),
(10, '55');

-- --------------------------------------------------------

--
-- Structure de la table `cinema`
--

CREATE TABLE `cinema` (
  `id` int(11) NOT NULL,
  `Nom_cinema` varchar(11) NOT NULL,
  `adresse` varchar(11) NOT NULL,
  `Num` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cinema`
--

INSERT INTO `cinema` (`id`, `Nom_cinema`, `adresse`, `Num`) VALUES
(4, 'rio', 'sousse', '11'),
(5, 'andre', 'azer', '11'),
(12, 'rio', 'sousse', '11'),
(14, 'rio', 'sousse', '11'),
(15, 'rio', 'sousse', '11'),
(4, 'rio', 'sousse', '11'),
(5, 'andre', 'azer', '11'),
(12, 'rio', 'sousse', '11'),
(14, 'rio', 'sousse', '11'),
(15, 'rio', 'sousse', '11');

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE `comment` (
  `idcomment` int(11) NOT NULL,
  `idevenement` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `commentaire` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`idcomment`, `idevenement`, `iduser`, `commentaire`) VALUES
(3, 14, 1, 'helllo'),
(4, 14, 1, 'helllo'),
(5, 14, 1, 'hello'),
(6, 8, 1, 'ggagagagaag'),
(7, 14, 1, 'hello'),
(8, 14, 1, 'whyyyy123y');

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `nbreplaces` int(11) NOT NULL,
  `nbreparticipants` int(11) NOT NULL,
  `DateDebut` date NOT NULL,
  `DateFin` date NOT NULL,
  `image` varchar(255) NOT NULL,
  `id_cat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `lieu`, `titre`, `nbreplaces`, `nbreparticipants`, `DateDebut`, `DateFin`, `image`, `id_cat`) VALUES
(8, 'aaa', 'aaa', 11, 0, '2023-02-02', '2023-02-02', 'event284285.jpg', 3),
(13, 'kebeli', 'thhttj', 5556, 0, '2024-09-02', '2024-09-02', 'event205131.jpg', 10),
(14, 'tuins', 'hachmi', 232, 0, '2024-03-02', '2024-09-02', 'event242719.jpg', 9);

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `id_offr` int(11) NOT NULL,
  `nomfilm_offr` varchar(255) NOT NULL,
  `contenu_offr` varchar(255) NOT NULL,
  `datedebut_offr` varchar(255) NOT NULL,
  `datefin_offr` varchar(255) NOT NULL,
  `code_offr` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `offre`
--

INSERT INTO `offre` (`id_offr`, `nomfilm_offr`, `contenu_offr`, `datedebut_offr`, `datefin_offr`, `code_offr`) VALUES
(1, '', 'aaaaa', 'aaaaa', 'aaaaaaa', 'aaaaaa'),
(2, 'Butterfly', 'aaaaa', 'aaaaa', 'aaaaaaa', '12345698'),
(3, 'Matrix', 'moitie du prix', '10/10/2023', '15/11/2023', '12356456'),
(4, 'ragnarok', 'half off', '10/10/2023', '20/10/2023', 'azerty'),
(1, '', 'aaaaa', 'aaaaa', 'aaaaaaa', 'aaaaaa'),
(2, 'Butterfly', 'aaaaa', 'aaaaa', 'aaaaaaa', '12345698'),
(3, 'Matrix', 'moitie du prix', '10/10/2023', '15/11/2023', '12356456'),
(4, 'ragnarok', 'half off', '10/10/2023', '20/10/2023', 'azerty');

-- --------------------------------------------------------

--
-- Structure de la table `offre_produit`
--

CREATE TABLE `offre_produit` (
  `idOffre` int(11) NOT NULL,
  `pourcentage` int(11) NOT NULL,
  `idProduit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `offre_produit`
--

INSERT INTO `offre_produit` (`idOffre`, `pourcentage`, `idProduit`) VALUES
(1, 0, 1),
(2, 20, 1),
(3, 30, 1),
(4, 30, 1),
(6, 40, 1);

-- --------------------------------------------------------

--
-- Structure de la table `parking`
--

CREATE TABLE `parking` (
  `id_parking` int(11) NOT NULL,
  `nom_parking` varchar(255) NOT NULL,
  `logo_parking` varchar(255) NOT NULL,
  `capacite_parking` int(11) NOT NULL,
  `takenP_parking` int(11) NOT NULL,
  `prix_parking` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `parking`
--

INSERT INTO `parking` (`id_parking`, `nom_parking`, `logo_parking`, `capacite_parking`, `takenP_parking`, `prix_parking`) VALUES
(9, 'Agore', '', 100, 50, 10),
(10, 'Parking3', '', 200, 50, 10),
(11, 'Parking4', '', 200, 100, 50),
(12, 'Parking5', '', 150, 20, 7),
(13, 'park1', '', 200, 100, 5000);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `idProduit` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix` float NOT NULL,
  `etat` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `idCategorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`idProduit`, `code`, `nom`, `quantite`, `prix`, `etat`, `description`, `image`, `idCategorie`) VALUES
(1, 'P88', 'PopCorn Sucré', 245, 12, 1, 'poCorn Bnin mosh normal', 'image', 2),
(2, 'B78', 'Coca Cola', 877, 13, 1, 'Gazouza Bnina mosh normal', 'image', 1),
(3, 'C88', 'Chocolat', 334, 9, 1, 'chocolat Bnina mosh normal', 'image', 3),
(9, 'col88', 'biscuit', 555, 7, 1, 'biscuit Bnin mosh normal', 'image', 3),
(10, 'kjj', 'bashkoutou', 555, 7, 0, 'null', 'null', 0),
(11, 'b5', 'chocotom', 44, 3, 0, 'null', 'null', 0),
(12, 'n55', 'bouga', 666, 7, 0, 'null', 'null', 0),
(13, 'ff22', 'mljfkj', 555, 5, 0, 'null', 'null', 0),
(14, 'l44', 'mmm', 88, 5, 0, 'null', 'null', 0),
(15, 'l55', 'kjhh', 88, 5, 0, 'null', 'null', 0),
(16, 'jh55', 'llll', 555, 9, 0, 'null', 'null', 0);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_reclamation` int(11) NOT NULL,
  `date_reclamation` date NOT NULL,
  `categorie_reclamation` varchar(255) NOT NULL,
  `type_reclamation` varchar(255) DEFAULT NULL,
  `message_reclamation` varchar(255) NOT NULL,
  `etat_reclamation` tinyint(1) DEFAULT NULL,
  `importance_reclamation` int(11) DEFAULT NULL,
  `reponse_reclamation` varchar(255) DEFAULT NULL,
  `cin` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_reclamation`, `date_reclamation`, `categorie_reclamation`, `type_reclamation`, `message_reclamation`, `etat_reclamation`, `importance_reclamation`, `reponse_reclamation`, `cin`) VALUES
(1, '2023-03-06', 'Evenements', 'Ticket', 'heeelloo', 1, 3, '', '1'),
(2, '2023-03-06', 'Evenements', 'Ticket', 'hhiiiii', 0, 3, '', '1'),
(3, '2023-03-06', 'Evenements', 'Staff', 'test test', 0, 2, '', '12345678');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_res` int(11) NOT NULL,
  `nom_res` varchar(255) NOT NULL,
  `prenom_res` varchar(255) NOT NULL,
  `email_res` varchar(255) NOT NULL,
  `nom_evnmt` varchar(255) NOT NULL,
  `typeticket_res` varchar(255) NOT NULL,
  `date_res` varchar(255) NOT NULL,
  `code_offr` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_res`, `nom_res`, `prenom_res`, `email_res`, `nom_evnmt`, `typeticket_res`, `date_res`, `code_offr`) VALUES
(34, 'bbbbb', 'bbbbbb', 'amine.khalfaoui@esprit.tn', 'bbbbbbb', 'bbbbb', '10/12/2055', 'bbbb'),
(35, 'Ouanes', 'Chebil', 'amine.khalfaoui@esprit.tn', 'Film Ragnarok', 'premium', '10/10/2023', 'azerty'),
(36, 'amine', 'khalfaoui', 'amine.khalfaoui98@gmail.com', 'Matrix', 'Balcony', 'azerty12', '10/10/2023'),
(34, 'bbbbb', 'bbbbbb', 'amine.khalfaoui@esprit.tn', 'bbbbbbb', 'bbbbb', '10/12/2055', 'bbbb'),
(35, 'Ouanes', 'Chebil', 'amine.khalfaoui@esprit.tn', 'Film Ragnarok', 'premium', '10/10/2023', 'azerty'),
(36, 'amine', 'khalfaoui', 'amine.khalfaoui98@gmail.com', 'Matrix', 'Balcony', 'azerty12', '10/10/2023');

-- --------------------------------------------------------

--
-- Structure de la table `reservationparking`
--

CREATE TABLE `reservationparking` (
  `id_reservation` int(11) NOT NULL,
  `id_parking` int(11) NOT NULL,
  `nb_hours` int(11) NOT NULL,
  `cin` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservationparking`
--

INSERT INTO `reservationparking` (`id_reservation`, `id_parking`, `nb_hours`, `cin`) VALUES
(2, 8, 3, '1'),
(3, 10, 3, '1'),
(4, 8, 3, '1'),
(5, 10, 3, '1'),
(6, 10, 3, '1'),
(7, 9, 3, '1');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE `salle` (
  `id` int(60) NOT NULL,
  `nom_salle` varchar(50) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `num_places` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`id`, `nom_salle`, `adresse`, `num_places`) VALUES
(1, 'bbs', 'aaq', '12'),
(2, 'test', 'manaarch', '51'),
(3, 'bbs', 'aaq', '12'),
(4, 'test', 'manaarch', '51'),
(5, 'bbs', 'aaq', '12'),
(6, 'bba', 'aaq', '12'),
(1, 'bbs', 'aaq', '12'),
(2, 'test', 'manaarch', '51'),
(3, 'bbs', 'aaq', '12'),
(4, 'test', 'manaarch', '51'),
(5, 'bbs', 'aaq', '12'),
(6, 'bba', 'aaq', '12');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `prenom_user` varchar(255) NOT NULL,
  `cin_user` varchar(255) NOT NULL,
  `email_user` varchar(255) NOT NULL,
  `role_user` varchar(255) NOT NULL,
  `mdp_user` varchar(255) NOT NULL,
  `Date_inscri` varchar(255) DEFAULT NULL,
  `nom_user` varchar(255) DEFAULT NULL,
  `Salaire` varchar(255) DEFAULT NULL,
  `Type_A` varchar(255) DEFAULT NULL,
  `date_contract` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id_user`, `prenom_user`, `cin_user`, `email_user`, `role_user`, `mdp_user`, `Date_inscri`, `nom_user`, `Salaire`, `Type_A`, `date_contract`) VALUES
(1, 'marwa', '18131516', 'marwa.triaa@esprit.tn', 'Agent', '112233', '20-02-2023', 'triaa', '1500', 'Gestion de Reservation', '01-01-2022'),
(9, 'Chebil', '1234567', 'Mohamedouanes.chebil@esprit.tn', 'Administrateur', '2222', NULL, 'Ouanes', '', '', ''),
(10, 'Amine', '12345678', 'Amine.Khalfaoui@esprit.tn', 'Membre', '3333', '22/01/2023', 'Khalfaoui', NULL, NULL, NULL),
(11, 'Chebil', '123458867', 'chaima.hichri1@esprit.tn', 'Agent', '4444', NULL, 'Ouanes', '1500', 'Stock', '2Ans'),
(14, 'Chebli', '75698478', '....@llllff.fr', 'Agent', '5555', NULL, 'Med', '1500', 'Cinemas et salles', '2ans'),
(21, 'Cheb', '12347859', 'Moham@gmail.com', 'Agent', 'aa', '22/01/2000', 'Emna', '2500', 'Gestion de Parkings', NULL),
(22, 'eeeee', '77777898', 'ahmed.mezghani@esprit.tn', 'Agent', 'bb', '22/12/2000', 'Cheee', '3000', 'Reclamation', NULL),
(23, 'Marwa', '78945123', 'Marwa@email.fr', 'Agent', 'cc', '78/01/20000', 'Marwa', '1200', 'Films et events', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `vote`
--

CREATE TABLE `vote` (
  `id` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_comm` int(11) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `vote`
--

INSERT INTO `vote` (`id`, `id_client`, `id_comm`, `type`) VALUES
(2, 1, 8, 2),
(3, 10, 13, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `avis_produit`
--
ALTER TABLE `avis_produit`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `categorie_event`
--
ALTER TABLE `categorie_event`
  ADD PRIMARY KEY (`id_categori`);

--
-- Index pour la table `categorie_produit`
--
ALTER TABLE `categorie_produit`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`idcomment`),
  ADD KEY `idevenement` (`idevenement`),
  ADD KEY `iduser` (`iduser`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cat` (`id_cat`);

--
-- Index pour la table `offre_produit`
--
ALTER TABLE `offre_produit`
  ADD PRIMARY KEY (`idOffre`);

--
-- Index pour la table `parking`
--
ALTER TABLE `parking`
  ADD PRIMARY KEY (`id_parking`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`idProduit`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_reclamation`);

--
-- Index pour la table `reservationparking`
--
ALTER TABLE `reservationparking`
  ADD PRIMARY KEY (`id_reservation`),
  ADD KEY `id_parking_constraint` (`id_parking`),
  ADD KEY `id_user_constr` (`cin`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`,`cin_user`);

--
-- Index pour la table `vote`
--
ALTER TABLE `vote`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_client` (`id_client`),
  ADD KEY `id_comm` (`id_comm`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `avis_produit`
--
ALTER TABLE `avis_produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `categorie_event`
--
ALTER TABLE `categorie_event`
  MODIFY `id_categori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `comment`
--
ALTER TABLE `comment`
  MODIFY `idcomment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `parking`
--
ALTER TABLE `parking`
  MODIFY `id_parking` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_reclamation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `vote`
--
ALTER TABLE `vote`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`idevenement`) REFERENCES `evenement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `evenement_ibfk_1` FOREIGN KEY (`id_cat`) REFERENCES `categorie_event` (`id_categori`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `vote`
--
ALTER TABLE `vote`
  ADD CONSTRAINT `vote_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vote_ibfk_2` FOREIGN KEY (`id_comm`) REFERENCES `evenement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
