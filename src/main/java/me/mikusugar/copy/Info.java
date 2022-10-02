package me.mikusugar.copy;

/**
 * @author mikusugar
 * @version 1.0, 2022/9/28 09:38
 */
public class Info
{
    private final String str;

    private final Long time;

    public Info(String str, Long time)
    {
        this.str = str;
        this.time = time;
    }

    public String getStr()
    {
        return str;
    }

    public Long getTime()
    {
        return time;
    }

    @Override
    public String toString()
    {
        return "Info{" + "str='" + str + '\'' + ", time=" + time + '}';
    }
}
