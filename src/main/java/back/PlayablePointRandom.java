package back;


/**
 * utilisable lors du mode ordinateur, represente le point jouable et qui va etre jouer par l'ordinateur,
 * avec la ligne qui va etre tracer a partir de ce point.
 * 
 * @author bilal_brahimi
 *
 */
public class PlayablePointRandom {

	private Point point;
	private Line line;

	/**
	 * class constructor
	 * @param p
	 * @param l
	 */
	public PlayablePointRandom(Point p, Line l){
		this.point = p;
		this.line = l;
	}

	public Point getPoint(){
		return point;
	}

	public Line getLine(){
		return this.line;
	}
	@Override
	public String toString(){
		
		return point.toString() +" "+line.toString();
	}
	

    @Override
    public boolean equals(Object o){
        if(! (o instanceof PlayablePointRandom)) return false;

        PlayablePointRandom l = (PlayablePointRandom) o;

        return  this.getPoint().equals(l.getPoint()) && this.getLine().equals(l.getLine());
    }
}
