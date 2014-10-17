import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class MarblesTest extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testEmpty() {
    Marbles m = new Marbles(0);
    try{
    m.remove(2);
    assert(false);
    }
    catch(Marbles.EmptyPile e){
      assert(true);
    }
    catch(Marbles.NotPrime e){
      assert(false);
    }
  }
  
  public void testNotPrime() {
    Marbles m = new Marbles(5);
    try{
    m.remove(4);
    assert(false);
    }
    catch(Marbles.EmptyPile e){
      assert(false);
    }
    catch(Marbles.NotPrime e){
      assert(true);
    }
  }
  
  public void testGood() {
    Marbles m = new Marbles(5);
    try{
    m.remove(3);
    assert(true);
    }
    catch(Marbles.EmptyPile e){
      assert(false);
    }
    catch(Marbles.NotPrime e){
      assert(false);
    }
  }
  
  public void testRemoveCountGood(){
    Marbles m = new Marbles(10);
    try{
      m.remove(3);
    }
    catch(Exception e){
      assert(false);
    }
    assert(m.count()==7);
  }
  
  public void testRemoveTo0(){
    Marbles m = new Marbles(3);
    try{
      m.remove(3);
    }
    catch(Exception e){
      assert(false);
    }
    assert(m.count()==0);
  }
  
  public void testRemoveCountNotPrime(){
    Marbles m = new Marbles(10);
    try{
      m.remove(6);
    }
    catch(Marbles.NotPrime e){
      assert(true);
    }
    catch(Exception e){
      assert(false);
    }
    assert(m.count()==10);
  }
  
  public void testRemoveCount(){
    Marbles m = new Marbles(10);
    try{
      m.remove(11);
    }
    catch(Marbles.NotPrime e){
      assert(false);
    }
    catch(Marbles.EmptyPile e){
      assert(true);
    }
    assert(m.count()==10);
  }
}