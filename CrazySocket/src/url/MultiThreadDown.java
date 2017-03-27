package url;

public class MultiThreadDown {
	public static void main(String[] args) throws Exception {
		// 初始化DownUtil对象
		final DownUtil downUtil = new DownUtil(
				"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png",
				"d:\\GM\\ios.png", 4);
		// 开始下载
		downUtil.download();
		new Thread() {
			@Override
			public void run() {
				while (downUtil.getCompleteRate() < 1) {
					// 每隔0.01秒查询一次任务的完成进度，
					System.out.println("已完成：" + downUtil.getCompleteRate());
					try {
						Thread.sleep(10);
					} catch (Exception ex) {
					}
				}
			}
		}.start();
	}
}
