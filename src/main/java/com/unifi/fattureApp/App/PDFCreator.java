package com.unifi.fattureApp.App;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.unifi.fattureApp.App.Client;

public class PDFCreator {
	private Company selectedCompany;
	private Client selectedClient;
	private Invoice selectedInvoice;
	
	final PDFont courierBoldFont = PDType1Font.COURIER_BOLD;


	public PDFCreator(Company company, Client client, Invoice invoice) {

		this.selectedCompany = company;
		this.selectedClient = client;
		this.selectedInvoice = invoice;
		
		create();
	}

	private void create() {

		final PDPage singlePage = new PDPage();

		try (final PDDocument document = new PDDocument()) {
			document.addPage(singlePage);
			 PDPageContentStream contentStream = new PDPageContentStream(document, singlePage);
			contentStream.beginText();
			contentStream = this.companyName(contentStream, singlePage);
			contentStream.endText();
			contentStream.close(); // Stream must be closed before saving document.
			document.save("EmptyPage.pdf");
		} catch (IOException ioEx) {
			System.err.println("Exception while trying to create blank document - " + ioEx);
		}
	}
	
	private PDPageContentStream companyName(PDPageContentStream cs, PDPage sp) {
		Font titleFont = new Font("Courier", Font.PLAIN, 16);
		String s = selectedCompany.getName();
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true); 
		float textWidth = (float) titleFont.getStringBounds(s, frc).getWidth();
		
		try {
			cs.setFont(courierBoldFont, 16);
			cs.newLineAtOffset(sp.getMediaBox().getWidth() / 2 - textWidth / 2, sp.getMediaBox().getHeight() - 100);
			cs.showText(selectedCompany.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;
		
	}
	
	private float pt2mm(float pt) {
		   return pt * 25.4f / 72;
	}
}
