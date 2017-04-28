package huawei;

import java.util.Scanner;
import java.util.TreeSet;

public class ip {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String[] arrays = new String[5];

		arrays[0] = in.nextLine();
		arrays[1] = in.nextLine();
		arrays[2] = in.nextLine();
		arrays[3] = in.nextLine();
		arrays[4] = in.nextLine();

		TreeSet<String> A = new TreeSet<>();
		TreeSet<String> B = new TreeSet<>();
		TreeSet<String> C = new TreeSet<>();
		TreeSet<String> D = new TreeSet<>();
		TreeSet<String> E = new TreeSet<>();
		for (int j = 0; j < 5; j++) {
			String[] a = arrays[j].split("\\.");
			int net = Integer.parseInt(a[0]);
			if (net <= 126 && net > 0)
				A.add(arrays[j]);
			else if (net <= 191 && net >= 128)
				B.add(arrays[j]);
			else if (net <= 223 && net >= 192)
				C.add(arrays[j]);
			else if (net <= 239 && net >= 224)
				D.add(arrays[j]);
			else if (net <= 255 && net >= 240)
				E.add(arrays[j]);
		}
		StringBuilder sb = new StringBuilder();
		if (!A.isEmpty()) {
			sb.append("A:[");
			for (String a : A)
				sb.append(a + ',');
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			System.out.println(sb);
		}
		sb.delete(0, sb.length());
		if (!B.isEmpty()) {
			sb.append("B:[");
			for (String a : B)
				sb.append(a + ',');
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			System.out.println(sb);
		}
		sb.delete(0, sb.length());
		if (!C.isEmpty()) {
			sb.append("C:[");
			for (String a : C)
				sb.append(a + ',');
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			System.out.println(sb);
		}
		sb.delete(0, sb.length());
		if (!D.isEmpty()) {
			sb.append("D:[");
			for (String a : D)
				sb.append(a + ',');
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			System.out.println(sb);
		}
		sb.delete(0, sb.length());
		if (!E.isEmpty()) {
			sb.append("E:[");
			for (String a : E)
				sb.append(a + ',');
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			System.out.println(sb);
		}
		
	}
}
