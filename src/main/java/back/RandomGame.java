package back;

import java.util.List;









public class RandomGame {
	
	/**
	 * Constructeur vide.
	 */
	public RandomGame(){
		
	}
	
	/**
	 * methode utiliser par l'ordinateur pour choisir le point a jouer et la ligne aléatoirement.
	 * 
	 * @param list_playable_point
	 * @return
	 */
	public PlayablePointRandom choice(List<PlayablePoint> list_playable_point) {
		int randomPoint = (int)(Math.random() * (list_playable_point.size()));
		int randomLine =  (int)(Math.random() * (list_playable_point.get(randomPoint).getListLines().size()));	
		return new PlayablePointRandom(list_playable_point.get(randomPoint).getPoint(), list_playable_point.get(randomPoint).getListLines().get(randomLine));
	
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
    	while (listPointLines.size() > 0){
    		pointLine = choice(listPointLines);
    		pointLine.getPoint().setState(shotNumber);
			ge.getAllListLines().add(pointLine.getLine());
			ge.setScore(shotNumber);
			shotNumber++;
    		listPointLines = ge.get_playable_points();
		}
    	
    	
    }
	


	


}
