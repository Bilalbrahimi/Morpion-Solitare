package front;





import java.util.List;


import back.GameEvolution;
import back.Line;
import back.PlayablePoint;
import back.Point;
import back.controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;

import javafx.stage.Screen;

// GameScene
public class PrincipalScene {
	
    private Scene scene;


    private GameEvolution model;

    private GridPane points_grid;
    private Pane stack;

    final static int CELL_SIZE=40;
    
    
    /**
     * 
     */
    public PrincipalScene(GameEvolution model){
        this.model=model;
        build_game_scene();
    }
	
	
	
    /**
     * 
     */
     void build_game_scene(){



    	points_grid = new GridPane();
        stack = new Pane();

        draw_grid();

        points_grid.setAlignment(Pos.CENTER);

        stack.getChildren().add(points_grid);

        BorderPane borderPane = new BorderPane();


        borderPane.setCenter(stack);


        BorderPane.setMargin(stack, new Insets(25,120,120,300));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        setScene(new Scene(borderPane,screenBounds.getWidth(), screenBounds.getHeight()));

    }

     

      void draw_grid(){
         Point p;
         for(int i=0; i<GameEvolution.GRID_SIZE; i++) {
             for (int j = 0; j < GameEvolution.GRID_SIZE; j++) {
                 p=model.getGrid()[i][j];
                // traitement des differents boutons iciii par la suite
                 points_grid.add(get_view_of_point(p),j,i);
             }
         }
         draw_lines(model.getAllListLines());
     }
      

       
       /**
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
        * 
        * @param listLines
        */
       private void draw_lines(List<Line> listLines){
           for(Line l : listLines) draw_line(l);
       }
       
       	/**
       	 * 
       	 * @param line
       	 */
        void draw_line(Line line){
           javafx.scene.shape.Line l = new javafx.scene.shape.Line(mapModelCoordinateToViewCoordinate(line.getP_start().getY()), mapModelCoordinateToViewCoordinate(line.getP_start().getX()), mapModelCoordinateToViewCoordinate(line.getP_end().getY()), mapModelCoordinateToViewCoordinate(line.getP_end().getX()));
           l.setStroke(Color.GRAY);
           stack.getChildren().add(l);
       }
        
        /**
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
		 * 
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
	                System.out.println("cliiiiiiiiiiiiiiiiiiiick");
	                eraseDrawOfCandidatePoints(pl_p);
	                g_c.validate_line(pl);
	                
	            });
	            points_grid.add(viewPoint,pl.getPoint().getY(),pl.getPoint().getX());
	        }
	    }
	    
	    /**
	     * 
	     * @param pl_p
	     */
	     void eraseDrawOfCandidatePoints(List<PlayablePoint> pl_p){

	        for(PlayablePoint pl : pl_p){
	            ImageView viewPoint =new ImageView();
	            viewPoint.xProperty().setValue(pl.getPoint().getX());
	            viewPoint.yProperty().setValue(pl.getPoint().getY());
	            viewPoint.setFitHeight(CELL_SIZE);
	            viewPoint.setFitWidth(CELL_SIZE);
	            viewPoint.setImage(new Image("file:src/main/img/btn_vert.png"));
	            points_grid.add(viewPoint,pl.getPoint().getY(),pl.getPoint().getX());
	        }
	    }
	     

}
