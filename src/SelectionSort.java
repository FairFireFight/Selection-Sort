import java.util.ArrayList;

/**
 * This Class contains several Static Methods that sort an ArrayList<> using
 * the Selection Sort algorithm.
 * */
public class SelectionSort {
	/**
	 * Sorts the entire list, in Ascending order.
	 * */
	public static ArrayList<Student> Ascending(ArrayList<Student> list) {
		// clone the list to be sorted to maintain immutability.
		ArrayList<Student> sortedList = new ArrayList<Student>(list);
		
		int n = sortedList.size();

        // iterate through the entire list
        for (int i = 0; i < n - 1; i++) {
        	
            // find the min element in the unsorted part of the list
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                if (sortedList.get(j).grade < sortedList.get(minIndex).grade) {
                    minIndex = j;
                }
            }

            // swap the found minimum element with the first element
            Student temp = sortedList.get(minIndex);
            sortedList.set(minIndex, sortedList.get(i));
            sortedList.set(i, temp);
        }
		
		return sortedList;
	}
	
	/**
	 *  overloaded ascending method, only sorts up to `limit` 
	 *  @return ArrayList<Student> the lowest `limit` values
	 **/
	public static ArrayList<Student> Ascending(ArrayList<Student> list, int limit) {
		ArrayList<Student> sortedList = new ArrayList<Student>(list);
		
		int n = sortedList.size() - 1;
		
		// ensure limit doesn't exceed list size
		limit = Math.min(limit, n + 1);
		
		// only loop 'limit' times
        for (int i = 0; i < limit; i++) {
        	int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (sortedList.get(j).grade < sortedList.get(minIndex).grade) {
                    minIndex = j;
                }
            }

            Student temp = sortedList.get(minIndex);
            sortedList.set(minIndex, sortedList.get(i));
            sortedList.set(i, temp);
        }
		
        // only return the desired limit of elements
        return new ArrayList<Student>(sortedList.subList(0, limit));
	}
	
	/**
	 * Sorts the entire list, in Descending order.
	 * */
	public static ArrayList<Student> Descending(ArrayList<Student> list) {
		ArrayList<Student> sortedList = new ArrayList<Student>(list);
		
		int n = sortedList.size();
		
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            
            for (int j = i + 1; j < n; j++) {
            	// to change the order of sorting we only need to change
            	// the comparison here      V
                if (sortedList.get(j).grade > sortedList.get(maxIndex).grade) {
                    maxIndex = j;
                }
            }

            Student temp = sortedList.get(maxIndex);
            sortedList.set(maxIndex, sortedList.get(i));
            sortedList.set(i, temp);
        }
		
		return sortedList;
	}
	
	/**
	 *  overloaded descending method, only sorts up to `limit` 
	 *  @return ArrayList<Student> the highest `limit` values
	 **/
	public static ArrayList<Student> Descending(ArrayList<Student> list, int limit) {
		ArrayList<Student> sortedList = new ArrayList<Student>(list);
		
		int n = sortedList.size() - 1;
		limit = Math.min(limit, n + 1);

		// only loop 'limit' times
        for (int i = 0; i < limit; i++) {
            int maxIndex = i;
            
            for (int j = i + 1; j < n; j++) {
            	// changed comparison operator
            	//                     here V
                if (sortedList.get(j).grade > sortedList.get(maxIndex).grade) {
                    maxIndex = j;
                }
            }

            Student temp = sortedList.get(maxIndex);
            sortedList.set(maxIndex, sortedList.get(i));
            sortedList.set(i, temp);
        }
		
        // only return the desired limit of elements
		return new ArrayList<Student>(sortedList.subList(0, limit));
	}
}
