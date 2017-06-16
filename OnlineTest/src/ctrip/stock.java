package ctrip;

import java.util.*;

public class stock {
	// buy[i] = Math.max(buy[i - 1], sell[i - k] - prices[i]);
	// sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
	// ֻ��Ҫ��cooldownΪ1��Ļ����ϰ�1��Ϊk����
	// �����������������Ĳ��Ǻ����
	// ��ʵ������˼��k����������
	// ��k=1ʱ
	// ����˵��i����������ôi+1��Ϳ�������
	// ����Ҫ��k��Ϊk-1�������гɹ�
	public static int maxProfit(int[] prices, int k) {
		int n = prices.length;
		int[] buy = new int[n];
		int[] sell = new int[n];
		buy[0] = -prices[0];
		for (int i = 1; i < n; i++) {
			buy[i] = Math.max(buy[i - 1], (i - k < 0 ? 0 : sell[i - k]) - prices[i]);
			sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
		}
		return sell[n - 1];
	}

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
