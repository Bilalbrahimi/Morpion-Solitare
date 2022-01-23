package back.controller;

import java.util.ArrayList;
import java.util.List;


import front.PrincipalScene;
import back.GameEvolution;
import back.Line;
import back.PlayablePoint;
import back.Point;


/**
 * Classe qui permet au joueur de controler la partie.
 * 
 * @author bilal_brahimi
 *
 */
public class GameController {

	protected GameEvolution game_evolution;
    protected PrincipalScene principal_scene;
    protected List<PlayablePoint> liste_playable_points = new ArrayList<PlayablePoint>();

    /**
     * 
     * Constructeur de la classe.
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
     * et les trace sur la grille.
     * 
     * @param liste_playable_points
     */
    private void play(List<PlayablePoint> liste_playable_points){
    	principal_scene.drawCandidatePoints(liste_playable_points,this);

    }
    
    /**
     * methode qui permet au joueur de choisir la ligne a tracer s'il y en a au moins deux 
     * a partir du point en question.
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
      * methode qui valide le cout du joueur, dessine la ligne et incremente le score
      * puis redonne la main au joueur pour un autre coup.
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
         principal_scene.addViewToPointsGrid(p);
         liste_playable_points = game_evolution.get_playable_points();
         play(liste_playable_points);
     }

}
