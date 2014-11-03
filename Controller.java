import java.util.ArrayList;
/**
 * An experimental controller.  The computer plays against itself
 * and information about the game is printed out
 * @author Greg Flynn
 */

public class Controller{
  
  private boolean player;
  private Computer c1, c2;
  private Marbles m;
  private ArrayList<Integer> moves;
  private int size;
  
  
/**
 * A constructor for letting the computer
 * play against itself
 */
  public Controller(){
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
   */
  public void init(int size, ArrayList<Integer> moves, boolean player, int depth){
    m = new Marbles(size,moves);
    player = player;
    c1 = new Computer(player,m,depth);
    c2 = new Computer(!player,m,depth);
    System.out.print(m.count()+","+(player?1:0)+","+depth+",");
    run();
  }
  
  
  /**
   * Runs the controller class.  This method
   * lets the computer play against itself.  It
   * prints out the size of the pile and does
   * p1 go first (1:0).  In the end it prints out
   * who wins
   * @param size how many marbles should there be to start with
   * @param moves what are legal moves
   * @param player does the human go first
   * @param depth1 how deep should the game tree be for the human, set to 1 to be random?
   * @param depth2 how deep should the game tree be for the computer?
   */
  public void init(int size, ArrayList<Integer> moves, boolean player, int depth1, int depth2){
    m = new Marbles(size,moves);
    player = player;
    c1 = new Computer(player,m,depth1);
    c2 = new Computer(!player,m,depth2);
    System.out.print(m.count()+","+(player?1:0)+",");
    run();
  }
  
  
  /**
   * Prints out the headers for the file
   */
  public void printHeaders(){
    System.out.println("Pile size, Human goes first, depth of tree 1, depth of tree 2, winner");
  }
  
  
  private void run(){
    while(!m.win()){
      if(player) removeNext1(m);
      else removeNext2(m);
      
      player = !player;
    }
    System.out.println(player?1:0);
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