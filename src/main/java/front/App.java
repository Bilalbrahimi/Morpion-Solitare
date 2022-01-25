package front;



import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import back.DVersion;
import back.GameEvolution;
import back.GlutonGame;
import back.RandomGame;
import back.TVersion;
import back.Version;
import back.controller.GameController;
import back.controller.GlutonGameController;
import back.controller.RandomGameController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
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
    public void start(Stage stage) throws IOException {
    	
    	GridPane points_grid = new GridPane();
    	Pane stack = new Pane();
        //draw_grid();
        points_grid.setAlignment(Pos.CENTER);
        stack.getChildren().add(points_grid);
        
        
        ChoiceBox<String> game_version = new ChoiceBox<String>();
        game_version.getItems().addAll("     5T     ","     5D     ","     4T     ","     4D     ");
        game_version.setValue("     5T     ");
        
        
        Button btn_play= new Button("Play Game");
        
        Button btn_random= new Button("Random Game");
        
        Button btn_gluton= new Button("Gluton Game");
        
        
        HBox header= new HBox(300);
        header.getChildren().addAll(game_version);
        header.setAlignment(Pos.BASELINE_LEFT);
        header.setMargin(game_version,new Insets(10, 10, 10, 50));
        
        VBox centre_content= new VBox(30);
        centre_content.getChildren().addAll(btn_play,btn_random,btn_gluton);
        centre_content.setAlignment(Pos.CENTER);
        centre_content.setMargin(btn_play,new Insets(0, 0, 0, 250));
        centre_content.setMargin(btn_random,new Insets(0, 0, 0, 250));
        centre_content.setMargin(btn_gluton,new Insets(0, 0, 0, 250));
        
        
        Label score = new Label("Best Score is:"+this.lire_score()+"  ");
        score.setFont(new Font("Arial", 30));
     

        //score.textProperty().bind(score_str);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(header);
        borderPane.setCenter(centre_content);
        borderPane.setRight(score);
        BorderPane.setMargin(stack, new Insets(25,120,120,300));
        
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        //setScene(new Scene(borderPane,screenBounds.getWidth(), screenBounds.getHeight()));
        //new Scene(borderPane,screenBounds.getWidth(), screenBounds.getHeight());
        
        
        Scene scene = new Scene(borderPane,screenBounds.getWidth(), screenBounds.getHeight());
        
        
        // Lancer la partie à partir du menu |new -> game|
        btn_play.setOnAction(e -> {
            GameEvolution gameModel = new GameEvolution(getLineSize(game_version), getVersion(game_version));
            PrincipalScene gameScene = new PrincipalScene(gameModel,stage,scene);
            PrincipalScene.IS_RANDOM = false;
            new GameController(gameModel,gameScene);
            stage.setScene(gameScene.getScene());
        	
        });
        
        // Lancer une partie Randome à partir du menu |new -> game|
        btn_random.setOnAction(e -> {
            GameEvolution gameModel = new GameEvolution(getLineSize(game_version), getVersion(game_version), new RandomGame());
            PrincipalScene gameScene = new PrincipalScene(gameModel,stage,scene);
            PrincipalScene.IS_RANDOM = true;
            stage.setScene(gameScene.getScene());
            new RandomGameController(gameModel,gameScene);
        	
        });
        
        btn_gluton.setOnAction(e -> {
            GameEvolution gameModel = new GameEvolution(getLineSize(game_version), getVersion(game_version), new GlutonGame());
            PrincipalScene gameScene = new PrincipalScene(gameModel,stage,scene);
            PrincipalScene.IS_RANDOM = true;
            stage.setScene(gameScene.getScene());
            new GlutonGameController(gameModel,gameScene);
        	
        });
        
        
        //stage.setScene(new Scene(borderPane,screenBounds.getWidth(), screenBounds.getHeight()));
        stage.setScene(scene);
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

        switch (cb.getValue().substring(6,7)){
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
        return Integer.parseInt(cb.getValue().substring(5,6));

    }
    
    public String lire_score() throws IOException{
	    FileReader lecteur;
	    List<Integer> list_score = new ArrayList<>();
	    int c;
	    String ch = "0";
	    lecteur = new FileReader("src/main/score/player.txt");
	    while((c = lecteur.read()) != -1) {
	    	if(ascii_to_str(c).equals("\n")) {
	    		list_score.add(Integer.parseInt(ch));
	    		ch = "0";
	    	}else {
	    		ch+=ascii_to_str(c);
	    	}
	    }
	    
	    lecteur.close();
	    
	    int res = 0;
	    if(list_score.isEmpty()) {
	    	return "0";
	    }else {
	    	System.out.println(list_score);
	    	for(int i : list_score) {
	    		if(i>res) {
	    			res = i;
	    		}
	    	}
	    }
	    return " "+res;
    }
    
    
    @SuppressWarnings("deprecation")
	public String ascii_to_str(int i) {
        return new Character((char) i).toString();
        
     }

    
    
   
}

