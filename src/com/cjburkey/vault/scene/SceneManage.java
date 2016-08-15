package com.cjburkey.vault.scene;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.vault.local.LocalHelper;
import com.cjburkey.vault.local.LoginInfo;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class SceneManage extends Scene {

	public BorderPane root = null;
	
	private TextField search = new TextField();
	private ScrollPane mainPane = new ScrollPane();
	private VBox scrollable = new VBox();
	
	private Rectangle2D size = Screen.getPrimary().getVisualBounds();
	
	public SceneManage() {
		super(new BorderPane());
		
		if(this.getRoot() instanceof BorderPane) {
			this.root = (BorderPane) this.getRoot();
		}
	}
	
	public void init() {
		VBox center = new VBox();
		VBox top = new VBox();
		if(this.root != null) {
			List<Node> buttons = new ArrayList<Node>();
			
			LocalHelper.loadLogins();
			
			Button add = new Button("Add Password");
			add.getStyleClass().add("addButtonDontIgnore");
			buttons.add(add);
			buttons.add(new Separator());
			
			for(LoginInfo i : LocalHelper.logins) {
				Button b = new Button(i.siteName);
				buttons.add(b);
			}
			
			for(Node b : buttons) {
				if(b instanceof Button) {
					((Button) b).setMaxWidth(Double.MAX_VALUE);
				}
			}
			
			this.search.setPromptText("Search...");
			this.search.setOnKeyTyped(e -> {
				for(Node n : buttons) {
					if(n instanceof Button) {
						Button b = (Button) n;
						
						if(this.search.getText().trim().isEmpty()) {
							b.setVisible(true);
							b.setManaged(true);
						} else if(!b.getStyleClass().contains("addButtonDontIgnore")) {
							boolean show = b.getText().contains(this.search.getText().trim());
							b.setVisible(show);
							b.setManaged(show);
						}
					}
				}
			});
			
			mainPane.setPrefWidth(size.getWidth() / 2d);
			mainPane.setPrefHeight(size.getHeight() / 2d);
			
			mainPane.setContent(scrollable);
			mainPane.setFitToWidth(true);
			
			scrollable.setFillWidth(true);
			scrollable.setPadding(new Insets(10));
			scrollable.setSpacing(10);
			scrollable.getChildren().addAll(buttons);
			
			top.setPadding(new Insets(10));
			top.setSpacing(10);
			top.getChildren().add(search);
			
			center.setPadding(new Insets(10));
			center.setSpacing(10);
			center.getChildren().addAll(mainPane);
			
			this.root.setTop(top);
			this.root.setCenter(center);
			
			this.root.requestFocus();
		}
	}
	
}