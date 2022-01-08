package Front;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.stage.Screen;
import javafx.stage.Stage;



/**
 * 
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
    	
    	
    	
    	// Create MenuBar 
    	MenuBar menuBar = new MenuBar(); 
    	
    	// Create menus
    	Menu game_version = new Menu("Version"); 
    	Menu play_game=new Menu("New");
   
    	MenuItem game_item = new MenuItem("game");

    	play_game.getItems().addAll(game_item);


    	// RadioMenuItem
    	RadioMenuItem item_5D = new RadioMenuItem("5D");
    	RadioMenuItem item_5T = new RadioMenuItem("5T"); 
    	ToggleGroup group = new ToggleGroup();
    	item_5D.setToggleGroup(group);
    	item_5T.setToggleGroup(group);
    	item_5D.setSelected(true);
    	
    	Button playerButton= new Button("play");
    	VBox menu = new VBox(30);
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(playerButton);
        
    	// Add menuItems to the Menus
    	game_version.getItems().addAll(item_5D,item_5T); 
    	
    	// Add Menus to the MenuBar 
    	menuBar.getMenus().addAll(play_game,game_version);
    	BorderPane root = new BorderPane();
    	root.setTop(menuBar);
    	
    	// recuperer les dimensions de l'ecran
    	Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    	
    	Scene scene = new Scene(root,screenBounds.getWidth(), screenBounds.getHeight());
    	stage.setTitle("Morpion Solitaire");
    	stage.setScene(scene); 
    	
    	stage.setResizable(false);
    	stage.show();

    	
    }


    
   
}

