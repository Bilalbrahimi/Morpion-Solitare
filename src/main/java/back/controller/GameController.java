package back.controller;

import java.util.ArrayList;
import java.util.List;


import front.PrincipalScene;
import back.GameEvolution;
import back.Line;
import back.PlayablePoint;
import back.Point;


/**
 * Classe qui permet au joueur de jouer des cout manuellement.
 * 
 * @author bilal_brahimi
 *
 */
public class GameController {

    private GameEvolution game_evolution;
    private PrincipalScene principal_scene;
    private List<PlayablePoint> liste_playable_points = new ArrayList<PlayablePoint>();

    /**
     * 
     * @param game_evolution
     * @param principal_scene
     */
    public GameController(GameEvolution game_evolution,PrincipalScene principal_scene){
        this.principal_scene=principal_scene;
        this.game_evolution=game_evolution;
        liste_playable_points=game_evolution.get_playable_points();
        play(liste_playable_points);
    }
    


    /**
     * cette methode prends en arguments une liste des points qui sont jouable.
     * 
     * @param liste_playable_points
     */
    private void play(List<PlayablePoint> liste_playable_points){
    	principal_scene.drawCandidatePoints(liste_playable_points,this);

    }
    
    /**
     * 
     * @param pl
     */
     public void validate_line(PlayablePoint pl){
        if(pl.getListLines().size()>1){

        	principal_scene.drawChoiceLines(pl,this);
        }
        else {
            validateMove(pl.getPoint(),pl.getListLines().get(0));
        }
    }
     
     /**
      * 
      * @param p
      * @param l
      */
     public void validateMove(Point p, Line l){
    	 game_evolution.setScore(game_evolution.getScore()+1);
         p.setState(game_evolution.getScore());
         game_evolution.change_state(p,l);
         principal_scene.updateScore();
         principal_scene.draw_line(l);
         //System.out.println("draaaaaaaaaw ---------------");
         principal_scene.addViewToPointsGrid(p);
         liste_playable_points = game_evolution.get_playable_points();
         play(liste_playable_points);
     }

}
