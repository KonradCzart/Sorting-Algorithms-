package Algorithms;
import java.util.Random;

/**
 * 
 * @author Konrad Czart
 * QuickSort with random pivot selection
 * 
 */
public class QuickSort <T extends Comparable<? super T>> implements SortClass<T> {

    private T tab[];
    private int length;
    private Random generator;
	
    private long numberShift;
    private long numberComparison;
    private long time;
	
    public QuickSort() {
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
    	
        if (tab == null || tab.length <= 1) {
            return;
        }
        this.tab = tab;
        length = tab.length;
        quickSortASC(0, length - 1);
        
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
        quickSortDESC(0, length - 1);
        
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
	
	private void quickSortASC(int minIndex, int maxIndex) {
		
		if(maxIndex-minIndex <= 0)
			return;

		
		int i = minIndex;
		int p = (int) (minIndex + generator.nextDouble()*(maxIndex-minIndex)); // random selection pivot
		
		T pivot = tab[p];
		swap(p,minIndex); // swap pivot with first element
		
		for(int j = i+1; j <= maxIndex; j++) {
			
			numberComparison++; //making a comparison
			if(tab[j].compareTo(pivot) <= 0) {
				
				i++;
				swap(i,j);
			}
		}
		swap(i,minIndex);
		
		quickSortASC(minIndex, i-1);
		quickSortASC(i+1, maxIndex);

	}
	
	private void quickSortDESC(int minIndex, int maxIndex) {
		
		if(maxIndex-minIndex <= 0)
			return;

		int i = minIndex;
		int p = (int) (minIndex + generator.nextDouble()*(maxIndex-minIndex)); // random selection pivot
		
		T pivot = tab[p];
		swap(p,minIndex);  // swap pivot with first element
		
		
		for(int j = i+1; j <= maxIndex; j++) {
			
			numberComparison++; //making a comparison
			if(tab[j].compareTo(pivot) >= 0) {
				i++;
				swap(i,j);
			}
		}
		swap(i,minIndex);
		
		quickSortDESC(minIndex, i-1);
		quickSortDESC(i+1, maxIndex);

	}
	
    private void swap(int i, int j) {
        T temp = tab[i];
        tab[i] = tab[j];
        numberShift++; //making a shift
        tab[j] = temp;
        numberShift++; //making a shift
    }
}
