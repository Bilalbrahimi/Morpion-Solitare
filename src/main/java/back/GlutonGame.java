package back;

import java.util.List;


public class GlutonGame {

	
	/**
	 * Constructeur vide.
	 */
	public GlutonGame(){
		
	}
	
	/**
	 * methode utiliser par l'ordinateur pour choisir le point a jouer et la ligne aléatoirement.
	 * 
	 * @param list_playable_point
	 * @return
	 */
	public PlayablePointRandom choice(List<PlayablePoint> list_playable_point,GameEvolution ge) {
		
		GameEvolution ge_c = ge.get_copie_game_evolution();
		List<PlayablePoint> listPointLines = ge_c.get_playable_points();
		int nb_p = 0;
		PlayablePointRandom res = new PlayablePointRandom(new Point(0,0), new Line(new Point(0,0), new Point(1,3)));
		int shotNumber = 1;
		for(PlayablePoint pl : listPointLines) {
			int randomLine =  (int)(Math.random() * (pl.getListLines().size()));
			PlayablePointRandom pl_r = new PlayablePointRandom(pl.getPoint(), pl.getListLines().get(randomLine));
			pl_r.getPoint().setState(shotNumber);
			ge_c.getAllListLines().add(pl_r.getLine());
			ge_c.setScore(shotNumber);
			shotNumber++;
			if(ge_c.get_playable_points().size() > nb_p) {
				nb_p = ge_c.get_playable_points().size();
				res = pl_r;
			}
			ge_c = ge.get_copie_game_evolution();
    		listPointLines = ge_c.get_playable_points();
			
		}
		
		return res;
	}

	
	/**
	 * methode qui lance le jeu de l'ordinateur.
	 * 
	 * @param ge
	 */
	public void play(GameEvolution ge) {
    	List<PlayablePoint> listPointLines = ge.get_playable_points();
    	PlayablePointRandom pointLine;
    	int shotNumber = 1;
    	while (listPointLines.size() > 0 && shotNumber<50){
    		pointLine = choice(listPointLines,ge);
    		pointLine.getPoint().setState(shotNumber);
			ge.getAllListLines().add(pointLine.getLine());
			ge.setScore(shotNumber);
			shotNumber++;
    		listPointLines = ge.get_playable_points();
		}
    	
    	
    }
	


	


	
}
