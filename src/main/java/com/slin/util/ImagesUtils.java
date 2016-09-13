package com.slin.util;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by slin on 16/9/12.
 */

public class ImagesUtils {
    private File file = null; // 文件对象
    private String inputDir; // 输入图路径
    private String outputDir; // 输出图路径
    private String inputFileName; // 输入图文件名
    private String outputFileName; // 输出图文件名
    private int outputWidth = 100; // 默认输出图片宽
    private int outputHeight = 100; // 默认输出图片高
    private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)
    public ImagesUtils() { // 初始化变量
        inputDir = "";
        outputDir = "";
        inputFileName = "";
        outputFileName = "";
        outputWidth = 100;
        outputHeight = 100;
    }
    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
    }
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }
    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
    public void setOutputWidth(int outputWidth) {
        this.outputWidth = outputWidth;
    }
    public void setOutputHeight(int outputHeight) {
        this.outputHeight = outputHeight;
    }
    public void setWidthAndHeight(int width, int height) {
        this.outputWidth = width;
        this.outputHeight = height;
    }
    //对图片路径，名称进行赋值
    public String compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height, boolean gp) {
        // 输入图路径
        this.inputDir = inputDir;
        // 输出图路径
        this.outputDir = outputDir;
        // 输入图文件名
        this.inputFileName = inputFileName;
        // 输出图文件名
        this.outputFileName = outputFileName;
        // 设置图片长宽
        setWidthAndHeight(width, height);
        // 是否是等比缩放 标记
        this.proportion = gp;
        return compressImages();
    }
    //图片处理
    public String compressImages() {
        try {
            //获得源文件
            file = new File(inputDir + inputFileName);
            if (!file.exists()) {
                return "";
            }
            Image img = ImageIO.read(file);
            // 判断图片格式是否正确
            if (img.getWidth(null) == -1) {
                System.out.println(" can't read,retry!" + "<BR>");
                return "no";
            } else {
                int newWidth; int newHeight;
                // 判断是否是等比缩放
                if (this.proportion == true) {
                    // 为等比缩放计算输出的图片宽度及高度
                    double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
                    double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
                    // 根据缩放比率大的进行缩放控制
                    double rate = rate1 > rate2 ? rate1 : rate2;
                    newWidth = (int) (((double) img.getWidth(null)) / rate);
                    newHeight = (int) (((double) img.getHeight(null)) / rate);
                } else {
                    newWidth = outputWidth; // 输出的图片宽度
                    newHeight = outputHeight; // 输出的图片高度
                }
                BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
               /*
                * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
                * 优先级比速度高 生成的图片质量比较好 但速度慢
                */
                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
//                FileOutputStream out = new FileOutputStream(outputDir + outputFileName);
//                // JPEGImageEncoder可适用于其他图片类型的转换
//                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//                encoder.encode(tag);
//                out.close();
                String dstName = outputDir + outputFileName;
                String formatName = dstName.substring(dstName.lastIndexOf(".") + 1);
                //FileOutputStream out = new FileOutputStream(dstName);
                //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                //encoder.encode(dstImage);
                ImageIO.write(tag, formatName, new File(dstName) );
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "ok";
    }
    //compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
    /* public static void main(String[] arg) {
    	 ImagesUtils mypic = new ImagesUtils();
    	 mypic.compressPic("G:\\img\\", "G:\\imgs\\", "phone.jpg", "6.jpg", 300, 300, true);
    	 System.out.println("成功");
     }
    */

    /**
     * 图片组合
     * @param rootDir
     * @param oldImgRealPath
     * @param newImgRealPath
     */
    public static void imgZuhe(String rootDir,String oldImgRealPath,String newImgRealPath){
        try{
            //读取第一张图片
            File fileOne = new File(rootDir + oldImgRealPath);
            BufferedImage ImageOne = ImageIO.read(fileOne);
            int width = ImageOne.getWidth();//图片宽度
            int height = ImageOne.getHeight();//图片高度
            //从图片中读取RGB
            int[] ImageArrayOne = new int[width*height];
            ImageArrayOne = ImageOne.getRGB(0,0,width,height,ImageArrayOne,0,width);
            //对第二张图片做相同的处理
            File fileTwo = new File(rootDir + "\\OnloadFile\\logo.jpg");
            BufferedImage ImageTwo = ImageIO.read(fileTwo);
            int widthTwo = ImageTwo.getWidth();//图片宽度
            int heightTwo = ImageTwo.getHeight();//图片高度

            int[] ImageArrayTwo = new int[widthTwo*heightTwo];
            ImageArrayTwo = ImageTwo.getRGB(0,0,widthTwo,heightTwo,ImageArrayTwo,0,widthTwo);

            //生成新图片
            BufferedImage ImageNew = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            ImageNew.setRGB(0,0,width,height,ImageArrayOne,0,width);//设置左半部分的RGB
            ImageNew.setRGB(width*1/3,height*1/3,widthTwo,heightTwo,ImageArrayTwo,0,widthTwo);//设置右半部分的RGB
            File outFile = new File(rootDir + newImgRealPath);
            ImageIO.write(ImageNew, "jpg", outFile);//写图片
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 图片水印
     * @param text
     */
    public static void imgYin(String rootDir,String oldImgRealPath,String text){
        byte[] bytes = null;
        try{
            String str = text;
            String pathAndName = rootDir+oldImgRealPath;
            File _file = new File(pathAndName);
            Image src = ImageIO.read(_file);
            int wideth=src.getWidth(null);
            int height=src.getHeight(null);
            BufferedImage image=new BufferedImage(wideth,height,BufferedImage.TYPE_INT_RGB);
            Graphics g=image.createGraphics();
            g.drawImage(src,0,0,wideth,height,null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("宋体",Font.PLAIN,20));
            Font aa=new Font("宋体",Font.PLAIN,20);

            g.drawString(str,wideth-110,height-10);
            g.dispose();
            ByteArrayOutputStream out1 = new ByteArrayOutputStream();
//            saveImage(image, out1);
            bytes = out1.toByteArray();
            out1.close();
            FileOutputStream out2 = new FileOutputStream(pathAndName);
            out2.write(bytes);
            out2.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void saveImage(BufferedImage img, OutputStream out1) throws Exception {
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out1);
//        encoder.encode(img);

    }





}
