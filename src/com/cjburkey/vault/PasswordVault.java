package com.cjburkey.vault;

import com.cjburkey.vault.scene.SceneLogin;
import com.cjburkey.vault.scene.SceneManage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PasswordVault extends Application {
	
	public static PasswordVault instance;
	
	public SceneLogin sceneLogin;
	public SceneManage sceneManage;
	
	public int userId = -1;
	public Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage s) {
		instance = this;
		stage = s;
		
		sceneLogin = new SceneLogin();
		sceneManage = new SceneManage();
		
		sceneLogin.init();
		sceneManage.init();
		
		s.setScene(sceneLogin);
		s.sizeToScene();
		s.centerOnScreen();
		
		s.setResizable(false);
		s.setTitle("P4ssword Safe");
		
		sceneLogin.root.requestFocus();
		s.show();
	}
	
	public void moveScene(Scene news) {
		stage.hide();
		
		stage.setScene(news);
		stage.sizeToScene();
		stage.centerOnScreen();
		
		stage.show();
	}
	
}