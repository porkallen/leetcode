package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution0801 {
	Solution0801(){
		System.out.printf("Ans: %d \n",uniquePath(30,38));
	}
	public long uniquePathDFS(int height, int width, int curH, int curW, long[][] map, ArrayList<MyPair<Integer,Integer>> l) {
		long ret = 0;
		if(curW > width || curH > height || curW < 0 || curH < 0 )
			return 0;
		if(curW == width && curH != 0) {
			return 0;
		}
		if(curW == width) {
			//System.out.printf("ret:%d (%d,%d)\n",ret,curH,curW);
			return 1;
		}
		if(map[curH][curW] != -1) {
			//System.out.printf("(%d,%d) \n",curH,curW);
			return map[curH][curW];
		}

		for(MyPair<Integer,Integer> p : l) {
			ret = (ret + uniquePathDFS(height,width,curH + p.key, curW + p.value, map, l)) %1000000007;
		}
		//System.out.printf("(%d,%d) map:%d \n",curH,curW,ret);
		map[curH][curW] = ret;
		return ret;
	}
    public int uniquePath(int height, int width) {
		ArrayList<MyPair<Integer,Integer>> l = new ArrayList<MyPair<Integer,Integer>>();
    	if(height == 0 || width == 0)
    		return 0;
    	//HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    	long[][] map = new long[height][width];
    	for(long[] m : map)
    		Arrays.fill(m, -1);
		l.add(new MyPair<Integer,Integer>(0,1));
		l.add(new MyPair<Integer,Integer>(-1,1));
		l.add(new MyPair<Integer,Integer>(1,1));
    	return (int)uniquePathDFS(height - 1, width - 1, 0 , 0 , map, l);
    }
}
