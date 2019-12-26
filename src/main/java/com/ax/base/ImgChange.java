package com.ax.base;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;

public class ImgChange {
    /*
     * pdf转换
     */
    public void pdfToImg(){
        System.out.println("entry Ingchange");
        //pdf文件
        File pdffile = new File("E:/gitaxforum/SSMForum/src/main/webapp/img/tele.pdf");
        // 转成的 png 文件存储全路径及文件名
        String targetPath = "E:/gitaxforum/SSMForum/src/main/webapp/img/telePng.png";
        FileInputStream fis = null;
        InputStream byteInputStream = null ;
        try {
            fis = new FileInputStream(pdffile);

            PDDocument doc = PDDocument.load(fis);
            PDFRenderer pdfRenderer = new PDFRenderer(doc);

            int pageCOunt = doc.getNumberOfPages();
            if (pageCOunt>0){
                BufferedImage image = pdfRenderer.renderImage(0, 2.0f);
                image.flush();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ImageOutputStream imOut  = ImageIO.createImageOutputStream(bs);
                ImageIO.write(image, "png", imOut);
                byteInputStream = new ByteArrayInputStream(bs.toByteArray());
                byteInputStream.close();

                File uploadFile = new File(targetPath);
                FileOutputStream fops;
                fops = new FileOutputStream(uploadFile);
                fops.write(readInputStream(byteInputStream));
                fops.flush();
                fops.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*
     * 转换图区，读取流
     */
    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /*
     * 读取二维码信息
     */
    //throws IOException, DocumentException
    public String extractImages() {
//        String filename = "E:/gitaxforum/SSMForum/src/main/webapp/img/billPdf.pdf";  error
//        String filename = "E:/gitaxforum/SSMForum/src/main/webapp/img/billQR.png";  ok
//        String filename = "E:/gitaxforum/SSMForum/src/main/webapp/img/telePng.png";  ok //camera1 null //camera2 ok
        String filename = "E:/gitaxforum/SSMForum/src/main/webapp/img/camera3.jpg";
        String returnResult = "";
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        File file = new File(filename);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 定义二维码参数
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 获取读取二维码结果
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        Result result = null;
        try {
            result = multiFormatReader.decode(binaryBitmap, hints);
            returnResult = result.getText();
            int a = 1;
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return returnResult;
    }

    /*
     * 将图片截图
     */
    public static void cutPNG(InputStream input, OutputStream out, int x,int y, int width, int height) throws IOException {
        ImageInputStream imageStream = null;
        try {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");
            ImageReader reader = readers.next();
            imageStream = ImageIO.createImageInputStream(input);
            reader.setInput(imageStream, true);
            ImageReadParam param = reader.getDefaultReadParam();

            System.out.println(reader.getWidth(0));
            System.out.println(reader.getHeight(0));

            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, "png", out);
        } finally {
            imageStream.close();
        }
    }
    public void isQRcodeImg(){
        String filename = "E:/gitaxforum/SSMForum/src/main/webapp/img/camera2.jpg";
        try {

            URL url = new URL(filename);
            HttpURLConnection hucon = (HttpURLConnection) url.openConnection();
            hucon.setConnectTimeout(5000);
            hucon.setRequestMethod("GET");
            if (hucon.getResponseCode() == 200) {
                InputStream inputStream = hucon.getInputStream();
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                //decodeImage(bitmap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
