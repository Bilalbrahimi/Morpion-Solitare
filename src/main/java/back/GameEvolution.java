package back;

import java.util.ArrayList;
import java.util.List;









/**
 * La classe qui difinie l'etat du jeu a chaque etape.
 * 
 * @author bilal_brahimi
 *
 */

public class GameEvolution {
	
    public final static int GRID_SIZE = 16;

    
    private Version version;
    private int line_size;

    private Point[][] grid = new Point[GRID_SIZE][GRID_SIZE];
    private List<Line> all_list_lines = new ArrayList<>();
    RandomGame random_game;
    GlutonGame gluton_game;
    

    private int score;
    
	/**
	 * 1er Constructeur utiliser pour mode utilisateur
	 * 
	 * @param lineSize
	 * @param version
	 */
	public GameEvolution(int lineSize,Version version){
		
		this.setLineSize(lineSize);
		this.setVersion(version);
		this.setGrid(Grid.startingGrid(lineSize,GRID_SIZE));
		this.score = 0;
	}
	
	/**
	 * 2em Constructeur utiliser pour mode ordinateur
	 * 
	 * @param lineSize
	 * @param version
	 * @param random_game
	 */
	public GameEvolution(int lineSize,Version version, RandomGame random_game){
		this.random_game = random_game;
		this.setLineSize(lineSize);
		this.setVersion(version);
		this.setGrid(Grid.startingGrid(lineSize,GRID_SIZE));
		this.score = 0;
	}
	
	
	/**
	 * 3em Constructeur utiliser pour mode ordinateur
	 * 
	 * @param lineSize
	 * @param version
	 * @param gluton_game
	 */
	public GameEvolution(int lineSize,Version version, GlutonGame gluton_game){
		this.gluton_game = gluton_game;
		this.setLineSize(lineSize);
		this.setVersion(version);
		this.setGrid(Grid.startingGrid(lineSize,GRID_SIZE));
		this.score = 0;
	}
	
	public GameEvolution get_copie_game_evolution (){
		GameEvolution game_copie= new GameEvolution(this.line_size, this.version);
		
		game_copie.grid = new Point[GRID_SIZE][GRID_SIZE];
		for(int i =0; i < GRID_SIZE; i++){
            for(int j=0; j < GRID_SIZE; j++){
            	Point p = new Point(this.grid[i][j].getX(),this.grid[i][j].getY());
            	p.setState(this.grid[i][j].getState());
            	game_copie.grid[i][j] = p;
            }
		}
		
		game_copie.all_list_lines = new ArrayList<>();
		
		for(Line l : this.all_list_lines) {
			Point ps = new Point(l.getP_start().getX(),l.getP_start().getY());
			ps.setState(l.getP_start().getState());
			Point pe = new Point(l.getP_end().getX(),l.getP_end().getY());
			pe.setState(l.getP_end().getState());
			Line ml = new Line(ps,pe);
			game_copie.all_list_lines.add(ml);
		}
		
		
		
		return game_copie;
	}
	
	

	
	/**
	 * fonction qui lance la partie en mode ordinateur.
	 */
    public void start_randome_game() {
    	random_game.play(this);
    }
    
	/**
	 * fonction qui lance la partie en mode ordinateur (gluton).
	 */
    public void start_gluton_game() {
    	gluton_game.play(this);
    }

    /**
     * getter pour la grille.
     * @return
     */
	public Point[][] getGrid() {
		return grid;
	}


	public void setGrid(Point[][] gameGrid) {
		this.grid = gameGrid;
	}

	public List<Line> getAllListLines() {
		return all_list_lines;
	}


	public Version getVersion() {
		return version;
	}


	public void setVersion(Version version) {
		this.version = version;
	}


	public int getLineSize() {
		return line_size;
	}


	public void setLineSize(int lineSize) {
		this.line_size = lineSize;
	}
	
	/**
	 * cette fonction renvoie une liste des points jouable ( les points verts dans l'interface graphique)
	 * l'objet PlayablePoint contient le point jouable et une liste des lignes traçable a partir de ce point.
	 * 
	 * @return PointLines
	 */
	public List<PlayablePoint> get_playable_points(){
    	List<PlayablePoint> listPointLines = new ArrayList<>();
    	PlayablePoint pl;
    	List<Line> listLines;

        for(int i =0; i < GRID_SIZE; i++){
            for(int j=0; j < GRID_SIZE; j++){
                if(grid[i][j].getState() == -1){
                	listLines = list_of_playable_lines(grid[i][j]);
                	if(listLines.size() > 0){
                		pl = new PlayablePoint(grid[i][j],listLines);
                		listPointLines.add(pl);
                	}
                }
            }
        }
        return listPointLines;
    }
	
	
	/**
	 * renvoie la liste des lignes tracable a partir du point p, (la liste peut etre vide 
	 * si le point est finalement pas utilisable )
	 * 
	 * @param p
	 * @return liste des ligne
	 */
	private List<Line> list_of_playable_lines(Point p){
		List<Line> list_lines= new ArrayList<>();
		int x = p.getX();
		int y = p.getY();
		
		Line lh = new Line(new Point(x-(line_size-1),y),new Point(x,y));
		Line ld1 = new Line(new Point(x-(line_size-1),y-(line_size-1)),new Point(x,y));
		Line ld2 = new Line(new Point(x-(line_size-1),y+(line_size-1)),new Point(x,y));
		Line lv = new Line(new Point(x,y-(line_size-1)),new Point(x,y));
		
		// recherche des ligne horizontale traçable a partir du point p
		for(int x_ = x-(line_size-1); x_<x+line_size; x_++) {
			for(int y_ = y-(line_size-1); y_<y+line_size; y_++) {
				
				int xx = x_;
				int yy = y_;
				
				if(x_ < 0) {
					xx = 0;
				}else if (x_ >= GRID_SIZE - (line_size)) {
					xx= GRID_SIZE - (line_size);
				}
				
					Point pp = new Point(xx,yy);
					
						if(lh.contain_Point(pp)){
							boolean b = true;
							for(int i=xx; i<xx+line_size; i++) {
								if(i >= 0 && i < GRID_SIZE) {
									if((this.grid[i][yy].getState() == -1 && i != x)) {
											b = false;
									}
								}
							}
							if(b) {
								boolean bb = true;
								int nb_points_shared = 1;
								if(version instanceof DVersion) {
									nb_points_shared = 0;
								}
								Line tmp_l = new Line(grid[xx][yy],grid[xx+line_size-1][yy]);
								for(Line l : all_list_lines) {
									if(sharedPoints(l, tmp_l)>nb_points_shared) {
										bb = false;
									}
								}
								if(bb) {
									list_lines.add(new Line(grid[xx][yy],grid[xx+line_size-1][yy]));
								}
							}	
						}
					
			}
		}
		
		// recherche des ligne verticale traçable a partir du point p
		for(int x_ = x-(line_size-1); x_<x+line_size; x_++) {
			for(int y_ = y-(line_size-1); y_<y+line_size; y_++) {
				
				int xx = x_;
				int yy = y_;
				if(y_ < 0) {
					yy = 0;
				}else if (y_ >= GRID_SIZE - (line_size)) {
					yy= GRID_SIZE - (line_size);
				}
					Point pp = new Point(xx,yy);
					if(lv.contain_Point(pp)) { 
						boolean b = true;
						for(int i=yy; i<yy+line_size; i++) {
							if(i >= 0 && i < GRID_SIZE) {
								if(this.grid[xx][i].getState() == -1 && i != y) {
									b = false;
								}
							}
						}
						if(b) {
							boolean bb = true;
							int nb_points_shared = 1;
							if(version instanceof DVersion) {
								nb_points_shared = 0;
							}
							Line tmp_l = new Line(grid[xx][yy],grid[xx][yy+line_size-1]);
							for(Line l : all_list_lines) {
								if(sharedPoints(l, tmp_l)>nb_points_shared) {
									bb = false;
								}
							}
							if(bb) {
								
								list_lines.add(tmp_l);
							}
							
						}
					}
			}
		}
		
		// recherche des ligne diagonale ( du bas vers le haut) traçable a partir du point p
		for(int x_ = x-(line_size-1); x_<x+line_size; x_++) {
			for(int y_ = y-(line_size-1); y_<y+line_size; y_++) {
				
				int xx = x_;
				int yy = y_;

				if(y_ < 0 && x_>=0) {
					yy = 0;
					xx = xx - y_;

				}else if(x_<0 && y_>=0) {
					xx = 0;
					yy = yy - x_;
					
				}else if(x_ < 0 && y_ < 0) {
					int z = Math.min(xx, yy);
					
					xx = xx - z;
					yy = yy - z;
				}else if (y_ >= GRID_SIZE - (line_size) && x_ < GRID_SIZE - (line_size) ) {
					yy= GRID_SIZE - (line_size);
					int diff = y_ - yy;
					xx = xx - diff;
				}else if (x_ >= GRID_SIZE - (line_size) && y_ < GRID_SIZE - (line_size)) {
					xx= GRID_SIZE - (line_size);
					int diff = x_ - xx;
					yy = yy - diff;
				}else if ((x_ >= GRID_SIZE - (line_size)) && (y_ >= GRID_SIZE - (line_size))) {
					int diff = Math.max(x_ - GRID_SIZE, y_ - GRID_SIZE);
					xx = x_ - diff - line_size;
					yy = y_ - diff - line_size;
				} 

					Point pp = new Point(xx,yy);
					if(ld1.contain_Point(pp)) { 
						boolean b = true;
						for(int i=0; i<line_size; i++) {
								if(xx+i >= 0 && xx + i < GRID_SIZE && yy+i >= 0 && yy + i < GRID_SIZE ) {
									if(this.grid[xx + i ][yy + i ].getState() == -1 && yy + i != y && xx+i != x) {
										b = false;
									}
								}
						}
						if(xx >= 0 && xx + line_size-1 < GRID_SIZE && yy >= 0 && yy + line_size-1 < GRID_SIZE ) {
							if(b) {
								boolean bb = true;
								int nb_points_shared = 1;
								if(version instanceof DVersion) {
									nb_points_shared = 0;
								}
								Line tmp_l = new Line(grid[xx][yy],grid[xx+line_size-1][yy+line_size-1]);
								for(Line l : all_list_lines) {
									if(sharedPoints(l, tmp_l)>nb_points_shared) {
										bb = false;
									}
								}
								if(bb) {
									list_lines.add(tmp_l);
								}
								
								
							}
						}else {
							continue;
						}
					}
			}
		}
		
		// recherche des ligne diagonale ( du haut vers le bas) traçable a partir du point p
		for(int x_ = x-(line_size-1); x_<x+line_size; x_++) {
			for(int y_ = y-(line_size-1); y_<y+line_size; y_++) {
				
				int xx = x_;
				int yy = y_;
				
				
				if(y_ >= GRID_SIZE && x_>=0) {
					
					yy = GRID_SIZE-1;
					xx = xx + (y_ - yy);
					
					
				}else if(x_<0 && y_<GRID_SIZE) {
					
					xx = 0;
					yy = yy + x_;
				}else if(x_ < 0 && y_ >= GRID_SIZE) {
					int z = Math.min(Math.abs(xx), yy - GRID_SIZE);
					
					xx = xx + z;
					yy = yy - z;
					
				}else if (y_ < (line_size) && x_ < GRID_SIZE - (line_size) ) {
					yy= line_size-1;
					int diff = line_size-1 - y_;
					xx = xx - diff;
					
				}else if (x_ >= GRID_SIZE - (line_size) && y_ >= (line_size)) {
					xx= GRID_SIZE - (line_size);
					int diff = x_ - xx;
					yy = yy + diff;
					
				}else if ((x_ >= GRID_SIZE - (line_size)) && (y_ <= (line_size))) {
					int diff = Math.max(x_ - (GRID_SIZE - line_size), (line_size - 1) - y_);
					xx = x_ - diff;
					yy = y_ + diff;
					
				} 
				

					Point pp = new Point(xx,yy);

					if(ld2.contain_Point(pp)) { 
						boolean b = true;
						for(int i=0; i<line_size; i++) {
								if(xx+i >= 0 && xx + i < GRID_SIZE && yy-i >= 0 && yy - i < GRID_SIZE ) {
									if(this.grid[xx + i ][yy - i ].getState() == -1 && yy - i != y && xx+i != x) {
										b = false;
									}
								}
						}
						if(xx >= 0 && xx + line_size-1 < GRID_SIZE && yy < GRID_SIZE && yy - (line_size-1) >= 0  ) {
							if(b) {
								
								boolean bb = true;
								
								int nb_points_shared = 1;
								if(version instanceof DVersion) {
									nb_points_shared = 0;
								}
								Line tmp_l = new Line(grid[xx][yy],grid[xx+line_size-1][yy-(line_size-1)]);
								for(Line l : all_list_lines) {
									if(sharedPoints(l, tmp_l)>nb_points_shared) {
										bb = false;
									}
								}
								if(bb) {
									list_lines.add(tmp_l);
								}
								
							}
						}else {
							continue;
						}
					}
			}
		}

		return list_lines;
		
		
	}
	
	
	/**
	 * fonction qui renvoie combien de points sont partagé entre 2 ligne
	 * @param l1
	 * @param l2
	 * @return int
	 */
	public int sharedPoints(Line l1, Line l2) {
		int cpt = 0;
		List<Point> lp1 = list_points_in_one_line(l1);
		List<Point> lp2 = list_points_in_one_line(l2);
		
		if(l1.get_orientation().equals(l2.get_orientation())) {
			for(Point p1 : lp1) {
				for(Point p2 : lp2) {
					if (p1.equals(p2)) {
						cpt++;
					}
					
				}
			}
		}else {
			return -1;
		}
		return cpt;
	}
	
	/**
	 * fonction qui renvois la liste de tous les points d'une ligne.
	 * 
	 * @param l
	 * @return liste des points
	 */
	public List<Point> list_points_in_one_line(Line l){
		
		List<Point> lp = new ArrayList<>();
    	int x = l.getP_start().getX();
    	int y = l.getP_start().getY();
   
    	int dx = 0;
    	int dy = 0;
    	
    	if(l.get_orientation().equals(OrientationLine.V)) {
    		dx = 1;
    		dy = 0;
    		
    	}else if(l.get_orientation().equals(OrientationLine.H)) {
    		dx = 0;
    		dy = 1;
    		
    	}else if(l.get_orientation().equals(OrientationLine.D1)) {
    		dx = 1;
    		dy = 1;
    		
    	}else if(l.get_orientation().equals(OrientationLine.D2)) {
    		dx = 1;
    		dy = -1;
    	}
    	
    	lp.add(l.getP_start());
    	for (int i = 0;i<this.line_size-1; i++) {
    		x=x+dx;
    		y=y+dy;
    		lp.add(new Point(x,y));
    	}
    	return lp;

	}
	
 	/**
 	 * Fonction qui change l'état du point et de la ligne quand le joueur trace la ligne l à pazrtir du point p
 	 *
 	 * @param p Point
 	 * @param l Line
 	 */
     public void change_state(Point p, Line l) {
     	this.grid[p.getX()][p.getY()].setState(p.getState());
     	this.all_list_lines.add(l);
     }
     
 	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
