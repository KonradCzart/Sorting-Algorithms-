package Algorithms;
/**
 * 
 * @author Konrad Czart
 *
 * 
 */
public class InsertionSort <T extends Comparable<? super T>> implements SortClass<T>{

    private int length;
    private long numberShift;
    private long numberComparison;
    private long time;
    
    public InsertionSort() {
    	numberShift = 0;
    	numberComparison = 0;
    }
    
	public void sortASC(T[] tab) {
		
		long start;
		long stop;
		start = System.nanoTime();
		
		numberShift = 0;
    	numberComparison = 0;
    	
        if (tab == null || tab.length <= 1) {
            return;
        }

        
        length = tab.length;
        
        T temp;
        int x;
        for (int i = 1; i < length; i++) {
        	x = i;
        	
        	numberComparison++; //making a comparison
        	if(tab[i].compareTo(tab[i-1]) < 0) {
            	temp = tab[i];
            	tab[x] = tab[x-1];
        		numberShift++; //making a shift
        		x--;
            	while(x > 0 && temp.compareTo(tab[x-1]) < 0)
            	{
            		numberComparison++; //making a comparison
            		tab[x] = tab[x-1];
            		numberShift++; //making a shift
            		x--;
            	}
            	
            	tab[x] = temp;
            	numberShift++; //making a shift
        	}
        }
        
		stop = System.nanoTime();
		time = stop - start;
		
	}

	public void sortDESC(T[] tab) {
		
		long start;
		long stop;
		start = System.nanoTime();
		
		numberShift = 0;
    	numberComparison = 0;
    	
        if (tab == null || tab.length <= 1) {
            return;
        }

        
        length = tab.length;
        
        T temp;
        int x;
        for (int i = 1; i < length; i++) {
        	x = i;
        	
        	numberComparison++; //making a comparison
        	if(tab[i].compareTo(tab[i-1]) < 0) {
            	temp = tab[i];
            	tab[x] = tab[x-1];
        		numberShift++; //making a shift
        		x--;
            	while(x > 0 && temp.compareTo(tab[x-1]) < 0)
            	{
            		numberComparison++; //making a comparison
            		tab[x] = tab[x-1];
            		numberShift++; //making a shift
            		x--;
            	}
            	
            	tab[x] = temp;
            	numberShift++; //making a shift
        	}
        }
        
		stop = System.nanoTime();
		time = stop - start;
		
	}

	public long getNumberShift() {
		return numberShift;
	}

	public long getNumberComparison() {
		return numberComparison;
	}
	
	public long getTime() {
		return time;
	}

}
