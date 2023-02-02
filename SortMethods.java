/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Nathan Bao
 *	@since	1/9/2023
 */
import java.util.List;
import java.util.ArrayList;
public class SortMethods {
	
	private String[] temp;
	
	/**
	 *	Swaps two String objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(String[] arr, int x, int y) {
		String ct1 = arr[x];
		arr[x] = arr[y];
		arr[y] = ct1;
	}
	
	/**
	 *	Selection Sort algorithm - sorts City objects by population in ascending order 
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(String[] arr) {
		String cts;
		int size = arr.length;
		for(int n = size;n>1;n--){
			int max = 0;
			for(int a = 1;a<n;a++){
				if(arr[a].compareTo(arr[max])>0)
					max = a;
			}
			swap(arr, max, n-1);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - sorts City objects by name in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(String[] arr) {
		String temps;
		int size = arr.length;
		for(int n = 1;n<size;n++){
			temps = arr[n];
			int placeholder = n;
			while(placeholder>0&&(temps.compareTo(arr[placeholder-1])<0)){
				arr[placeholder] = arr[placeholder-1];
				placeholder--;
			}
			arr[placeholder] = temps;
		}
	}
	
	/**
	 *	Merge Sort algorithm - sorts the array of City objects by population descending
	 *	@param array		array of Integer objects to sort
	 */
	public void mergeSort(String[] array) {
		int size = array.length;
		//System.out.println(size);
		//System.out.println(temp.size());
		temp = new String[100000];
		//System.out.println(temp.size());
		String filler = "z";
		for(int i = 0;i<100000;i++){
			temp[i] = filler;
		}
		//System.out.println(temp.size());
		recursiveSort(array, 0, size-1);
		for(int i = temp.length-1;i>=0;i--){
			temp[i] = "";
		}
	}
	
	/**
	 * Recursively splits the array into groups of 1 or 2 elements, sorts them, then merges and sorts them
	 * @param array		array of City objects to sort
	 * @param start		start of the array
	 * @param end		end of the array
	 */
	public void recursiveSort(String[] array, int start, int end){
		if(end-start<2){
			if(end>start&&array[end].compareTo(array[start])>0){
				swap(array, end, start);
			}
		}
		else{
			int middle = (start+end)/2;
			recursiveSort(array, start, middle);
			recursiveSort(array, middle+1, end);
			merge(array, start, middle, end);
		}
	}
	
	/**
	 * Merges and sorts the array
	 * @param arr		array of City objects to sort
	 * @param st		start of the array
	 * @param mid		middle of the array
	 * @param ed		end of the array
	 */
	
	public void merge(String arr[], int st, int mid, int ed){
		//System.out.println("I");
		int start = st;
		int half = mid+1;
		int start1 = st;
		while(start<=mid&&half<=ed){
			if(arr[start].compareTo(arr[half])>0){
				temp[start1] = arr[start];
				start++;
			}
			else{
				temp[start1] = arr[half];
				half++;
			}
			start1++;
		}
		while(start<=mid){
			temp[start1] = arr[start];
			start++;
			start1++;
		}
		while(half<=ed){
			temp[start1] = arr[half];
			start1++;
			half++;
		}
		for(start1 = st;start1<=ed;start1++){
			arr[start1] = temp[start1];
		}
	}
	
	/**
	 * Same as mergeSort, but sorts by name instead of population
	 * @param array		The array of City objects to be sorted
	 *
	public void mergeSortName(List<City> array) {
		int size = array.size();
		temp = new ArrayList<City>(100000);
		City filler = new City("M", "M", "M", -1);
		for(int i = 0;i<100000;i++){
			temp.add(filler);
		}
		recursiveSortC(array, 0, size-1);
	}
	/**
	 * Recursively splits the array into groups of 1 or 2 elements, sorts them, then merges and sorts them
	 * @param array		The array of City objects to be sorted
	 * @param start		start of the array
	 * @param end		end of the array
	 *
	public void recursiveSortC(List<City> array, int start, int end){
		if(end-start<2){
			if(end>start&&array.get(end).compareOther(array.get(start))>0){
				swap(array, end, start);
			}
		}
		else{
			int middle = (start+end)/2;
			recursiveSortC(array, start, middle);
			recursiveSortC(array, middle+1, end);
			mergeC(array, start, middle, end);
		}
	}
	/**
	 * Merges and sorts the array
	 * @param arr		The array of City objects to be sorted
	 * @param st		start of the array
	 * @param mid		middle of the array
	 * @param ed		end of the array
	 *
	public void mergeC(List<City> arr, int st, int mid, int ed){
		//System.out.println("I");
		int start = st;
		int half = mid+1;
		int start1 = st;
		while(start<=mid&&half<=ed){
			if(arr.get(start).compareOther(arr.get(half))>0){
				temp.set(start1, arr.get(start));
				start++;
			}
			else{
				temp.set(start1, arr.get(half));
				half++;
			}
			start1++;
		}
		while(start<=mid){
			temp.set(start1, arr.get(start));
			start++;
			start1++;
		}
		while(half<=ed){
			temp.set(start1, arr.get(half));
			start1++;
			half++;
		}
		for(start1 = st;start1<=ed;start1++){
			arr.set(start1, temp.get(start1));
		}
	}
	*/	
}
