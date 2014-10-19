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
  private ArrayList<Integer> primes = new ArrayList<Integer>(5);
  
  
  /**
   * A constructor creates a pile
   * with a certain amount of elements
   * 
   * @param elements the number of items in the pile
   * @param max the most elements that can be removed at one time
   */
  public Marbles(int elements, int max){
    this.elements = elements;
    if (max > 2) primes.add(3);
    if (max > 4) primes.add(5);
    if (max > 6) primes.add(7);
    if (max > 10) primes.add(11);
    if (!primes.contains(max)) System.out.println(max + " is not prime");
    printRemovable();
  }
  
  /**
   * Print out what elements can be removed
   */
  public void printRemovable(){
    System.out.print("Removable items: ");
    for (int i : primes){
      System.out.print(i+",");
    }
    System.out.println();
  }
  
  /**
   * Returns the legal moves
   * @return the number of pieces that can be removed
   */
  public ArrayList<Integer> legal(){
    return this.primes;
  }
  
  /**
   * A method to remove elements from the pile.
   * If more than the number of elements is removed
   * an exception is thrown.  If the number removed is not
   * prime then an exception is thrown.
   * 
   * @param rm the amount of pieces to remove
   * @throws EmptyPile if there are going to be negative elementes in the pile
   * @throws NotPrime if the number is not prime to remove
   */
  public void remove(int rm) throws EmptyPile, NotPrime{
    if (elements-rm<0){
      throw new EmptyPile();
    }
    if (!primes.contains(rm)){
      throw new NotPrime();
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
   * Is the pile in a winning position? If there are less than 3 pieces left then that is a win.
   * @return can a move be done on the pile
   */
  public boolean win(){
    return (elements < 3);
  }
  /**
   * An exception class to detect if there will be negative items in the pile
   */
  class EmptyPile extends Exception{
    
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
   * An exception class to detect if a number is not prime
   */
  class NotPrime extends Exception{
    
    /**
     * Create a new instance of the class
     */
    public NotPrime(){
    }
    
    /**
     * The error message to be displayed
     * @return the message
     */
    @Override
    public String getMessage(){
      return "Non prime number attempted to be removed from the pile";
    }
  }
}