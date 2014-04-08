/*
 * Copyright (c) 2010-2013 William Bittle  http://www.dyn4j.org/
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice, this list of conditions 
 *     and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *     and the following disclaimer in the documentation and/or other materials provided with the 
 *     distribution.
 *   * Neither the name of dyn4j nor the names of its contributors may be used to endorse or 
 *     promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.dyn4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.dyn4j.resources.Messages;

/**
 * Represents an Binary Search Tree.
 * <p>
 * Null elements are not allowed and duplicates can have unexpected behavior.
 * <p>
 * Use the {@link #isSelfBalancing()} and {@link #setSelfBalancing(boolean)} methods to 
 * make the tree an AVL tree.
 * @author William Bittle
 * @version 3.1.9
 * @since 2.2.0
 * @param <E> Comparable
 */
public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {
	/**
	 * Node class for a {@link BinarySearchTree}.
	 * @author William Bittle
	 * @version 3.1.9
	 * @since 2.2.0
	 */
	protected class Node {
		/** The comparable data */
		public E comparable;
		
		/** The parent node of this node */
		public Node parent;
		
		/** The node to the left; the left node is greater than this node */
		public Node left;
		
		/** The node to the right; the right node is greater than this node */
		public Node right;
		
		/**
		 * Minimal constructor.
		 * @param comparable the comparable object
		 */
		public Node(E comparable) {
			this(comparable, null, null, null);
		}
		
		/**
		 * Full constructor.
		 * @param comparable the comparable object
		 * @param parent the parent node
		 * @param left the left node
		 * @param right the right node
		 * @throws NullPointerException if comparable is null
		 */
		public Node(E comparable, Node parent, Node left, Node right) {
			if (comparable == null) throw new NullPointerException(Messages.getString("binarySearchTree.nullComparable"));
			this.comparable = comparable;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return this.comparable.toString();
		}
		
		/**
		 * Returns true if this node is the left child of
		 * its parent node.
		 * <p>
		 * Returns false if this node does not have a parent.
		 * @return boolean
		 */
		public boolean isLeftChild() {
			if (this.parent == null) return false;
			return (this.parent.left == this);
		}
	}
	
	/**
	 * Enumeration of the traversal orders.
	 * @author William Bittle
	 * @version 3.0.0
	 * @since 2.2.0
	 */
	public static enum Direction {
		/** Traverses the nodes in order */
		ASCENDING,
		/** Traverses the node in reverse order */
		DESCENDING
	}
	
	/**
	 * Iterator class for looping through the elements in order or in reverse order.
	 * <p>
	 * The {@link #remove()} method is unsupported.
	 * @author William Bittle
	 * @version 3.0.0
	 * @since 2.2.0
	 */
	public class TreeIterator implements Iterator<E> {
		/** The node stack for iterative traversal */
		protected Deque<Node> stack;
		
		/** The traversal direction */
		protected final Direction direction;
		
		/**
		 * Default constructor using {@link Direction#ASCENDING}.
		 * @param node the root node of the subtree to traverse
		 */
		public TreeIterator(Node node) {
			this(node, Direction.ASCENDING);
		}
		
		/**
		 * Full constructor.
		 * @param node the root node of the subtree to traverse
		 * @param direction the direction of the traversal
		 * @throws NullPointerException if node or direction is null
		 */
		public TreeIterator(Node node, Direction direction) {
			// check for null
			if (node == null) throw new NullPointerException(Messages.getString("binarySearchTree.nullSubTreeForIterator"));
			if (direction == null) throw new NullPointerException(Messages.getString("binarySearchTree.nullTraversalDirection"));
			// set the direction
			this.direction = direction;
			// create the node stack and initialize it
			this.stack = new ArrayDeque<Node>();
			// check the direction to determine how to initialize it
			if (direction == Direction.ASCENDING) {
				this.pushLeft(node);
			} else {
				this.pushRight(node);
			}
			
		}
		
		/**
		 * Pushes the left most nodes of the given subtree onto the stack.
		 * @param node the root node of the subtree
		 */
		protected void pushLeft(Node node) {
			// loop until we don't have any more left nodes
			while (node != null) {
				this.stack.push(node);
				node = node.left;
			}
		}
		
		/**
		 * Pushes the right most nodes of the given subtree onto the stack.
		 * @param node the root node of the subtree
		 */
		protected void pushRight(Node node) {
			// loop until we don't have any more right nodes
			while (node != null) {
				this.stack.push(node);
				node = node.right;
			}
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return !this.stack.isEmpty();
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() {
			// if the stack is empty throw an exception
			if (this.stack.isEmpty()) throw new NoSuchElementException();
			// get an element off the stack
			Node node = this.stack.pop();
			if (this.direction == Direction.ASCENDING) {
				// add all the left most nodes of the right subtree of this element 
				this.pushLeft(node.right);
			} else {
				// add all the right most nodes of the left subtree of this element 
				this.pushRight(node.left);
			}
			// return the comparable object
			return node.comparable;
		}
		
		/**
		 * Currently unsupported.
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	/** The root node of the tree */
	protected Node root;
	
	/** The current size of the tree */
	protected int size;
	
	/** Whether to keep the tree balanced or not */
	protected boolean selfBalancing = true;
	
	/**
	 * Creates a new unbalanced binary search tree.
	 */
	public BinarySearchTree() {
		this.root = null;
		this.size = 0;
		this.selfBalancing = false;
	}
	
	/**
	 * Creates a new binary search tree.
	 * @param selfBalancing true if the tree should self balance
	 */
	public BinarySearchTree(boolean selfBalancing) {
		this.root = null;
		this.size = 0;
		this.selfBalancing = selfBalancing;
	}
	
	/**
	 * Copy constructor.
	 * <p>
	 * This performs a deep copy of the elements in the tree.  The values contained
	 * in the tree are shallow copied.
	 * @param tree the tree to copy
	 * @since 3.0.0
	 */
	public BinarySearchTree(BinarySearchTree<E> tree) {
		this.selfBalancing = tree.selfBalancing;
		this.insertSubtree(tree);
	}

	/**
	 * Copy constructor.
	 * <p>
	 * This performs a deep copy of the elements in the tree.  The values contained
	 * in the tree are shallow copied.
	 * @param tree the tree to copy
	 * @param selfBalancing true if the tree should self balance
	 * @since 3.0.0
	 */
	public BinarySearchTree(BinarySearchTree<E> tree, boolean selfBalancing) {
		this.selfBalancing = selfBalancing;
		this.insertSubtree(tree);
	}
	
	/**
	 * Returns true if this tree is self balancing.
	 * @return boolean
	 * @since 3.0.0
	 */
	public boolean isSelfBalancing() {
		return this.selfBalancing;
	}
	
	/**
	 * Sets whether this tree should self balance.
	 * <p>
	 * If the tree already contains elements the tree is reconstructed and balanced.
	 * @param flag true if the tree should self balance
	 * @since 3.0.0
	 */
	public void setSelfBalancing(boolean flag) {
		// check if the flag is true (indicating the tree should self balance) and
		// check if the tree is not already self balancing
		if (flag && !this.selfBalancing) {
			// set the local flag
			this.selfBalancing = true;
			// check for elements (0, 1, or 2 element trees don't have to be re-balanced)
			if (this.size > 2) {
				// if the tree was not already self balancing and the new flag is to have
				// the tree self balance itself we need to recreate the tree
				this.balanceTree();
			}
		}
		// set the local flag
		this.selfBalancing = flag;
	}
	
	/**
	 * Inserts the given comparable object into this binary tree.
	 * @param comparable the comparable object to insert
	 * @return boolean true if the insert was successful
	 */
	public boolean insert(E comparable) {
		// check for null
		if (comparable == null) return false;
		// create a node for this object
		Node node = new Node(comparable);
		// otherwise we need to find where to insert this node
		this.insert(node);
		// return a success
		return true;
	}
	
	/**
	 * Removes the comparable object from the tree returning the node or
	 * null if the comparable object was not found.
	 * @param comparable the comparable object
	 * @return boolean true if the element was found and removed
	 */
	public boolean remove(E comparable) {
		// check for null
		if (comparable == null) return false;
		// check for an empty tree
		if (this.root == null) return false;
		// otherwise we need to find and remove the node
		// retaining any children of the removed node
		Node node = this.remove(comparable, this.root);
		// see if it was found
		return node != null;
	}

	/**
	 * Removes the minimum value node from this tree.
	 * @return E the minimum value; null if the tree is empty
	 */
	public E removeMinimum() {
		// check for an empty tree
		if (this.root == null) return null;
		// attempt to find the minimum
		return this.removeMinimum(this.root).comparable;
	}
	
	/**
	 * Removes the maximum value node from this tree.
	 * @return E the maximum value; null if the tree is empty
	 */
	public E removeMaximum() {
		// check for an empty tree
		if (this.root == null) return null;
		// attempt to find the maximum
		return this.removeMaximum(this.root).comparable;
	}
	
	/**
	 * Returns the minimum value of the tree.
	 * @return E the minimum value; null if the tree is empty
	 */
	public E getMinimum() {
		// check for an empty tree
		if (this.root == null) return null;
		// attempt to find the minimum
		return this.getMinimum(this.root).comparable;
	}
	
	/**
	 * Returns the maximum value of the tree.
	 * @return E the maximum value; null if the tree is empty
	 */
	public E getMaximum() {
		// check for an empty tree
		if (this.root == null) return null;
		// attempt to find the maximum
		return this.getMaximum(this.root).comparable;
	}

	/**
	 * Attempts to find the given comparable object within the tree.
	 * @param comparable the comparable object to find
	 * @return boolean true if the given comparable object was found
	 */
	public boolean contains(E comparable) {
		// check for null comparable
		if (comparable == null) return false;
		// check for empty tree
		if (this.root == null) return false;
		// attempt to find the comparable
		Node node = this.contains(this.root, comparable);
		// check for null
		if (node == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the root of the tree.
	 * @return E the root value; null if the tree is empty
	 */
	public E getRoot() {
		// check for an empty tree
		if (this.root == null) return null;
		// otherwise return the value
		return this.root.comparable;
	}

	/**
	 * Empties this tree.
	 */
	public void clear() {
		// just set the root to null
		this.root = null;
		this.size = 0;
	}
	
	/**
	 * Returns true if this tree is empty.
	 * @return boolean true if empty
	 */
	public boolean isEmpty() {
		return this.root == null;
	}
	
	/**
	 * Returns the maximum depth of the tree.
	 * @return int the maximum depth
	 * @since 3.0.0
	 */
	public int getHeight() {
		return this.getHeight(this.root);
	}
	
	/**
	 * Returns the number of elements in the tree.
	 * @return int
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Returns the in-order (ascending) iterator.
	 * @return Iterator&lt;E&gt;
	 */
	@Override
	public Iterator<E> iterator() {
		return this.inOrderIterator();
	}
	
	/**
	 * Returns a new iterator for traversing the tree in order.
	 * @return Iterator&lt;E&gt;
	 */
	public Iterator<E> inOrderIterator() {
		return new TreeIterator(this.root, Direction.ASCENDING);
	}
	
	/**
	 * Returns a new iterator for traversing the tree in reverse order.
	 * @return Iterator&lt;E&gt;
	 */
	public Iterator<E> reverseOrderIterator() {
		return new TreeIterator(this.root, Direction.DESCENDING);
	}
	
	/**
	 * Returns the minimum value of the subtree of the given node.
	 * @param node the subtree root node
	 * @return {@link Node} the node found; null if subtree is empty
	 */
	protected Node getMinimum(Node node) {
		// check for a null node
		if (node == null) return null;
		// loop until we find the minimum
		while (node.left != null) {
			// continue to the left since the minimum value
			// will always be the left most node
			node = node.left;
		}
		// the minimum will be last node traversed
		return node;
	}
	
	/**
	 * Returns the maximum value of the subtree of the given node.
	 * @param node the subtree root node
	 * @return {@link Node} the node found; null if subtree is empty
	 */
	protected Node getMaximum(Node node) {
		// check for a null node
		if (node == null) return null;
		// loop until we find the maximum
		while (node.right != null) {
			// continue to the right since the maximum value
			// will always be the right most node
			node = node.right;
		}
		// the maximum will be last node traversed
		return node;
	}
	
	/**
	 * Returns the root node of this tree.
	 * @return {@link Node}
	 * @since 3.0.0
	 */
	protected Node getRootNode() {
		return this.root;
	}
	
	/**
	 * Removes the minimum value node from the subtree of the given node.
	 * @param node the subtree root node
	 * @return {@link Node} the node removed
	 */
	protected Node removeMinimum(Node node) {
		// find the minimum
		node = this.getMinimum(node);
		// check if the given subtree root node is null
		if (node == null) return null;
		// is the minimum the root node?
		if (node == this.root) {
			// preserve the right subtree by setting the new root
			// of the tree to the root of the right subtree
			this.root = node.right;
		} else if (node.parent.right == node) {
			// otherwise the minimum node is the right node of its parent
			// overwrite the right pointer of the parent to the minimum
			// node's right subtree
			node.parent.right = node.right;
		} else {
			// otherwise the minimum node is the left node of its parent
			// overwrite the left pointer of the parent to the minimum
			// node's right subtree
			node.parent.left = node.right;
		}
		// decrement the size of the tree
		this.size--;
		// return the minimum
		return node;
	}
	
	/**
	 * Removes the maximum value node from the subtree of the given node.
	 * @param node the subtree root node
	 * @return {@link Node} the node removed
	 */
	protected Node removeMaximum(Node node) {
		// find the maximum
		node = this.getMaximum(node);
		// check if the given subtree root node is null
		if (node == null) return null;
		// is the maximum the root node?
		if (node == this.root) {
			// preserve the left subtree by setting the new root
			// of the tree to the root of the left subtree
			this.root = node.left;
		} else if (node.parent.right == node) {
			// otherwise the maximum node is the right node of its parent
			// overwrite the right pointer of the parent to the maximum
			// node's left subtree
			node.parent.right = node.left;
		} else {
			// otherwise the maximum node is the left node of its parent
			// overwrite the left pointer of the parent to the maximum
			// node's left subtree
			node.parent.left = node.left;
		}
		// decrement the size of the tree
		this.size--;
		// return the maximum
		return node;
	}

	/**
	 * Returns the maximum depth of the subtree of the given node.
	 * @param node the root node of the subtree
	 * @return int the maximum depth
	 * @since 3.0.0
	 */
	protected int getHeight(Node node) {
		// check for null node
		if (node == null) return 0;
		// check for the leaf node
		if (node.left == null && node.right == null) return 1;
		// otherwise recurse
		return 1 + Math.max(this.getHeight(node.left), this.getHeight(node.right));
	}
	
	/**
	 * Returns the number of elements in the subtree.
	 * @param node the root node of the subtree
	 * @return int
	 */
	protected int size(Node node) {
		// check for null node
		if (node == null) return 0;
		// check for the leaf node
		if (node.left == null && node.right == null) return 1;
		// otherwise recurse
		return 1 + this.size(node.left) + this.size(node.right);
	}
	
	/**
	 * Returns true if the given node is contained in this tree.
	 * @param node the node to find
	 * @return boolean
	 */
	protected boolean contains(Node node) {
		// check for null
		if (node == null) return false;
		// check for empty tree
		if (this.root == null) return false;
		// check for root node
		if (node == this.root) return true;
		// start at the root node
		Node curr = this.root;
		// make sure the node is not null
		while (curr != null) {
			// check for reference equality
			if (curr == node) return true;
			// otherwise pick the direction to search
			// by comparing the data in the nodes
			int diff = node.comparable.compareTo(curr.comparable);
			// check the difference
			if (diff == 0) {
				// we have found an item exactly like this
				// node but not the same reference so return
				// false
				return false;
			} else if (diff < 0) {
				// the comparable must be to the left of this node
				// since its less than this node
				curr = curr.left;
			} else {
				// the comparable must be to the right of this node
				// since its greater than this node
				curr = curr.right;
			}
		}
		// the node was not found
		return false;
	}
	
	/**
	 * Returns the node that contains the given value or null if the
	 * value is not found.
	 * @param comparable the comparable value
	 * @return {@link Node} the node containing the given value; null if its not found
	 */
	protected Node get(E comparable) {
		// check for null comparable
		if (comparable == null) return null;
		// check for empty tree
		if (this.root == null) return null;
		// attempt to find the comparable
		return this.contains(this.root, comparable);
	}
	
	/**
	 * Inserts the given subtree into this binary tree.
	 * <p>
	 * This method copies the elements from the given subtree.
	 * @return boolean true if the insertion was successful
	 * @param node the subtree root node
	 */
	protected boolean insertSubtree(Node node) {
		// check for null
		if (node == null) return false;
		// get an iterator to go through all the nodes
		Iterator<E> iterator = new TreeIterator(node);
		// iterate over the nodes
		while (iterator.hasNext()) {
			// create a copy of the node
			Node newNode = new Node(iterator.next());
			// insert the node
			this.insert(newNode);
		}
		// the inserts were successful
		return true;
	}

	/**
	 * Inserts the given subtree into this binary tree.
	 * <p>
	 * This method copies the elements from the given tree.
	 * @return boolean true if the insertion was successful
	 * @param tree the subtree
	 */
	protected boolean insertSubtree(BinarySearchTree<E> tree) {
		// check for null
		if (tree == null) return false;
		// check for empty source tree
		if (tree.root == null) return true;
		// get an iterator to go through all the nodes
		Iterator<E> iterator = tree.inOrderIterator();
		// iterate over the nodes
		while (iterator.hasNext()) {
			// create a copy of the node
			Node newNode = new Node(iterator.next());
			// insert the node
			this.insert(newNode);
		}
		// the inserts were successful
		return true;
	}
	
	/**
	 * Removes the node containing the given value and the corresponding 
	 * subtree from this tree.
	 * @param comparable the comparable to search for
	 * @return boolean true if the element was found and its subtree was removed
	 */
	protected boolean removeSubtree(E comparable) {
		// check for null input
		if (comparable == null) return false;
		// check for empty tree
		if (this.root == null) return false;
		// attempt to find the node
		Node node = this.root;
		while (node != null) {
			// compare the data to the current node
			int diff = comparable.compareTo(node.comparable);
			// check the difference
			if (diff < 0) {
				// if the given comparable is less than the current
				// node then go to the left on the tree
				node = node.left;
			} else if (diff > 0) {
				// if the given comparable is greater than the current
				// node then go to the right on the tree
				node = node.right;
			} else {
				// we found the node, now remove it
				if (node.isLeftChild()) {
					node.parent.left = null;
				} else {
					node.parent.right = null;
				}
				// decrement the size by the size of the removed subtree
				this.size -= this.size(node);
				// re-balance the tree
				if (this.selfBalancing) this.balanceTree(node.parent);
				// return success
				return true;
			}
		}
		// if we get here the node was not found
		return false;
	}
	
	/**
	 * Removes the given node and the corresponding subtree from this tree.
	 * @param node the node and subtree to remove
	 * @return boolean true if the node was found and removed successfully
	 */
	protected boolean removeSubtree(Node node) {
		// check for null input
		if (node == null) return false;
		// check for empty tree
		if (this.root == null) return false;
		// check for root node
		if (this.root == node) {
			// set the root node to null
			this.root = null;
		} else {
			// see if the tree contains the given node
			if (this.contains(node)) {
				// which child is the node?
				if (node.isLeftChild()) {
					node.parent.left = null;
				} else {
					node.parent.right = null;
				}
				// decrement the size by the size of the removed subtree
				this.size -= this.size(node);
				// re-balance the tree
				if (this.selfBalancing) this.balanceTree(node.parent);
				// return success
				return true;
			}
		}
		// if we get here the node was not found
		return false;
	}
	
	/**
	 * Inserts the given node into the tree.
	 * <p>
	 * This method will work for an empty tree.
	 * @param item the new node to insert
	 * @return boolean true if the insertion was successful
	 * @since 3.0.0
	 */
	protected boolean insert(Node item) {
		// check for an empty tree
		if (this.root == null) {
			// set the root to the new item
			this.root = item;
			// increment the size ot the tree
			this.size++;
			// return a success
			return true;
		} else {
			// otherwise use the internal insert method
			return this.insert(item, this.root);
		}
	}
	
	/**
	 * Internal insertion method.
	 * <p>
	 * This method cannot insert into the tree if the given node parameter is null.  Use the
	 * {@link #insert(Node)} method instead to ensure that the node is inserted.
	 * @param item the node to insert
	 * @param node the subtree root node to start the search
	 * @return true if the insertion was successful
	 * @see #insert(Node)
	 */
	protected boolean insert(Node item, Node node) {
		// make sure the given node is not null
		if (node == null) return false;
		// loop until we find where the node should be placed
		while (node != null) {
			// compare the item to the current item
			if (item.comparable.compareTo(node.comparable) < 0) {
				// if the new item is less than the current item,
				// then check the left node of the current item
				if (node.left == null) {
					// if its null then we can go ahead and add
					// the item to the tree at this location
					node.left = item;
					// don't forget to set the parent node
					item.parent = node;
					// we are done, so break from the loop
					break;
				} else {
					// if the left node is not null then we need
					// to continue searching for a place to 
					// insert the new item
					node = node.left;
				}
			} else {
				// if the new item is greater than (or equal) to 
				// the current item, then check the right node 
				// of the current item
				if (node.right == null) {
					// if its null then we can go ahead and add
					// the item to the tree at this location
					node.right = item;
					// don't forget to set the parent node
					item.parent = node;
					// we are done, so break from the loop
					break;
				} else {
					// if the right node is not null then we need
					// to continue searching for a place to 
					// insert the new item
					node = node.right;
				}
			}
		}
		// increment the size
		this.size++;
		// make sure the tree remains balanced
		if (this.selfBalancing) this.balanceTree(node);
		// return success
		return true;
	}

	/**
	 * Removes the given node from this tree and returns
	 * true if the node existed and was removed.
	 * @param node the node to remove
	 * @return boolean
	 */
	protected boolean remove(Node node) {
		// check for null
		if (node == null) return false;
		// check for empty tree
		if (this.root == null) return false;
		// make sure this node is contained in the tree
		if (this.contains(node)) {
			// remove the node
			this.removeNode(node);
			// return true that the node was removed
			return true;
		}
		// otherwise return false
		return false;
	}
	
	/**
	 * Returns the node removed if the comparable is found, null otherwise.
	 * @param comparable the comparable object to remove
	 * @param node the subtree node to start the search
	 * @return {@link Node} null if the given comparable was not found
	 */
	protected Node remove(E comparable, Node node) {
		// perform an iterative version of the remove method so that
		// we can return a boolean result about removal
		while (node != null) {
			// check if the given comparable object is less than the current 
			// subtree root node
			int diff = comparable.compareTo(node.comparable);
			if (diff < 0) {
				// if its less than, we need to continue to search for the item
				// in the left subtree
				node = node.left;
			} else if (diff > 0) {
				// if its greater than, we need to continue to search for the item
				// in the right subtree
				node = node.right;
			} else {
				// if we got here we know that we found the
				// node that contains the given comparable
				this.removeNode(node);
				// return the node removed
				return node;
			}
		}
		
		// if we get here we didn't find the node in the tree
		return null;
	}
	
	/**
	 * Internal method to remove the given node from the tree retaining
	 * all the subtree nodes.
	 * <p>
	 * This method assumes that the node is contained in this tree.
	 * @param node the node to remove
	 */
	protected void removeNode(Node node) {
		boolean isLeftChild = node.isLeftChild();
		// check how many children it has
		if (node.left != null && node.right != null) {
			// find the minimum node in the right subtree and
			// use it as a replacement for the node we are removing
			Node min = this.getMinimum(node.right);
			
			// remove the minimum node from the tree
			if (min != node.right) {
				// set the minimum node's parent's left pointer to
				// the minimum node's right pointer (this removes the minimum
				// node from the tree and preserves the elements to the right
				// of the minimum node; no elements should exist to the left
				// of the minimum node since this is the minimum for this
				// subtree)
				min.parent.left = min.right;
				// we need to change the parent of the right subtree also
				if (min.right != null) {
					min.right.parent = min.parent;
				}
				// preserve the subtree to the right of the node we plan to 
				// remove by setting the minimum node's right pointer
				min.right = node.right;
			}
			
			// change the node's right subtree's parent
			if (node.right != null) node.right.parent = min;
			if (node.left != null) node.left.parent = min;
			
			// check if the node we are removing is the root
			if (node == this.root) {
				// just set the root pointer to the replacement node
				this.root = min;
			} else if (isLeftChild) {
				// set the parent's left pointer of the node we plan to delete
				// to the replacement node (the minimum node in the right subtree)
				node.parent.left = min;
			} else {
				// set the parent's right pointer of the node we plan to delete
				// to the replacement node (the minimum node in the right subtree)
				node.parent.right = min;
			}
			
			// set the left subtree of the replacement node to the left
			// subtree of the node we are removing
			min.left = node.left;
			
			// set the parent of the replacement node to the parent of the
			// node we are removing
			min.parent = node.parent;
			
			// finally make sure the tree remains balanced
			if (this.selfBalancing) this.balanceTree(min.parent);
		} else if (node.left != null) {
			// otherwise the right node of the node we want to remove is null
			
			// check if the node we are removing is the root
			if (node == this.root) {
				// just set the root pointer to the left subtree node
				this.root = node.left;
			} else if (isLeftChild) {
				// if the node we are trying to remove is the left node
				// of its parent, then set the left node of the parent to the
				// left subtree of this node
				node.parent.left = node.left;
			} else {
				// if the node we are trying to remove is the right node
				// of its parent, then set the right node of the parent to the
				// left subtree of this node
				node.parent.right = node.left;
			}
			// we need to change the parent of the left subtree also
			if (node.left != null) {
				node.left.parent = node.parent;
			}
		} else if (node.right != null) {
			// otherwise the left node of the node we want to remove is null
			
			// check if the node we are removing is the root
			if (node == this.root) {
				// just set the root pointer to the right subtree node
				this.root = node.right;
			} else if (isLeftChild) {
				// if the node we are trying to remove is the left node
				// of its parent, then set the left node of the parent to the
				// right subtree of this node
				node.parent.left = node.right;
			} else {
				// if the node we are trying to remove is the right node
				// of its parent, then set the right node of the parent to the
				// right subtree of this node
				node.parent.right = node.right;
			}
			// we need to change the parent of the right subtree also
			if (node.right != null) {
				node.right.parent = node.parent;
			}
		} else {
			// if both are null then we can just remove the node
			// check if this node is the root node
			if (node == this.root) {
				this.root = null;
			} else if (isLeftChild) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		}
		// decrement the size
		this.size--;
	}
	
	/**
	 * Internal recursive method to find an item in the tree.
	 * @param node the subtree root node
	 * @param comparable the comparable to find
	 * @return {@link Node} the node found; null if not found
	 */
	protected Node contains(Node node, E comparable) {
		// make sure the node is not null
		while (node != null) {
			// compare the comparable
			E nodeData = node.comparable;
			int diff = comparable.compareTo(nodeData);
			if (diff == 0) {
				// we found the item and we can stop
				return node;
			} else if (diff < 0) {
				// the comparable must be to the left of this node
				// since its less than this node
				node = node.left;
			} else {
				// the comparable must be to the right of this node
				// since its greater than this node
				node = node.right;
			}
		}
		// the node was not found
		return null;
	}
	
	/**
	 * Re-balances the entire tree.
	 * @since 3.0.0
	 */
	protected void balanceTree() {
		// save the current tree
		Node root = this.root;
		// empty the tree
		this.root = null;
		this.size = 0;
		// create an iterator for the old tree
		Iterator<E> iterator = new TreeIterator(root);
		// add all the elements from the old tree into the new tree
		while (iterator.hasNext()) {
			// create a new node for each old node
			Node node = new Node(iterator.next());
			// add the new node to this tree
			this.insert(node);
		}
	}
	
	/**
	 * Balances the tree recursively to the root starting at the given node.
	 * @param node the node to begin balancing
	 * @since 3.0.0
	 */
	protected void balanceTree(Node node) {
		// loop until we reach the root node
		while (node != null) {
			// balance the tree; this can return a new root
			// node because of the rotations that happen therefore
			// we need to update the current node we are on
			node = balance(node);
			// next balance the parent of this node
			node = node.parent;
		}
	}
	
	/**
	 * Balances the given node's subtree.
	 * @param node the root node of the subtree
	 * @return {@link Node} the new root
	 * @since 3.0.0
	 */
	protected Node balance(Node node) {
		// check if the node is null
		if (node == null) return null;
		// check if the node has a height of 2 or more
		if (this.getHeight(node) < 2) return node;
		
		// get the child nodes
		Node p = node.parent;
		Node a = node.left;
		Node b = node.right;
		// get the heights of the children
		int ah = this.getHeight(a);
		int bh = this.getHeight(b);
		// compute the balance
		int balance = ah - bh;
		// check the balance
		if (balance > 1) {
			
			//	    node  or    node
			//     /           /
			//    a           a
			//   /             \
			//  c               c
			
			int ach = this.getHeight(a.right);
			
			// get the subtree into left-left case
			if (ach > 1) {
				// the subtree of node is left-right
				// change it to be left-left
				Node c = a.right;
				a.right = c.left; if (c.left != null) c.left.parent = a;
				c.left = a;
				a.parent = c;
				node.left = c;
				c.parent = node;
			}
			
			//		node
			//     /
			//    c
			//   /
			//  a
			
			Node c = node.left;
			node.left = c.right; if (c.right != null) c.right.parent = node;
			c.right = node;
			c.parent = node.parent;
			node.parent = c;
			
			if (p != null) {
				if (p.left == node) {
					p.left = c;
				} else {
					p.right = c;
				}
			} else {
				this.root = c;
			}
			
			//   c
			//  / \
			// a   node
			return c;
		}
		
		if (balance < -1) {
			// node   or    node
			//     \            \
			//      b            b
			//       \          /
			//        d        d
			
			// then the right subtree need to rotate
			int bch = this.getHeight(b.left);
			
			if (bch > 1) {
				Node d = b.left;
				b.left = d.right; if (d.right != null) d.right.parent = b;
				d.right = b;
				b.parent = d;
				node.right = d;
				d.parent = node;
			}
			
			// node
			//     \
			//      d
			//       \
			//        b
			
			Node d = node.right;
			node.right = d.left; if (d.left != null) d.left.parent = node;
			d.left = node;
			d.parent = node.parent;
			node.parent = d;
			
			if (p != null) {
				if (p.left == node) {
					p.left = d;
				} else {
					p.right = d;
				}
			} else {
				this.root = d;
			}
			
			//      d
			//     / \
			// node   b
			return d;
		}
		
		return node;
	}
}
