package com.bjsxt.shopping.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
	List<CartItem> items = new ArrayList<CartItem>();

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	public void add(CartItem ci) {
		for (Iterator<CartItem> iter = items.iterator(); iter.hasNext();) {
			CartItem item = iter.next();
			if(item.getProduct().getId() == ci.getProduct().getId()) {// 一样的改变数量
				item.setCount(item.getCount() + 1);
				return;
			}
		}
		
		items.add(ci);//因为list是允许有重复元素的 但是我们并不想让它有重复的元素 所以不一样的才加进来 一样的改变数量
	}
	
	public double getTotalMemberPrice() {
		double d = 0.0;
		for(Iterator<CartItem> it = items.iterator(); it.hasNext(); ) {
			CartItem current = it.next();
			d += current.getProduct().getMemberPrice() * current.getCount();
		}
		return d;
	}
	
	public void deleteItemById(int productId) {
		for (Iterator<CartItem> iter = items.iterator(); iter.hasNext();) {
			CartItem item = iter.next();
			if(item.getProduct().getId() == productId) {
				iter.remove();//不能调用容器的remove 如果用了iterator
			}
		}
	}
}
