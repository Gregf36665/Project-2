import java.util.ArrayList;

/**
 * A class for the AI
 */
public class Computer{
  
  /**
   * The list of legal moves
   */
  private ArrayList<Integer> legal;
  
  /**
   * The next value to remove from the pile
   */
  private int rm;
  
  public Computer(Marbles m){
    this.legal = m.legal();
  }
  
  public int nextRm(){
    do{
      rm = (int)(Math.random()*15);
    }
    while(!legal.contains(rm));
  return rm;
  }
}