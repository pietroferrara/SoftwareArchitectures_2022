SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `completed_delivery` (
  `id` int NOT NULL,
  `x` int NOT NULL,
  `y` int NOT NULL,
  `time` datetime(6) DEFAULT NULL,
  `drone` int DEFAULT NULL,
  `item` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `completed_delivery`
--

INSERT INTO `completed_delivery` (`id`, `x`, `y`, `time`, `drone_id`, `item_id`) VALUES
(7, 1, 1, '2022-11-08 14:42:09.684000', 5, 2),
(9, 12, 12, '2022-11-08 15:05:38.865000', 5, 2),
(11, 10, 10, '2022-11-08 15:07:40.569000', 4, 1),
(24, 100, 100, '2022-11-14 12:40:48.033000', 5, 2),
(25, 30, 20, '2022-11-14 12:42:04.833000', 4, 1),
(32, 100, 100, '2022-11-15 11:07:24.745000', 26, 1),
(33, 100, 100, '2022-11-15 11:08:34.235000', 28, 3),
(34, 100, 100, '2022-11-15 11:09:17.770000', 27, 2),
(35, 10, 10, '2022-11-15 14:02:13.658000', 22, 1),
(36, 10, 10, '2022-11-15 14:04:12.054000', 26, 1),
(40, 100, 100, '2022-11-16 12:33:56.091000', 4, 1),
(41, 200, 200, '2022-11-16 12:36:04.781000', 5, 1),
(42, 100, 100, '2022-11-16 12:38:53.524000', 5, 1),
(46, 100, 100, '2022-11-27 10:21:30.906000', 4, 1),
(49, 0, 0, '2022-11-27 10:25:20.174000', 5, 2),
(50, 0, 0, '2022-11-27 10:25:20.200000', 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `delivery`
--

CREATE TABLE `delivery` (
  `id` int NOT NULL,
  `x` int NOT NULL,
  `y` int NOT NULL,
  `drone` int DEFAULT NULL,
  `item` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `delivery`
--

INSERT INTO `delivery` (`id`, `x`, `y`, `drone`, `item`) VALUES
(47, 0, 0, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `drone`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(51);

ALTER TABLE `completed_delivery`
  ADD PRIMARY KEY (`id`);
--  ADD KEY `FK4ch3x40md02vbo1y7j1tamj9n` (`drone_id`),
--  ADD KEY `FKqaei44lsfhttj3fv3cc17x46s` (`item_id`);

--
-- Indexes for table `delivery`
--
ALTER TABLE `delivery`
  ADD PRIMARY KEY (`id`);
--  ADD KEY `FK4y03p3qe1k40wjerkrecqscl4` (`drone_id`),
--  ADD KEY `FKo9grlj8a03tc7ox0v1k3u9nlg` (`item_id`);


-- ALTER TABLE `completed_delivery`
--  ADD CONSTRAINT `FK4ch3x40md02vbo1y7j1tamj9n` FOREIGN KEY (`drone_id`) REFERENCES `drone` (`id`),
--  ADD CONSTRAINT `FKqaei44lsfhttj3fv3cc17x46s` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`);

--
-- Constraints for table `delivery`
--
-- ALTER TABLE `delivery`
--  ADD CONSTRAINT `FK4y03p3qe1k40wjerkrecqscl4` FOREIGN KEY (`drone_id`) REFERENCES `drone` (`id`),
--  ADD CONSTRAINT `FKo9grlj8a03tc7ox0v1k3u9nlg` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`);
COMMIT;
