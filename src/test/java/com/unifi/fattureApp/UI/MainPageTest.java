//package com.unifi.fattureApp.UI;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.rules.Verifier;
//import org.testfx.framework.junit.ApplicationTest;
//
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.input.KeyCode;
//import javafx.stage.Stage;
//
//public class MainPageTest extends ApplicationTest {
//	
//	private MainPageUI mainPage;
//	
//	
//	@Override
//    public void start(Stage stage) {
//        Scene scene = new mainpa
//        stage.setScene(scene);
//        stage.show();
//    }
//	
//	/* Just a shortcut to retrieve widgets in the GUI. */
//    public <T extends Node> T find(final String query) {
//        /** TestFX provides many operations to retrieve elements from the loaded GUI. */
//        return lookup(query).query();
//    }
//	
//	@Before
//	 public void setUp() {
//        /* Just retrieving the tested widgets from the GUI. */
//        createBtn = find("#createBtn");
//    }
//
//    @Test
//    public void createBtnText() {
//    	createBtn.setText("Create");
//    	
//    }
//}
