package jbs2011.tjhickey724.maze;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;

public class EmptyMaze implements MazeView {
    int width,depth;
    
	public EmptyMaze(int w, int d){
		this.width = w; this.depth = d;
	}
    public int getWidth(){
    	return this.width;
    }
    public int getDepth(){
    	return this.depth;
    }
	  public boolean canMove(MazePosition p, Direction d){
		  switch(d){
		  case NORTH:  return (p.col< depth-1);
		  case SOUTH:  return (p.col>0);
		  case EAST:  return (p.row<width-1);
		  case WEST:  return (p.row>0);
		  default: return true;
	  }
	  }
	  
}
