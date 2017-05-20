/**
 * May 20, 2017
 * @author Danny
 *
 */
package com.unifi.fattureApp.UI;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyCompanyPanelUITest {

	private FrameFixture window;

	private JPanelFixture myCompany_Panel;

	@Before public void setUp() {
		MainWindowUI frame = GuiActionRunner.execute(() -> new MainWindowUI());
		window = new FrameFixture(frame.getMainFrame());
//		myCompany_Panel = window.panel("CompanyPanel");
		window.show();
	}

	@After
	public void tearDown() {
		window.cleanUp();
	}

	@Test
	public void testCompanyPanelBackground() {
//		myCompany_Panel.background().requireEqualTo(new java.awt.Color(232,230,230));
		window.panel("CompanyPanel").background().requireEqualTo(new java.awt.Color(232,230,230));
	}

}
