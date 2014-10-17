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
  private final ArrayList<Integer> primes = new ArrayList<Integer>(5);
  
  
  /**
   * A constructor creates a pile
   * with a certain amount of elements
   * 
   * @param elements the number of items in the pile
   */
  public Marbles(int elements){
    this.elements = elements;
    primes.add(2);
    primes.add(3);
    primes.add(5);
    primes.add(7);
    primes.add(11);
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
      return "Non prime number attempted to be removed from the stack";
    }
  }
}