package front;



import back.DVersion;
import back.GameEvolution;
import back.RandomGame;
import back.TVersion;
import back.Version;
import back.controller.GameController;
import back.controller.RandomGameController;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.stage.Screen;
import javafx.stage.Stage;



/**
 * 
 * represente l'ecran principale de l'interface graphique.
 * 
 * @author bilal_brahimi
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
        game_version.getItems().addAll("5T","5D","4T","4D");
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
            GameEvolution gameModel = new GameEvolution(getLineSize(game_version), getVersion(game_version));
            PrincipalScene gameScene = new PrincipalScene(gameModel,stage,scene);
            new GameController(gameModel,gameScene);
            stage.setScene(gameScene.getScene());
        	
        });
        
        // Lancer une partie Randome à partir du menu |new -> game|
        game_random.setOnAction(e -> {
            GameEvolution gameModel = new GameEvolution(getLineSize(game_version), getVersion(game_version), new RandomGame());
            PrincipalScene gameScene = new PrincipalScene(gameModel,stage,scene);
            
            stage.setScene(gameScene.getScene());
            new RandomGameController(gameModel,gameScene);
        	
        });

        stage.setResizable(false);
        stage.show();

    	
    }


    
    /**
     * methode qui recupere la version choisi par l'utilisateur
     * 
     * @param cb
     * @return
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
    
    /**
     * methode qui recupere la longueure des lignes a tracer selon la version choisi
     * 
     * @param cb
     * @return
     */
    private int getLineSize(ChoiceBox<String> cb){
        return Integer.parseInt(cb.getValue().substring(0,1));

}
    
   
}

