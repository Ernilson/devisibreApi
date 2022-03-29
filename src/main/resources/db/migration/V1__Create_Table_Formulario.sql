CREATE TABLE IF NOT EXISTS `formulario` (
  `id_c` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `data` varchar(255) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `fone` varchar(20) DEFAULT NULL,
  `localidade` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_c`)
);