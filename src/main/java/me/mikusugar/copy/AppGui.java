package me.mikusugar.copy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;

/**
 * @author mikusugar
 * @version 1.0, 2022/10/17 15:47
 */
public class AppGui
{
    public static void main(String[] args) throws AWTException
    {
        Image image = getIconImage();
        checkSupport();
        initDock(image);
        redirectSystemStreams();
        final JFrame jFrame = getJFrame();
        String argConf = JOptionPane.showInputDialog("请输入启动配置:");
        jFrame.setVisible(true);
        final Conf conf = Conf.parseArgs(argConf.split(" "));
        APP.run(conf);

        TrayIcon trayIcon = new TrayIcon(image);

        trayIcon.addMouseListener(createMenuItemAdapter());

        PopupMenu popupMenu = new PopupMenu();

        popupMenu.add(getLogMenuItem(jFrame));
        popupMenu.addSeparator();
        popupMenu.add(getExitMenuItem());

        trayIcon.setPopupMenu(popupMenu);
        SystemTray systemTray = SystemTray.getSystemTray();
        systemTray.add(trayIcon);
    }

    private static MenuItem getLogMenuItem(JFrame logFrame)
    {
        final MenuItem exitItem = new MenuItem("日志");
        exitItem.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                logFrame.setVisible(true);
            }
        });
        return exitItem;
    }

    private static JFrame getJFrame()
    {
        final JFrame jFrame = new JFrame();
        jFrame.setSize(textArea.getWidth(), textArea.getHeight());
        jFrame.add(textArea);
        jFrame.setVisible(false);
        jFrame.setSize(640, 360);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)screensize.getWidth() / 2 - jFrame.getWidth() / 2;
        int y = (int)screensize.getHeight() / 2 - jFrame.getHeight() / 2;
        jFrame.setLocation(x, y);

        jFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                jFrame.setVisible(false);
            }
        });
        return jFrame;
    }

    private static void checkSupport()
    {
        if (!SystemTray.isSupported())
        {
            JOptionPane.showMessageDialog(null, "not support gui, please use cli");
            System.exit(1);
        }
    }

    private static void initDock(Image image)
    {
        if (isOSX())
        {
            com.apple.eawt.Application app = com.apple.eawt.Application.getApplication();
            app.setDockIconImage(image);
        }
    }

    private static MouseAdapter createMenuItemAdapter()
    {
        return new MouseAdapter()
        {
            // 鼠标事件
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {
                    JOptionPane.showMessageDialog(null, "sugar-copy");
                }
            }
        };
    }

    private static Image getIconImage()
    {
        URL url = AppGui.class.getClassLoader().getResource("icon.png");
        assert url != null;
        ImageIcon icon = new ImageIcon(url);
        return icon.getImage();
    }

    private static MenuItem getExitMenuItem()
    {
        final MenuItem exitItem = new MenuItem("退出");
        exitItem.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        return exitItem;
    }

    private static boolean isOSX()
    {
        String os = System.getProperty("os.name");
        return os.equals("Mac OS X");
    }

    private static final JTextArea textArea = new JTextArea(4, 25);

    private static void updateTextArea(final String text)
    {
        SwingUtilities.invokeLater(() -> textArea.append(text));
    }

    private static void redirectSystemStreams()
    {
        OutputStream out = new OutputStream()
        {
            @Override
            public void write(int b) throws IOException
            {
                updateTextArea(String.valueOf((char)b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException
            {
                updateTextArea(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException
            {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

}
