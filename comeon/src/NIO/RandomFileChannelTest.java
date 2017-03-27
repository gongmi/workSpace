package NIO;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class RandomFileChannelTest {
	public static void main(String[] args) throws IOException {
		File f = new File("a.txt");
		try (RandomAccessFile raf = new RandomAccessFile(f, "rw");
				FileChannel randomChannel = raf.getChannel())
		{
			MappedByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
			randomChannel.position(f.length());
			randomChannel.write(buffer);
		}
	}
}
