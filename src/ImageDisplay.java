package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImageDisplay extends JPanel implements KeyListener{
	private List<Image> imgs;
	private int curImage;
	
	private final String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	
	public ImageDisplay() {
		super();
		imgs = new ArrayList<Image>();
		curImage = 0;
		addKeyListener(this);
	}
	
	public ImageDisplay(Image... images) {
		super();
		imgs = new ArrayList<Image>();
		for(Image img: images){
			imgs.add(img);
		}
		curImage = 0;
		addKeyListener(this);
	}
	
	public void addImage(Image img){
		this.imgs.add(img);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(imgs.size()>0)
			g.drawImage(imgs.get(curImage),0,0,this.getWidth(),this.getHeight(),Color.BLACK,null);
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(500, 500);
		ImageSearch is = new ImageSearch(id, key);//YOUR STUFF HERE: CUSTOM SEARCH ID AND KEY
		List<String> urls = is.getImageURLs(JOptionPane.showInputDialog("Enter Search Term"));
		ImageDisplay id = new ImageDisplay();
		for(String url : urls){
			try {
				id.addImage(ImageIO.read(new URL(url)));
				System.out.println("Image Loaded.");
			} catch (IOException e) {
				System.out.println("IOException on URL "+url);
			}
		}
		id.setFocusable(true);
		jf.add(id);
		jf.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			curImage--;
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			curImage++;
		curImage+=imgs.size();
		curImage%=imgs.size();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
