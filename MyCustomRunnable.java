import java.awt.*;
import java.awt.image.BufferedImage;


public class MyCustomRunnable implements Runnable{
    
    private int startX;
    private int endX;

    private int startY;
    private int endY;

    private BufferedImage bImage;

    public MyCustomRunnable(BufferedImage bImage, int startX, int endX, int startY, int endY){
        this.startX = startX;
        this.endX = endX;
        
        this.startY = startY;
        this.endY = endY;

        this.bImage = bImage;
    }

    //Her thread'in es zamanli ve birbirlerini etkilemeyecek sekilde islem yapabilmesi adina olusturdugum ozel
    //Runnable implement eden class'imin icindeki 'run' fonksiyonum...
    public void run(){
        try{
            Color colorWhite = new Color(255, 255, 255);
            int color = colorWhite.getRGB();
            for(int y = this.startY; y <= this.endY - 1; y++){
                for(int x = this.startX; x <= this.endX - 1; x++){
                    this.bImage.setRGB(x, y, color);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException err){
            System.out.println("Hata var");
        }


    }
    
}
