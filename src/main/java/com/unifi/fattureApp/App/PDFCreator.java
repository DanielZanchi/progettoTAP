package com.unifi.fattureApp.App;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.unifi.fattureApp.App.Client;

public class PDFCreator {
	private Company selectedCompany;
	private Client selectedClient;
	private Invoice selectedInvoice;

	private float topInsets = 88;
	private float margin = 60;

	final PDFont helveticaBoldFont = PDType1Font.HELVETICA_BOLD;
	final PDFont helveticaFont = PDType1Font.HELVETICA;

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
			contentStream = this.billTo(contentStream, singlePage);
			contentStream = this.invoiceNumber(contentStream, singlePage);
			contentStream = this.invoiceContainer(contentStream, singlePage);
			contentStream = this.invoiceDescription(contentStream, singlePage);
			contentStream = this.footer(contentStream, singlePage);
			contentStream.close(); // Stream must be closed before saving document.
			document.save("Invoice.pdf");
		} catch (IOException ioEx) {
			System.err.println("Exception while trying to create blank document - " + ioEx);
		}
	}

	private PDPageContentStream companyName(PDPageContentStream cs, PDPage sp) {
		int fontSize = 18;
		String stringToPrint = selectedCompany.getName();
		stringToPrint = stringToPrint.toUpperCase();
		float textWidth = getTextWidth(fontSize, stringToPrint);
		try {
			cs.beginText();
			cs.setFont(helveticaBoldFont, fontSize);
			cs.newLineAtOffset(verticalCenter(sp, textWidth), sp.getMediaBox().getHeight() - topInsets);
			cs.showText(stringToPrint);
			cs.endText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;
	}
	
	private PDPageContentStream billTo(PDPageContentStream cs, PDPage sp) {
		int fontSize = 10;
		String stringToPrint = "Bill To:";
		try {
			cs.setFont(helveticaBoldFont, fontSize);
			float y = 140;
			cs.beginText();
			float textWidth = getTextWidth(fontSize, stringToPrint);
			cs.newLineAtOffset(verticalLeft(sp, textWidth), sp.getMediaBox().getHeight() - y);
			cs.showText(stringToPrint);
			cs.endText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fontSize = 9;
		float leading = 1.5f * fontSize;

		stringToPrint = selectedClient.getName() + " - " + selectedClient.getFiscalCode() + "\n"
				+ selectedClient.getCity() + " - " + selectedClient.getZip() + " - " + selectedClient.getCityResidence()
				+ " - " + selectedClient.getCountry() + "\n" + "Tel: " + selectedClient.getPhone() + " - "
				+ selectedClient.getEmail();
		float textWidth = getTextWidth(fontSize, stringToPrint);

		// new line when finds \n
		ArrayList<String> lines = splitLines(stringToPrint, fontSize, sp);

		try {
			cs.setFont(helveticaFont, fontSize);
			float y = 140 + leading;
			for (String line : lines) {
				cs.beginText();
				textWidth = getTextWidth(fontSize, line);
				cs.newLineAtOffset(verticalLeft(sp, textWidth), sp.getMediaBox().getHeight() - y);
				cs.showText(line);
				cs.endText();
				y = y + leading;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;
	}
	
	private PDPageContentStream invoiceNumber(PDPageContentStream cs, PDPage sp) {
		int fontSize = 10;
		String stringToPrint = "Invoice #: ";
		float textWidth = getTextWidth(fontSize, "invoice date: dd/MM/yyyy   ");
		float leading = 1.5f * fontSize;

		try {
			cs.beginText();
			cs.setFont(helveticaBoldFont, fontSize);
			cs.newLineAtOffset(verticalRight(sp, textWidth) - 2, sp.getMediaBox().getHeight() - 140);
			cs.showText(stringToPrint);
			
			cs.setFont(helveticaFont, fontSize);
			cs.newLineAtOffset(54, 0);
			//invoice number here
			cs.showText("1");
			
			
			stringToPrint = "Invoice date: ";
			cs.setFont(helveticaBoldFont, fontSize);
			cs.newLineAtOffset(- (textWidth / 2) + 6, -leading);
			cs.showText(stringToPrint);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new java.util.Date();
			String dateString = dateFormat.format(date);
			
			stringToPrint = dateString;
			cs.setFont(helveticaFont, fontSize);
			cs.newLineAtOffset(getTextWidth(fontSize, "invoice date: ") + 8, 0);
			cs.showText(stringToPrint);
			
			cs.endText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;
	}

	private PDPageContentStream invoiceContainer(PDPageContentStream cs, PDPage sp) {
		// container title
		int fontSize = 18;
		String stringToPrint = "INVOICE";
		float textWidth = getTextWidth(fontSize, stringToPrint);
		float x = verticalLeft(sp, textWidth);
		try {
			cs.beginText();
			cs.setFont(helveticaBoldFont, fontSize);
			cs.setNonStrokingColor(new Color(0, 51, 102));
			cs.newLineAtOffset(x, 578);
			cs.showText(stringToPrint);
			cs.endText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// create a rectangle
		float rectWidth = (sp.getMediaBox().getWidth() - (margin * 2));
		x = verticalLeft(sp, rectWidth);
		try {
			cs.addRect(x, 550, rectWidth, 20);
			cs.fill();
			cs.setNonStrokingColor(Color.BLACK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// create table header
		fontSize = 12;
		stringToPrint = " #    Description";
		textWidth = getTextWidth(fontSize, stringToPrint);
		x = verticalLeft(sp, textWidth);
		try {
			cs.beginText();
			cs.setFont(helveticaBoldFont, fontSize);
			cs.setNonStrokingColor(Color.white);
			cs.newLineAtOffset(x, 556);
			cs.showText(stringToPrint);

			stringToPrint = " Price (ex VAT)";
			cs.newLineAtOffset(320, 0);
			cs.showText(stringToPrint);

			stringToPrint = " Total";
			cs.newLineAtOffset(132, 0);
			cs.showText(stringToPrint);

			cs.setNonStrokingColor(Color.black);
			cs.endText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;
	}

	private PDPageContentStream invoiceDescription(PDPageContentStream cs, PDPage sp) {
		// create table header
		int fontSize = 11;
		String stringToPrint = " 1      " + selectedInvoice.getName() + " (" + selectedInvoice.getDescription()  + ")";
		float textWidth = getTextWidth(fontSize, stringToPrint);
		float x = verticalLeft(sp, textWidth);
		try {
			cs.beginText();
			cs.setFont(helveticaFont, fontSize);
			cs.newLineAtOffset(x, 530);
			cs.showText(stringToPrint);

			stringToPrint = "  €  " + getPriceExcVAT(selectedInvoice.getPrice());
			cs.newLineAtOffset(320, 0);
			cs.showText(stringToPrint);
			

			stringToPrint = "  €  " + selectedInvoice.getPrice();
			cs.newLineAtOffset(132, 0);
			cs.showText(stringToPrint);

			cs.endText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;
	}

	

	private PDPageContentStream footer(PDPageContentStream cs, PDPage sp) {
		int fontSize = 9;
		float leading = 1.5f * fontSize;

		String stringToPrint = selectedCompany.getName() + " - " + selectedCompany.getVatCode() + "\n"
				+ selectedCompany.getAddress() + " - " + selectedCompany.getZipCode() + " - "
				+ selectedCompany.getCity() + " - " + selectedCompany.getCountry() + "\n" + "Tel: "
				+ selectedCompany.getPhone() + " - " + selectedCompany.getEmail();
		float textWidth = getTextWidth(fontSize, stringToPrint);

		// new line when finds \n
		ArrayList<String> lines = splitLines(stringToPrint, fontSize, sp);

		try {
			cs.setFont(helveticaFont, fontSize);
			float y = topInsets;
			for (String line : lines) {
				cs.beginText();
				textWidth = getTextWidth(fontSize, line);
				cs.newLineAtOffset(verticalCenter(sp, textWidth), y);
				cs.showText(line);
				cs.endText();
				y = y - leading;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cs;
	}

	private ArrayList<String> splitLines(String stringToSplit, int fontSize, PDPage sp) {
		PDRectangle mediabox = sp.getMediaBox();
		float width = mediabox.getWidth() - 2 * margin;

		ArrayList<String> lines = new ArrayList<String>();

		for (String text : stringToSplit.split("\n")) {
			int lastSpace = -1;
			while (text.length() > 0) {
				int spaceIndex = text.indexOf(' ', lastSpace + 1);
				if (spaceIndex < 0)
					spaceIndex = text.length();
				String subString = text.substring(0, spaceIndex);
				float size = getTextWidth(fontSize, subString);
				if (size > width) {
					if (lastSpace < 0)
						lastSpace = spaceIndex;
					subString = text.substring(0, lastSpace);
					lines.add(subString);
					text = text.substring(lastSpace).trim();
					lastSpace = -1;
				} else if (spaceIndex == text.length()) {
					lines.add(text);
					text = "";
				} else {
					lastSpace = spaceIndex;
				}
			}
		}
		return lines;
	}

	private float verticalCenter(PDPage sp, float textWidth) {
		return (sp.getMediaBox().getWidth() / 2 - textWidth / 2);
	}

	private float verticalLeft(PDPage sp, float textWidth) {
		return (margin);
	}

	private float verticalRight(PDPage sp, float textWidth) {
		return (sp.getMediaBox().getWidth() - margin - textWidth);
	}

	private float getTextWidth(int fontSize, String s) {
		Font titleFont = new Font("Helvetica", Font.PLAIN, fontSize);
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
		float textWidth = (float) titleFont.getStringBounds(s, frc).getWidth();
		return textWidth;
	}
	
	private String getPriceExcVAT(String price) {
		float p = Float.parseFloat(price);
		p = (float) (p / 1.22);
		return Float.toString(p);
		
	}

}
