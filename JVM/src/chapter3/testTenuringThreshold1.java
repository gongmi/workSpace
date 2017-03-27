package chapter3;

/**
 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 
 * -XX:MaxTenuringThreshold=1  -XX:+PrintTenuringDistribution
 */
// -XX:MaxTenuringThreshold=15时失败了 
public class testTenuringThreshold1 {
	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];  // 什么时候进入老年代决定于XX:MaxTenuringThreshold设置
		allocation2 = new byte[4 * _1MB];
		//执行下面语句前 发现eden（一共8M）的空间不够了 因此发生第一次GC
//		把allocation1放进了survivor中   allocation1 的 age为1
//		把allocation2放进老年代中
		allocation3 = new byte[4 * _1MB];
//		执行完上面的语句后 eden中有 allocation3  survivor中 有 allocation1
		allocation3 = null;
		//执行下面语句前 发现eden（一共8M）的空间不够了 因此发生第2次GC
//		survivor中   allocation1 的 age为2 大于1 因此放入老年代中
//		由于allocation3 = null; 所以eden中的 new byte[4 * _1MB]被回收了
		allocation3 = new byte[4 * _1MB];
//		执行完上面的语句后 eden中有 allocation3（新的）  survivor中 没有
	}



}
