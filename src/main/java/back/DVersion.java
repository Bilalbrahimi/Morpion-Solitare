package back;

import java.util.List;

public class DVersion implements Version{
	
	/**
	 * verifie si le point p est utilisable pour tracer une ligne ( s'il n'appartient à aucune ligne
	 * de la liste des lignes qui ont comme orientation o)
	 * ici (Version D) il faut que le point soit en dehors de toute le lignes (celle avec l'orientation o)
	 * autrement dit, pas de chauvauchement entre les ligne
	 * 
	 * @param p
	 * @param o
	 * @param ll
	 * @return
	 */
	@Override
	public boolean is_point_usable(Point p, OrientationLine o, List<Line> ll) {
		return p.getState() >= 0 && !p.is_in_lines(o, ll);
	}

}
