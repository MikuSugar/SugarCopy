package me.mikusugar.copy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * @author mikusugar
 * @version 1.0, 2022/9/28 09:38
 */
public class Info implements Serializable
{

    private String str;

    private Long time;

    private String imgBase64;

    public Info()
    {
        this.time = System.currentTimeMillis();
    }

    public String getStr()
    {
        return str;
    }

    public Long getTime()
    {
        return time;
    }

    public boolean isImage()
    {
        return imgBase64 != null;
    }

    public BufferedImage getImage()
    {
        return ImageUtils.base64StrToImg(imgBase64);
    }

    public void setImage(Image image)
    {
        this.imgBase64 = ImageUtils.imgToBase64Str(ImageUtils.image2BufferedImage(image), "png");
    }

    public boolean equal(Image image)
    {
        if (image == null)
        {
            return false;
        }
        return ImageUtils.imgToBase64Str(ImageUtils.image2BufferedImage(image), "png").equals(imgBase64);
    }

    public void setStr(String str)
    {
        this.str = str;
    }

    @Override
    public String toString()
    {
        return "Info{" + "str='" + str + '\'' + ", time=" + time + '}';
    }
}
