package front;



import back.DVersion;
import back.GameEvolution;
import back.TVersion;
import back.Version;
import back.controller.GameController;
import javafx.application.Application;
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
        MenuBar menu_bar = new MenuBar();
        
        // Create menus
        Menu play_game=new Menu("Play");

        MenuItem game_item = new MenuItem("New Game");
        MenuItem game_random = new MenuItem("Randome Game");
        
        Menu version_menu = new Menu();
        
        
        


        play_game.getItems().addAll(game_item,game_random);
        
        
        ChoiceBox<String> game_version = new ChoiceBox<String>();
        game_version.getItems().addAll("5T","5D");
        game_version.setValue("5T");


        
        version_menu.setId("transparent");
        version_menu.setGraphic(game_version);
   

        // Add Menus to the MenuBar
        menu_bar.getMenus().addAll(play_game,version_menu);
        BorderPane root = new BorderPane();
        root.setTop(menu_bar);

        // recuperer les dimensions de l'ecran
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root,screenBounds.getWidth(), screenBounds.getHeight());
        stage.setTitle("Morpion Solitaire");
        stage.setScene(scene); 
        
        
        // Lancer la partie à partir du menu |new -> game|
        game_item.setOnAction(e -> {
            GameEvolution gameModel = new GameEvolution(5, getVersion(game_version));
            PrincipalScene gameScene = new PrincipalScene(gameModel);
            new GameController(gameModel,gameScene);
            stage.setScene(gameScene.getScene());
        	
        });

        stage.setResizable(false);
        stage.show();

    	
    }


    /**
     * 
     * @param cb ChoiceBox
     * @return GameVersion
     */
    private Version getVersion(ChoiceBox<String> cb){

        switch (cb.getValue().substring(1)){
            case "T":
                return new TVersion();

            case "D":
                return new DVersion();

            default:
                return new TVersion();
        }
    }
    
   
}

