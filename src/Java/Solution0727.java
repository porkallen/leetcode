package leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Solution0727 {
	Solution0727(){
		//String[] tmp = {"F2 Enter 18","F2 Exit 19","F1 Enter 10","F1 Exit 20"};
		//String[] tmp = {"F1 Enter 10","F1 Exit 18","F1 Enter 19","F1 Exit 20"};
		//String[] s = getRuntime(tmp);
		//int[][] test = {{-1,-3,2},{2,3,4},{-3,7,2}};
		//int[][] test = {{-1,-1},{-1,-1}};
		//int[][] test = {{1,1,1},{1,2,1},{1,1,1}};
		int[][] test = {{-965},{-70},{-548},{422},{160}};
		System.out.printf("%d \n",solve(test));
	}
    public int solve(int[][] arr) {//1489. The Largest Sum Of The Matrix Boundary Elements
    	int ret = Integer.MIN_VALUE;
    	int[][] dpArrs = new int[arr.length + 1][arr[0].length + 1];
    	int rowLen = dpArrs.length, colLen = dpArrs[0].length; 

    	for(int[] arrs : dpArrs)
    		Arrays.fill(arrs, 0);    	
    	//Draw the DP map
    	for(int i = 1; i < rowLen; i++) {
    		for(int j = 1; j < colLen; j++) {
    			dpArrs[i][j] = dpArrs[i][j - 1] + 
    					dpArrs[i - 1][j] - dpArrs[i - 1][j - 1] + arr[i - 1][j - 1];
    		}
    	}
    	for(int i = 0; i < rowLen; i++) {
    		for(int j = 0; j < colLen; j++) {
    			System.out.printf("%d ",dpArrs[i][j]);
    		}
			System.out.printf("\n");
    	}
    	for(int i = 0; i < arr.length; i++) {
    		for(int j = 0; j < arr[0].length; j++) {
    			ret = Math.max(ret, arr[i][j]);
    		}
    	}
		System.out.printf("(%d)\n",ret);
    	for(int i = 1; i < rowLen; i++) {
    		for(int j = 1; j < colLen; j++) {
				int rowRange = i - 1;//how many row can reduce
				int colRange = j - 1;//how many col can reduce
				for(int k = 0; k <= rowRange; k++) {
					for(int l = 0; l <= colRange; l++) {
	    				int curRowLen = i - k;//3
	    				int curColLen = j - k;//3
	    				int coreArea = 0;
	    				int minusArea = 0;
	    				if(curRowLen >= 3 && curColLen >= 3) {
	    					int coreRowIdx = i - 1;//2
	    					int coreColIdx = j - 1;//2
	    					int coreRowLen = curRowLen - 2;//1: minus broader
	    					int coreColLen = curColLen - 2;//1: minus broader
	    					coreArea = dpArrs[coreRowIdx][coreColIdx] - dpArrs[coreRowIdx][coreColIdx - coreColLen] - 
								dpArrs[coreRowIdx - coreRowLen][coreColIdx] + dpArrs[coreRowIdx - coreRowLen][coreColIdx - coreColLen];
	    				}
						
						/*System.out.printf("(%d,%d)(%d,%d) - core:%d %d %d %d\n",i,j,minusRow,minusCol,dpArrs[i - 1][j - 1],
								dpArrs[i - 1- minusRow][j - 1],dpArrs[i - 1][j - 1- minusCol],
								dpArrs[i - 1- minusRow][j - 1- minusCol]);*/
						
						minusArea = dpArrs[k][j] + dpArrs[i][l] - dpArrs[k][l];
						System.out.printf("(%d) core: %d minus:%d (%d,%d)\n",dpArrs[i][j],
								coreArea,minusArea,k,l);
	    				ret = Math.max(ret, dpArrs[i][j] - minusArea - coreArea);
					}
				}
    		}
    	}
    	return ret;
    }
    public String judgingTriangle(int[] arr) {
    	String ret = "no";
    	HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
    	if(arr.length <= 2)
    		return ret;
    	Arrays.sort(arr);
    	for(int a : arr) {
    		hm.put(a, 1);
    	}
    	for(int i = 0; i < arr.length - 2; i++) {
    		for(int j = i + 1; j < arr.length - 1; j++) {
    			for(int k = j+1; k < arr.length; k++) {
    				if(arr[i] + arr[j] > arr[k])
    					return "yes";
    				else
    					continue;
    			}
    		}
    	}
    	return ret;
    }
	public void getSetDFS(int[][] setList, int idx, List<Integer> tmpList,List<List<Integer>> retList) {	
		if(idx == setList.length) {
			retList.add(tmpList);
			return;
		}
		for(int i = 0; i < setList[idx].length; i++) {
			List<Integer> tmptmpList = new LinkedList<Integer>(tmpList);
			tmptmpList.add(setList[idx][i]);
			getSetDFS(setList,idx+1,tmptmpList,retList);
			//tmpList.remove(curList[i]);
		}
		return;
	}
    public List<List<Integer>> getSet(int[][] setList) {
    	List<List<Integer>> retList = new LinkedList<List<Integer>>();
    	List<Integer> tmpList = new LinkedList<Integer>();
    	getSetDFS(setList,0,tmpList,retList);
    	return retList;
    }
    public String[] getRuntime(String[] a) {
    	ArrayList<String> ret = new ArrayList<String>();
    	HashMap<String,ArrayList<MyPair<Integer,Integer>>> hm = new HashMap<String,ArrayList<MyPair<Integer,Integer>>>();
    	
    	String [] sRet = ret.toArray(new String[ret.size()]);
    	if(a.length == 0)
    		return sRet;
    	for(String s:a) {
    		String[] tmp = s.split(" ");
    		ArrayList<MyPair<Integer,Integer>> tmpList;
    		int end = 0;
    		MyPair<Integer,Integer> p;
    		if(tmp[1] != null) {
    			if(hm.get(tmp[0]) != null) {
    				tmpList = hm.get(tmp[0]);
    				end = tmpList.size() - 1;
    				p = tmpList.get(end);
    				if(p.key != -1 && p.value != -1)
    					p = new MyPair(-1,-1);
    			}
    			else {
    				tmpList = new ArrayList<MyPair<Integer,Integer>>();
    				p = new MyPair(-1,-1);
    			}
    			if(tmp[1].equals("Enter")) {
    				p.key = Integer.parseInt(tmp[2]);
    			}
    			else {
    				p.value = Integer.parseInt(tmp[2]);
    			}
        	    //System.out.printf("%s sz:%d end:%d %d %d\n", tmp[0],tmpList.size(), end,p.key,p.value);
    			if(hm.get(tmp[0]) != null) {
    				if(p.key == -1 || p.value == -1)
        				tmpList.add(p);
    				else
    					tmpList.set(end, p);
    			}
    			else
    				tmpList.add(p);
    			hm.put(tmp[0], tmpList);
    		}
    	}
    	Map<String, ArrayList<MyPair<Integer,Integer>>> treeMap = new TreeMap<String, ArrayList<MyPair<Integer,Integer>>>(hm);
    	Arrays.sort(a, Collections.reverseOrder());
    	for (Map.Entry<String, ArrayList<MyPair<Integer,Integer>>> entry : hm.entrySet()) {
    	    String key = entry.getKey();
    	    int totalTime = 0;
    	    ArrayList<MyPair<Integer,Integer>> tmpList = entry.getValue();
    	    for(MyPair<Integer,Integer> p: tmpList) {
    	    	totalTime += (p.value - p.key);
    	    }
    	    key += "|"+Integer.toString(totalTime);
    	    System.out.printf("%s \n", key);
    	    ret.add(key);
    	}
    	sRet = ret.toArray(new String[ret.size()]);
    	return sRet;
    }
}
