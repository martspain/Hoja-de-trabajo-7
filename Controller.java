import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.IOException;

public class Controller{
	public void run(){
		File file = new File("dictionary.txt");
		
		try{
			Scanner reader = new Scanner(file);
			ArrayList<String> list = new ArrayList<String>();
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
				BinaryTree<String> node = new BinaryTree<String>(link); 
				
			}
			
		}
		catch(Exception e){
			System.out.println("\nUps.. Hubo un error...");
			e.printStackTrace();
		}
	}
}