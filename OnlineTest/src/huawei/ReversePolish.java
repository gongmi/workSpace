package huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ReversePolish {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		try {
			input = br.readLine();
			Integer left, right, val = 0;
			Stack<Integer> stack = new Stack<>();
			for (int i=0;i<input.length();i++) {
				char s=input.charAt(i);
//			for (char s:input){
				if (s == '+' || s == '-' || s == '*') {
					right = stack.pop();
					left = stack.pop();
					switch (s) {
					case '+':
						val = left + right;
						break;
					case '-':
						val = left - right;
						break;
					case '*':
						val = left * right;
						break;
					}
					stack.push(val);
				} else if ('0' <= s && s <= '9')
					stack.push(s - 48);
				else
					stack.push(s - 55);
			}
			System.out.println(stack.pop());
		} catch (IOException e) {
			e.printStackTrace();
		}

}
}
