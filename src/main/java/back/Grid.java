package back;


/**
 * 
 * @author bilal_brahimi
 * 
 * la classe gride nous permet de generer une grille des point, et nous permet 
 * de dessiner la croix des points au milieu.
 *
 */
public class Grid {
    /**
     * 
     * @param lineSize
     * @param gridHight
     * @param gridWidth
     * @return Point[][]
     */
	  public static Point[][] startingGrid(int lineSize,int gridSize) {
	        Point[][] gameGrid = new Point[gridSize][gridSize];
	        for (int i = 0; i < gridSize ; i++) {
	            for ( int j = 0 ; j < gridSize ; j++ ) {
	                gameGrid[i][j] = new Point(i, j, -1 ,gridSize,gridSize);
	            }
	        }
	       
	        gameGrid[1][4].setState(0);
	        gameGrid[2][3].setState(0);
	        gameGrid[3][2].setState(0);
	        gameGrid[4][1].setState(0);
	        
	        gameGrid[2][5].setState(0);
	        gameGrid[3][4].setState(0);
	        gameGrid[4][3].setState(0);
	        gameGrid[5][2].setState(0);
	        /*
	        gameGrid[10][0].setState(0);
	        gameGrid[11][1].setState(0);
	        gameGrid[13][3].setState(0);
	        gameGrid[14][4].setState(0);
	        
	        gameGrid[11][0].setState(0);
	        gameGrid[12][1].setState(0);
	        gameGrid[14][3].setState(0);
	        gameGrid[15][4].setState(0);
	        */
	            int x = (int) gridSize/2 -1;
	            int y = (int) gridSize/2 -1;
	            
	            for(int i = x-4; i<= x+5; i++) {
	            	if((i == x-4) || (i == x+5)) {
	            		gameGrid[i][y-1].setState(0);
	                    gameGrid[i][y].setState(0);
	                    gameGrid[i][y + 1].setState(0);
	                    gameGrid[i][y + 2].setState(0);
	            	}else if((i == x-1) || (i == x+2)) {
	                    gameGrid[i][y-1].setState(0);
	                    gameGrid[i][y-2].setState(0);
	                    gameGrid[i][y-3].setState(0);
	                    gameGrid[i][y-4].setState(0);

	                    gameGrid[i][y+2].setState(0);
	                    gameGrid[i][y+3].setState(0);
	                    gameGrid[i][y+4].setState(0);
	                    gameGrid[i][y+5].setState(0);
	            	}else if((i == x) || (i == x+1)) {
	            		gameGrid[i][y - 4].setState(0);
	            		gameGrid[i][y + 5].setState(0);
	            	}else {
	            		gameGrid[i][y - 1].setState(0);
	            		gameGrid[i][y + 2].setState(0);
	            	}
	            }

	        
	        return gameGrid;
	    }

}
