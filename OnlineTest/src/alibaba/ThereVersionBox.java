package alibaba;

import java.util.Scanner;

class BoxTemplate {
	int price;
	int length;
	int width;
	int height;
}

class Model {
	int price;
	int length;
	int width;
	int height;
}

public class ThereVersionBox {
	/** 请完成下面这个process函数，实现题目要求的功能 **/
	/** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^ **/
	static final int CUSTOMS_LIMIT_MONEY_PER_BOX = 2000;
	static BoxTemplate boxTemplate = new BoxTemplate();
	static Model[] items = null;

	private static int process() {

		for (Model item : items) {
			if (item.price > boxTemplate.price || item.height > boxTemplate.height
					|| item.length > boxTemplate.length || item.width > boxTemplate.width) {
				return -1;
			}
		}
		return items.length-1;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);

		boxTemplate.price = CUSTOMS_LIMIT_MONEY_PER_BOX;

		while (scanner.hasNext()) {
			boxTemplate.length = scanner.nextInt();
			boxTemplate.width = scanner.nextInt();
			boxTemplate.height = scanner.nextInt();

			int itemNum = scanner.nextInt();
			items = new Model[itemNum];
			for (int i = 0; i < itemNum; i++) {
				Model item = new Model();
				item.price = scanner.nextInt();
				item.length = scanner.nextInt();
				item.width = scanner.nextInt();
				item.height = scanner.nextInt();
				items[i] = item;
			}
			long startTime = System.currentTimeMillis();
			int boxMinNum = Integer.MAX_VALUE;
			System.out.println(process());

		}
	}

}
