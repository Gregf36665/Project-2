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
   * Create a new tree
   * @param player is it the player's turn
   * @param m the stack of marbles
   */
  public GameTree(boolean player, Marbles m){
    this.player=player;
    root = new GameTreeNode(m.count(),player);
  }
  
  /**
   * Extend the node tree
   * @param depth how deep to extend the tree
   */
  public void extend(int depth){
    root.extend(depth);
  }
  
  /**
   * Extend the node tree by 1
   */
  public void extend(){
    root.extend(1);
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
   * Remove some marbles from the pile
   */
  public void remove(int rm){
    GameTreeNode old = root;
    switch(rm){
      case 3: 
        if(root.rm3()!= null) root = root.rm3();
      case 5: 
        if(root.rm5()!=null) root = root.rm5();
      case 7: 
        if(root.rm7()!=null) root = root.rm7();
      case 11: 
        if(root.rm11()!=null)root = root.rm11();
    }
    if(root!=old){
      root.extend();
      update();
    }
  }
  
  /**
   * Calculate which path is the best to take
   */
  public void update(){
    root.updateAll();
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
     * The parent node
     */
    private GameTreeNode parent;
    /**
     * Remove 3 elements
     */
    private GameTreeNode rm3;
    /**
     * Remove 5 elements
     */
    private GameTreeNode rm5;
    /**
     * Remove 7 elements
     */
    private GameTreeNode rm7;
    /**
     * Remove 11 elements
     */
    private GameTreeNode rm11;
    
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
     */
    public GameTreeNode (int size, boolean player){
      this.size = size;
      this.player = player;
      if (size < 3) value = player ? -1:1;
      
    }
    
    
    /**
     * The constructor for a node with a parent
     * @param size how many elements are there in the pile
     * @param player is it the player's turn
     * @param n the parent node
     */
    public GameTreeNode (int size, boolean player, GameTreeNode n){
      this.size = size;
      this.player = player;
      if (size < 3) value = player ? -1:1;
      this.parent = n;
    }
    /**
     * Calculate the next step
     */
    public void extend(){
      if(size - 3 > 0) rm3 = new GameTreeNode(size-3,!player,this);
      if(size - 5 > 0) rm5 = new GameTreeNode(size-5,!player,this);
      if(size - 7 > 0) rm7 = new GameTreeNode(size-7,!player,this);
      if(size - 11 > 0) rm11 = new GameTreeNode(size-11,!player,this);
    }
    
    /**
     * Calculate to a certain depth
     * @param depth how far to calculate
     */
    public void extend(int depth){
      if (depth == 0) return;
      extend();
      depth--;
      if (rm3 != null) rm3.extend(depth);
      if (rm5 != null) rm5.extend(depth);
      if (rm7 != null) rm7.extend(depth);
      if (rm11 != null) rm11.extend(depth);
    }
    

    
    /**
     * Returns the node after 3 is removed from the pile
     * @return rm3
     */
    public GameTreeNode rm3(){
      return this.rm3;
    }
    
    /**
     * Returns the node after 5 is removed from the pile
     * @return rm5
     */
    public GameTreeNode rm5(){
      return this.rm5;
    }
    
    /**
     * Returns the node after 7 is removed from the pile
     * @return rm7
     */
    public GameTreeNode rm7(){
      return this.rm7;
    }
    
    /**
     * Returns the node after 11 is removed from the pile
     * @return rm11
     */
    public GameTreeNode rm11(){
      return this.rm11;
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
      if (this.value == -1) parent.setValue(-1);
    }
    
    /**
     * Starts at the root and traverses the entire tree and updates
     */
    public void updateAll(){
      if (rm3()!=null) rm3.updateAll();
      if (rm5()!=null) rm5.updateAll();
      if (rm7()!=null) rm7.updateAll();
      if (rm11()!=null) rm11.updateAll();
      this.update();
      return;
    }
    
  }
}