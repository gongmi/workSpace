package url;

public class MultiThreadDown {
	public static void main(String[] args) throws Exception {
		// ��ʼ��DownUtil����
		final DownUtil downUtil = new DownUtil(
				"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png",
				"d:\\GM\\ios.png", 4);
		// ��ʼ����
		downUtil.download();
		new Thread() {
			@Override
			public void run() {
				while (downUtil.getCompleteRate() < 1) {
					// ÿ��0.01���ѯһ���������ɽ��ȣ�
					System.out.println("����ɣ�" + downUtil.getCompleteRate());
					try {
						Thread.sleep(10);
					} catch (Exception ex) {
					}
				}
			}
		}.start();
	}
}
