import junit.framework.TestCase;
import java.util.Scanner;
import java.util.AbstractList;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class GameTest extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testPlayAsHuman() {
    Scanner sc = new Scanner("human");
    Game g = new Game();
    assert(g.choice(sc));     
  }
  
  public void testPlayAsComputer() {
    Scanner sc = new Scanner("computer");
    Game g = new Game();
    assert(!g.choice(sc));     
  }
  
  public void testPlayAsHumanCaseErrorThenComputer() {
    Scanner sc = new Scanner("Human computer");
    Game g = new Game();
    assert(!g.choice(sc));     
  }
  
    public void testPlayAsComputerCaseErrorThenHuman() {
    Scanner sc = new Scanner("Computer human");
    Game g = new Game();
    assert(g.choice(sc));     
  }
    
  public void testPlayAsInvalidStringThenComputer() {
    Scanner sc = new Scanner("somethingElse computer");
    Game g = new Game();
    assert(!g.choice(sc));     
  }  
    
  public void testPlayAsInvalidStringThenHuman() {
    Scanner sc = new Scanner("somethingElse human");
    Game g = new Game();
    assert(g.choice(sc));     
  }
  
  public void testPlayAsIntThenComputer() {
    Scanner sc = new Scanner("123 computer");
    Game g = new Game();
    assert(!g.choice(sc));     
  }  
    
  public void testPlayAsDoubleThenComputer() {
    Scanner sc = new Scanner("12.3 computer");
    Game g = new Game();
    assert(!g.choice(sc));     
  }
  
  public void testPlayAsNumberThenHuman() {
    Scanner sc = new Scanner("123 human");
    Game g = new Game();
    assert(g.choice(sc));     
  }
  
  public void testTreeDepth5() {
    Scanner sc = new Scanner("5");
    Game g = new Game();
    assert(5==g.treeDepth(sc));     
  }  
  
  public void testTreeDepthDoublethen5() {
    Scanner sc = new Scanner("3.5 5");
    Game g = new Game();
    assert(5==g.treeDepth(sc));     
  }
    
  public void testTreeDepth0then5() {
    Scanner sc = new Scanner("0 5");
    Game g = new Game();
    assert(5==g.treeDepth(sc));     
  }
  
  public void testTreeDepthStringthen5() {
    Scanner sc = new Scanner("Hello 5");
    Game g = new Game();
    assert(5==g.treeDepth(sc));     
  }

  public void testTreeDepthLt0then5() {
    Scanner sc = new Scanner("-2 5");
    Game g = new Game();
    assert(5==g.treeDepth(sc));     
  }
  
 
  public void testPileSize5() {
    Scanner sc = new Scanner("5");
    Game g = new Game();
    assert(5==g.pileSize(sc));     
  }
    
  public void testPileSizeDoubleThen3() {
    Scanner sc = new Scanner("5.5 3");
    Game g = new Game();
    assert(3==g.pileSize(sc));     
  }
  
  
  public void testPileSizeLt0then5() {
    Scanner sc = new Scanner("-2 5");
    Game g = new Game();
    assert(5==g.pileSize(sc));     
  }
  
  public void testPileSizeStringthen5() {
    Scanner sc = new Scanner("Abc 5");
    Game g = new Game();
    assert(5==g.pileSize(sc));     
  }
  
  public void testValidMovesOne(){
    Scanner sc = new Scanner("1");
    Game g = new Game();
    AbstractList<Integer> test = g.validMoves(sc);
    assert(test.get(0)==1);
  }
  
  public void testValidMovesMany(){
    Scanner sc = new Scanner("1 2 3");
    Game g = new Game();
    AbstractList<Integer> test = g.validMoves(sc);
    assert(test.get(0)==1);
    assert(test.get(1)==2);
    assert(test.get(2)==3);
  }
  
  public void testValidMovesManyDouble(){
    Scanner sc = new Scanner("1.0 2 3");
    Game g = new Game();
    AbstractList<Integer> test = g.validMoves(sc);
    assert(test.get(0)==2);
    assert(test.get(1)==3);
  }
  
  public void testValidMovesSingleDouble(){
    Scanner sc = new Scanner("1.0");
    Game g = new Game();
    AbstractList<Integer> test = g.validMoves(sc);
    assert(test.size()==0);
  }
  
  public void testValidMovesString(){
    Scanner sc = new Scanner("Hello world");
    Game g = new Game();
    AbstractList<Integer> test = g.validMoves(sc);
    assert(test.size()==0);
  }
  
  
}
