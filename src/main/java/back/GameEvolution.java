package back;

import java.util.ArrayList;
import java.util.List;






/**
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


	/**
	 * 
	 * 
	 */
	public GameEvolution(int lineSize,Version version){
		
		this.setLineSize(lineSize);
		this.setVersion(version);
		this.setGrid(Grid.startingGrid(lineSize,GRID_SIZE));
	}


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
	
	
	private List<Line> list_of_playable_lines(Point p){
		List<Line> list_lines= new ArrayList<>();
		int x = p.getX();
		int y = p.getY();
		
		Line lh = new Line(new Point(x-(line_size-1),y),new Point(x,y));
		Line ld1 = new Line(new Point(x-(line_size-1),y-(line_size-1)),new Point(x,y));
		Line ld2 = new Line(new Point(x-(line_size-1),y+(line_size-1)),new Point(x,y));
		Line lv = new Line(new Point(x,y-(line_size-1)),new Point(x,y));
		
		
		for(int x_ = x-(line_size-1); x_<x+line_size; x_++) {
			for(int y_ = y-(line_size-1); y_<y+line_size; y_++) {
				
				int xx = x_;
				int yy = y_;
				//int line_size_h = line_size;
				
				if(x_ < 0) {
					xx = 0;
					//line_size_h = line_size + x_;
				}else if (x_ >= GRID_SIZE - (line_size)) {
					xx= GRID_SIZE - (line_size);
				}
				
				
				//if(xx >= 0 && xx < GRID_SIZE-line_size+1 && yy >= 0 && yy < GRID_SIZE-line_size+1) {
					Point pp = new Point(xx,yy);
					
					if(lh.contain_Point(pp)){// horizontal
						boolean b = true;
						for(int i=xx; i<xx+line_size; i++) {
							//System.out.println("-------xxx " + xx +"iiiiii "+ i );
							if(i >= 0 && i < GRID_SIZE) {
								if(this.grid[i][yy].getState() == -1 && i != x) {
									b = false;
								}
							
								
							}
						}
						if(b) {
							//System.out.println("-------xxx " + xx +" x+L "+ (xx+line_size_h-1) +" yyyyy "+ yy );
							list_lines.add(new Line(grid[xx][yy],grid[xx+line_size-1][yy]));
						}
						
						
						
					}
			
			}
		}
		for(int x_ = x-(line_size-1); x_<x+line_size; x_++) {
			for(int y_ = y-(line_size-1); y_<y+line_size; y_++) {
				
				int xx = x_;
				int yy = y_;
				//int line_size_h = line_size;
				if(y_ < 0) {
					yy = 0;
				}else if (y_ >= GRID_SIZE - (line_size)) {
					yy= GRID_SIZE - (line_size);
				}
				
				
				//if(xx >= 0 && xx < GRID_SIZE-line_size+1 && yy >= 0 && yy < GRID_SIZE-line_size+1) {
					Point pp = new Point(xx,yy);
					

					if(lv.contain_Point(pp)) { //vertical
						boolean b = true;
						for(int i=yy; i<yy+line_size; i++) {
							//System.out.println("-------xxx " + xx +"iiiiii "+ i );
							if(i >= 0 && i < GRID_SIZE) {
								if(this.grid[xx][i].getState() == -1 && i != y) {
									b = false;
								}
							
							}
						}
						if(b) {
							//System.out.println("-------y " + yy +" y+L "+ (yy+line_size-1) +" xx "+ xx );
							list_lines.add(new Line(grid[xx][yy],grid[xx][yy+line_size-1]));
						}
						
					}
					
			
			}
		}
		
		//diagonale
		for(int x_ = x-(line_size-1); x_<x+line_size; x_++) {
			for(int y_ = y-(line_size-1); y_<y+line_size; y_++) {
				
				int xx = x_;
				int yy = y_;
				//int line_size_h = line_size;
				if(y_ < 0 && x_>=0) {
					
					//System.out.println("------- 1");
					//System.out.println("("+x_+","+y_+")");
					yy = 0;
					xx = xx - y_;
					//System.out.println(">>>--->>>> " + xx+ " , "+yy );
					
				}else if(x_<0 && y_>=0) {
					//System.out.println("------- 2");
					//System.out.println("("+x_+","+y_+")");
					xx = 0;
					yy = yy - x_;
					//System.out.println(">>>--->>>> " + xx+ " , "+yy );
					
					
				}else if(x_ < 0 && y_ < 0) {
					//System.out.println("------- 3");
					//System.out.println("("+x_+","+y_+")");
					int z = Math.min(xx, yy);
					
					xx = xx - z;
					yy = yy - z;
					//System.out.println(">>>--->>>> " + xx+ " , "+yy );
				}else if (y_ >= GRID_SIZE - (line_size) && x_ < GRID_SIZE - (line_size) ) {
					//System.out.println("------- 4");
					//System.out.println("("+x_+","+y_+")");
					yy= GRID_SIZE - (line_size);
					int diff = y_ - yy;
					xx = xx - diff;
					//System.out.println(">>>--->>>> " + xx+ " , "+yy );
				}else if (x_ >= GRID_SIZE - (line_size) && y_ < GRID_SIZE - (line_size)) {
					//System.out.println("------- 5");
					//System.out.println("("+x_+","+y_+")");
					xx= GRID_SIZE - (line_size);
					int diff = x_ - xx;
					yy = yy - diff;
					//System.out.println(">>>--->>>> " + xx+ " , "+yy );
				}else if ((x_ >= GRID_SIZE - (line_size)) && (y_ >= GRID_SIZE - (line_size))) {
					//System.out.println("------- 6");
					//System.out.println("("+x_+","+y_+")");
					int diff = Math.max(x_ - GRID_SIZE, y_ - GRID_SIZE);
					xx = x_ - diff - line_size;
					yy = y_ - diff - line_size;
					//System.out.println(">>>--->>>> " + xx+ " , "+yy );
				} 
				

				
				//if(xx >= 0 && xx < GRID_SIZE-line_size+1 && yy >= 0 && yy < GRID_SIZE-line_size+1) {
					Point pp = new Point(xx,yy);
					

					if(ld1.contain_Point(pp)) { //vertical
						boolean b = true;
						for(int i=0; i<line_size; i++) {
							//System.out.println("-------xxx " + xx +"iiiiii "+ i );
								if(xx+i >= 0 && xx + i < GRID_SIZE && yy+i >= 0 && yy + i < GRID_SIZE ) {
									if(this.grid[xx + i ][yy + i ].getState() == -1 && yy + i != y && xx+i != x) {
										b = false;
									}
								
								}
							
						}
						if(xx >= 0 && xx + line_size-1 < GRID_SIZE && yy >= 0 && yy + line_size-1 < GRID_SIZE ) {
							if(b) {
								//System.out.println("->>>>> xx = "+xx+" - yy = "+yy+ "->>>>> xx+ls = "+(xx+line_size-1)+" - yy+ls = "+(yy+line_size-1));
								list_lines.add(new Line(grid[xx][yy],grid[xx+line_size-1][yy+line_size-1]));
							}
						}else {
							continue;
						}
						
					}
			}
		}
		
		//diagonale 2
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
				

				
				//if(xx >= 0 && xx < GRID_SIZE-line_size+1 && yy >= 0 && yy < GRID_SIZE-line_size+1) {
					Point pp = new Point(xx,yy);
					

					if(ld2.contain_Point(pp)) { //vertical
						boolean b = true;
						for(int i=0; i<line_size; i++) {
							//System.out.println("-------xxx " + xx +"iiiiii "+ i );
								if(xx+i >= 0 && xx + i < GRID_SIZE && yy-i >= 0 && yy - i < GRID_SIZE ) {
									if(this.grid[xx + i ][yy - i ].getState() == -1 && yy - i != y && xx+i != x) {
										b = false;
									}
								
								}
							
						}
						if(xx >= 0 && xx + line_size-1 < GRID_SIZE && yy < GRID_SIZE && yy - (line_size-1) >= 0  ) {
							if(b) {
								System.out.println("->>>>> xx = "+xx+" - yy = "+yy+ "->>>>> xx+ls = "+(xx+line_size-1)+" - yy+ls = "+(yy+line_size-1));
								list_lines.add(new Line(grid[xx][yy],grid[xx+line_size-1][yy-(line_size-1)]));
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
 	 *
 	 * @param p Point
 	 * @param l Lines
 	 */
     public void change_state(Point p, Line l) {
     	this.grid[p.getX()][p.getY()].setState(p.getState());
     	this.all_list_lines.add(l);
     }
	
}
