package front;





import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import back.GameEvolution;
import back.Line;
import back.PlayablePoint;
import back.Point;
import back.controller.GameController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * 
 * represente l'ecran de deroulement d'une partie.
 * 
 * @author bilal_brahimi
 *
 */
public class PrincipalScene {
	
	public static boolean IS_RANDOM = false;
	
    private Scene scene;
    private Scene menu;
    private Stage stage;

    private GameEvolution model;

    private GridPane points_grid;
    private Pane stack;

    final static int CELL_SIZE=40;
    
    private StringProperty score_str = new SimpleStringProperty("Your Score is : 0    ");


    
    /**
     * Constructeur de la classe
     * 
     * @param model
     * @param stage
     * @param menu
     */
    public PrincipalScene(GameEvolution model, Stage stage, Scene menu){
        this.model=model;
        this.menu = menu;
        this.stage = stage;
        build_game_scene();
    }
	
	
	
    /**
     * preparation de l'interface d'une partie (la grille, les points de depart, affichage du scores...)
     */
     void build_game_scene(){



    	points_grid = new GridPane();
        stack = new Pane();
        draw_grid();
        points_grid.setAlignment(Pos.CENTER);
        stack.getChildren().add(points_grid);
        
        
        Button btn_main_menu= new Button("Quit game");
        btn_main_menu.setOnAction(e ->{
        	stage.setScene(menu);
        	try {
        		if(!IS_RANDOM) {
        			this.write_score("src/main/score/player.txt");
        		}else {
        			this.write_score("src/main/score/random.txt");
        		}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        VBox header= new VBox(300);
        header.getChildren().addAll(btn_main_menu);
        header.setAlignment(Pos.CENTER_LEFT);
        
        
        Label score = new Label(score_str.getValue());
        score.setFont(new Font("Arial", 30));
        

        score.textProperty().bind(score_str);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(header);
        borderPane.setCenter(stack);
        borderPane.setRight(score);
        BorderPane.setMargin(stack, new Insets(25,120,120,300));
        
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        setScene(new Scene(borderPane,screenBounds.getWidth(), screenBounds.getHeight()));

    }

     
     /**
      * dessin de la grille
      */
      public void draw_grid(){
         Point p;
         for(int i=0; i<GameEvolution.GRID_SIZE; i++) {
             for (int j = 0; j < GameEvolution.GRID_SIZE; j++) {
                 p=model.getGrid()[i][j];
                 points_grid.add(get_view_of_point(p),j,i);
             }
         }
         draw_lines(model.getAllListLines());
     }
      

       
       /**
        * representation des deffirents points dans la grille.
        * 
        * @param p Point
        * @return  Imageview
        */
       private ImageView get_view_of_point(Point p){
           ImageView viewPoint =new ImageView();
           viewPoint.xProperty().setValue(p.getX());
           viewPoint.yProperty().setValue(p.getY());
           viewPoint.setFitHeight(CELL_SIZE);
           viewPoint.setFitWidth(CELL_SIZE);

               if(p.getState()==-1) {
                   viewPoint.setImage(new Image("file:src/main/img/btn_croix.png"));
                   return viewPoint;
               }
               else {
                   viewPoint.setImage(new Image("file:src/main/img/btn_bleu.png"));
                   return viewPoint;
               }
           }
       
       /**
        * dessine toutes les lignes a chaque etape de la partie.
        * 
        * @param listLines
        */
       private void draw_lines(List<Line> listLines){
           for(Line l : listLines) draw_line(l);
       }
       
       	/**
       	 * dessine une ligne.
       	 * @param line
       	 */
        public void draw_line(Line line){
           javafx.scene.shape.Line l = new javafx.scene.shape.Line(mapModelCoordinateToViewCoordinate(line.getP_start().getY()), mapModelCoordinateToViewCoordinate(line.getP_start().getX()), mapModelCoordinateToViewCoordinate(line.getP_end().getY()), mapModelCoordinateToViewCoordinate(line.getP_end().getX()));
           l.setStroke(Color.RED);
           stack.getChildren().add(l);
       }
        
        /**
         * calculer dune coordonée (resultat en type double)
         * utiliser pour dessiner une ligne dans la methode drawline.
         * 
         * @param coordinate int
         * @return double
         */
        private double mapModelCoordinateToViewCoordinate(int coordinate){
            return coordinate*CELL_SIZE+CELL_SIZE/2;
        }



		public Scene getScene() {
			return scene;
		}



		public void setScene(Scene scene) {
			this.scene = scene;
		}
		
		/**
		 * methode qui dessine les points jouable (point vert)
		 * @param pl_p
		 * @param g_c
		 */
	    public void drawCandidatePoints(List<PlayablePoint> pl_p, GameController g_c){

	        for(PlayablePoint pl : pl_p){
	            ImageView viewPoint =new ImageView();
	            viewPoint.xProperty().setValue(pl.getPoint().getX());
	            viewPoint.yProperty().setValue(pl.getPoint().getY());
	            viewPoint.setFitHeight(CELL_SIZE);
	            viewPoint.setFitWidth(CELL_SIZE);
	            viewPoint.setImage(new Image("file:src/main/img/btn_vert.png"));
	            viewPoint.setOnMouseClicked(e->{
	                
	                eraseDrawOfCandidatePoints(pl_p);
	                g_c.validate_line(pl);
	                
	            });
	            points_grid.add(viewPoint,pl.getPoint().getY(),pl.getPoint().getX());
	        }
	    }
	    
	    /**
	     * methode qui efface les points jouable pour passe a l'etape suivante du jeu.
	     * @param pl_p
	     */
	     public void eraseDrawOfCandidatePoints(List<PlayablePoint> pl_p){

	        for(PlayablePoint pl : pl_p){
	            ImageView viewPoint =new ImageView();
	            viewPoint.xProperty().setValue(pl.getPoint().getX());
	            viewPoint.yProperty().setValue(pl.getPoint().getY());
	            viewPoint.setFitHeight(CELL_SIZE);
	            viewPoint.setFitWidth(CELL_SIZE);
	            viewPoint.setImage(new Image("file:src/main/img/btn_croix.png"));
	            points_grid.add(viewPoint,pl.getPoint().getY(),pl.getPoint().getX());
	        }
	     }
	     
	     
	     /**
	      * ajoute la representation du point a partir duquel on a déja tracer une ligne ( rond avec un chiffre)
	      * a la grille
	      * 
	      * @param p
	      */
	     public void addViewToPointsGrid(Point p){
	    	 points_grid.add(getViewOfAMovePoint(p),p.getY(),p.getX());
	     }

	     /**
	      * dessine la representation du point a partir duquel on a déja tracer une ligne ( rond avec un chiffre)
	      * 
	      * @param p
	      */
	      StackPane getViewOfAMovePoint(Point p){
	         int i =p.getX();
	         int j = p.getY();

	         ImageView imV = new ImageView(new Image("file:src/main/img/btn_blanc.png"));
	         imV.xProperty().setValue(i);
	         imV.yProperty().setValue(j);
	         imV.setFitHeight(CELL_SIZE);
	         imV.setFitWidth(CELL_SIZE);
	         Label ll = new Label(String.valueOf(p.getState()));
	         ll.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 10));
	         StackPane sp = new StackPane(imV,ll);
	         return sp;
	     }

	      /**
	       * fonction qui donne le choix de lignes traçable a partir d'un point quand plusieurs possibilités s'offrent au joueur
	       * a partir du meme point.
	       * 
	       * @param pl
	       * @param playerController
	       */
	       public void drawChoiceLines(PlayablePoint pl,GameController playerController){
	          List<javafx.scene.shape.Line> listViewLine= new ArrayList<>();
	          for(Line line: pl.getListLines()){

	              javafx.scene.shape.Line l = new javafx.scene.shape.Line(mapModelCoordinateToViewCoordinate(line.getP_start().getY()), mapModelCoordinateToViewCoordinate(line.getP_start().getX()),mapModelCoordinateToViewCoordinate(line.getP_end().getY()),mapModelCoordinateToViewCoordinate(line.getP_end().getX()));
	              listViewLine.add(l);
	              l.setStroke(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
	              l.setStrokeWidth(5);
	              l.setOnMouseClicked(e->{
	                  for(int i =0;i<listViewLine.size();i++) stack.getChildren().remove(listViewLine.get(i));
	                  playerController.validateMove(pl.getPoint(),line);
	              });
	              stack.getChildren().add(l);
	          }
	      }
	       
	       /**
	        * met a jour l'affichage du score a chaque etape.
	        */
	       public void updateScore(){
	    	   score_str.setValue("Your Score is : "+model.getScore()+"    ");
	       }
	       
	       
	       public void write_score(String file) throws IOException{
		       	FileWriter writer;
			    FileReader reader;
			    List<Integer> ch = new ArrayList<>();
			    int c;
			    reader = new FileReader(file);
			    while((c = reader.read()) != -1) {
			    	ch.add(c);
			    }
			    reader.close();
			    writer = new FileWriter(file);
		   	    for(int i : ch) {
		   	    	writer.write(i);
		   	    }
		   	    writer.write(model.getScore()+"\n");
		   	    writer.close();
	       }

}
