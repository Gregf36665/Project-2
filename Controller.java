import java.util.ArrayList;
/**
 * An experimental controller.  The computer plays against itself
 * and information about the game is printed out.
 * @author Greg Flynn
 */

public class Controller{
  
  private ArrayList<Integer> legalMoves;
  
  
/**
 * A constructor for letting the computer
 * play against itself
 */
  public Controller(){
  }
  
  
  private void loopPlayer(){
    loopTreeDepth(true);
    loopTreeDepth(false);
  }
  
  private void loopTreeDepth(boolean player){
    for (int depth = 1;depth < 10; depth++){
      loopMoves(player,depth);
    }
  }
    
    
  private void loopMoves(boolean player,int treeDepth){
    for(int i=31;i<32;i++){
      legalMoves=primes(i);
      loopSize(i,legalMoves,player,treeDepth);
    }
  }
  

  
  private void loopSize(int moveCode, ArrayList<Integer> moves,boolean player,int depth){
    for(int size=20;size<41;size++){
      System.out.print(moveCode+",");
      System.out.print(size+","+(player?1:0)+","+depth+",");
      System.out.println(percentWin(size,moves,player,depth));
    }

  }
  
  
  
  private double percentWin(int size, ArrayList<Integer> moves,boolean player, int depth){
    int wins = 0;
    for (int i=0;i<10;i++){
      AIGame g = new AIGame();
      wins += g.init2(size,moves,player,depth);
    }
   return wins/10.0;
    
  }
  
  private void printHeaders(){
    System.out.println("Move code, Pile size, Human goes first, depth of tree, percent Wins");
  }  
      
  
  /**
   * This algorithm uses binary values for a number
   * passed in and creates the appropriate list of 
   * legal moves.  Encoding:  (11)(7)(5)(3)(2) 
   * eg 35 (10011) =  => 11,3,2
   * @param pos the permutation of primes
   * @return a specific list of prime numbers
   */
  private ArrayList<Integer> primes(int pos){
    if(pos>31) throw new RuntimeException("primes not defined to go that large\nset to less than 32");
    if(pos<1) throw new RuntimeException("No valid moves specified in primes\nset to more than 0");
    ArrayList<Integer> out = new ArrayList<Integer>();
    if(bitTest(pos,1)) out.add(2);
    if(bitTest(pos,2)) out.add(3);
    if(bitTest(pos,3)) out.add(5);
    if(bitTest(pos,4)) out.add(7);
    if(bitTest(pos,5)) out.add(11); 
    
    return out;
  }
  

  /**
   * This method test the bit of a number, used to decode 
   * the encoding for the legal moves.  The algorithm assumes
   * that the LSB is to the right and indexed as 1.
   * @param number the number to try and decode
   * @param bit the bit to check if it is 0 or 1
   * @return if the bit is 1
   */
  private boolean bitTest(int number, int bit){
    for(int i=bit;i>1;i--){
      number /= 2;
    }
    return (number%2==1);
  }
  

  
  /**
   * This method runs all permutations of move combinaitons
   * @param args do nothing
   */
  public static void main(String[] args){
    Controller c = new Controller();
    c.printHeaders();
    c.loopPlayer();
  }
  
}