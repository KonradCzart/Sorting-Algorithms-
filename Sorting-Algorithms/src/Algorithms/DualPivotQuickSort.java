package Algorithms;
import java.util.Random;

/**
 * 
 * @author Konrad Czart
 *
 * DualPivotQuickSort with random pivot selection
 */
public class DualPivotQuickSort <T extends Comparable<? super T>> implements SortClass<T>{
	
    private T tab[];
    private int length;
    private long numberShift;
    private long numberComparison;
    private long time;
    private int numberPivot;
    private Random generator;
    
    public DualPivotQuickSort() {
    	numberShift = 0;
    	numberComparison = 0;
    	generator = new Random();
    }

	public void sortASC(T[] tab) {
		
		long start;
		long stop;
		start = System.nanoTime();
		
    	numberShift = 0;
    	numberComparison = 0;
    	numberPivot = 0;
    	
        if (tab == null || tab.length <= 1) {
            return;
        }
        this.tab = tab;
        length = tab.length;
        dualQuickSortASC(0, length - 1);
        
		stop = System.nanoTime();
		time = stop - start;
	}

	public void sortDESC(T[] tab) {
		
		long start;
		long stop;
		start = System.nanoTime();
		
    	numberShift = 0;
    	numberComparison = 0;
    	numberPivot = 0;

        
        if (tab == null || tab.length <= 1) {
            return;
        }
        this.tab = tab;
        length = tab.length;
        dualQuickSortDESC(0, length - 1);
        
		stop = System.nanoTime();
		time = stop - start;
		
	}
	private void dualQuickSortASC(int minIndex, int maxIndex)
	{
		if(maxIndex-minIndex <= 0)
			return;
		
		int p1 =  (int) (minIndex + generator.nextDouble()*(maxIndex-minIndex)); // random selection pivot1
		int p2 =   (int) (minIndex + generator.nextDouble()*(maxIndex-minIndex)); // random selection pivot2
		T pivot1 = tab[p1];
		T pivot2 = tab[p2];
		

		swap(p1, minIndex); // swap pivot1 with first element
		if(p1 == p2 || p2 == minIndex) {
			p2 = maxIndex;
			pivot2 = tab[p2];
		}
		swap(p2, maxIndex); // swap pivot2 with first element
		
		
		if(pivot1.compareTo(pivot2) > 0) {
			numberComparison++; //making a comparison
			swap(minIndex, maxIndex); // swap pivot places if pivot1 > pivot2
			pivot1 = tab[minIndex];
			pivot2 = tab[maxIndex];
		}
	    int less  = minIndex  + 1;
	    int great = maxIndex - 1;
	    // sorting
	    for (int k = less; k <= great; k++) {
	    	
	    	// if more items before pivot 1 then compare first with pivot1 
	    	if(numberPivot >= 0) {

		    	numberComparison++; //making a comparison
		    	numberComparison++; //making a comparison
		        if (tab[k].compareTo(pivot1) < 0) {
		            swap(k, less);
		            less++;
		            numberComparison--; //remove a comparison
		            numberPivot++; // ++ numberPivot
		        } 
		        else if (tab[k].compareTo(pivot2) > 0) {
		            swap(k, great);
		            great--;
		            k--;
		            numberPivot--; // -- numberPivot
		        }
	    	}
	    	else {
	    		numberComparison++; //making a comparison 
		        numberComparison++; //making a comparison
		        if(tab[k].compareTo(pivot2) > 0) {
		            swap(k, great);
		            great--;
		            k--;
		            numberPivot--; // -- numberPivot
		            numberComparison--; //remove a comparison
		        }
	    		else if (tab[k].compareTo(pivot1) < 0) {
		            swap(k, less);
		            less++;
		            numberPivot++; // ++ numberPivot
		        }
	    	}

	    }
	    
	    swap(less  - 1, minIndex);
	    swap(great + 1, maxIndex);
	    

	    dualQuickSortASC(minIndex,   less - 2);
	    dualQuickSortASC(great + 2, maxIndex);
	    dualQuickSortASC(less, great);
	}
	
	private void dualQuickSortDESC(int minIndex, int maxIndex)
	{
		if(maxIndex-minIndex <= 0)
			return;
		
		int p1 =  (int) (minIndex + generator.nextDouble()*(maxIndex-minIndex)); // random selection pivot1
		int p2 =   (int) (minIndex + generator.nextDouble()*(maxIndex-minIndex)); // random selection pivot2

		T pivot1 = tab[p1];
		T pivot2 = tab[p2];
		
		swap(p1, minIndex); // swap pivot1 with first element
		if(p1 == p2 || p2 == minIndex) {
			p2 = maxIndex;
			pivot2 = tab[p2];
		}
		swap(p2, maxIndex); // swap pivot2 with first element
		
		if(pivot1.compareTo(pivot2) < 0) {
			numberComparison++; //making a comparison
			swap(minIndex, maxIndex); // swap pivot places if pivot1 < pivot2
			pivot1 = tab[minIndex];
			pivot2 = tab[maxIndex];
		}
	    int less  = minIndex  + 1;
	    int great = maxIndex - 1;
	    // sorting
	    for (int k = less; k <= great; k++) {
	    	
	    	// if more items before pivot 1 then compare first with pivot1 
		    if(numberPivot >= 0) {
	
		    	numberComparison++; //making a comparison
		        if (tab[k].compareTo(pivot1) > 0) {
		            swap(k, less);
		            less++;
		            numberComparison--; //making a comparison
		        } 
		        else if (tab[k].compareTo(pivot2) < 0) {
		            swap(k, great);
		            great--;
		            k--;
		        }
		        numberComparison++; //making a comparison
	    	}
	    	else {
		    	numberComparison++; //making a comparison
		    	if (tab[k].compareTo(pivot2) < 0) {
		            swap(k, great);
		            great--;
		            k--;
		            numberComparison--; //making a comparison
		        }
		    	else if (tab[k].compareTo(pivot1) > 0) {
		            swap(k, less);
		            less++;
		        } 
		        numberComparison++; //making a comparison
	    	}

	    }
	    swap(less  - 1, minIndex);
	    swap(great + 1, maxIndex);
        
        
	    dualQuickSortDESC(minIndex,   less - 2);
	    dualQuickSortDESC(great + 2, maxIndex);
	    dualQuickSortDESC(less, great);
	}
	
    private void swap(int i, int j) {
        T temp = tab[i];
        tab[i] = tab[j];
        numberShift++; //making a shift
        tab[j] = temp;
        numberShift++; //making a shift
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
