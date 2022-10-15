package me.mikusugar.copy;

import cn.hutool.core.swing.clipboard.ClipboardUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author mikusugar
 * @version 1.0, 2022/10/2 11:23
 */
public class SocketServer implements Runnable
{

    private final int port;

    public SocketServer(int port)
    {
        this.port = port;
    }

    @Override
    public void run()
    {
        try (ServerSocket server = new ServerSocket(port))
        {
            System.out.println(new Date() + " 监听服务启动，监听端口：" + port);
            while (true)
            {
                Socket socket = server.accept();
                final ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                final Info info = (Info)objectInputStream.readObject();
                System.out.println(new Date() + " 接收到来自[" + socket.getLocalAddress() + "]的消息：");
                if (info.isImage() && !info.equal(ClipboardUtil.getImage()))
                {
                    ClipboardUtil.setImage(info.getImage());
                }
                else if (!info.isImage() && !info.getStr().equals(ClipboardUtil.getStr()))
                {
                    ClipboardUtil.setStr(info.getStr());
                }
                socket.close();

            }
        }
        catch (IOException e)
        {
            System.out.println(new Date() + " 监听本地" + port + "出现异常");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }

    }
}
