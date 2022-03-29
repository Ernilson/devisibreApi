package br.com.devsibre;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.devsibre.Model.PatrimonioModel;
import br.com.devsibre.Service.FormularioService;
import br.com.devsibre.Service.PatrimonioService;

@SpringBootTest
class DevsibreApplicationTests {
	
	@Autowired
	private FormularioService service;
	
	@Autowired
	private PatrimonioService service2;

	@Test
	void contextLoads() {
//		FormularioModel fm = new FormularioModel();
//		
//		fm.setNome("teste9");
//		fm.setFone("1234");
//		fm.setEmail("teste@teste.com");
//		fm.setData("27/03/2022");
//		fm.setStatus("membro");
//		fm.setCep("72601108");
//		fm.setBairro("recanto");
//		fm.setLocalidade("aqui");
//		fm.setUf("DF");
//		
//		service.saveOrUpdate(fm);
		
		PatrimonioModel pm = new PatrimonioModel();
		pm.setDescricao("teste1");
		pm.setQuantidade(10);
		pm.setData("28/03/2022");
		pm.setPreco(new BigDecimal("13.70"));
		
		service2.saveOrUpdate(pm);
	}

}
