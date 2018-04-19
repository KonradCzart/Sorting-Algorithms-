package Algorithms;
/**
 * 
 * @author Konrad
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
		boolean shift = false;
		
		numberShift = 0;
    	numberComparison = 0;
    	
        if (tab == null || tab.length <= 1) {
            return;
        }

        
        length = tab.length;
        
        T temp;
        int x;
        for (int i = 1; i < length; i++) {
        	temp = tab[i];
        	x = i;
        	while(x > 0 && temp.compareTo(tab[x-1]) < 0)
        	{
        		numberComparison++; //making a comparison
        		System.err.println("Comparison: current="+ temp + " vs tab["+ (x-1) +"]=" + tab[x-1]); // err print comparison
        		tab[x] = tab[x-1];
        		System.err.println("Shift: tab["+x+"] = " + tab[x-1]); //err print shift
        		numberShift++; //making a shift
        		x--;
        		shift = true;
        	}
        	numberComparison++; //making a comparison
        	if(x>0)
        		System.err.println("Comparison: current="+ temp + " vs tab["+ (x-1) +"]=" + tab[x-1]); // err print comparison
        	if(shift) {
            	tab[x] = temp;
            	System.err.println("Shift: tab["+x+"] = " + temp); //err print shift
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
		boolean shift = false;
		
		numberShift = 0;
    	numberComparison = 0;
    	
        if (tab == null || tab.length <= 1) {
            return;
        }

        
        length = tab.length;
        
        T temp;
        int x;
        for (int i = 1; i < length; i++) {
        	temp = tab[i];
        	x = i;
        	
        	while(x > 0 && temp.compareTo(tab[x-1]) > 0)
        	{
        		numberComparison++; //making a comparison
        		System.err.println("Comparison: current="+ temp + " vs tab["+ (x-1) +"]=" + tab[x-1]); // err print comparison
        		tab[x] = tab[x-1];
        		System.err.println("Shift: tab["+x+"] = " + tab[x-1]); //err print shift
        		numberShift++; //making a shift
        		x--;
        		shift = true;
        	}
        	numberComparison++; //making a comparison
        	if(x>0)
        		System.err.println("Comparison: current="+ temp + " vs tab["+ (x-1) +"]=" + tab[x-1]); // err print comparison
        	if(shift) {
            	tab[x] = temp;
            	System.err.println("Shift: tab["+x+"] = " + temp); //err print shift
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
