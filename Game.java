import java.util.Scanner;

/**
 * The class which runs the game
 * @author Greg Flynn
 */
public class Game{
  
  /**
   * The size of the pile
   */
  private int size;
  /**
   * The marble pile
   */
  Marbles m;
  
  
  public Game(){
  }
  
  
  /**
   * Run the game
   */
  public void run(){
    init();
  }
  
  /**
   * Initialize the game.  Create a new pile of a certain size
   */
  public void init(){
     System.out.println("Type in the initial size of the pile");
     Scanner sc = new Scanner(System.in);
       try{
         size = sc.nextInt();
       }
       catch(java.util.InputMismatchException e){
         System.out.println("Not a number!");
         init();
       }
       
       m = new Marbles(size);
     
     
  }
    
  public static void main(String[] args){
    Game g = new Game();
    g.run();
  }
}