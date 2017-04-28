package alibaba;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class polish {
	public static void main(String[] args) {

		ArrayList<Integer> inputs = new ArrayList<Integer>();
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		if (line != null && !line.isEmpty()) {
			int res = resolve(line.trim());
			System.out.println(String.valueOf(res));
		}
	}

	public static int resolve(String str) {
		String[] input = str.split(" ");
		Integer val = 0;
		Stack<Integer> stack = new Stack<>();
		for (String s : input) {
			if (s.equals(""))
				continue;
			if (s.equals("+") || s.equals("^") || s.equals("*")) {
				switch (s) {
				case "+":
					if (stack.size() < 2)
						return -1;
					val = stack.pop() + stack.pop();
					break;
				case "^":
					if (stack.size() < 1)
						return -1;
					val = stack.pop() + 1;
					break;
				case "*":
					if (stack.size() < 2)
						return -1;
					val = stack.pop() * stack.pop();
					break;
				}
				stack.push(val);
			} else if (stack.size() == 16)
				return -2;
			else
				stack.push(Integer.parseInt(s));
		}
		if (stack.size() < 1)
			return -1;
		return stack.pop();
	}
}
