/**
 * ContainerPractice.java
 * Nov 11, 2017,2017 Created by allen
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Nov 11, 2017,2017 @author allen
 *
 */
public class ContainerPractice {
	final static int COUNT = 10;
	public static Map<Integer, String> myMap;
	String[] studentDatabase;
	class SortbyKey implements Comparator<MyPair<Integer,String>>
	{
	    // Used for sorting in ascending order of
	    // roll number
	    public int compare(MyPair<Integer,String> a, MyPair<Integer,String> b)
	    {
	        return a.getKey().compareTo(b.getKey());
	    }
	}
	ContainerPractice(){
		myMap = new HashMap<Integer, String>();
		for(int i = 0 ; i < COUNT; i++) {
			myMap.put(i,"I am Value: "+Integer.toString(i));
		}
		studentDatabase = new String[]{"Ryan","Alfred","Beth"};
		sortToTest();
	}
    public int sortToTest() {
        ArrayList<MyPair<Integer,String>> ar = new ArrayList<MyPair<Integer,String>>();
        ar.add(new MyPair<Integer,String>(111, "bbbb"));
        ar.add(new MyPair<Integer,String>(131, "aaaa"));
        ar.add(new MyPair<Integer,String>(121, "cccc"));
        
    	System.out.printf("Before Sorted\n");
        for(MyPair<Integer,String> p : ar)
        	System.out.printf("K:%d V:%s \n",p.getKey(),p.getValue());
        
        Collections.sort(ar, new SortbyKey());
        
    	System.out.printf("After Sorted\n");
        for(MyPair<Integer,String> p : ar)
        	System.out.printf("K:%d V:%s \n",p.getKey(),p.getValue());
    	return 0;
    }
	private static HashMap sortByValues(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
	public int hashmapIter() {
		Iterator<Entry<Integer, String>> iterator = myMap.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry<Integer, String> mapEntry = (Entry<Integer, String>) iterator.next();
			System.out.println("(Iterator)The key is: " + mapEntry.getKey()+ 
					",value is :" + mapEntry.getValue());
		}
		
		return 0;
	}
	public int hashmapForeach() {
		for (Map.Entry<Integer, String> entry : myMap.entrySet()) {
			System.out.println("(entrySet)Key : " + entry.getKey() + " Value : "+
					entry.getValue());
		}
		for (Object key : myMap.keySet()) {
			System.out.println("(keySet)Key : " + key.toString() + " Value : "+ 
					myMap.get(key));
		}
		return 0;
	}
	public int listOP() {		
		List<String> tmpList = Arrays.asList(studentDatabase);
		List<String> cloneList = new LinkedList<String>(tmpList);
		// convert the student database into list
		//List<String> alist = new ArrayList();
		//cloneList.add
		List<String> alist = new ArrayList<String>();
		alist.addAll(cloneList);

        /* Collections.sort method is sorting the
        elements of ArrayList in descending order. */
		Collections.sort(alist,Collections.reverseOrder());
		
		if(alist.contains("Hi")) {
			System.out.println("Hit");
		}
				
		// print value for indicated element
		System.out.println("0 Agent:"+alist.get(0));
		// print how many student on the list
		System.out.println("Count of Student:"+alist.size());
		
		// print the contents of our list
		for(String s:alist){
			System.out.println(s);
		}
		return 0;
	}
	public int hashSetOP() {
        HashSet<String> hs = new HashSet<String>();
        HashSet <String> cloneSet = new HashSet <String>();
        //add elements to HashSet
        hs.add("first");
        hs.add("second");
        hs.add("third");
        hs.add("apple");
        hs.add("rat");
        cloneSet = (HashSet)hs.clone();
        System.out.println("==HS Set ==");
        System.out.println(hs);
        HashSet<String> subSet = new HashSet<String>();
        subSet.add("rat");
        subSet.add("second");
        subSet.add("first");
        subSet.add("No way");
        if(subSet.add("No way!")) {
        	System.out.println("Add element Success");
        }
        else {
        	System.out.println("Add element fail");
        }
        System.out.println("==Sub Set ==");
        System.out.println(subSet);
        /*Remove elements included in other set*/
        for(String s:subSet) {
        	hs.remove(s);
        }
    	System.out.println("==HS after removing==");
    	System.out.println(hs);
    	/*Retain All elements, Keep the same element is one Set*/
    	cloneSet.retainAll(subSet);        
        System.out.println("==Hs after retaining==");
        System.out.println(hs);
		return 0;
	}
}
