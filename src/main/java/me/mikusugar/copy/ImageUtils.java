package me.mikusugar.copy;

import cn.hutool.core.swing.clipboard.ClipboardUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Base64;

/**
 * @author mikusugar
 * @version 1.0, 2022/10/14 16:25
 */
public class ImageUtils
{

    /**
     * Image 类型转换成BufferedImage
     *
     * @param img Image
     * @return BufferedImage
     */
    public static BufferedImage image2BufferedImage(Image img)
    {

        if (img instanceof BufferedImage)
        {
            return (BufferedImage)img;
        }

        // Create a buffered image with transparency
        BufferedImage bufferImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bufferImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bufferImage;
    }

    /**
     * 图像-> base64
     *
     * @param img        图像
     * @param formatName 图像类型
     * @return base64字符串
     */
    public static String imgToBase64Str(final RenderedImage img, final String formatName)
    {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (final OutputStream b64os = Base64.getEncoder().wrap(os))
        {
            ImageIO.write(img, formatName, b64os);
        }
        catch (final IOException ioe)
        {
            throw new UncheckedIOException(ioe);
        }
        return os.toString();
    }

    /**
     * base64->图像
     *
     * @param base64Str base64
     * @return BufferedImage
     */
    public static BufferedImage base64StrToImg(final String base64Str)
    {
        try
        {
            return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64Str)));
        }
        catch (final IOException ioe)
        {
            throw new UncheckedIOException(ioe);
        }
    }
}
