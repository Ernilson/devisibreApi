package br.com.devsibre.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devsibre.Model.FormularioModel;
import br.com.devsibre.Repository.FormularioRepository;
import br.com.devsibre.Service.FormularioService;

@Service
public class FormularioServiceImpl implements FormularioService {

	@Autowired
	private FormularioRepository formularioRepo;
	
	@Override
	public List<FormularioModel> listAll() {
		 List<FormularioModel> cm = new ArrayList<>();
		 formularioRepo.findAll().forEach(cm::add); //fun with Java 8
	        return cm;
	}

	@Override
	public List<FormularioModel> findByNomeContainingIgnoreCase(String nome) {
		return formularioRepo.findByNomeContainingIgnoreCase(nome);	        
	}

	@Override
	public boolean alterar(FormularioModel dto) {
		 try {
			 formularioRepo.save(dto);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	}

	@Override
	public FormularioModel getId(Long id) {
		 return formularioRepo.findById(id).get();
	}

	@Override
	public FormularioModel saveOrUpdate(FormularioModel cm) {
		formularioRepo.save(cm);
	        return cm;
	}

	@Override
	public void delete(Long id) {
		formularioRepo.deleteById(id);
	}

}
