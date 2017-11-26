/**
 * ContainerPractice.java
 * Nov 11, 2017,2017 Created by allen
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
	ContainerPractice(){
		myMap = new HashMap<Integer, String>();
		for(int i = 0 ; i < COUNT; i++) {
			myMap.put(i,"I am Value: "+Integer.toString(i));
		}
		studentDatabase = new String[]{"Ryan","Alfred","Beth"};
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
