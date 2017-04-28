package ctrip;
import java.util.Scanner;
import java.util.Stack;

public class IntegerBreak {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            System.out.println(times(in.nextInt()));
        }
    }
    public static int times(int n) {
        if(n < 5) {
            return n;
        }
        Stack<Integer> stack = new Stack<>();
        int flag = 0;
        int base = 2;
        while(n > base) {
            stack.push(base);
            n -= base++;
        }
        if(n != 0) {
            n += stack.pop();
            if(n >= 2*base -5) {
                flag = 1;
                stack.push(n - stack.size());
            } else {
                stack.push(n);
            }
        }
        int res = 1;
        while(!stack.isEmpty()) {
            res *= (flag+stack.pop());
        }
        return res;
    }
}
