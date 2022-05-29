
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main(String args[])
    {   
        File file = new File("bigImage.jpeg");

        try{
            BufferedImage bImage = ImageIO.read(file);
            //BufferedImage bImage2 = ImageIO.read(file);
            //System.out.println("Resim ozellikleri => Genislik --> " + bImage.getWidth() + " Yukseklik --> " + bImage.getHeight());

            List<Thread> runnables = new ArrayList<Thread>();

            boolean isImageWidthEven = bImage.getWidth() % 2 == 0;
            boolean isImageHeightEven = bImage.getHeight() % 2 == 0;


            //ASAGIDA TUM THREADLARIMA RESMİ ESİT SEKİLDE PAYLASTIRDIM....
            //1 PIKSELLIK EKSIKLIKLER OLABİLİR ONLARI COK UMURSAMADIM...
            //AMA ZATEN HIZ FARKINI GORMEK....
            //THREAD-1
            runnables.add(new Thread(new MyCustomRunnable(bImage, 0, bImage.getWidth() / 2, 0, bImage.getHeight() / 2)));
            //THREAD-2
            runnables.add(new Thread(new MyCustomRunnable(bImage, (isImageWidthEven) ? bImage.getWidth() / 2 : (bImage.getWidth() / 2) ,
                    bImage.getWidth(), 0, bImage.getHeight() / 2)));

            //THREAD-3
            runnables.add(new Thread(new MyCustomRunnable(bImage, 0, bImage.getWidth() / 2,
                    (isImageHeightEven) ? (bImage.getHeight() / 2 ) : bImage.getHeight() / 2, bImage.getHeight())));

            //THREAD-4
            runnables.add(new Thread(new MyCustomRunnable(bImage, (isImageWidthEven) ? (bImage.getWidth() / 2 ) : bImage.getWidth() / 2,
                    bImage.getWidth(), (isImageHeightEven) ? (bImage.getHeight() / 2 ) : (bImage.getHeight() / 2), bImage.getHeight())));

            long startMilliSecond =  System.currentTimeMillis();
            //TUM THREAD'LERİ LOOP EDEREK BASLATTIM....
            for(Thread thread : runnables){
                thread.run();
            }
            long elapsedTime = System.currentTimeMillis() - startMilliSecond;
            System.out.println("4 thread ile sonuc => " + elapsedTime + "ms");
            File outputFile = new File("processedImage.jpeg");
            ImageIO.write(bImage, "jpeg", outputFile);


//            //tek thread ile....
//            long startMilliSecond = System.currentTimeMillis();
//            Color colorWhite = new Color(255, 255, 255);
//            int color = colorWhite.getRGB();
//            for(int y = 0; y < bImage2.getHeight();y++){
//                for(int x = 0; x < bImage2.getWidth(); x++){
//                    bImage2.setRGB(x, y, color);
//                }
//            }
//            long elapsedTime = System.currentTimeMillis() - startMilliSecond;
//            System.out.println("1 thread ile sonuc => " + elapsedTime + "ms");
//
//            File outputFile2 = new File("processedImage2.jpeg");
//            ImageIO.write(bImage2, "jpeg", outputFile2);
//





        }
        catch(IOException ioException){
            System.out.println("IOException error...");
        }
    }

   




}