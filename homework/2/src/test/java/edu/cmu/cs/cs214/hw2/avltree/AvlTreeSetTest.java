package edu.cmu.cs.cs214.hw2.avltree;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AvlTreeSetTest {
    private AvlTreeSet mTestTree;

    /** Called before each test case method. */
    @Before
    public void setUp() throws Exception {
        // Start each test case method with a brand new AvlTreeSet object.
        mTestTree = new AvlTreeSet();
    }

    /** Called after each test case method. */
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
   
    /** Tests size() method can return correct number*/
  @Test
  public void test(){
	   mTestTree.insert(1);
	   mTestTree.insert(0);
	   mTestTree.insert(2);
	 //  mTestTree.insert(1);
	 //  mTestTree.insert(0);
     assertEquals(3, mTestTree.size());
	  assertTrue(mTestTree.contains(1));
	 assertTrue(mTestTree.contains(1));
	  assertTrue(mTestTree.contains(0));
	  assertTrue(mTestTree.contains(2));

	  assertFalse(mTestTree.contains(3));


	  
//	   mTestTree.mRoot;
//	   int m = mTestTree.size();
//	   assertEquals(1, mTestTree.size());
  }
  
   
}
