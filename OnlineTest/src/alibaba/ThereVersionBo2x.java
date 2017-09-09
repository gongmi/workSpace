package alibaba;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
//350410;嘉兴中心;浙江省;西安中心;陕西省;9.6m; 
//350424;西安中心;陕西省;嘉兴中心;浙江省;9.6m; 
//350428;嘉兴中心;浙江省;长沙中心;湖南省;17.5m; 
//350432;长沙中心;湖南省;武汉中心;湖北省;17.5m; 
//350448;武汉中心;湖北省;嘉兴中心;浙江省;17.5m; 
//350476;嘉兴中心;浙江省;潍坊中心;山东省;9.6m; 
//350479;潍坊中心;山东省;嘉兴中心;浙江省;17.5m; 
//350481;嘉兴中心;浙江省;成都中心;四川省;9.6m;

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
		private String sCen;// 出发分拨
		private String sPro;// 出发省
		private String eCen;// 到达分拨
		private String ePro;// 到达省
		// 9.6m/17.5m
		private String tType;// 车型

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