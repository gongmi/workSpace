package ctrip;
import java.util.*;

public class stock {

	/* ������������������ʵ����ĿҪ��Ĺ��� */
	/* ��Ȼ����Ҳ���Բ������������ģ����������ȫ�����Լ����뷨�� ^-^ */
	/****************************** ��ʼд���� ******************************/
	static int maxProfit(int[] p, int k) {
		
			if (p == null || p.length <= 1)
				return 0;

			int b0 = -p[0], b1 = b0;
			int s0 = 0, s1 = 0, s2 = 0;

			for (int i = 1; i < p.length; i++) {
				b0 = Math.max(b1, s2 - p[i]);
				s0 = Math.max(s1, b1 + p[i]);
				b1 = b0;
				s2 = s1;
				s1 = s0;
			}
			return s0;
		

	}

	/****************************** ����д���� ******************************/

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int res;

		int _stockPrices_size = 0;
		_stockPrices_size = Integer.parseInt(in.nextLine().trim());
		int[] _stockPrices = new int[_stockPrices_size];
		int _stockPrices_item;
		for (int _stockPrices_i = 0; _stockPrices_i < _stockPrices_size; _stockPrices_i++) {
			_stockPrices_item = Integer.parseInt(in.nextLine().trim());
			_stockPrices[_stockPrices_i] = _stockPrices_item;
		}

		int _k;
		_k = Integer.parseInt(in.nextLine().trim());

		res = maxProfit(_stockPrices, _k);
		System.out.println(String.valueOf(res));

	}
}
