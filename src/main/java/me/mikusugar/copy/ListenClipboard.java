package me.mikusugar.copy;

import cn.hutool.core.swing.clipboard.ClipboardUtil;

import java.util.Date;

/**
 * @author mikusugar
 * @version 1.0, 2022/9/28 09:34
 */
public class ListenClipboard implements Runnable
{
    private final MessageBox messageBox;

    public ListenClipboard(MessageBox messageBox)
    {
        this.messageBox = messageBox;
    }

    @Override
    public void run()
    {
        System.out.println(new Date() + " 启动剪贴板监听进程");
        ClipboardUtil.listen((clipboard, transferable) ->
        {
            try
            {
                String str = ClipboardUtil.getStr();
                if (str.trim().isEmpty())
                {
                    return null;
                }
                Long time = System.currentTimeMillis();
                final Info info = new Info(str, time);
                System.out.println(new Date() + " 检测到剪贴板值发送变化，发送消息到所有机器");
                messageBox.sendMsgAll(info.getStr());
                return null;
            }
            catch (Exception e)
            {
                System.out.println(new Date() + " 发送消息出现异常");
                e.printStackTrace();
                return null;
            }
        });
    }
}
