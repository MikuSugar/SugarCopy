package me.mikusugar.copy;

public class APP
{
    public static void main(String[] args)
    {
        System.setProperty("apple.awt.UIElement", "true");

        Conf conf = getConf(args);
        run(conf);

    }

    public static void run(Conf conf)
    {
        final MessageBox messageBox = new MessageBox(conf.getComputeInfos());
        final SocketServer socketServer = new SocketServer(conf.getLocalPort());
        final ListenClipboard listen = new ListenClipboard(messageBox);

        new Thread(socketServer).start();
        new Thread(listen).start();
    }

    private static Conf getConf(String[] args)
    {
        if (args.length == 0)
        {
            return Conf.interactiveInput();
        }
        return Conf.parseArgs(args);

    }

}
