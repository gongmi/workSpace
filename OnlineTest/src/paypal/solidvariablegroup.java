package paypal;
/*Given two zero-indexed arrays A and B 
consisting of M and N (M > 0, N > 0) strings, 
which represent M and N variables in two different models P and Q. 
Each variable is a string, 
which contains letters "a-z" in lower case only. 
Variables in arrays A and B are initially sorted by their contribution to the model, 
from large to small, which means, 
A[0] has the largest contribution to model P, 
while A[M-1] has the smallest contribution to model P. 
If there are X variables existing in same order in both A and B, 
they form a "solid variable group" of which the length is X. 
The goal is to calculate the length of the longest "solid variable group",
 and find out the variables in the longest "solid variable group".
 If there are more than 1 longest "solid variable groups", 
find out the one which has the largest contribution to model P. 
For example: 
A is ['paypal', 'business', 'money', 'innovation', 'strong'] 
B is ['innovation', 'paypal', 'strong', 'inclusion'] 
The length of the longest "solid variable group" is 2, 
and two "solid variable groups" of length 2 
are ('paypal', 'strong') 
and ('innovation', 'strong').
Since 'paypal' has larger contribution to model P than 'innovation', 
the output should be 2 and ('paypal', 'strong').

paypal business money dead innovation strong happy
innovation paypal strong happy inclusion dead*/
import java.util.*;

public class solidvariablegroup {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String A = in.nextLine();
		String B = in.nextLine();
		String[] a = A.split(" ");
		String[] b = B.split(" ");
		int[] index = new int[a.length];
		Arrays.fill(index, -1);
		for (int i = 0; i < a.length; i++) {
			String s = a[i];
			for (int j = 0; j < b.length; j++)
				if (b[j].equals(s)) {
					index[i] = j;
					break;
				}
		}
		int count = 0;
		for (int i : index)
			if (i == -1)
				count++;
		int[] index2 = new int[a.length - count];
		int j = 0;
		for (int i : index)
			if (i != -1)
				index2[j++] = i;
		int[] resArray = new int[index2.length];
		int res = lengthOfLIS0(index2,resArray);
		System.out.println(res);
		System.out.println(Arrays.toString(resArray));
		String Sres = "";
		Stack<Integer> stack=new Stack<>();
		int i =1;
		stack.push(index2[0]);
			for(int idx=1;idx<resArray.length;idx++)
				if (resArray[idx]==stack.size()+1)
				{	if(index2[idx]>stack.peek())
					stack.push(index2[idx]);
					else
						stack.pop();
				}
			
			Sres = Sres + b[resArray[i]] + " ";
		System.out.println(Sres.trim());
		}
	public static int lengthOfLIS0(int[] nums, int[] resArray) {
		int len = nums.length;
		if (len == 0)
			return 0;
		int[] max_lens = new int[len];
		max_lens[0] = 1;
		int res = 1;
		for (int i = 1; i < len; i++) {
			for (int j = 0; j < i; j++)
				if (nums[i] > nums[j])
					max_lens[i] = Math.max(max_lens[i], max_lens[j]);
			max_lens[i]++;
			res = Math.max(res, max_lens[i]);
		}
		
		for(int i=0;i<len;i++)
			resArray[i]=max_lens[i];
		return res;
	}
	public static int lengthOfLIS(int[] nums, int[] res) {
		if (nums.length == 0) {
			return 0;
		}
		int len = 0;
		int[] tails = new int[nums.length];
		tails[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] <= tails[0]) // 相等时就替换吧 替换花不了多少时间 但是可以节约下一个判断的写法
				tails[0] = nums[i];
			else if (nums[i] < tails[len])
				tails[binarySearch(tails, 0, len, nums[i])] = nums[i];
			else if (nums[i] > tails[len])
				tails[++len] = nums[i];
		}
		for (int i = 0; i < nums.length; i++)
			res[i] = tails[i];
		return len + 1;
	}

	private static int binarySearch(int[] tails, int min, int max, int target) {
		while (min <= max) {
			int mid = min + (max - min) / 2;
			if (tails[mid] == target) {
				return mid;
			}
			if (tails[mid] < target) {
				min = mid + 1;
			}
			if (tails[mid] > target) {
				max = mid - 1;
			}
		}
		return min;
	}
}