/**
 * Hoja de Trabajo 7
 * Algoritmos y Estructuras de Datos
 * Sección: 30
 * Martín España Carné: 19258
 * Fecha de entrega: 18/03/2020
 * Implementación clase Binary Search Tree
 * @author, 2001 duane a. bailey
 * Última fecha de modificación: 18/03/2020
 * Versión: 1.0
*/

package structure5;
import java.util.Iterator;
import java.util.Comparator;


public class BinarySearchTree<E extends Comparable<E>>
{
   
    protected BinaryTree<E> root;
    protected final BinaryTree<E> EMPTY = new BinaryTree<E>();
    protected int count;
    protected Comparator<E> ordering;


    public BinarySearchTree()
    {
        this(new NaturalComparator<E>());
    }

    public BinarySearchTree(Comparator<E> alternateOrder)
    {
        root = EMPTY;
        count = 0;
        ordering = alternateOrder;
    }

    public boolean isEmpty()
    {
        return root == EMPTY;
    }

    public void clear()
    {
        root = new BinaryTree<E>();
        count = 0;
    }

    public int size()
    {
        return count;
    }

    protected BinaryTree<E> locate(BinaryTree<E> root, E value)
    {
        E rootValue = root.value();
        BinaryTree<E> child;

        // found at root: done
        if (rootValue.equals(value)) return root;
        // look left if less-than, right if greater-than
        if (ordering.compare(rootValue,value) < 0)
        {
            child = root.right();
        } else {
            child = root.left();
        }
        // no child there: not in tree, return this node,
        // else keep searching
        if (child.isEmpty()) {
            return root;
        } else {
            return locate(child, value);
        }
    }

    protected BinaryTree<E> predecessor(BinaryTree<E> root)
    {
        Assert.pre(!root.isEmpty(), "No predecessor to middle value.");
        Assert.pre(!root.left().isEmpty(), "Root has left child.");
        BinaryTree<E> result = root.left();
        while (!result.right().isEmpty()) {
            result = result.right();
        }
        return result;
    }
    
    protected BinaryTree<E> successor(BinaryTree<E> root)
    {
        Assert.pre(!root.isEmpty(), "Tree is non-null.");
        Assert.pre(!root.right().isEmpty(), "Root has right child.");
        BinaryTree<E> result = root.right();
        while (!result.left().isEmpty()) {
            result = result.left();
        }
        return result;
    }

    public void add(E value)
    {
        BinaryTree<E> newNode = new BinaryTree<E>(value,EMPTY,EMPTY);

        // add value to binary search tree 
        // if there's no root, create value at root
        if (root.isEmpty())
        {
            root = newNode;
        } else {
            BinaryTree<E> insertLocation = locate(root,value);
            E nodeValue = insertLocation.value();
            // The location returned is the successor or predecessor
            // of the to-be-inserted value
            if (ordering.compare(nodeValue,value) < 0) {
                insertLocation.setRight(newNode);
            } else {
                if (!insertLocation.left().isEmpty()) {
                    // if value is in tree, we insert just before
                    predecessor(insertLocation).setRight(newNode);
                } else {
                    insertLocation.setLeft(newNode);
                }
            }
        }
        count++;
    }

    public boolean contains(E value)
    {
        if (root.isEmpty()) return false;

        BinaryTree<E> possibleLocation = locate(root,value);
        return value.equals(possibleLocation.value());
    }

    public E get(E value)
    {
        if (root.isEmpty()) return null;

        BinaryTree<E> possibleLocation = locate(root,value);
        if (value.equals(possibleLocation.value()))
          return possibleLocation.value();
        else
          return null;
    }

    public E remove(E value) 
    {
        if (isEmpty()) return null;
      
        if (value.equals(root.value())) // delete root value
        {
            BinaryTree<E> newroot = removeTop(root);
            count--;
            E result = root.value();
            root = newroot;
            return result;
        }
        else
        {
            BinaryTree<E> location = locate(root,value);

            if (value.equals(location.value())) {
                count--;
                BinaryTree<E> parent = location.parent();
                if (parent.right() == location) {
                    parent.setRight(removeTop(location));
                } else {
                    parent.setLeft(removeTop(location));
                }
                return location.value();
            }
        }
        return null;
    }

    protected BinaryTree<E> removeTop(BinaryTree<E> topNode)
    {
        // remove topmost BinaryTree from a binary search tree
        BinaryTree<E> left  = topNode.left();
        BinaryTree<E> right = topNode.right();
        // disconnect top node
        topNode.setLeft(EMPTY);
        topNode.setRight(EMPTY);
        // Case a, no left BinaryTree
        //   easy: right subtree is new tree
        if (left.isEmpty()) { return right; }
        // Case b, no right BinaryTree
        //   easy: left subtree is new tree
        if (right.isEmpty()) { return left; }
        // Case c, left node has no right subtree
        //   easy: make right subtree of left
        BinaryTree<E> predecessor = left.right();
        if (predecessor.isEmpty())
        {
            left.setRight(right);
            return left;
        }
        // General case, slide down left tree
        //   harder: successor of root becomes new root
        //           parent always points to parent of predecessor
        BinaryTree<E> parent = left;
        while (!predecessor.right().isEmpty())
        {
            parent = predecessor;
            predecessor = predecessor.right();
        }
        // Assert: predecessor is predecessor of root
        parent.setRight(predecessor.left());
        predecessor.setLeft(left);
        predecessor.setRight(right);
        return predecessor;
    }

    public Iterator<E> iterator()
    {
        return root.inorderIterator();
    }

    public int hashCode(){
        return root.hashCode();
    } 

    public String treeString(){
        return root.treeString();
    }

    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<BinarySearchTree:");
        if (!root.isEmpty()) {
            s.append(root);
        }
        s.append(">");
        return s.toString();
    }
}
