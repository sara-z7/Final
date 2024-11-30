package application;

import javafx.stage.Stage;

public class Menu {
	
	
	private Stage primaryStage;
	private FlashcardController flashcardController;
	
	public Menu(Stage stage) {
		this.primaryStage = stage;
		this.flashcardController = new FlashcardController(stage);
	}
	
	//public Scene createMenuScene() {
		
   //}

}
