package me.mikusugar.copy;

import cn.hutool.core.swing.clipboard.ClipboardUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
                InputStreamReader in = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(in);
                StringBuilder str = new StringBuilder();
                String content;
                while ((content = br.readLine()) != null && !"".equals(content))
                {
                    str.append(content);
                }
                System.out.println(new Date() + " 接收到来自：" + socket.getLocalAddress() + "的消息：" + str);

                final String result = str.toString();
                final String oldStr = ClipboardUtil.getStr();
                if (!result.equals(oldStr))
                {
                    ClipboardUtil.setStr(result);
                }
                else
                {
                    System.out.println(new Date() + " " + "当前剪贴板的值与消息一致，不更新剪贴板");
                }
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println("消息收到成功");
                printWriter.close();
                socket.close();
            }
        }
        catch (IOException e)
        {
            System.out.println(new Date() + " 监听本地" + port + "出现异常");
            e.printStackTrace();
            System.exit(1);
        }

    }
}
