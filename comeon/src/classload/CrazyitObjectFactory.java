package classload;

import java.util.Date;

import javax.swing.JFrame;
public class CrazyitObjectFactory {
	public static Object getInstance(Class cls) {
		try {
			return cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		Date d = CrazyitObjectFactory2.getInstance(Date.class);
		JFrame f = CrazyitObjectFactory2.getInstance(JFrame.class);
	}
}
