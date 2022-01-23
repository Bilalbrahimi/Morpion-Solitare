package back.controller;





import back.GameEvolution;


import front.PrincipalScene;

/**
* Classe qui permet a l'ordinateur de controler la partie.
* 
* @author bilal_brahimi
*
*/
public class RandomGameController {
	
    private GameEvolution game_evolution;
    private PrincipalScene principal_scene;


    /**
     * Constructeur de la classe.
     * 
     * @param game_evolution
     * @param principal_scene
     */
    public RandomGameController(GameEvolution game_evolution, PrincipalScene principal_scene){
        this.principal_scene=principal_scene;
        this.game_evolution=game_evolution;

        playGame();
    }


    /**
     * methode qui permet a l'ordinateur de commencer la partie.
     */
    private void playGame(){
    	game_evolution.start_randome_game();
    	principal_scene.draw_grid();
    	principal_scene.updateScore();
    }


}
