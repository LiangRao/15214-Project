package edu.cmu.cs.cs214.hw2.avltree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the AvlTree
 * @author raoliang
 *
 */
public class AvlTreeSetTest {
    private AvlTreeSet mTestTree;
    
    /**
     * Called before each test case method
     * @throws Exception 
     */
    @Before
    public void setUp() throws Exception {
        // Start each test case method with a brand new AvlTreeSet object.
        mTestTree = new AvlTreeSet();
    }
    
    /**
     * Called after each test case method.
     * @throws Exception  throw Exception when the method fail
     */
    @After
    public void tearDown() throws Exception {
        // Don't need to do anything here.
    }

    /** Tests that an empty tree has size 0. */
    @Test
    public void testEmptyTreeSize() {
        // First argument is the expected value.
        // Second argument is the actual returned value.
        assertEquals(0, mTestTree.size());
    }

    /** Tests isEmpty() method can return correct number*/
    @Test 
    public void testIsEmpty(){
    assertTrue(mTestTree.isEmpty());
    }
   
    /** Tests size(),insert(int value) and Contains(int value) method can return correct number*/
  @Test
  public void testInsertContainsSize(){
	   mTestTree.insert(1);
	   mTestTree.insert(0);
	   mTestTree.insert(2);
	   mTestTree.insert(3);
	   mTestTree.insert(3);
       assertEquals(4, mTestTree.size());
	   assertTrue(mTestTree.contains(1));
	   assertTrue(mTestTree.contains(3));
	   assertTrue(mTestTree.contains(0));
	   assertTrue(mTestTree.contains(2));
	   assertFalse(mTestTree.contains(600)); 
  }
  
  /** Tests part of remove(int value) method can return a exception*/ 
  @Test(expected = IllegalStateException.class)
  public void testRemove(){
	  mTestTree.remove(1);
  }
  
  /** Tests part of remove(int value) method can return correct number*/ 
  @Test
  public void testRemove2(){
	  mTestTree.insert(30);
	  mTestTree.insert(10);
	  mTestTree.remove(30);
	  assertFalse(mTestTree.contains(30));
	  
	  mTestTree.insert(40);
	  mTestTree.remove(40);
	  assertFalse(mTestTree.contains(40));
	  
	  mTestTree.insert(20);
	  mTestTree.insert(40);
	  mTestTree.remove(20);
	  mTestTree.remove(40);
	  assertFalse(mTestTree.contains(20));
	  assertFalse(mTestTree.contains(40));

	  mTestTree.insert(20);
	  mTestTree.insert(35);
	  mTestTree.insert(40);
	  mTestTree.remove(40);
	  assertFalse(mTestTree.contains(40));

	  mTestTree.remove(10);
	  assertFalse(mTestTree.contains(10));
 
  }
  
  /** Tests part of Balance() method can return correct number*/
  @Test
  public void testBalance(){
	   mTestTree.insert(20);
	   mTestTree.insert(15);
	   mTestTree.insert(30);
	   mTestTree.insert(10);
	   mTestTree.insert(5);
	   
	   assertEquals(2, mTestTree.getHeight());
	   mTestTree.insert(35);
	   mTestTree.insert(40);
	   assertEquals(2, mTestTree.getHeight());
	   
	   mTestTree.insert(45);
	   mTestTree.insert(42);
	   
	   assertEquals(3, mTestTree.getHeight());
	   
	   mTestTree.insert(4);
	   mTestTree.insert(3);
	   assertEquals(3, mTestTree.getHeight());
	   
  }
  
  @Test
  public void testMaxMinHeight(){
	  assertEquals(-1, mTestTree.getHeight());
	  mTestTree.insert(10);
	  mTestTree.insert(3);
	  mTestTree.insert(4);
	  mTestTree.insert(5);
	  assertEquals(10, mTestTree.getMax());
	  assertEquals(3, mTestTree.getMin());
	  assertEquals(2, mTestTree.getHeight());
	  
	  mTestTree.insert(6);
	  assertEquals(2, mTestTree.getHeight());



  }
  @Test(expected = IllegalStateException.class)
  public void testMin(){
	  mTestTree.getMin();
  }
  
  @Test(expected = IllegalStateException.class)
  public void testMax(){
	  mTestTree.getMax();
  }
   
}
