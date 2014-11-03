import java.util.AbstractList;
import java.util.ArrayList;
/**
 * This class is for the marbles, the objects that
 * are played with
 * @author Greg Flynn
 */
public class Marbles{
  /**
   * This is the number of elements left in the pile
   */
  private int elements;
  
  /**
   * This is choices for elements that can be removed
   */
  private AbstractList<Integer> legalMoves;
  
  
  /**
   * A constructor creates a pile
   * with a certain amount of elements
   * 
   * @param elements the number of items in the pile
   * @param legalMoves a list of moves that are permitted
   * @throws RuntimeException if the list of legal moves contains a negative
   */
  public Marbles(int elements, AbstractList<Integer> legalMoves){
    this.elements = elements;
    if (containsLt0(legalMoves)) throw new RuntimeException("List contains negative numbers");
    this.legalMoves = legalMoves;
  }
  

  private boolean containsLt0(AbstractList<Integer> list){
    for (int i : list){
      if (i < 0) return true;
    }
    return false;
  }
   
  
  /**
   * Print out what elements can be removed
   */
  public void printRemovable(){
    System.out.print("Removable items: ");
    for (int i : legalMoves){
      System.out.print(i+",");
    }
    System.out.println();
  }
  
  /**
   * Returns the legal moves
   * @return the number of pieces that can be removed
   */
  public AbstractList<Integer> legal(){
    return this.legalMoves;
  }
  
  /**
   * A method to remove elements from the pile.
   * If more than the number of elements is removed
   * an exception is thrown.  If the number removed is not
   * prime then an exception is thrown.
   * 
   * @param rm the amount of pieces to remove
   * @throws EmptyPile if there are going to be negative elementes in the pile
   * @throws NotValid if the number is not prime to remove
   */
  public void remove(int rm) throws EmptyPile, NotValid{
    if (elements-rm<0){
      throw new EmptyPile();
    }
    if (!legalMoves.contains(rm)){
      throw new NotValid();
    }
    elements -= rm;
  }
  
  /**
   * Get the number of pieces left in the pile
   * @return the count of elements
   */
  public int count(){
    return this.elements;
  }
  
  
  /**
   * Is the pile in a winning position? 
   * If there are less than the minimum amount of
   * pieces left then that is a win.
   * @return can a move be done on the pile
   */
  public boolean win(){
    return (elements < min());
  }
  
  /**
   * What is the fewest pieces that can be removed
   * @return the minimum amount of pieces that can be removed
   */
  public int min(){
    int min = Integer.MAX_VALUE;
    for (int i : legalMoves){
      if (i < min ) min = i;
    }
    return min;
  }
    
  /**
   * An exception class to detect if there will be negative items in the pile
   */
  protected class EmptyPile extends Exception{
    
    /**
     * Creates a new instance of the class
     */
    public EmptyPile(){
    }
    
    /**
     * The error message to be displayed
     * @return the message
     */
    @Override
    public String getMessage(){
      return "Attempted to remove more element than exist in the pile";
    }
    
  }
  
  /**
   * An exception class to detect if a number is not a legal move
   */
  protected class NotValid extends Exception{
    
    /**
     * Create a new instance of the class
     */
    public NotValid(){
    }
    
    /**
     * The error message to be displayed
     * @return the message
     */
    @Override
    public String getMessage(){
      return "Illegal amout of pieces removed";
    }
  }
}