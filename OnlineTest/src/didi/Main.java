package didi;

import java.util.*;
//有一个合法的字符串，
//现在每次将这个序列第一个左括号删去，
//再将任意一个右括号删去，
//每次删去后的序列必须合法，求有多少种方法 
//样例1：
//Input:
//()()()()
//Output：
//1
//
//样例2：
//Input
//(((())))()()
//Output:
//24
//首先我们很容易知道如果模拟的话很复杂，
//在进一步思考会发现从左到右走会有后效性，
//当前所选的右括号会决定序列是否合法且影响到下一次的选择，也就是说 我在最左边选择一个左括号 那么这个左括号右边的右括号 要思考选哪个 才满足是合法的
//既然有后效性，那就倒过来，从右往左！ 就是从最右边选择一个左括号 序列从右边数 第一个左括号删去  
//可以证明到右边先选不会影响到左边。（证明略）
//( ( ) ( ) ( ( ) ) )
//1 2   2   2 3
//
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();

		long res = 1;
		int closedParenthesisCount = 0;

		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == ')')
				closedParenthesisCount++;
			else if (s.charAt(i) == '(') {
				res *= closedParenthesisCount;
				closedParenthesisCount--;
			}
		}
		System.out.println(res);
	}
}
