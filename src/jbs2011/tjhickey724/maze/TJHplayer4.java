package jbs2011.tjhickey724.maze;



import java.util.ArrayList;
import java.util.HashMap;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.RandomPlayer;
import jbs2011.tjhickey.maze.MazeDist;
/**
 * This player just ignores the walls and goes for the nearest jewel
 * @author tim
 *
 */
public class TJHplayer4 extends MazePlayer {
	
	public static void main(String[] arrrrrgs){
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
//		  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim1"));
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer3("tim3"));
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer4("tim4"));
//		  players.add(new RandomPlayer("tim2rand"));
		  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	}

	public TJHplayer4(String n) {
		super(n);
	}
	/**
	 * This player simply picks a random direction and tries to move that way.
	 * It doesn't even check to see if the move is possible... and relies on the
	 * GameController to handle impossible move requests responsibly.... 
	 */
	   public Direction nextMove(
			   HashMap<String,MazePosition> players,
			   ArrayList<MazePosition> jewels,
			   MazeView maze) {
		   // First calculate the distance from every position to one of the jewels
		   // ignoring walls
		   MazeView m = new EmptyMaze(maze.getWidth(),maze.getDepth());
		   MazeDist dist1 = new MazeDist(m,jewels);
		   return dist1.goFrom(players.get(this.name));
	   }
		 
}
