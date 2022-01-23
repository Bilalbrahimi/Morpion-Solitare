package back;



/**
 * 
 * @author bilal_brahimi
 *
 *la classe line nous permet de dessiner une ligne.
 *une ligne est definie par un point de depart et un point d'arriver et une orientation.
 */
public class Line {
		
		private Point p_start;
	    private Point p_end;

	    private OrientationLine orientation;


	    /**
	     * class constructor
	     * @param s
	     * @param e
	     */
	    public Line(Point ps, Point pe){
	    	setP_start(ps);
	    	setP_end(pe);
	    	orientation = OrientationLine.P;
	        if(ps.getX()==pe.getX()) orientation=OrientationLine.H;
	        if(ps.getY()==pe.getY()) orientation=OrientationLine.V;
	        if((ps.getX()>pe.getX() && ps.getY()>pe.getY()) ||(ps.getX()<pe.getX() && ps.getY()<pe.getY())) orientation=OrientationLine.D1;
	        if((ps.getX()>pe.getX() && ps.getY()<pe.getY()) ||(ps.getX()<pe.getX() && ps.getY()>pe.getY())) orientation=OrientationLine.D2;

	    }


		public Point getP_start() {
			return p_start;
		}


		public void setP_start(Point p_start) {
			this.p_start = p_start;
		}


		public Point getP_end() {
			return p_end;
		}


		public void setP_end(Point p_end) {
			this.p_end = p_end;
		}
		
		public void set_orientation(OrientationLine o) {
			this.orientation = o;
		}


		public OrientationLine get_orientation() {
			return this.orientation;
		}

		
		/**
		 * renvoie vrai si le point appartient a la ligne ( y compris les exstrimités)
		 * 
		 * @param p
		 * @return boolean
		 */
		public boolean contain_Point(Point p) {
	        Point p1 = new Point( p.getX() - this.getP_start().getX(),  p.getY() -this.getP_start().getY());
	        
	        Point p2 = new Point( this.getP_end().getX() - this.getP_start().getX(),  this.getP_end().getY() -this.getP_start().getY());

	        float v1 = (float)p1.getX()/p2.getX();
	        float v2 = (float)p1.getY()/p2.getY();
	        if((p2.getY()*v1 == p1.getY() && v1 >= 0 && v1 <= 1) || (p2.getX()*v2 == p1.getX() && v2 >= 0 && v2 <= 1)) {
	        	return true;
	        }
	        return false;
		}
		
		
		/**
		 * redifinition de la methode toString
		 */
		public String toString() {
			return "{ ("+this.getP_start().getX()+ "," +this.getP_start().getY()+") , (" + this.getP_end().getX()+ "," +this.getP_end().getY()+") }";
		}

}
