package leetcode;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;

public class Main {
    @SuppressWarnings("rawtypes")
	public static void main(String[] args) {
    		List<String> list1 = new LinkedList();
    		list1.add("Pork");
    		list1.add("Allen");
    		list1.add("Zoe");
    		list1.add("Vincent");
    		list1.add("Pork");
    	
    	    list1.sort( new Comparator<String>(){
    	    	@Override
    	    		public int compare(String o1,String o2){
    	    			return Collator.getInstance().compare(o1,o2);
    	    		}
    	    	});
       for (Iterator<String> it = list1.iterator(); it.hasNext();) {
    	   		System.out.printf("Sorted Items: %s \n",it.next());
       } 

    }
}