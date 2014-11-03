/**
 * A class for the AI
 * @author Greg Flynn
 */
public class Computer{

  private GameTree g;

  /**
   * Create a new computer
   * @param player does the human go first
   * @param m the pile of marbles
   * @param depth how deep does the game tree go?
   */
  public Computer(boolean player, Marbles m, int depth){
    g = new GameTree(player,m,depth);
    g.extend();
  }
  
  
  /**
   * Figure out the best move to do
   * @return the number of pieces to take out of the pile
   */
  public int nextRm(){
    return g.rmBest();
  }
  
  
  /**
   * Notice that the player has removed some pieces from 
   * the pile
   * @param rm the number of pieces removed from the pile
   */
  public void playerRm(int rm){
    g.remove(rm);
  }
      
}