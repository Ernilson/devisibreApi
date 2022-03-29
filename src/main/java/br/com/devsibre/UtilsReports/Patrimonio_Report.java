package br.com.devsibre.UtilsReports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.devsibre.Model.PatrimonioModel;
import br.com.devsibre.ServiceImpl.PatrimonioServiceImpl;

@Service
public class Patrimonio_Report implements Patrimonio_Report_Service{

	@Override
	public boolean creatPdf(List<PatrimonioModel> pat, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 40, 40, 10, 10);

        try {
            String filePath = context.getRealPath("/resources/reports");
            File file = new File(filePath);
            boolean exists = new File(filePath).exists();
            if (!exists) {
                new File(filePath).mkdirs();
            }

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "pat" + ".pdf"));
            document.open();

            Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);

            Paragraph paragraph = new Paragraph("Todos os patastros", mainFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10);

            Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody = FontFactory.getFont("Arial", 8, BaseColor.BLACK);

            float[] columnWidths = {5f, 5f, 4f, 3f, 3f};
            table.setWidths(columnWidths);
           
            PdfPCell descricao = new PdfPCell(new Paragraph("Descrição", tableHeader));
            descricao.setBorderColor(BaseColor.BLACK);
            descricao.setPaddingLeft(10);
            descricao.setHorizontalAlignment(Element.ALIGN_CENTER);
            descricao.setVerticalAlignment(Element.ALIGN_CENTER);
            descricao.setBackgroundColor(BaseColor.WHITE);
            descricao.setExtraParagraphSpace(5f);
            table.addCell(descricao);

            PdfPCell qtd = new PdfPCell(new Paragraph("Quantidade", tableHeader));
            qtd.setBorderColor(BaseColor.BLACK);
            qtd.setPaddingLeft(10);
            qtd.setHorizontalAlignment(Element.ALIGN_CENTER);
            qtd.setVerticalAlignment(Element.ALIGN_CENTER);
            qtd.setBackgroundColor(BaseColor.WHITE);
            qtd.setExtraParagraphSpace(5f);
            table.addCell(qtd);

            PdfPCell dataValue = new PdfPCell(new Paragraph("data", tableHeader));
            dataValue.setBorderColor(BaseColor.BLACK);
            dataValue.setPaddingLeft(10);
            dataValue.setHorizontalAlignment(Element.ALIGN_CENTER);
            dataValue.setVerticalAlignment(Element.ALIGN_CENTER);
            dataValue.setBackgroundColor(BaseColor.WHITE);
            dataValue.setExtraParagraphSpace(5f);
            table.addCell(dataValue);
             
            PdfPCell precoValue = new PdfPCell(new Paragraph("Preço", tableHeader));
            precoValue.setBorderColor(BaseColor.BLACK);
            precoValue.setPaddingLeft(10);
            precoValue.setHorizontalAlignment(Element.ALIGN_CENTER);
            precoValue.setVerticalAlignment(Element.ALIGN_CENTER);
            precoValue.setBackgroundColor(BaseColor.WHITE);
            precoValue.setExtraParagraphSpace(5f);
            table.addCell(precoValue);
                     	
            
            for (PatrimonioModel patri : pat) {
            	PdfPCell descricao1 = new PdfPCell(new Paragraph(patri.getDescricao().toString(), tableBody));
            	descricao1.setBorderColor(BaseColor.BLACK);
            	descricao1.setPaddingLeft(10);
            	descricao1.setHorizontalAlignment(Element.ALIGN_CENTER);
            	descricao1.setVerticalAlignment(Element.ALIGN_CENTER);
            	descricao1.setBackgroundColor(BaseColor.WHITE);
            	descricao1.setExtraParagraphSpace(5f);
                table.addCell(descricao1);               


                PdfPCell qtdValue = new PdfPCell(new Paragraph(Integer.toString(patri.getQuantidade()), tableBody));
                qtdValue.setBorderColor(BaseColor.BLACK);
                qtdValue.setPaddingLeft(10);
                qtdValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                qtdValue.setVerticalAlignment(Element.ALIGN_CENTER);
                qtdValue.setBackgroundColor(BaseColor.WHITE);
                qtdValue.setExtraParagraphSpace(5f);
                table.addCell(qtdValue);

                PdfPCell dataValue1 = new PdfPCell(new Paragraph(patri.getData(), tableBody));
                dataValue1.setBorderColor(BaseColor.BLACK);
                dataValue1.setPaddingLeft(10);
                dataValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
                dataValue1.setVerticalAlignment(Element.ALIGN_CENTER);
                dataValue1.setBackgroundColor(BaseColor.WHITE);
                dataValue1.setExtraParagraphSpace(5f);
                table.addCell(dataValue1);
 
                PdfPCell precoValue1 = new PdfPCell(new Paragraph(patri.getPreco().toString(), tableBody));
                precoValue1.setBorderColor(BaseColor.BLACK);
                precoValue1.setPaddingLeft(10);
                precoValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
                precoValue1.setVerticalAlignment(Element.ALIGN_CENTER);
                precoValue1.setBackgroundColor(BaseColor.WHITE);
                precoValue1.setExtraParagraphSpace(5f);
                table.addCell(precoValue1);
               
            }

            document.add(table);
            document.close();
            writer.close();
            return true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PatrimonioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PatrimonioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
	}

	@Override
	public boolean createExcel(List<PatrimonioModel> pat, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return false;
	}


	
}
