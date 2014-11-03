import java.util.ArrayList;
import java.util.Iterator;
import java.util.AbstractList;
/**
 * A minmax tree for the AI to solve the best moves
 * @author Greg Flynn
 */

public class GameTree{
  
  /**
   * Does the player go first?
   */
  private boolean player;
  
  /**
   * The current position in the GameTree
   */
  private GameTreeNode root;
   
  /**
   * The list of legal moves
   */
  private AbstractList<Integer> legalMoves;
  
  
  /**
   * The depth of the game tree
   */
  private int depth;
  
  /**
   * Create a new tree
   * @param player is it the player's turn
   * @param m the stack of marbles
   * @throws RuntimeException if the GameTree has no depth
   */
  public GameTree(boolean player, Marbles m,int depth){
    this.player=player;
    this.legalMoves = m.legal();
    this.depth = depth;
    if (depth < 1 ) throw new RuntimeException("Depth must be more than 1 for the GameTree!");
    root = new GameTreeNode(m.count(),player,legalMoves);    
  }
  

  
  /**
   * Extend the node tree by 1
   */
  public void extend(){
    root.extend(depth);
    root.updateAll();
  }
  
  /**
   * The value (-1,0,1) at the node
   * @return the value of the node
   */
  public int rootValue(){
    return root.getValue();
  }
  
  /**
   * the amount of marbles at the node
   * @return the number of marbles
   */
  public int rootSize(){
    return root.size();
  }
  
  
  /**
   * Performs the best move and decends down the tree as
   * well as updating the tree
   * @return the number of pieces to remove to win
   */
  public int rmBest(){
    int currentSize = root.size();
    root = root.bestMove();
    extend();
    return currentSize - root.size();
  }
  
  /**
   * Remove some marbles from the pile
   * @param rm the value to remove
   */
  public void remove(int rm){
    root = root.remove(rm); 
    extend();   
  }
  
  
  
  
  /**
   * The nodes for the minmax tree.  If the player has won
   * then the value for the node is 1.  If the player has lost
   * then the value for the node is -1.  Else it is 0.
   */
  class GameTreeNode{
    /**
     * The amount of items left in the pile
     */
    private int size;
    
    /**
     * The list of legal moves
     */
    private AbstractList<Integer> legalMoves;
  
    /**
     * An iterator of legal moves
     */
    private Iterator<Integer> moves;
    
    /**
     * The parent node
     */
    private GameTreeNode parent;
    /**
     * Children after some amount has been removed
     */
    private AbstractList<GameTreeNode> children;
    /**
     * The value of the node, 0 by default
     */
    private int value = 0;
    
    /**
     * Is it the player's turn?
     */
    private boolean player;
    
    /**
     * The constructor for the root
     * @param size how many elements are there in the pile
     * @param player is it the player's turn
     * @param legalMoves an arrayList of legal moves
     */
    public GameTreeNode (int size, boolean player, AbstractList<Integer> legalMoves){
      this.size = size;
      this.player = player;
      this.children = new ArrayList<GameTreeNode>();
      if (size < 2) value = player ? -1:1;
      if (size < 0) value = player ? 1:-1;
      this.legalMoves = legalMoves;
      this.moves=legalMoves.iterator();
    }
    
    
    /**
     * The constructor for a node with a parent
     * @param size how many elements are there in the pile
     * @param player is it the player's turn
     * @param n the parent node
     * @param legalMoves an arrayList of legal moves
     */
    public GameTreeNode (int size, boolean player, GameTreeNode n, AbstractList<Integer> legalMoves){
      this.size = size;
      this.player = player;
      this.children = new ArrayList<GameTreeNode>();
      if (size < 2) value = player ? -1:1;
      if (size < 0) value = player ? 1:-1;
      this.parent = n;
      this.legalMoves = legalMoves;
      this.moves=legalMoves.iterator();
    }
    
    
    
    /**
     * Calculate the next step
     */
    public void extend(){
      while(moves.hasNext()){
        int rm = moves.next();
        if(size-rm<0) return;
        GameTreeNode node = new GameTreeNode(size-rm,!player,this,legalMoves);
        children.add(node);
      }

    }
    
    /**
     * Calculate to a certain depth
     * @param depth how far to calculate
     */
    public void extend(int depth){
      if (depth == 0) return;
      extend();
      depth--;
      for (GameTreeNode n : children){
        n.extend(depth);
      }
    }
    

    /**
     * Finds the best move to do.  This algorithm finds the most pieces that can be removed
     * and still win
     * @return the node of the most pieces to remove
     */
    public GameTreeNode bestMove(){
      GameTreeNode rm;
      ArrayList<GameTreeNode> good = new ArrayList<GameTreeNode>();
      for (GameTreeNode n : children){
        if (n.getValue() == -1) good.add(n);
      }
      if (good.size()>0) return good.get(good.size()-1);
      int next = (int)(Math.random()*children.size());
      return children.get(next);
    }
    
   /**
    * Select the next node after the player has removed a certian amount
    * @param rm the number of pieces removed
    * @throws RuntimeException if the node doesn't exist
    */
    public GameTreeNode remove(int rm){
      for (GameTreeNode n : children){
        if (this.size()-n.size()==rm) return n;
      }
    
      throw new RuntimeException("Invalid remove performed according to GameTree");
    }
    /**
     * The value of the node (-1,0,1)
     * @return is the position a win
     */
    public int getValue(){
      return this.value;
    }
    
    /**
     * Returns the amount of marbles left in the pile
     * @return amount of marbles
     */
    public int size(){
      return this.size;
    }
    
    /**
     * Sets the value of a node
     * @param s the value to set the node to
     */
    public void setValue(int s){
      this.value = s;
    }
    
    /**
     * Checks the values of the nodes to see if there is a winning path
     */
    public void update(){
      if (parent == null) return;
      if (this.value == -1)parent.setValue(-1);
      
    }
    
    /**
     * Starts at the root and traverses the entire tree and then updates
     */
    public void updateAll(){
      
      for(GameTreeNode n : children){
        n.updateAll();
      }      
      this.update();
      return;
    }    
  }
}