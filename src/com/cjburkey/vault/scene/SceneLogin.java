package com.cjburkey.vault.scene;

import com.cjburkey.vault.PasswordVault;
import com.cjburkey.vault.net.Connection;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SceneLogin extends Scene {
	
	public BorderPane root = null;
	
	private TextField email = new TextField();
	private PasswordField pass = new PasswordField();
	private Button go = new Button("Login");
	private Button help = new Button("Forgot Login Info");
	private Button reg = new Button("Register an Account");
	
	public SceneLogin() {
		super(new BorderPane());
		
		if(this.getRoot() instanceof BorderPane) {
			this.root = (BorderPane) this.getRoot();
		}
	}
	
	public void init() {
		VBox center = new VBox();
		if(this.root != null) {
			email.setPromptText("Account Email Address");
			pass.setPromptText("Account Password");
			
			go.setPrefWidth(500);
			help.setPrefWidth(500);
			reg.setPrefWidth(500);
			
			center.setPadding(new Insets(10));
			center.setSpacing(10);
			center.getChildren().addAll(email, pass, go, help, reg);
			
			this.root.setCenter(center);
			
			go.setOnAction(e -> {
				Connection c = new Connection();
				c.initConnection();
				int id;
				if((id = c.loginRequest(email.getText(), pass.getText())) >= 0) {
					System.out.println("Logged in.");
					PasswordVault.instance.userId = id;
					PasswordVault.instance.moveScene(PasswordVault.instance.sceneManage);
				}
			});
			
			help.setOnAction(e -> {  });
			reg.setOnAction(e -> {  });
		}
	}
	
}