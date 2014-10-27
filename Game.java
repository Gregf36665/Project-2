import java.util.Scanner;

/**
 * The class which runs the game
 * @author Greg Flynn
 */
public class Game{
  
  /**
   * The scanner to read in user input
   */
  Scanner sc;
  
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
  public void run(Scanner sc){
    sc = new Scanner(System.in);
    init(sc);
    while(!m.win()){
      if(player) playUser(sc);
      else playComputer();
    }
    winner();
    System.out.println("Play again? Y or N");
    if (sc.next().equals("Y")) run(sc);
    
      
  }
  
  /**
   * Create the scanner and start the game
   *
   */
  public void makeScanner(){
    sc = new Scanner(System.in);
    run(sc);
  }
  
  /**
   * Who has won?
   */
  public void winner(){
    if (!m.win()) return;
    System.out.println("There are " + m.count() + " marbles");
    if (!player) System.out.println("Player wins!");
    else System.out.println("Computer wins!");
  }
  
  
  /**
   * This is the user's ply during the game
   * @ sc the scanner to read the user input
   */
  public void playUser(Scanner sc){
    System.out.println("There are "+m.count()+" marbles in the pile.");
    System.out.println("How many marbles do you want to remove?");
    try{
      m.remove(sc.nextInt());
      player = false;
    }
    catch(Marbles.NotValid e){
      System.err.println(e.getMessage());
    }
    catch(Marbles.EmptyPile e){
    }
    catch(java.util.InputMismatchException e){
      System.err.println("Not a number");
    }
  }
  
  
  /**
   * This is the computers's ply during the game
   */
  public void playComputer(){
    
    int rm = comp.nextRm();
   
    try{
      m.remove(3);
      System.out.println("Computer removes " + rm + " marbles");
      player = true;
    }
    catch(Exception e){      
    }
  }
  

  
  /**
   * Initialize the game.  Create a new pile of a certain size with a max amount of items to be removed.
   * @param sc the Scanner to read the user input
   */
  public void init(Scanner sc){
     System.out.println("Type in the initial size of the pile (20-40)");
       try{
         initSize = sc.nextInt();
         if(initSize < 20){
           System.out.println("That pile is too small!");
           init(sc);
         }
         if(initSize > 40){
           System.out.println("That pile is too big!");
           init(sc);
         }
         System.out.println("What is the maximum amount of pieces to be removed?");
         max = sc.nextInt();
       }
       catch(java.util.InputMismatchException e){
         System.out.println("Invalid number!");
         init(sc);
       }
       try{
       m = new Marbles(initSize,max);
       
       }
       catch(RuntimeException e){
         System.out.println(e.getMessage());
         init(sc);
       }
       System.out.println("Who should go first? P or C");
       String s = sc.next();  
       if (s.equals("P")) player = true;
       else if(s.equals("C")) player = false;
       else{
         System.out.println("Invalid choice\nPlayer going first");
         player = true;
       }
       comp = new Computer(player,m);
  }
    
  /**
   * The main method to run the game
   * @param args does nothing
   */
  public static void main(String[] args){
    Game g = new Game();
    g.makeScanner();
  }
}