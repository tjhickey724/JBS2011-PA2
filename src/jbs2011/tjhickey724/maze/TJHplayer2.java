package jbs2011.tjhickey724.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import jbs2011.tjhickey.maze.Direction;
import jbs2011.tjhickey.maze.MazePlayer;
import jbs2011.tjhickey.maze.MazePosition;
import jbs2011.tjhickey.maze.MazeView;
import jbs2011.tjhickey.maze.RandomPlayer;
import jbs2011.tjhickey.maze.MazeDist;
/**
 * This strategy picks the shortest path to a jewel ignoring other players
 * @author tim
 *
 */
public class TJHplayer2 extends MazePlayer {
	
	public static void main(String[] arrrrrgs){
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim1"));
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer2("tim2"));
		  players.add(new RandomPlayer("tim2rand"));
		  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	}

	public TJHplayer2(String n) {
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
		   ArrayList<MazePosition> target = new ArrayList<MazePosition>();
		   target = jewels;
		   MazeDist dist = new MazeDist(maze,target,6);
		   Direction minDir = dist.goFrom(players.get(this.name));
		   if (minDir.equals(Direction.CENTER)){
				int pick = new Random().nextInt(Direction.values().length);
				return Direction.values()[pick];
			}
		   else return minDir;
	}
}
