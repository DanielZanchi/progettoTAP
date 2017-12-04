package com.unifi.fattureApp.App;

import java.awt.Font;
import java.awt.Insets;
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
	
	private float topInsets = 88;
	private float insets = 60;
	
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
			contentStream = this.companyName(contentStream, singlePage);
			contentStream = this.footer(contentStream, singlePage);
			contentStream.close(); // Stream must be closed before saving document.
			document.save("EmptyPage.pdf");
		} catch (IOException ioEx) {
			System.err.println("Exception while trying to create blank document - " + ioEx);
		}
	}
	
	private PDPageContentStream companyName(PDPageContentStream cs, PDPage sp) {
		int fontSize = 16;
		String stringToPrint = selectedCompany.getName();
		float textWidth = getTextWidth(fontSize, stringToPrint);
		
		try {
			cs.beginText();
			cs.setFont(courierBoldFont, fontSize);
			System.out.println(sp.getMediaBox().getHeight() - topInsets);
			cs.newLineAtOffset(verticalCenter(sp, textWidth), sp.getMediaBox().getHeight() - topInsets);
			cs.showText(stringToPrint);
			cs.endText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;
	}
	
	private PDPageContentStream footer(PDPageContentStream cs, PDPage sp) {
		int fontSize = 11;
		String stringToPrint = selectedCompany.getName() + selectedCompany.getVatCode() + selectedCompany.getAddress() + selectedCompany.getCity() + selectedCompany.getCountry() + selectedCompany.getPhone() + selectedCompany.getEmail();
		float textWidth = getTextWidth(fontSize, stringToPrint);
		
		try {
			cs.beginText();
			cs.setFont(courierBoldFont, fontSize);
			cs.newLineAtOffset(verticalCenter(sp, textWidth), topInsets);
			cs.showText(stringToPrint);
			cs.endText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;
	}
	
	private float verticalCenter(PDPage sp,  float textWidth) {
		return (sp.getMediaBox().getWidth() / 2 - textWidth / 2);
	}
	
	private float verticalLeft(PDPage sp,  float textWidth) {
		return (insets);
	}
	
	private float verticalRight(PDPage sp,  float textWidth) {
		return (sp.getMediaBox().getWidth() - insets - textWidth);
	}
	
	private float getTextWidth(int fontSize, String s) {
		Font titleFont = new Font("Courier", Font.PLAIN, fontSize);
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true); 
		float textWidth = (float) titleFont.getStringBounds(s, frc).getWidth();
		return textWidth;
	}
	

}
