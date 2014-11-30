import java.util.ArrayList;
/**
 * A class to create a game where the computer plays itself
 * @author Greg Flynn
 */
public class AIGame{
  
  private boolean player;
  private Computer c1, c2;
  private Marbles m;
  
  /**
   * Create a new game
   */
  public AIGame(){
  }
  
  /**
   * Runs the controller class.  This method
   * lets the computer play against itself.  It
   * prints out the size of the pile and does
   * p1 go first (1:0).  It then prints out the 
   * depth of the gametree.  In the end it prints 
   * out who wins.
   * @param size how many marbles should there be to start with
   * @param moves what are legal moves
   * @param player does the human go first
   * @param depth how deep should the game tree be?
   * @reutrn did p1 win
   */
  public int init(int size, ArrayList<Integer> moves, boolean player, int depth){
    m = new Marbles(size,moves);
    c1 = new Computer(player,m,depth);
    c2 = new Computer(!player,m,depth);
    return run();
  }
  
  /**
   * Runs the controller class.  This method
   * lets the computer play against itself.  It
   * prints out the size of the pile and does
   * p1 go first (1:0).  It then prints out the 
   * depth of the gametree for p1.  P2 has a fixed
   * depth of 5.  In the end it prints out who wins.
   * @param size how many marbles should there be to start with
   * @param moves what are legal moves
   * @param player does the human go first
   * @param depth how deep should the game tree be for p1
   * @reutrn did p1 win
   */
  public int init2(int size, ArrayList<Integer> moves, boolean player, int depth){
    m = new Marbles(size,moves);
    c1 = new Computer(player,m,depth);
    c2 = new Computer(!player,m,2);
    return run();
  }
  
    private int run(){
    while(!m.win()){
      if(player) removeNext1(m);
      else removeNext2(m);
      
      player = !player;
    }
    return player?1:0;
  }
  
  
  private void removeNext1(Marbles m){
    try{
      int rm = c1.nextRm();
      c2.playerRm(rm);
      m.remove(rm);
    }
    catch(Exception e){
      System.err.println(e);
    }
  }
  
  
  private void removeNext2(Marbles m){
    try{
      int rm = c2.nextRm();
      c1.playerRm(rm);
      m.remove(rm);
    }
    catch(Exception e){
      System.err.println(e);
    }
  }
  
}
