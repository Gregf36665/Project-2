import junit.framework.TestCase;

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
    Marbles m = new Marbles(20,11);
    GameTree t = new GameTree(true,m,3);
    assert(t.rootValue()==0);
  }
  
  public void testSizeNew() {
    Marbles m = new Marbles(20,11);
    GameTree t = new GameTree(true,m,3);
    assert(t.rootSize()==20);
  }
  
  public void testValueExtend1() {
    Marbles m = new Marbles(20,11);
    GameTree t = new GameTree(true,m,1);
    t.extend();
    assert(t.rootValue()==0);
  }
  
  public void testValueExtend2() {
    Marbles m = new Marbles(20,11);
    GameTree t = new GameTree(true,m,2);
    t.extend();
    assert(t.rootValue()==0);
  }
  
  public void testValueExtend3() {
    Marbles m = new Marbles(20,11);
    GameTree t = new GameTree(true,m,3);
    t.extend();
    assert(t.rootValue()==0);
  }
  
  public void testValueExtend4() {
    Marbles m = new Marbles(5,3);
    GameTree t = new GameTree(true,m,4);
    t.extend();
    assert(t.rootValue()==-1);
  }
  
  public void testValueExtend2Remove1() {
    Marbles m = new Marbles(20,11);
    GameTree t = new GameTree(false,m,2);
    t.extend();
    assert(t.rootValue()==0);
    t.remove(2);
    assert(t.rootValue()==-1);
  }
    
  
}
