package com.momo.common.util.verificationCode.captcha;


import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * png格式验证码
 * Created by 李杰 on 2018-07-27 上午 10:08.
 */
public class SpecCaptcha extends com.wf.captcha.Captcha {

    public SpecCaptcha() {
    }

    public SpecCaptcha(int width, int height) {
        this();
        setWidth(width);
        setHeight(height);
    }

    public SpecCaptcha(int width, int height, int len) {
        this(width, height);
        setLen(len);
    }

    public SpecCaptcha(int width, int height, int len, Font font) {
        this(width, height, len);
        setFont(font);
    }

    /**
     * 生成验证码
     *
     * @param out 输出流
     * @return 是否成功
     */
    @Override
    public boolean out(OutputStream out) {
        checkAlpha();
        return false;
//        return graphicsImage(textChar(), out);
    }
    public String outaaaa(OutputStream out) {
        checkAlpha();
        return graphicsImage(textChar(), out);
    }
    /**
     * 生成验证码图形
     *
     * @param strs 验证码
     * @param out  输出流
     * @return boolean
     */
    private String graphicsImage(char[] strs, OutputStream out) {
        boolean ok;
        try {
            BufferedImage bi = new BufferedImage(150, 50, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) bi.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 150, 50);
            // 抗锯齿
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setStroke(new BasicStroke(1.3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
            // 随机画干扰线
            drawLine(3, g);
            // 随机画干扰圆
            drawOval(8, g);
            // 画字符串
            AlphaComposite ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);// 指定透明度
            g.setComposite(ac3);
            int hp = (height - font.getSize()) >> 1;
            int h = height - hp;
            int w = width / strs.length;
            int sp = (w - font.getSize()) / 2;
            for (int i = 0; i < strs.length; i++) {
                g.setColor(new Color(20 + num(110), 20 + num(110), 20 + num(110)));
                // 计算坐标
                int x = i * w + sp + num(3);
                int y = h - num(3, 6);
                if (x < 8) {
                    x = 8;
                }
                if (x + font.getSize() > width) {
                    x = width - font.getSize();
                }
                if (y > height) {
                    y = height;
                }
                if (y - font.getSize() < 0) {
                    y = font.getSize();
                }
                g.setFont(font.deriveFont(num(2) == 0 ? Font.PLAIN : Font.ITALIC));
                g.drawString(String.valueOf(strs[i]), x, y);
            }
//            ImageIO.write(bi, "png", out);
            ByteArrayOutputStream outaa = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", outaa);
            boolean flag = ImageIO.write(bi, "gif", outaa);
            /*byte[] b = outaa.toByteArray();
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            // 返回Base64编码过的字节数组字符串
            String base=encoder.encode(b);*/
            String imgsrc = Base64.encodeBase64String(outaa.toByteArray());
//            out.flush();
            ok = true;
            return imgsrc;
        } catch (IOException e) {
            ok = false;
            e.printStackTrace();
        } finally {
            try {
//                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}