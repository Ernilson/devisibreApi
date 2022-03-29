package br.com.devsibre.UtilsReports;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.devsibre.Model.FormularioModel;
import br.com.devsibre.Model.PatrimonioModel;

public interface Formulario_Report_Service {

	public boolean creatPdf(List<FormularioModel> cad, ServletContext context, HttpServletRequest request, HttpServletResponse response);

    boolean createExcel(List<FormularioModel> cad, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
