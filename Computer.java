//import java.util.ArrayList;

/**
 * A class for the AI
 */
public class Computer{

  /**
   * A tree of moves
   */
  public GameTree g;
  /**
   * The next value to remove from the pile
   */
  //private int rm;
  
  public Computer(boolean player, Marbles m){
    g = new GameTree(player,m,3);
  }
  
  public int nextRm(){
    return 3;
  }
      
}