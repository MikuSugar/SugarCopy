package me.mikusugar.copy;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
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

    public void sendMsgAll(Info msg)
    {
        for (ComputeInfo info : recipients)
        {
            try
            {
                Socket socket = new Socket(info.getHost(), info.getPort());
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                System.out.println(new Date() + " 往[" + info.getHost() + "]发送" + (msg.isImage() ? "图片" : "文字"));
                objectOutputStream.writeObject(msg);
                objectOutputStream.flush();
                objectOutputStream.close();
                socket.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
