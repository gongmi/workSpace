package NIO;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class FileChannelTest {
	public static void main(String[] args) {
		File f = new File("d:\\GM\\FileChannelTest.txt");
		try (FileChannel inChannel = new FileInputStream(f).getChannel();
			FileChannel outChannel = new FileOutputStream("d:\\GM\\FileChannelTest2.txt").getChannel())
		{
			MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length()); // ¢Ù
			outChannel.write(buffer); // ¢Ú
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
