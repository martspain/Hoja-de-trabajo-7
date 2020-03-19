
// @author, 2001 duane a. bailey
package structure5;
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
