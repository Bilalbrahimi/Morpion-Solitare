package back.controller;

import back.GameEvolution;
import front.PrincipalScene;

public class GlutonGameController {
	
    private GameEvolution game_evolution;
    private PrincipalScene principal_scene;


    /**
     * Constructeur de la classe.
     * 
     * @param game_evolution
     * @param principal_scene
     */
    public GlutonGameController(GameEvolution game_evolution, PrincipalScene principal_scene){
        this.principal_scene=principal_scene;
        this.game_evolution=game_evolution;

        playGame();
    }


    /**
     * methode qui permet a l'ordinateur de commencer la partie.
     */
    private void playGame(){
    	game_evolution.start_gluton_game();
    	principal_scene.draw_grid();
    	principal_scene.updateScore();
    }
}
