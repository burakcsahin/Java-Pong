import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Texts {
	public String text;
	public Font font;
	public double x,y;
	public Color color;
	public double width, height;
	
	public Texts(String text, Font font, double x, double y, Color color) {
		this.text = text;
		this.font = font;
		this.x = x;
		this.y = y;
		this.color = color;
		this.width = font.getSize() * text.length();
		this.height = font.getSize();
	}
	
	public Texts(int text, Font font, double x, double y, Color color) {
		this.text = "" + text;
		this.font = font;
		this.x = x;
		this.y = y;
		this.color = color;

	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.setFont(font);
		g2d.drawString(text, (float)x, (float)y);
	}
	
	
}
