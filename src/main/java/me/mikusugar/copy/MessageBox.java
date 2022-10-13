package me.mikusugar.copy;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * @author mikusugar
 * @version 1.0, 2022/10/2 11:00
 */
public class MessageBox
{
    private final List<ComputeInfo> recipients;

    public MessageBox(List<ComputeInfo> recipients)
    {
        this.recipients = recipients;
    }

    public void sendMsgAll(String msg)
    {
        for (ComputeInfo info : recipients)
        {
            try
            {
                Socket socket = new Socket(info.getHost(), info.getPort());
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

                System.out.println(new Date() + " 往" + info + "发送信息：");
                System.out.println(msg);
                pw.write(msg);
                pw.flush();
                socket.shutdownOutput();

                String reply;
                while (!((reply = br.readLine()) == null))
                {
                    System.out.println(new Date() + " " + info + "返回的信息：" + reply);
                }

                br.close();
                is.close();
                pw.close();
                os.close();
                socket.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
