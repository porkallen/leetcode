package leetcode;

public class Main {
    public static void main(String[] args) {
    	ContainerPractice cp = new ContainerPractice();
    	cp.hashmapIter();
    	cp.hashmapForeach();
    	cp.listOP();
    	cp.hashSetOP();
        System.out.println("Func: "+Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}