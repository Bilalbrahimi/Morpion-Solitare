package back;

import java.util.List;



public class TVersion implements Version{
	
	/**
	 * returns true if a point can be used to make a line , returns false otherwise
	 * @param p
	 * @param direction
	 * @param setLines
	 * @return
	 */
	@Override
	public boolean is_point_usable(Point p, OrientationLine o, List<Line> ll) {
		return p.getState() >= 0 && !p.is_inside_lines(o, ll);
	}

}
