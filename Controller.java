import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.IOException;

public class Controller{
	
	ArrayList<BinaryTree<String>> list = new ArrayList<BinaryTree<String>>();
	ArrayList<String[]> dict = new ArrayList<String[]>();
	String orderedTree = "";
	
	public void run(){
		File file = new File("dictionary.txt");
		File file2 = new File("text.txt");
		try{
			
			Scanner reader = new Scanner(file);
			ArrayList<BinaryTree<ComparableAssociation<String, String>>> list = new ArrayList<BinaryTree<ComparableAssociation<String, String>>>();
			String key = "";
			String value = "";
			
			//Aqui se lee el archivo dictionary.txt y se extraen las llaves y sus valores para el diccionario
			while(reader.hasNext()){
				String line = reader.nextLine();
				String subline = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
				String[] words = subline.split(",");
				key = words[0];
				value = words[1].substring(1);
				ComparableAssociation<String, String> link = new ComparableAssociation<String, String>(key, value);
				BinaryTree<ComparableAssociation<String, String>> node = new BinaryTree<ComparableAssociation<String,String>>(link); 
				list.add(node);
				String[] temp = {key, value};
				dict.add(temp);
				
				//Muestra las asociaciones hechas
				//System.out.print("("+key+", "+value+") \n");
				
			}
			//Muestra el tamaño del arbol
			//System.out.print("Size: "+list.size());
			
			//!!!***
			//this.craftTree();
			
			//Aqui se lee el archivo text.txt y se extraen las palabras que se van a traducir
			Scanner reader2 = new Scanner(file2);
			String[] words2 = {};
			while(reader2.hasNext()){
				String line2 = reader2.nextLine();
				String subline2 = line2.substring(0, line2.indexOf("."));
				words2 = subline2.split(" ");
			}
			
			
			int counter2 = 0;
			ArrayList<String> sentence = new ArrayList<String>();
			for(int i = 0;i<words2.length;i++){
				sentence.add(words2[i]);
			}
			
			for(int b=0;b<sentence.size();b++){
				while(counter2 < dict.size() && b<sentence.size()){
					if(sentence.get(b).equalsIgnoreCase(dict.get(counter2)[0])){
						System.out.print(dict.get(counter2)[1]+" ");
						sentence.remove(b);
						counter2 = 0;
					}
					else{
						counter2 += 1;
					}
					if(counter2 >= dict.size()){
						System.out.print("*"+sentence.get(b)+"* ");
					}
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
		catch(Exception e){
			System.out.println("\nUps.. Hubo un error...");
			e.printStackTrace();
		}
	}
	
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