package com.li.tools.utils.text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextTest {

    public static void main(String[] args)throws Exception {

        String outputFile = "H:/itext1.pdf";

        OutputStream os = new FileOutputStream(outputFile);       
        ITextRenderer renderer = new ITextRenderer();   
        
        String inputFile = "‪C:\\Users\\lijuntao\\Desktop\\test.html";
        String url = new File(inputFile).toURI().toURL().toString();
        url = "file:///D:/form/crForm-editor-1.2/preview/form_barthel_v1.html";
        renderer.setDocument(url);     

        // 解决中文支持问题       
        ITextFontResolver fontResolver = renderer.getFontResolver();      
        fontResolver.addFont("C:/Windows/Fonts/SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);       
        //解决图片的相对路径问题  
        renderer.getSharedContext().setBaseURL("file:///E:/workspace/PDF/WebContent/WEB-INF/");  
        renderer.layout();      
        renderer.createPDF(os);    

        os.flush();  
        os.close();  
        System.out.println("转换完成！");
    }
    
    @Test
	public void toPDF(){
		String pdfPath = "H:/test.pdf";
		String htmlCode = getHtml();
		Document doc = new Document(PageSize.A4);  
	    try {  
	        PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));  
	        doc.open();  
	        // 解决中文问题  
	        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
	        Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);  
	        Paragraph t = new Paragraph(htmlCode, FontChinese);  
	        doc.add(t);  
	        doc.close();  
	        System.out.println("文档创建成功");  
	    }catch(Exception e) {  
	        e.printStackTrace();  
	    }  
	}
	
	@Test
	public void toPDF2() throws IOException{
		Runtime.getRuntime().exec("wkhtmltopdf \"github.com/wkhtmltopdf/wkhtmltopdf\"  H:/github2.pdf");
		Runtime.getRuntime().exec("wkhtmltopdf \"https://baike.baidu.com/item/%E9%B9%A4%E9%A9%BC#hotspotmining\"  H:/baike2.pdf");
	}
	
	public String getHtml(){
		try {
			String html = FileUtils.readFileToString(new File("H:/Wandering.html"), "utf-8");
			System.out.println(html);
			return html;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}