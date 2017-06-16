package ctrip;

import java.util.*;

public class stock {
	// buy[i] = Math.max(buy[i - 1], sell[i - k] - prices[i]);
	// sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
	// 只需要在cooldown为1天的基础上把1变为k即可
	// 但是这个题可能描述的不是很清楚
	// 其实它的意思是k天后可以买入
	// 当k=1时
	// 比如说第i天卖出了那么i+1天就可以买入
	// 所以要把k变为k-1才能运行成功
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
