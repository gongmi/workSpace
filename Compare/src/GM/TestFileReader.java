package GM;

import java.io.*;
import java.util.Arrays;
public class TestFileReader {
  public static void main(String[] args) {
    FileReader fr = null; 
    FileWriter fw1 = null; 
    FileWriter fw2 = null;
    File file1=new File("C:\\Users\\wyq\\Desktop\\1.txt");
    File file2=new File("C:\\Users\\wyq\\Desktop\\2.txt");
    int c = 0;
//    String[] S=new String[11758];
 //  String[] S=new String[109059];
      String[] S=new String[21];
    try {
    	fr = new FileReader("C:\\Users\\wyq\\Desktop\\test.txt");
     // fr = new FileReader("C:\\Users\\wyq\\Desktop\\2158048826_2100837269.new2.txt");
      fw1 = new FileWriter(file1);
      fw2 = new FileWriter(file2);
      int i=-1;
      while ((c = fr.read()) != -1) {
    	  if ((char)c=='[')
    	  {  i++; 
    		  S[i]="";}
    		  S[i]=S[i]+(char)c;
    	  }
      fr.close();
//      for (int j=0;j<S.length;j++)
//    	  System.out.println(S[j]);
      for (int j=0;j<S.length;j++)
    		 // System.out.println(S[j]);      
    	    fw1.write(S[j]); 
      Arrays.sort(S);
    for (int j=0;j<S.length;j++)
	 // System.out.println(S[j]);      
    fw2.write(S[j]);
    fw1.close();
    fw2.close();
      for (int j=0;j<S.length-1;j++)
      {	//  System.out.println(S[j]);
    	  	  if (S[j].equals(S[j+1])) System.out.println("重复的是："+j);
    	  	  }
     String s=" ";
     String[] arrays=s.split(" ");
      System.out.println(arrays.length);
    } catch (FileNotFoundException e) {
      System.out.println("找不到指定文件");
    } catch (IOException e) {
      System.out.println("文件读取错误");
    }

  }
}