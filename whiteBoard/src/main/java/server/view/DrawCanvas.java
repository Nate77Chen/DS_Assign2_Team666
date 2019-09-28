package server.view;


import java.awt.*;

public class DrawCanvas extends Canvas {

    private Image image=null;




    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(image, 0, 0, null);

    }

    @Override
    public void update(Graphics g) {
        // TODO Auto-generated method stub
        paint(g);
    }







}
