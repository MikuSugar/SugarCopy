package me.mikusugar.copy;

import java.util.*;

/**
 * @author mikusugar
 * @version 1.0, 2022/10/2 11:36
 */
public class Conf
{
    private final int localPort;

    private final List<ComputeInfo> computeInfos;

    public static Conf parseArgs(String[] args)
    {
        System.out.println(new Date() + " 解析配置：" + Arrays.toString(args));
        final int localPort = Integer.parseInt(args[0]);
        final List<ComputeInfo> computeInfos = new ArrayList<>();
        for (int i = 1; i < args.length; i++)
        {
            final String[] strs = args[i].split(":");
            final ComputeInfo computeInfo = new ComputeInfo(strs[0], Integer.parseInt(strs[1]));
            computeInfos.add(computeInfo);
        }
        final Conf res = new Conf(localPort, computeInfos);
        System.out.println(new Date() + " 配置解析成功:" + res);
        return res;
    }

    public static Conf interactiveInput()
    {
        System.out.println(new Date() + " 进入交互式输入配置");
        System.out.println("请输入配置：");
        Scanner scanner = new Scanner(System.in);
        final String args = scanner.nextLine();
        scanner.close();
        return parseArgs(args.split(" "));
    }

    @Override
    public String toString()
    {
        return "Conf{" + "localPort=" + localPort + ",computeInfos=" + computeInfos + "}";
    }

    public Conf(int localPort, List<ComputeInfo> computeInfos)
    {
        this.localPort = localPort;
        this.computeInfos = computeInfos;
    }

    public int getLocalPort()
    {
        return localPort;
    }

    public List<ComputeInfo> getComputeInfos()
    {
        return computeInfos;
    }
}
