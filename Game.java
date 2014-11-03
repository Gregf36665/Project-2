import java.util.Scanner;
import java.util.ArrayList;
import java.util.AbstractList;

/**
 * The class which runs the game
 * @author Greg Flynn
 */
public class Game{
  
  private Scanner sc;
  private int initSize;
  private Marbles m;
  private Computer comp;
  private AbstractList<Integer> legalMoves;
  private boolean player;
  private int depth;
  
  
  /**
   * Create a new instance of the game to play against the computer
   */
  public Game(){
  }
  
  
  /**
   * Run the game
   * @param sc the scanner for the user input
   */
  private void run(Scanner sc){
    legalMoves = new ArrayList<Integer>();
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
   * Create the scanner and start the game.
   * After the game has been run the scanner closes.
   *
   */
  public void startNewGame(){
    sc = new Scanner(System.in);
    run(sc);
    sc.close();
  }
  
  /**
   * Who has won?
   */
  private void winner(){
    if (!m.win()) return;
    System.out.println("There are " + m.count() + " marbles");
    if (!player) System.out.println("Player wins!");
    else System.out.println("Computer wins!");
  }
  
  
  /**
   * This is the user's ply during the game
   * @param sc the scanner to read the user input
   */
  private void playUser(Scanner sc){
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
  


  private void playComputer(){
    try{
      int rm = comp.nextRm();
      m.remove(rm);
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
  private void init(Scanner sc){
    constants(sc);          
    m = new Marbles(initSize,legalMoves);       
    comp = new Computer(player,m,depth);
    m.printRemovable();
  }
  
  
  private void constants(Scanner sc){
    initSize = pileSize(sc);
    legalMoves=validMoves(sc);
    depth = treeDepth(sc);
    player = choice(sc);
  }
  
  
  /***************************Human*inputs*****************************/
  
  /**
   * Pick who should go first.  If the user doesn't
   * specify 'human' or 'computer' exactly it
   * goes in a loop until a legal move is specified.
   * @param sc the scanner to read the user input
   * @return does the human go first
   */
  public boolean choice(Scanner sc){
    boolean out;
    System.out.println("Who should go first? 'computer' or 'human'");
       String s = sc.next();  
       if (s.equals("human")) out = true;
       else if(s.equals("computer")) out = false;
       else{
         System.out.println("Invalid choice, pick again");  
         out = choice(sc);
       }
    return out;
  }
  
  
  /**
   * Define the depth of the game tree for the computer.
   * If the users puts in an invalid number (double or
   * NaN or<=0) it loops back and runs the method again
   * @param sc the scanner to read the user input
   * @return how deep the game tree should be
   */
  public int treeDepth(Scanner sc){
    int depth;
    System.out.println("How deep should the game tree be?");
    try{
      depth = sc.nextInt();
      if (depth<1){
        System.out.println("The tree needs to have some depth!");
        depth = pileSize(sc);
      }
        
    }
    catch(java.util.InputMismatchException e){
         System.out.println("Invalid number!");
         String badValue = sc.next(); // to remove the bad number
         depth = pileSize(sc);
       }
    return depth;
  }
  
  
  
  /**
   * Set the size of the pile of marbles based on a user input.
   * If the user puts in an invalid number (double or NaN or <=0)
   * it loops back and runs the method again.
   * @param sc the scanner to read the user input
   * @return the size of the pile
   */
  public int pileSize(Scanner sc){
    int next;
    System.out.println("How large should the pile be?");
    try{
      next = sc.nextInt();
    }
    catch(java.util.InputMismatchException e){
         System.out.println("Invalid number!");
         String badValue = sc.next(); // to remove the bad number
         next = pileSize(sc);
       }
    if (next < 0){
      System.out.println("The pile size must be positive!");
      next = pileSize(sc);
    }
    return next;
  }
  
  
  /**
   * A method to find a list of numbers from the scanner.
   * This method assumes that the input is on one line and
   * each value is seperated by a spacebar.  The method keeps
   * running until there is a valid list of numbers.
   * @param sc the scanner to read data from
   * @return an list of the valid moves
   */
  public AbstractList<Integer> validMoves(Scanner sc){
    AbstractList<Integer> valid = null;
    while (valid==null){
      System.out.println("What are valid moves?");
      valid = getNumbers(sc);
    }
    return valid;
  }
  
  

  private ArrayList<Integer> getNumbers(Scanner sc){
    ArrayList<Integer> num = new ArrayList<Integer>();
    String next = sc.next();
    String line = next;
    try{
      line += sc.nextLine();
    }
    catch(java.util.NoSuchElementException e){
    }
    sc = new Scanner(line);
    while(sc.hasNext()){
      try{
        num.add(sc.nextInt());
      }
      catch(java.util.InputMismatchException e){
        System.out.println(sc.next() + " is not a valid option!");
      }
    }
      
    return num;
  }
  
  
  /**********************END*Human*inputs********************/
  
  
  /**
   * The main method to run the game
   * @param args does nothing
   */
  public static void main(String[] args){
    Game g = new Game();
    g.startNewGame();
  }
  
}