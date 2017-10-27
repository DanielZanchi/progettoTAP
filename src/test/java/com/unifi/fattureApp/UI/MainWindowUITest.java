package com.unifi.fattureApp.UI;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.unifi.fattureApp.App.MongoUiComunication;

public class MainWindowUITest {
	private FrameFixture window;
	
	@Before
	public void setUp() {
		//MainWindowUI frame = GuiActionRunner.execute(() -> new MainWindowUI());
		MainWindowUI frame=new MainWindowUI();
		window = new FrameFixture(frame.getMainFrame());
		window.show();
	}
	
	@After
	public void tearDown() {
	  window.cleanUp();
	}
	
	@Test
	public void testApplicationName() {
		window.requireTitle("Fatture App");
	}
	
	@Test
	public void testWindowIsEnable() {
		window.requireVisible();
	}
	
	@Test
	public void testWindowIsVisible() {
		window.isEnabled();
		
	}
	
	@Test
	public void testWindowBckgColor() {
		window.background().requireEqualTo(java.awt.Color.LIGHT_GRAY);
	}
	
	@Test
	public void testEditButton() {
		JButtonFixture editButton=window.button("EditCompanyButton");
	}
	
	
}