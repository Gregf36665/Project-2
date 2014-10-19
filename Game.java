import java.util.Scanner;

/**
 * The class which runs the game
 * @author Greg Flynn
 */
public class Game{
  
  /**
   * The initial size of the pile
   */
  private int initSize;
  
  /**
   * The marble pile
   */
  private Marbles m;
  
  /**
   * The computer
   */
  private Computer comp;
  
  /**
   * The max amount of pieces to be removed
   */
  private int max;
  
  /**
   * Who's turn is it
   */
  private boolean player;
  
  public Game(){
  }
  
  
  /**
   * Run the game
   */
  public void run(){
    init();
    while(!m.win()){
      if(player) playUser();
      else playComputer();
    }
    winner();
    
  }
  
  
  /**
   * Who has won?
   */
  public void winner(){
    if (!m.win()) return;
    String message = player ? "" : "not";
    System.out.println("It is " + message + " player's turn");
    System.out.println("There are " + m.count() + " marbles");
    if (!player) System.out.println("Player wins!");
    else System.out.println("Computer wins!");
  }
  
  
  /**
   * This is the user's ply during the game
   */
  public void playUser(){
    System.out.println("There are "+m.count()+" marbles in the pile.");
    System.out.println("How many marbles do you want to remove?");
    Scanner sc = new Scanner(System.in);
    try{
      m.remove(sc.nextInt());
    }
    catch(Exception e){
      System.err.println(e.getMessage());
      playUser();
    }
    finally{
      sc.close();
      player = false;
      
    }
  }
  
  
  /**
   * This is the computers's ply during the game
   */
  public void playComputer(){
    
    int rm = comp.nextRm();
   
    try{
      m.remove(rm);
      System.out.println("Computer removes " + rm + " marbles");
      player = true;
    }
    catch(Exception e){      
    }
  }
  

  
  /**
   * Initialize the game.  Create a new pile of a certain size with a max amount of items to be removed.
   */
  public void init(){
     System.out.println("Type in the initial size of the pile (20-40)");
     Scanner sc = new Scanner(System.in);
       try{
         initSize = sc.nextInt();
         if(initSize < 20){
           System.out.println("That pile is too small!");
           init();
         }
         if(initSize > 40){
           System.out.println("That pile is too big!");
           init();
         }
         System.out.println("What is the maximum amount of pieces to be removed?");
         max = sc.nextInt();
       }
       catch(java.util.InputMismatchException e){
         System.out.println("Invalid number!");
         init();
       }
       try{
       m = new Marbles(initSize,max);
       comp = new Computer(m);
       }
       catch(RuntimeException e){
         System.out.println(e.getMessage());
         init();
       }
       System.out.println("Who should go first? P or C");
       String s = sc.next();  
       if (s.equals("P")) player = true;
       else if(s.equals("C")) player = false;
       else{
         System.out.println("Invalid choice\nPlayer going first");
         player = true;
       }
  }
    
  public static void main(String[] args){
    Game g = new Game();
    g.run();
  }
}