package back;


/**
 * 
 * @author bilal_brahimi
 *	Le classe Point nous permet de generer des points sur la garille pour pouvoir jouer.
 *	un point est caracteriser par ses coordoners(x,y) et un statut (-1 par defaut).
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
	     * 
	     * @param x
	     * @param y
	     * @param limit_hight
	     * @param limit_width
	     */
		public Point(int x, int y,int limit_hight,int limit_width){
	    	if(is_valide_point(x,limit_hight) && is_valide_point(y,limit_width) ) {
	            this.setX(x);
	            this.setY(y);
	    	}else System.err.println("the point " + this.toString() + " is not valid");
	    }
		
	    /**
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
	     * 
	     * @param x
	     * @param limit
	     * @return True si x >0 et y<limit
	     */
	    public static boolean is_valide_point(int x, int limit){
	        return x >= 0 && x < limit;
	    }

		public int getX() {
			return x;
		}

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

}
