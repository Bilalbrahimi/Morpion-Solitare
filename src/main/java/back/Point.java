package back;

import java.util.List;



/**
 * 
 * @author bilal_brahimi
 *	La classe Point nous permet de generer des points sur la garille pour pouvoir jouer.
 *	un point est caracteriser par ses coordoners(x,y) et un statut (-1 par defaut). 
 *  il determine l'etat du point en question dans la grille, par exemple s'il peut etre attacher a une ligne.
 */
public class Point {
	
		//abssise du point
	  	private int x;
	  	
	  	//ordonner du point
	    private int y;
	    
	    //le statut du point 
	    private int state;
		
	    /**
	     * Constructeure de la classe Point
	     * 
	     * @param x
	     * @param y
	     */
	    public Point(int x, int y){
	        this.setX(x);
	        this.setY(y);
	    }
	    
	    
		
	    /**
	     * 2em Constructeure de la classe Point 
	     * 
	     * @param x
	     * @param y
	     * @param state
	     * @param limit_hight
	     * @param limit_width
	     */
	    public Point(int x, int y, int state,int limit_hight,int limit_width){
	    	if(is_valide_point(x,limit_hight) && is_valide_point(y,limit_width) ) {
	            this.setX(x);
	            this.setY(y);
	            this.setState(state);
	    	}else System.err.println("the point " + this.toString() + " is not valid");
	    		
	    }
	    
	    /**
	     * cettte methode verefie si le point apartient a au moins une des ligne de la list
	     * des ligne donné en parametre ( y compris les extrimités )
	     * 
	     * @param o
	     * @param ll
	     * @return boolean
	     */
	    public boolean is_in_lines(OrientationLine o, List<Line> ll) {
	    	for(Line l : ll) {
	    		if((l.get_orientation() == o) && l.contain_Point(this)) {
	    			return true;
	    		}
	    	}
	    	return false;
	    	
	    }
	    
	    
	    /**
	     * cettte methode verefie si le point apartient a au moins une des ligne de la list
	     * des ligne donné en parametre ( sans les extrimités )
	     * 
	     * @param o
	     * @param ll
	     * @return
	     */
	    public boolean is_inside_lines(OrientationLine o, List<Line> ll) {
	    	for(Line l : ll) {
	    		if((l.get_orientation() == o) && l.contain_Point(this)) {
	    			if(!(this.equals(l.getP_start())) && (!(this.equals(l.getP_end())))) {
	    				return true;
	    			}
	    		}
	    	}
	    	return false;
	    	
	    }

	    
	    /**
	     * pour verifier si x est compris entre 0 et limit ( utiliser pour s'assurer que les coordonnés ne 
	     * depassent pas la grille.
	     * 
	     * @param x
	     * @param limit
	     * @return True si x >0 et y<limit
	     */
	    public static boolean is_valide_point(int x, int limit){
	        return x >= 0 && x < limit;
	    }

	    /**
	     * getter pour x
	     * @return x
	     */
		public int getX() {
			return x;
		}

		
		/**
		 * setter pour x.
		 * @param x
		 */
		public void setX(int x) {
			this.x = x;
		}

		/*
		 * getter pour Y
		 */
		public int getY() {
			return y;
		}

		/*
		 * Setter de Y
		 */
		public void setY(int y) {
			this.y = y;
		}

		/*
		 * getter pour State
		 */
		public int getState() {
			return state;
		}

		/*
		 * Setter pour State
		 */
		public void setState(int state) {
			this.state = state;
		}
	    
		
		/*
		 * la methode toString()
		 */
	    @Override
	    public String toString() {
	    	return "("+ x+  ","+ y+ ")";
	    }
	    
	    
	    /**
	     * redifinition de la methode equals
	     */
	    @Override
	    public boolean equals(Object o){
	        if(! (o instanceof Point)) return false;

	        Point p = (Point) o;

	        return  (x==p.getX()) && (y==p.getY());
	    }

}
