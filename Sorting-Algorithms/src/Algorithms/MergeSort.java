package Algorithms;

/**
 * 
 * @author Konrad Czart
 * Merge sort with sing only one additional table
 * 
 */
public class MergeSort<T extends Comparable<? super T>> implements SortClass<T> {

	
    private T tab[];
    private int length;
    private T[] tempTab;
    private long numberShift;
    private long numberComparison;
    private long time;
    
    public MergeSort() {
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
        this.tab = tab;
        length = tab.length;
        tempTab = tab.clone();
        divTabASC(0, length - 1);
        
		stop = System.nanoTime();
		time = stop - start;
		
	}

	public void sortDESC(T[] tab) {
		
		long start;
		long stop;
		start = System.nanoTime();
		
    	numberShift = 0;
    	numberComparison = 0;
        if (tab == null || tab.length == 0) {
            return;
        }
        this.tab = tab;
        length = tab.length;
        tempTab = tab.clone();
        divTabDESC(0, length - 1);
        
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
	
    private void divTabASC(int minIndex, int maxIndex) {
        
    	// dividing index until the array is one-element
        if (minIndex < maxIndex) {
            int middle = minIndex + (maxIndex - minIndex) / 2;

            divTabASC(minIndex, middle);
            divTabASC(middle + 1, maxIndex);

            mergeASC(minIndex, middle, maxIndex);
        }
    }
    
    private void divTabDESC(int minIndex, int maxIndex) {
        
    	// dividing index until the array is one-element
        if (minIndex < maxIndex) {
            int middle = minIndex + (maxIndex - minIndex) / 2;

            divTabDESC(minIndex, middle);
            divTabDESC(middle + 1, maxIndex);

            mergeDESC(minIndex, middle, maxIndex);
        }
    }
    
    private void mergeASC(int minIndex, int middle, int maxIndex) {
    	
    	//copying elements to a secondary board
        for (int i = minIndex; i <= maxIndex; i++) {
            tempTab[i] = tab[i];
            numberShift++; //making a shift
        }
        int i = minIndex;
        int j = middle + 1;
        int k = minIndex;
        while (i <= middle && j <= maxIndex) {
        	numberComparison++; //making a comparison
        	if (tempTab[i].compareTo(tempTab[j]) <= 0) {
                tab[k] = tempTab[i];
                i++;
            } else {
                tab[k] = tempTab[j];
                j++;
            }
        	numberShift++; //making a shift
            k++;
        }
        // assigning elements from the first part
        while (i <= middle) {
            tab[k] = tempTab[i];
            numberShift++; //making a shift
            k++;
            i++;
        }
 
    }
    
    private void mergeDESC(int minIndex, int middle, int maxIndex) {
   	 	
    	//copying elements to a secondary board
        for (int i = minIndex; i <= maxIndex; i++) {
            tempTab[i] = tab[i];
            numberShift++; //making a shift
        }
        int i = minIndex;
        int j = middle + 1;
        int k = minIndex;
        while (i <= middle && j <= maxIndex) {
        	numberComparison++; //making a comparison
        	if (tempTab[i].compareTo(tempTab[j]) >= 0) {
                tab[k] = tempTab[i];
                i++;
            } else {
                tab[k] = tempTab[j];
                j++;
            }
        	numberShift++; //making a shift
            k++;
        }
        // assigning elements from the first part
        while (i <= middle) {
            tab[k] = tempTab[i];
            numberShift++; //making a shift
            k++;
            i++;
        }
 
    }

}
