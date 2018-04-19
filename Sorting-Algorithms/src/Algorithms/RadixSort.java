package Algorithms;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

/**
 * 
 * @author Konrad Czart
 * Radix sort for String or Integer
 * 
 */
public class RadixSort<T extends Comparable<? super T>> implements SortClass<T> {

    private int length;
    private long numberShift;
    private long numberComparison;
    private long time;
    private int division;
    private HybrydQuickSort<T> sort2;
    private String[] tmp;

    
    public RadixSort() {
    	numberShift = 0;
    	numberComparison = 0;
    	division = 100;
    	sort2 = new HybrydQuickSort<T>();

    }
    
    
	@Override
	public void sortASC(T[] tab) {
		long start;
		long stop;
    	numberShift = 0;
    	numberComparison = 0;
		time = 0;
		
        if (tab == null || tab.length <= 1) {
            return;
        }
		start = System.nanoTime();
		
		if(tab[0] instanceof Integer)
			radixSortIntASC((Integer[]) tab);
		else if(tab[0] instanceof String) {
			tmp = new String[tab.length];
			radixSortStringASC((String[]) tab);
		} else {
			sort2.sortASC(tab);
			numberShift = sort2.getNumberShift();
			numberComparison = sort2.getNumberComparison();
		}
		
		stop = System.nanoTime();
		time = stop - start;
	}

	@Override
	public void sortDESC(T[] tab) {
		long start;
		long stop;
    	numberShift = 0;
    	numberComparison = 0;
		time = 0;
		
        if (tab == null || tab.length <= 1) {
            return;
        }
    	
		start = System.nanoTime();
		
		if(tab[0] instanceof Integer)
			radixSortIntDESC((Integer[]) tab);
		else if(tab[0] instanceof String)
			radixSortStringDESC((String[]) tab);
		else {
			sort2.sortDESC(tab);
			numberShift = sort2.getNumberShift();
			numberComparison = sort2.getNumberComparison();
		}
		
		stop = System.nanoTime();
		time = stop - start;
		
	}

	@Override
	public long getNumberShift() {
		return numberShift;
	}

	@Override
	public long getNumberComparison() {
		return numberComparison;
	}

	@Override
	public long getTime() {
		return time;
	}
	
	private void radixSortIntASC(Integer[] tab) {
		
        int max = tab[0];
        int exp = 1;
        int n = tab.length;

        Integer[] out = new Integer[n];

        for (int i = 1; i < n; i++)
            if (tab[i] > max)
                max = tab[i];

        int log = (int) ((Math.log(max)/Math.log(2))/2);
        int divisionTemp = (int) Math.pow(2, log);
        division = Math.min(512, divisionTemp);
        
        while (max / exp > 0) {

        	int[] bucket = new int[division];

            for (int i = 0; i < n; i++)
                bucket[(tab[i] / exp) % division]++;

            for (int i = 1; i < division; i++)
                bucket[i] += bucket[i - 1];

            for (int i = n - 1; i >= 0; i--) {
                out[--bucket[(tab[i] / exp) % division]] = tab[i];
                numberShift++; //making a shift
            }

            for (int i = 0; i < n; i++) {           
                tab[i] = out[i];
                numberShift++; //making a shift
            }
            
            exp *= division;        

        }
	}
	
	private void radixSortIntDESC(Integer[] tab) {
		
        int max = tab[0];
        int exp = 1;
        int n = tab.length;

        Integer[] out = new Integer[n];

        for (int i = 1; i < n; i++)
            if (tab[i] > max)
                max = tab[i];
        
        int log = (int) ((Math.log(max)/Math.log(2))/2);
        int divisionTemp = (int) Math.pow(2, log);
        division = Math.min(512, divisionTemp);

        while (max / exp > 0) {

            int[] bucket = new int[division];

 

            for (int i = 0; i < n; i++)
                bucket[(tab[i] / exp) % division]++;

            for (int i = division-2; i >= 0; i--)
                bucket[i] += bucket[i + 1];

            for (int i = n - 1; i >= 0; i--)
                out[--bucket[(tab[i] / exp) % division]] = tab[i];

            for (int i = 0; i < n; i++)
                tab[i] = out[i];

            exp *= division;        

        }
	}
	
	private void radixSortStringASC(String[] tab) {
        int n = tab.length;
        int R = 256;
        String[] out = new String[n];
        
        int maxLength = tab[0].length();
        
        for(int i = 0; i < n; i++)
        	if(maxLength < tab[i].length())
        		maxLength=tab[i].length();

        for (int d = maxLength-1; d >= 0; d--) {

            int[] bucket = new int[R];
            
            for (int i = 0; i < n; i++) {
            	if(d<tab[i].length())
            		bucket[tab[i].charAt(d)]++;
            	else
            		bucket[0]++;
            }

            for (int i = 1; i < R; i++)
            	bucket[i] += bucket[i-1];

            for (int i = n-1; i >= 0; i--)
            	if(d<tab[i].length())
            		out[--bucket[tab[i].charAt(d)]] = tab[i];
            	else
            		out[--bucket[0]] = tab[i];

            for (int i = 0; i < n; i++)
                tab[i] = out[i];
        }
	}
	
	private void radixSortStringDESC(String[] tab) {
        int n = tab.length;
        int R = 256;  
        String[] out = new String[n];
        
        int maxLength = tab[0].length();
        
        for(int i = 0; i < n; i++)
        	if(maxLength < tab[i].length())
        		maxLength=tab[i].length();

        for (int d = maxLength-1; d >= 0; d--) {


            int[] bucket = new int[R];
            
            for (int i = 0; i < n; i++) {
            	if(d<tab[i].length())
            		bucket[tab[i].charAt(d)]++;
            	else
            		bucket[0]++;
            }

            for (int i = R-2; i >= 0; i--)
            	bucket[i] += bucket[i+1];

            for (int i = n-1; i >= 0; i--)
            	if(d<tab[i].length())
            		out[--bucket[tab[i].charAt(d)]] = tab[i];
            	else
            		out[--bucket[0]] = tab[i];

            for (int i = 0; i < n; i++)
                tab[i] = out[i];
        }
	}

}
