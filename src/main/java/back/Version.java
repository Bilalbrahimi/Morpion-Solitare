package back;

import java.util.List;

public interface Version {
	public boolean is_point_usable(Point p, OrientationLine o, List<Line> ll);
}
