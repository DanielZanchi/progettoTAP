/**
 * May 20, 2017
 * @author Danny
 *
 */
package com.unifi.fattureApp.UI;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyCompanyPanelUITest {
	
private FrameFixture window;
	
	@Before
	public void setUp() {
		MainWindowUI frame = GuiActionRunner.execute(() -> new MainWindowUI());
		window = new FrameFixture(frame.getMainFrame());
		window.show();
	}
	
	@After
	public void tearDown() {
	  window.cleanUp();
	}
	
	@Test
	public void testAddCompanyButton() {
		window.panel("CompanyPanel");
	}
	
}
