package br.com.devsibre.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devsibre.Model.FormularioModel;
import br.com.devsibre.Model.PatrimonioModel;
import br.com.devsibre.ServiceImpl.PatrimonioServiceImpl;
import br.com.devsibre.UtilsReports.Patrimonio_Report;

@RestController
@RequestMapping(value = "/patrimonio")
public class PatrimonioControl {

	@Autowired
	private PatrimonioServiceImpl psl;

	@Autowired
	private Patrimonio_Report patreport;

	@Autowired
	private ServletContext context;

	// Metodo para listar todos os patastros
	@GetMapping("/lista_patrimonio")
	public List<PatrimonioModel> listarPatrimonio() {
		return psl.listAll();
	}

	// Metodo para salvar debitos
	@RequestMapping(method = RequestMethod.POST, value = "/salvar_Patrimonio")
	public ResponseEntity<PatrimonioModel> salvar_Invent(@RequestBody PatrimonioModel c) {

		psl.saveOrUpdate(c);
		return new ResponseEntity<PatrimonioModel>(HttpStatus.OK);
	}

	// Metodo para alterar débitos
	@PutMapping("/editePatrimonio/{id_p}")
	public String editarInvent(@PathVariable long id_p, PatrimonioModel pts) {
		PatrimonioModel ptt = new PatrimonioModel();
		ptt.setId_p(pts.getId_p());
		ptt.setDescricao(pts.getDescricao());
		psl.alterar(ptt);

		return "ok";
	}

	// Metodo para excluir dados do débitos
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<PatrimonioModel> remove_Inventario(@PathVariable("id") long id_p) {
		psl.delete(id_p);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/pdf")
	public void createPdf(HttpServletRequest request, HttpServletResponse response) {
		List<PatrimonioModel> pat = psl.listAll();
		boolean isFlag = patreport.creatPdf(pat, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "pat" + ".pdf");
			filedownload(fullPath, response, "pat.pdf");
		}
	}

	@GetMapping(value = "/Exls")
	public void createExcel(HttpServletRequest request, HttpServletResponse response) {
		List<PatrimonioModel> pat = psl.listAll();
		boolean isFlag = patreport.createExcel(pat, context, request, response);
	}

	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);

		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + fileName);
				OutputStream outputStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();
			} catch (Exception e) {
			}
		}
	}
}
