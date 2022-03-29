package br.com.devsibre.UtilsReports;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.devsibre.Model.PatrimonioModel;

public interface Patrimonio_Report_Service {

	public boolean creatPdf(List<PatrimonioModel> cad, ServletContext context, HttpServletRequest request, HttpServletResponse response);

    boolean createExcel(List<PatrimonioModel> cad, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
