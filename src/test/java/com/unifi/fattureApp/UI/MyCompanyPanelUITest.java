/**
 * May 20, 2017
 * @author Danny
 *
 */
package com.unifi.fattureApp.UI;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyCompanyPanelUITest {

	private FrameFixture window;
	private JPanelFixture myCompany_Panel;
	private JPanelFixture addCompany_Panel;
	private JButtonFixture addCompany_Button;

	@Before public void setUp() {
		MainWindowUI frame = GuiActionRunner.execute(() -> new MainWindowUI());
		window = new FrameFixture(frame.getMainFrame());
		window.show();
		myCompany_Panel = window.panel("CompanyPanel");
		addCompany_Button = window.panel("CompanyPanel").button("AddCompanyButton");
	}

	@After
	public void tearDown() {
		window.cleanUp();
	}

	@Test
	public void testCompanyPanelBackground() {
		myCompany_Panel.background().requireEqualTo(new java.awt.Color(232,230,230));
	}
	
	@Test
	public void testAddButtonText() {
		addCompany_Button.requireText("Add");
	}
	
	@Test
	public void testAddButtonAction() {		
		addCompany_Button.click();
		addCompany_Panel = window.panel("AddCompanyPanel");
		addCompany_Panel.requireVisible();
	}
	
}
