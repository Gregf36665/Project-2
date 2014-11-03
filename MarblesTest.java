import junit.framework.TestCase;
import java.util.ArrayList;

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
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    Marbles m = new Marbles(0,l);
    try{
    m.remove(2);
    assert(false);
    }
    catch(Marbles.EmptyPile e){
      assert(true);
    }
    catch(Marbles.NotValid e){
      assert(false);
    }
  }
  
  
  public void testAboveMax() {
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    Marbles m = new Marbles(15,l);
    try{
    m.remove(7);
    assert(false);
    }
    catch(Marbles.EmptyPile e){
      assert(false);
    }
    catch(Marbles.NotValid e){
      assert(true);
    }
    assert(m.count()==15);
  }
    
  
  public void testNotPrime() {
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    Marbles m = new Marbles(5,l);
    try{
    m.remove(4);
    assert(false);
    }
    catch(Marbles.EmptyPile e){
      assert(false);
    }
    catch(Marbles.NotValid e){
      assert(true);
    }
  }
  
  public void testGood() {
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    Marbles m = new Marbles(5,l);
    try{
    m.remove(3);
    assert(true);
    }
    catch(Marbles.EmptyPile e){
      assert(false);
    }
    catch(Marbles.NotValid e){
      assert(false);
    }
  }
  
  public void testRemoveCountGood(){
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    Marbles m = new Marbles(10,l);
    try{
      m.remove(3);
    }
    catch(Exception e){
      assert(false);
    }
    assert(m.count()==7);
  }
  
  public void testRemoveTo0(){
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    Marbles m = new Marbles(3,l);
    try{
      m.remove(3);
    }
    catch(Exception e){
      assert(false);
    }
    assert(m.count()==0);
  }
  
  public void testRemoveCountNotPrime(){
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    Marbles m = new Marbles(10,l);
    try{
      m.remove(6);
    }
    catch(Marbles.NotValid e){
      assert(true);
    }
    catch(Exception e){
      assert(false);
    }
    assert(m.count()==10);
  }
  
  public void testRemoveCount(){
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    Marbles m = new Marbles(10,l);
    try{
      m.remove(11);
    }
    catch(Marbles.NotValid e){
      assert(false);
    }
    catch(Marbles.EmptyPile e){
      assert(true);
    }
    assert(m.count()==10);
  }
  
  public void testNotWin(){
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    Marbles m = new Marbles(10,l);
    assert(!m.win());
  }
  
  public void testWin(){
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    Marbles m = new Marbles(8,l);
     try{
      m.remove(7);     
    }
    catch(Marbles.NotValid e){
      assert(false);
    }
    catch(Marbles.EmptyPile e){
      assert(false);
    }
    assert(m.win());
  }

  
  public void testWinRmMin3(){
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    Marbles m = new Marbles(9,l);
     try{
      m.remove(7);     
    }
    catch(Marbles.NotValid e){
      assert(false);
    }
    catch(Marbles.EmptyPile e){
      assert(false);
    }
    assert(m.win());
  }
  
  public void testMinSorted(){
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    Marbles m = new Marbles(9,l);
    int min = m.min();
    assert(min==3);
  }
  
  public void testLt0(){
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(-2);
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    try{
      Marbles m = new Marbles(9,l);
      assert(false);
    }
    catch(java.lang.RuntimeException e){
      assert(true);
    }

  }
  
  public void testMinShuffled(){
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(7);
    l.add(11);
    l.add(3);
    l.add(5);
    Marbles m = new Marbles(9,l);
    int min = m.min();
    assert(min==3);
  }
  
  
}
