package io;

import java.io.*;
import java.util.*;
public class WriteToProcess
{
	public static void main(String[] args)
		throws IOException
	{
		// ����java ReadStandard����������и�������ӽ���
		Process p = Runtime.getRuntime().exec("java ReadStandard");
		try(
			// ��p���̵����������PrintStream����
			// ���������Ա����������������p��������������
			PrintStream ps = new PrintStream(p.getOutputStream()))
		{
			// ��ReadStandard����д�����ݣ���Щ���ݽ���ReadStandard��ȡ
			ps.println("��ͨ�ַ���");
			ps.println(new WriteToProcess());
		}
	}
}
// ����һ��ReadStandard�࣬������Խ��ܱ�׼���룬
// ������׼����д��out.txt�ļ���
// ����ʹ��Scanner������װ�� ����BufferedReader��
class ReadStandard
{
	public static void main(String[] args)
	{   
		try(
			// ʹ��System.in����Scanner�������ڻ�ȡ��׼����
//			Scanner sc = new Scanner(System.in);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintStream ps = new PrintStream(new FileOutputStream("d:\\GM\\out.txt")))
		{
//			// ��������һ�н�ֻ�ѻس���Ϊ�ָ���
//			sc.useDelimiter("\n");
//			// �ж��Ƿ�����һ��������
//			while(sc.hasNext())
//			{
//				// ���������
//				ps.println("��������������ǣ�" + sc.next());
//			}
			String line=null;
			while((line=br.readLine())!=null)
			{
				if (line.equals("bye"))
					break;
				ps.println(line);
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
