import junit.framework.TestCase;
import java.util.ArrayList;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class GameTreeTest extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testValueNew() {
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    Marbles m = new Marbles(20,l);
    GameTree t = new GameTree(true,m,3);
    assert(t.rootValue()==0);
  }
  
  public void testSizeNew() {
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    Marbles m = new Marbles(20,l);
    GameTree t = new GameTree(true,m,3);
    assert(t.rootSize()==20);
  }
  
  public void testValueExtend1() {
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    Marbles m = new Marbles(20,l);
    GameTree t = new GameTree(true,m,1);
    t.extend();
    assert(t.rootValue()==0);
  }
  
  public void testValueExtend2() {
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    Marbles m = new Marbles(20,l);
    GameTree t = new GameTree(true,m,2);
    t.extend();
    assert(t.rootValue()==0);
  }
  
  public void testValueExtend3() {
        ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    Marbles m = new Marbles(20,l);
    GameTree t = new GameTree(true,m,3);
    t.extend();
    assert(t.rootValue()==0);
  }
  
  public void testValueExtend4() {
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    Marbles m = new Marbles(5,l);
    GameTree t = new GameTree(true,m,4);
    t.extend();
    assert(t.rootValue()==-1);
  }
  
  public void testValueExtend3Remove1() {
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    Marbles m = new Marbles(20,l);
    GameTree t = new GameTree(true,m,3);
    t.extend();
    assert(t.rootValue()==0);
    t.remove(11);
    assert(t.rootValue()==-1);
  }
  
  public void testValueExtend3Remove1TestBest() {
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(2);
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(11);
    Marbles m = new Marbles(20,l);
    GameTree t = new GameTree(true,m,3);
    t.extend();
    assert(t.rootValue()==0);
    t.remove(11);
    assert(t.rmBest()==5);
  }
       
  
}
