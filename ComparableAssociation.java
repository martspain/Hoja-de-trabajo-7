/**
 * Hoja de Trabajo 7
 * Algoritmos y Estructuras de Datos
 * Sección: 30
 * Martín España Carné: 19258
 * Fecha de entrega: 18/03/2020
 * Implementación clase ComparableAssociation
 * @author, 2001 duane a. bailey
 * Última fecha de modificación: 18/03/2020
 * Versión: 1.0
*/

	/*
		Referencia para implementacion de ComparableAssociation obtenida del libro de texto "Data Structures in Java for the principled Programmer" (Bailey, 2007),
		y del ejemplo publicado por el catedrático Julio Ayala.
		Además se utilzó como guía el ejemplo de Stackoverflow obtenido de: https://stackoverflow.com/questions/13443724/uml-how-to-implement-association-class-in-java
	*/

import java.util.Map;

public class ComparableAssociation<K,V>
{
	
	protected K key;
	protected V value;
	
	
    public ComparableAssociation(K key)
    {
        this.key = key;
		this.value = null;
    }

    public ComparableAssociation(K key, V value)
    {
        this.key = key;
		this.value = value;
    }

    public boolean compareTo(ComparableAssociation<K,V> that)
    {
        return this.getKey().equals(that.getKey());
    }

    public String toString()
    {
        return this.key.toString() + ", " + this.value.toString();
    }
	
	public K getKey(){
		return this.key;
	}
}
