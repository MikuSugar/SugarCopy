package me.mikusugar.copy;

/**
 * @author mikusugar
 * @version 1.0, 2022/10/2 11:01
 */
public class ComputeInfo
{
    private final String host;

    private final int port;

    public ComputeInfo(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public String getHost()
    {
        return host;
    }

    public int getPort()
    {
        return port;
    }

    @Override
    public String toString()
    {
        return host + ":" + port;
    }
}
