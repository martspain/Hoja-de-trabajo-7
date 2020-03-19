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

	/*
		Referencia para implementacion de binary tree obtenida del libro de texto "Data Structures in Java for the principled Programmer" (Bailey, 2007),
		y del ejemplo publicado por el catedrático Julio Ayala.
		Además se utilzó como guía para el metodo de in-order el ejemplo de Swatee Chand obtenido de: https://medium.com/edureka/java-binary-tree-caede8dfada5
	*/
import java.util.Iterator;

public class BinaryTree<E>{

    protected E val; 
	protected BinaryTree<E> parent;
	protected BinaryTree<E> left, right;
	
	public BinaryTree()
	// Constructor que genera un nodo vacío
	{
		val = null;
		parent = null; left = right = this;
	}
	
	public BinaryTree(E value)
	// Constructor que asigna el valor al nodo y genera dos arboles vacios como hijos
	{
		val = value;
		right = left = new BinaryTree<E>();
		setLeft(left);
		setRight(right);
	}
	
	public BinaryTree(E value, BinaryTree<E> left, BinaryTree<E> right)
	// Constructor que asigna un valor y dos arboles como hijos
	{
		val = value;
		if (left == null){ 
			left = new BinaryTree<E>(); 
		}
		setLeft(left);
		
		if (right == null){ 
			right = new BinaryTree<E>();
		}
		setRight(right);
	}
	
	public BinaryTree<E> left()
	// Devuelve el arbol hijo izquierdo
	{
		return left;
	}
	
	public BinaryTree<E> parent()
	//Devuelve el arbol del padre si es que tiene
	{
		return parent;
	}
	
	public void setRight(BinaryTree<E> newRight){
		if (isEmpty()) return;
		if (right != null && right.parent() == this) right.setParent(null);
		right = newRight;
		right.setParent(this);
	}
	
	public void setLeft(BinaryTree<E> newLeft)
	// Le asigna valor al hijo izquierdo
	{
		if (isEmpty()) return;
		if (left != null && left.parent() == this) left.setParent(null);
		left = newLeft;
		left.setParent(this);
	}
	
	protected void setParent(BinaryTree<E> newParent)
	// Le asigna valor al padre
	{
		if (!isEmpty()) {
		parent = newParent;
		}
	}
	
	public Iterator<E> iterator()
	// Crea una lista con los elementos ordenados
	{
		return null;
	}
	
	public boolean isLeftChild()
	// post: returns true if this is a left child of parent
	{
		return this == parent.left();
	}
	
	public E value()
	// post: returns value associated with this node
	{
		return val;
	}
	
	public void setValue(E value)
	// post: sets the value associated with this node
	{
		val = value;
	}
	
	public boolean isEmpty(){
		if(this.left == null && this.right == null && this.parent == null){
			return true;
		}
		else{
			return false;
		}
	}
	
}