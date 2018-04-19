package Algorithms;
import java.util.Random;
/**
 * 
 * @author Konrad Czart
 *  HybrydQuickSort is combination QuickSort and InsertionSort
 * 
 */
public class HybrydQuickSort<T extends Comparable<? super T>> implements SortClass<T> {

	
    private T tab[];
    private int length;
    private Random generator;
	
    private long numberShift;
    private long numberComparison;
    private long time;
    
    public HybrydQuickSort() {
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
        hybrydSortASC(0, length - 1);
		
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
        hybrydSortDESC(0, length - 1);		
		
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
	
	private void hybrydSortASC(int minIndex, int maxIndex) {
		
		int count = maxIndex-minIndex;
		if(count <= 0)
			return;
		else if(count < 10)  // if the n-element table switch to InsertionSort
			insertionSortASC(minIndex, maxIndex);
		else {
			
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
			
			hybrydSortASC(minIndex, i-1);
			hybrydSortASC(i+1, maxIndex);
		}

	}
	
	private void hybrydSortDESC(int minIndex, int maxIndex) {
		
		int count = maxIndex-minIndex;
		if(count <= 0)
			return;
		else if(count < 10)
			insertionSortDESC(minIndex, maxIndex); // if the n-element table switch to InsertionSort
		else {

			int i = minIndex;
			int p = (int) (minIndex + generator.nextDouble()*(maxIndex-minIndex)); // random selection pivot
			
			T pivot = tab[p];
			swap(p,minIndex); // swap pivot with first element
			
			
			for(int j = i+1; j <= maxIndex; j++) {
				
				numberComparison++; //making a comparison
				if(tab[j].compareTo(pivot) >= 0) {
					
					i++;
					swap(i,j);
				}
			}
			swap(i,minIndex);
			
			hybrydSortDESC(minIndex, i-1);
			hybrydSortDESC(i+1, maxIndex);
		}
	}
	
	private void insertionSortASC(int minIndex, int maxIndex) {
    	      
        T temp;
        int x;
        for (int i = minIndex; i <= maxIndex; i++) {
        	temp = tab[i];
        	x = i;
        	numberComparison++; //making a comparison
        	while(x > minIndex && temp.compareTo(tab[x-1]) < 0)
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
	
	private void insertionSortDESC(int minIndex, int maxIndex) {
	      
        T temp;
        int x;
        for (int i = minIndex; i <= maxIndex; i++) {
        	temp = tab[i];
        	x = i;
        	numberComparison++; //making a comparison
        	while(x > minIndex && temp.compareTo(tab[x-1]) > 0)
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
	
    private void swap(int i, int j) {
        T temp = tab[i];
        tab[i] = tab[j];
        numberShift++; //making a shift
        tab[j] = temp;
        numberShift++; //making a shift
    }
}
