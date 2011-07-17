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

public class TJHplayer3 extends MazePlayer {
	
	public static void main(String[] arrrrrgs){
		  ArrayList<MazePlayer> players = new ArrayList<MazePlayer>();
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer("tim1"));
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer2("tim2"));
		  players.add(new jbs2011.tjhickey724.maze.TJHplayer3("tim3"));
		  players.add(new RandomPlayer("tim2rand"));
		  jbs2011.tjhickey.maze.MazeGame.playTournament( players);
	}

	public TJHplayer3(String n) {
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
		   // First calculate the distance from every position to one of the other players
		   ArrayList<MazePosition> others = new ArrayList<MazePosition>(players.values());
		   others.remove(players.get(this.name));
		   MazeDist dist1 = new MazeDist(maze,others);
		   
		   // next calculate the distance from every cell to me
		   ArrayList<MazePosition> myPos = new ArrayList<MazePosition>();
		   myPos.add(players.get(this.name));
		   MazeDist dist2 = 
			    new MazeDist(maze,myPos);
		   
		   // next find a jewel which is closer to me than to any other player
		   // if such a jewel exists, otherwise use the first jewel
		   MazePosition bestJewel,OKjewel;
		   MazePosition dummy = new MazePosition(-1,-1);
		   bestJewel = dummy;
		   OKjewel = dummy;
		   int jewelDist = 1000000;
		   
		   for(MazePosition p:jewels) {
			   if ((dist2.minDist(p)>-1)) { // the jewel at p is reachable from my pos
				   OKjewel = p;
				   if  ((dist1.minDist(p)==-1) || // either the jewel isn't reachable from foes
					    (dist2.minDist(p)<dist1.minDist(p))) {// or its closer to me
					   if (dist2.minDist(p) < jewelDist) {
						   jewelDist = dist2.minDist(p); // if its closer then record it!
						   bestJewel = p;
					   }
				   }
			   }
		   }
		   
		  if (bestJewel == dummy) // if no jewel is closer to me than other, pick a reachable jewel
			  bestJewel = OKjewel;
		  
          if (bestJewel==dummy) { // if none are reachable, just move randomly!
  				int pick = new Random().nextInt(Direction.values().length);
  				return Direction.values()[pick];
  			}
          
		   // calculate the distance from every position to the bestJewel
		   myPos = new ArrayList<MazePosition>();
		   myPos.add(bestJewel);
		   dist1 = new MazeDist(maze,myPos);
         

		   // go toward the bestJewel (or move randomly if no jewel is available)
		   Direction minDir = dist1.goFrom(players.get(this.name));
          return minDir;
	}
}
