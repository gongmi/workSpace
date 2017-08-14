package NIO;

import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class NServer {
    private Selector selector = null;
    static final int PORT = 30000;
    private Charset charset = Charset.forName("UTF-8");

    public void init() throws IOException {
        selector = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
        server.bind(isa);
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            for (SelectionKey sk : selector.selectedKeys()) {
                selector.selectedKeys().remove(sk);

                // 如果sk对应的Channel包含客户端的连接请求！！！！！！说明他只可能是ServerSocketChannel
                if (sk.isAcceptable()) {
//                    System.out.println(sk.channel());
//                    System.out.println(sk.channel() == server);
//                  上面返回true  说明 只有server才可能是acceptable
//                    因此在本例子中 这里只有ServerSocketChannel能进来

                    SocketChannel sc = server.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }
                // 如果sk对应的Channel有数据需要读取   说明只能是SocketChannel
                if (sk.isReadable())     // ③
                {
//                    System.out.println(sk.channel());
                    SocketChannel sc = (SocketChannel) sk.channel();
                    ByteBuffer buff = ByteBuffer.allocate(1024);
                    String content = "";
                    // 开始读取数据
                    try {
                        while (sc.read(buff) > 0) {
                            buff.flip();
                            content += charset.decode(buff);
                        }
                        System.out.println("读取的数据：" + content);
                    }
                    // 如果捕捉到该sk对应的Channel出现了异常，即表明该Channel
                    // 对应的Client出现了问题，所以从Selector中取消sk的注册
                    catch (IOException ex) {
                        // 从Selector中删除指定的SelectionKey
                        sk.cancel();
                        if (sk.channel() != null) {
                            sk.channel().close();
                        }
                    }
                    // 如果content的长度大于0，即聊天信息不为空
                    if (content.length() > 0) {
                        // 遍历该selector里注册的所有SelectionKey 1个ServerSocketChannel的 加N个SocketChannel
                        System.out.println("所有的key为：");
                        for (SelectionKey key : selector.keys()) {
                            System.out.println(key);
                            System.out.println(key.channel());
                            Channel targetChannel = key.channel();
                            if (targetChannel instanceof SocketChannel) {
                                SocketChannel dest = (SocketChannel) targetChannel;
                                dest.write(charset.encode(content));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args)
            throws IOException {
        new NServer().init();
    }
}
