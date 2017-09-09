package alibaba;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
//350410;��������;�㽭ʡ;��������;����ʡ;9.6m; 
//350424;��������;����ʡ;��������;�㽭ʡ;9.6m; 
//350428;��������;�㽭ʡ;��ɳ����;����ʡ;17.5m; 
//350432;��ɳ����;����ʡ;�人����;����ʡ;17.5m; 
//350448;�人����;����ʡ;��������;�㽭ʡ;17.5m; 
//350476;��������;�㽭ʡ;Ϋ������;ɽ��ʡ;9.6m; 
//350479;Ϋ������;ɽ��ʡ;��������;�㽭ʡ;17.5m; 
//350481;��������;�㽭ʡ;�ɶ�����;�Ĵ�ʡ;9.6m;

public class ThereVersionBo2x {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<UnilateralLine> lineList = new ArrayList<UnilateralLine>();
		while (scanner.hasNextLine()) {
			String[] options = scanner.nextLine().split(";");
			if (options.length < 5) {
				break;
			}
			lineList.add(new UnilateralLine(options[0], options[1], options[2], options[3],
					options[4], options[5]));
		}
		scanner.close();

		// wirte your code here
		List<String> result = calculateUnilateral(lineList);

		for (String str : result) {
			System.out.println(str);
		}
	}

	public static List<String> calculateUnilateral(List<UnilateralLine> lineList) {
		List<String> result = new ArrayList<String>();
		HashSet<UnilateralLine> set = new HashSet<>();
		for (UnilateralLine line1 : lineList) {
			if (set.contains(line1))
				continue;
			for (UnilateralLine line2 : lineList) {
				if (line1.eCen.equals(line2.sCen) && line1.ePro.equals(line2.sPro)
						&& line1.sCen.equals(line2.eCen) && line1.sPro.equals(line2.ePro)
						&& line1.tType.equals(line2.tType)) {
					set.add(line2);
					result.add("rule1:" + line1.id + "+" + line2.id);
				}
			}
		}

		return result;
	}

	public static class UnilateralLine {
		private String id;
		private String sCen;// �����ֲ�
		private String sPro;// ����ʡ
		private String eCen;// ����ֲ�
		private String ePro;// ����ʡ
		// 9.6m/17.5m
		private String tType;// ����

		public UnilateralLine(String id, String sCen, String sPro, String eCen, String ePro,
				String tType) {
			this.id = id;
			this.sCen = sCen;
			this.sPro = sPro;
			this.eCen = eCen;
			this.ePro = ePro;
			this.tType = tType;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSCen() {
			return sCen;
		}

		public void setSCen(String ePro) {
			this.ePro = ePro;
		}

		public String getSPro() {
			return sPro;
		}

		public void setSPro(String sPro) {
			this.sPro = sPro;
		}

		public String getECen() {
			return eCen;
		}

		public void setECen(String eCen) {
			this.eCen = eCen;
		}

		public String getEPro() {
			return ePro;
		}

		public void setEPro(String ePro) {
			this.ePro = ePro;
		}

		public String getTType() {
			return tType;
		}

		public void setTType(String tType) {
			this.tType = tType;
		}

		@Override
		public String toString() {
			return "UnilateralLine [id=" + id + ", sCen=" + sCen + ", sPro=" + sPro + ", eCen="
					+ eCen + ", ePro=" + ePro + ", tType=" + tType + "]";
		}
	}
}