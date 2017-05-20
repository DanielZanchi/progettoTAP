package com.unifi.fattureApp.UI;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;


public class MainWindowsUITest {

	private FrameFixture window;
	
	@Before
	public void setUp() {
		MainWindowUI frame = GuiActionRunner.execute(() -> new MainWindowUI());
		window = new FrameFixture(frame);
		window.show();
	}
	
	@Test
	public void testApplicationName() {
		window.panel("").background();
	}


}
