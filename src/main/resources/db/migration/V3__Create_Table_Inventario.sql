CREATE TABLE IF NOT EXISTS `inventario` (
  `id_p` bigint(20) NOT NULL,
  `data` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `preco` decimal(19,2) DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  PRIMARY KEY (`id_p`)
);