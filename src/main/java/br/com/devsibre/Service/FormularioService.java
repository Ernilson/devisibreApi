package br.com.devsibre.Service;

import java.util.List;

import br.com.devsibre.Model.FormularioModel;

public interface FormularioService {

	List<FormularioModel> listAll();

	List<FormularioModel> findByNomeContainingIgnoreCase(String nome);

	boolean alterar(FormularioModel dto);

	FormularioModel getId(Long id);

	FormularioModel saveOrUpdate(FormularioModel cm);

	void delete(Long id);

}
