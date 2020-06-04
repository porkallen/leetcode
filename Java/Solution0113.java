package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution0113 {
	Solution0113(){
		int[][] mines = {{0,0},{0,1},{0,2},{0,7},{1,2},{1,3},{1,9},{2,3},{2,5},{2,7},{2,8},{3,2},{3,5},{3,7},{4,2},{4,3},{4,5},{4,7},{5,1},{5,4},{5,8},{5,9},{7,2},{7,5},{7,7},{7,8},{8,5},{8,8},{9,0},{9,1},{9,2},{9,8}};
		int i = orderOfLargestPlusSign(10,mines);
		System.out.printf("ret:%d\n",i);
	}
	public int updfs(int x,int y,final int grid[][],final int N,int order) {
		System.out.printf("(updfs) %d,%d:%d\n",x,y,order);
		boolean result = true;
		if(order < 0)
			return 0;
		if(x < 0 || y < 0 || x > N-1 || y > N-1)
			return order;
		if(grid[x][y] == 0)
			return 0;
		return updfs(x+1,y,grid,N,order-1);
	}
	public int downdfs(int x,int y,final int grid[][],final int N,int order) {
		System.out.printf("(downdfs) %d,%d:%d\n",x,y,order);
		boolean result = true;
		if(order < 0)
			return 0;
		if(x < 0 || y < 0 || x > N-1 || y > N-1)
			return order;
		if(grid[x][y] == 0)
			return 0;
		return downdfs(x-1,y,grid,N,order-1);
	}
    public int orderOfLargestPlusSign(int N, int[][] mines) {
    		int maxOrder = 0;
    		if(N == 1 && mines.length != 0)
    			return 0;
    		
        int grid[][] = new int[N][N];
        for(int i = 0; i< N;i++) {
            Arrays.fill(grid[i], 1);
        }
        for(int i = 0; i< mines.length;i++) {
            grid[mines[i][0]][mines[i][1]] = 0;
        }
        for(int i = 0; i<N;i++) {
			System.out.printf("[%d] ",i);
    			for(int j = 0; j<N; j++) {
    				System.out.printf("%d ",grid[i][j]);
    				if(grid[i][j] == 1) {
    					maxOrder = 1;
    					//break;
    				}
    			}
    			System.out.printf("\n");
        }        
        if(maxOrder == 0)
        		return maxOrder;
        if(maxOrder == 1 && N == 2)
    			return maxOrder;
        

        for(int i = 0; i< N;i++) {
        		int bitsNum = 0;
        		int shift = 0;
        		for(int j = 0; j < N; j++) {
        			System.out.printf("(%d,%d):%d\n", i,j,grid[i][j]);
        			if(grid[i][j] == 1) {
                		int upOrder = 0;
                		int downOrder = 0;
        				bitsNum++;
        				if(bitsNum >=3) {
        					if(bitsNum%2 == 0) {
        						
        					}
        					else {
        						
        					}
        				}
        				upOrder = updfs(i,j-shift,grid,N,shift);
        				downOrder = downdfs(i,j-shift,grid,N,shift);
					System.out.printf("(%d,%d):%d up%d down%d order:%d\n",i,j-bitsNum/2,bitsNum,upOrder,downOrder,shift+1);
//    					if(isAlign == true) {
//    						if(shift+1 > maxOrder)
//    							maxOrder = shift+1;
//    					}
        			}
        			else
        				bitsNum = 0;
        		}
        }
        return maxOrder;
    }
    public List<Integer> partitionLabels(String S) {
    		List<Integer> retList = new ArrayList<Integer>();
    		int lstDigitLoc[] = new int[S.length()+1];
    		for(int i = 0 ; i< S.length();i++) {
    			lstDigitLoc[i] = S.lastIndexOf(S.charAt(i));
    		}
    		for(int i = 0 ; i< S.length();) {
    			int start = i;
    			int end = lstDigitLoc[i];
    			boolean isExtend = false;
    			for(int j = start; j < end;j++) {
    				if(lstDigitLoc[j] > end) {
    					end = lstDigitLoc[j];
    					isExtend = true;
    				}
    			}
    			System.out.printf("hi:%d %d len:%d\n",start,end,S.substring(start, end).length()+1);
			retList.add(S.substring(start, end).length()+1);
			i = end+1;
    		}
    		return retList;
    }
	public boolean isPrime(int num) {
		if(num == 1) {
			return false; 
		}
		if(num == 2) {
			return true; 
		}
		if(num == 3)
			return true;
		else {
			if(((num%2) == 0) || ((num%3) == 0)) {
				return false;
			}
			return true;
		}
	}
	public int countBit(int num) {
		int retNum = 0;
		while(num != 0) {
			if(num%2 == 1)
				retNum++;
			num = num >>1;
		}
		return retNum;
	}
    public int countPrimeSetBits(int L, int R) {
    		int retNum = 0;
        for(int i = L; i<=R; i++) {
        		int bitsNum = countBit(i);
        		if(isPrime(bitsNum))
        			retNum++;
        		System.out.printf("i%d bits:%d ret:%d\n",i,bitsNum,retNum);

        }
        return retNum;
    }
}
