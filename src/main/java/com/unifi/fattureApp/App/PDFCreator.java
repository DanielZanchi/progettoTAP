package com.unifi.fattureApp.App;

import java.awt.Font;
import java.awt.Insets;
import java.util.List;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.testcontainers.shaded.org.bouncycastle.crypto.prng.drbg.SP80090DRBG;

import com.unifi.fattureApp.App.Client;

public class PDFCreator {
	private Company selectedCompany;
	private Client selectedClient;
	private Invoice selectedInvoice;

	private float topInsets = 88;
	private float margin = 60;

	final PDFont courierBoldFont = PDType1Font.COURIER_BOLD;
	final PDFont courierFont = PDType1Font.COURIER;

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
			cs.setFont(courierFont, fontSize);
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

	private PDPageContentStream billTo(PDPageContentStream cs, PDPage sp) {
		int fontSize = 10;
		String stringToPrint = "Bill To:";
		try {
			cs.setFont(courierBoldFont, fontSize);
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
			cs.setFont(courierFont, fontSize);
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
		Font titleFont = new Font("Courier", Font.PLAIN, fontSize);
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
		float textWidth = (float) titleFont.getStringBounds(s, frc).getWidth();
		return textWidth;
	}

}
