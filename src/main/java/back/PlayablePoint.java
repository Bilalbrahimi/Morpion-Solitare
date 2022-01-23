package back;

import java.util.List;


/**
 * Classe qui vas definir les points qui sont jouable.
 * represente le point jouable et la liste des lignes traçable a partir de ce point.
 * 
 * @author bilal_brahimi
 *
 */
public class PlayablePoint {

	private Point point;
	private List<Line> list_lines;

	/**
	 * class constructor
	 * @param p
	 * @param l
	 */
	public PlayablePoint(Point p, List<Line> l){
		this.point = p;
		this.list_lines = l;
	}

	public Point getPoint(){
		return point;
	}

	public List<Line> getListLines(){
		return list_lines;
	}
	@Override
	public String toString(){
		String str = point.toString();
		
		for(Line line : list_lines) {
			str = str +" "+ line.toString();
		}
		return str;
	}
	
	/**
	 * redifinition de equals
	 */
    @Override
    public boolean equals(Object o){
        if(! (o instanceof PlayablePoint)) return false;

        PlayablePoint l = (PlayablePoint) o;

        return  this.getPoint().equals(l.getPoint()) && this.getListLines().equals(l.getListLines());
    }
}
