package huawei;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//别人的更好
//http://blog.csdn.net/jocelyn92/article/details/69943043
public class dice {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		 int[] array = { 0,1,2,3,4,5,6 };
		try {
			s = br.readLine();
			int len=s.length();
			int temp=0;
			for (int j = 0; j < len; j++) {
				char c =s.charAt(j);
				switch(c){
				case 'L': 
					temp=array[1];
					array[1]=array[5];
					array[5]=array[2];
					array[2]=array[6];
					array[6]=temp;
						break;
				case 'R':
					temp=array[1];
					array[1]=array[6];
					array[6]=array[2];
					array[2]=array[5];
					array[5]=temp;
				
				
						break;
				case 'F':
					temp=array[3];
					array[3]=array[5];
					array[5]=array[4];
					array[4]=array[6];
					array[6]=temp;
						break;
				case 'B':
					temp=array[3];
					array[3]=array[6];
					array[6]=array[4];
					array[4]=array[5];
					array[5]=temp;
						break;
				case 'A':
					temp=array[3];
					array[3]=array[1];
					array[1]=array[4];
					array[4]=array[2];
					array[2]=temp;
						break;
				case 'C':	
					temp=array[3];
					array[3]=array[2];
					array[2]=array[4];
					array[4]=array[1];
					array[1]=temp;
						break;
				}
			}
			String ss="";
			for(int i=1;i<array.length;i++)
		     ss=ss+array[i];
				System.out.println(ss);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

