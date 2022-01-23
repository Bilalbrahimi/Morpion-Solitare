package back;

import java.util.List;



public class TVersion implements Version{
	
	/**
	 * verifie si le point p est utilisable pour tracer une ligne ( s'il n'appartient à aucune ligne
	 * de la liste des lignes qui ont comme orientation o)
	 * ici (Version T) il faut que le point soit en dehors de toute le lignes ou juste l'extrémité de ces derniers
	 * autrement dit, le chauvauchement entre les lignes est possible (mais qu'à une extremité)
	 * 
	 * @param p
	 * @param o
	 * @param ll
	 * @return
	 */
	@Override
	public boolean is_point_usable(Point p, OrientationLine o, List<Line> ll) {
		return p.getState() >= 0 && !p.is_inside_lines(o, ll);
	}

}
