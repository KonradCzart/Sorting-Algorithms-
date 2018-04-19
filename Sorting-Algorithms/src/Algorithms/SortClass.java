package Algorithms;

/**
 * 
 * @author Konrad Czart
 * Interface for sorting classes
 */
public interface SortClass<T extends Comparable<? super T>>{
	
	public void sortASC(T [] tab);
	
	public void sortDESC(T [] tab);
	
	public long getNumberShift();

	public long getNumberComparison();
	
	public long getTime();
	
}
