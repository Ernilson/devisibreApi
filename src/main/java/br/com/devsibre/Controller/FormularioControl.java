package br.com.devsibre.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devsibre.Model.FormularioModel;
import br.com.devsibre.ServiceImpl.FormularioServiceImpl;
import br.com.devsibre.UtilsReports.Formulario_Report;
import br.com.devsibre.security.DefaultError;

@RestController
@RequestMapping(value="/api")
public class FormularioControl {

	@Autowired
	private FormularioServiceImpl service;

	@Autowired
	private Formulario_Report cadreport;

	@Autowired
	private ServletContext context;
	
	
	//Metodo para salvar cadastro
    @RequestMapping(method = RequestMethod.POST, value = "/salvar")
    public ResponseEntity<FormularioModel> salvar(@RequestBody FormularioModel c) {
        service.saveOrUpdate(c);
        return new ResponseEntity<FormularioModel>(HttpStatus.OK);
    }

    //Metodo para listar todos e buscar os cadastros
    @GetMapping("/listarCadastro")
    public List<FormularioModel>lista(){   
        return service.listAll();
    }

    //Metodo para buscar cadastro      
    @GetMapping(path = {"/{id}" })
    public ResponseEntity<?> getById(@PathVariable long id){     	
    	try {
    		FormularioModel fm = service.getId(id);
    		return ResponseEntity.ok().body(fm);
		} catch (Exception e) {
			DefaultError err = new DefaultError();
			err.setTimestamp(Instant.now()); err.setStatus(HttpStatus.NOT_FOUND.value());
			err.setError("Resource not found");	err.setMessage(e.getMessage());
			err.setPath("/cadastro/ " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}       
		
    }   

    //Metodo para excluir dados do cadastro
    @DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public  ResponseEntity<?> remover(@PathVariable("id_c") Long id_c) {  
    	FormularioModel fm = service.getId(id_c);
    	if (fm != null) {
    		service.delete(id_c);		
    		return ResponseEntity.ok().build();
		}else{
			DefaultError err = new DefaultError ();
			err.setTimestamp(Instant.now()); err.setStatus(HttpStatus.NOT_FOUND.value());
			err.setError("Resource not found");	err.setMessage("Erro");
			err.setPath("/cadastro/ " + id_c);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}
		
    	
    }
    
    //----------------------------------------------------------------------------------------------
    @GetMapping(value = "/pdf")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) {
        List<FormularioModel> cad = service.listAll();
        boolean isFlag = cadreport.creatPdf(cad, context, request, response);
        if (isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "cad" + ".pdf");
            filedownload(fullPath, response, "cad.pdf");
        }
    }
    
     @GetMapping(value = "/Exls")
    public void createExcel(HttpServletRequest request, HttpServletResponse response) {
        List<FormularioModel> cad = service.listAll();
        boolean isFlag = cadreport.createExcel(cad, context, request, response);
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
