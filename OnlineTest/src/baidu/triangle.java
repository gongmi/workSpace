package baidu;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Point {
	int color;
	int x;
	int y;
	int z;

	public Point(String color, int x, int y, int z) {
		super();
		if (color.equals("R"))
			this.color = 0;
		else if (color.equals("G"))
			this.color = 1;
		else
			this.color = 2;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

public class triangle {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int line = in.nextInt();
		in.nextLine();
		String[] a = new String[line];
		List<Point> lst = new ArrayList<Point>();
		for (int i = 0; i < line; i++) {
			a[i] = in.nextLine();
		}
		for (int i = 0; i < line; i++) {
			String[] cs = a[i].split(" ");
			Point p = new Point(cs[0], Integer.parseInt(cs[1]), Integer.parseInt(cs[2]),
					Integer.parseInt(cs[3]));
			lst.add(p);
		}
		double max = 0;
		for (int i = 0; i < line; i++)
			for (int j = i + 1; j < line; j++)
				for (int k = j + 1; k < line; k++) {
					double cur = count_triangle_area(lst.get(i), lst.get(j), lst.get(k));
					if (cur > max)
						max = cur;
				}
		System.out.println(max);

	}

	static double count_triangle_area(Point a, Point b, Point c) {
		double area = -1;

		double[] side = new double[3];// 存储三条边的长度;

		side[0] = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2)
				+ Math.pow(a.z - b.z, 2));
		side[1] = Math.sqrt(Math.pow(a.x - c.x, 2) + Math.pow(a.y - c.y, 2)
				+ Math.pow(a.z - c.z, 2));
		side[2] = Math.sqrt(Math.pow(c.x - b.x, 2) + Math.pow(c.y - b.y, 2)
				+ Math.pow(c.z - b.z, 2));

		if ((a.color != b.color && a.color != c.color) || a.color == b.color && b.color == c.color) {
			if (side[0] + side[1] <= side[2] || side[0] + side[2] <= side[1]
					|| side[1] + side[2] <= side[0])
				return area;

			double p = (side[0] + side[1] + side[2]) / 2;
			area = Math.sqrt(p * (p - side[0]) * (p - side[1]) * (p - side[2]));
			return area;
		} else
			return area;
	}
}
