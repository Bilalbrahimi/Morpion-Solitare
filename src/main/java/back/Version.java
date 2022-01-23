package back;

import java.util.List;

/**
 * Interface qui permet de definir les deffirentes version du jeu ( VERSION D ET T)
 * 
 * @author bilal_brahimi
 *
 */
public interface Version {
	public boolean is_point_usable(Point p, OrientationLine o, List<Line> ll);
}
