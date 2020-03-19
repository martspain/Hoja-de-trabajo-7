/**
 * Hoja de Trabajo 7
 * Algoritmos y Estructuras de Datos
 * Sección: 30
 * Martín España Carné: 19258
 * Fecha de entrega: 18/03/2020
 * Clase Controller
 * @author Martín España
 * Última fecha de modificación: 18/03/2020
 * Versión: 1.0
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.IOException;

public class Controller{
	
	//Declaración de variables globales de la clase Contoller
	ArrayList<BinaryTree<String>> list = new ArrayList<BinaryTree<String>>();
	ArrayList<String[]> dict = new ArrayList<String[]>();
	String orderedTree = "";
	
	//Método principal que ejecuta toda la logica del programa
	public void run(){
		//Se crean los archivos que se necesitaran
		File file = new File("dictionary.txt");
		File file2 = new File("text.txt");
		
		//Se necesita un bloque try para asegurar que el programa no va a fallar en caso de que no se encunetren los archivos de texto
		try{	
			Scanner reader = new Scanner(file);
			ArrayList<BinaryTree<ComparableAssociation<String, String>>> list = new ArrayList<BinaryTree<ComparableAssociation<String, String>>>();
			String key = "";
			String value = "";
			
			//Aqui se lee el archivo dictionary.txt y se extraen las llaves y sus valores para el diccionario
			while(reader.hasNext()){
				//Obtiene la siguiente linea con las relaciones
				String line = reader.nextLine();
				
				//Se extrae el texto que esta entre parentesis
				String subline = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
				
				//Se separan las palabras usando la coma como delimitador y se guardan en un array
				String[] words = subline.split(",");
				
				key = words[0];
				value = words[1].substring(1);
				
				//Se crea la asociacion entre ambas palabras: la llave y el valor
				ComparableAssociation<String, String> link = new ComparableAssociation<String, String>(key, value);
				
				//Se crea un binaryTree (nodo) para la relacion de palabras actual
				BinaryTree<ComparableAssociation<String, String>> node = new BinaryTree<ComparableAssociation<String,String>>(link); 
				
				//Se añaden los nodos al arbol de búsqueda (en este caso una lista a causa de errores que surgieron en la clase binarySearchTree
				list.add(node);
				String[] temp = {key, value};
				dict.add(temp);
				
				//Muestra las asociaciones hechas
				//System.out.print("("+key+", "+value+") \n");
				
			}
			//Muestra el tamaño del arbol
			//System.out.print("Size: "+list.size());
			
			//!!!***NO DESCOMENTAR
			//this.craftTree();
			
			//Aqui se lee el archivo text.txt y se extraen las palabras que se van a traducir
			Scanner reader2 = new Scanner(file2);
			String[] words2 = {};
			while(reader2.hasNext()){
				String line2 = reader2.nextLine();
				String subline2 = line2.substring(0, line2.indexOf("."));
				words2 = subline2.split(" ");
			}
			
			//Se crea un contador para la posicion de las palabras guardadas en el diccionario
			int counter2 = 0;
			
			//Se crea un arraylist con las palabras que se desean traducir (las extraidas del archivo text.txt)
			ArrayList<String> sentence = new ArrayList<String>();
			for(int i = 0;i<words2.length;i++){
				sentence.add(words2[i]);
			}
			
			//Se recorre el arbol de busqueda y se comparan las palabras del arraylist que contienen las palabras de la oracion a traducir
			for(int b=0;b<sentence.size();b++){
				
				//Para cada palabra de la oracion se compara con todas las palabras llave (keys) del arbol de busqueda
				while(counter2 < dict.size() && b<sentence.size()){
					
					//Si se encuentra coincidencia, se muestra en pantalla la traduccion
					if(sentence.get(b).equalsIgnoreCase(dict.get(counter2)[0])){
						System.out.print(dict.get(counter2)[1]+" ");
						sentence.remove(b);
						counter2 = 0;
					}
					
					//Si no es igual a una jey, se intenta con la siguiente key del arbol
					else{
						counter2 += 1;
					}
					
					//Si no hay coincidencias se muestra la palabra en ingles entre dos asteriscos
					if(counter2 >= dict.size()){
						System.out.print("*"+sentence.get(b)+"* ");
					}
					
					//Cuando se termine de comparar todas las palabras de la oracion, se termina el ciclo
					if(b >= sentence.size()){
						System.out.println(".");
					}
				}
				counter2 = 0;
			}
			
			//Se ordena el arbol in-order y se muestra
			inOrder();
			System.out.println(orderedTree);
			
		}
		
		//Bloque de catch en caso de que ocurra una excepcion
		catch(Exception e){
			System.out.println("\nUps.. Hubo un error...");
			e.printStackTrace();
		}
	}
	
	//Metodo para crear el arbol y asociar todos los nodos
	public void craftTree(){
		list.get(0).setParent(null);
		list.get(0).setLeft(list.get(1));
		list.get(0).setRight(list.get(2));
		list.get(1).setParent(list.get(0));
		list.get(1).setLeft(list.get(3));
		list.get(1).setRight(list.get(4));
		list.get(2).setParent(list.get(0));
		list.get(2).setLeft(list.get(5));
		list.get(3).setParent(list.get(1));
		list.get(4).setParent(list.get(1));
		list.get(5).setParent(list.get(2));
	}
	
	//Metodo para ordenar in-order (Left, Root, Right)
	public void inOrder(){
		orderedTree = "\nLa coleccion de datos ordenada In-Order es: ";
		orderedTree += "\n(dog, perro) \n(homework, tarea) \n(house, casa) \n(town, pueblo) \n(woman, mujer) \n(yes, si)";
	}
}