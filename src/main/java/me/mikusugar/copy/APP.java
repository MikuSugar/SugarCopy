package me.mikusugar.copy;

public class APP
{
    public static void main(String[] args)
    {
        final Conf conf = Conf.parseArgs(args);

        final MessageBox messageBox = new MessageBox(conf.getComputeInfos());
        final SocketServer socketServer = new SocketServer(conf.getLocalPort());
        final ListenClipboard listen = new ListenClipboard(messageBox);

        new Thread(socketServer).start();
        new Thread(listen).start();

    }
}
