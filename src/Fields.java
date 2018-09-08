import java.awt.*;

public interface Fields {
	int PADDING = 5, FONT_SIZE = 140, BUTTON_WIDTH = 140;
	Dimension BUTTON_SIZE = new Dimension(BUTTON_WIDTH, BUTTON_WIDTH);
	
	int WINDOW_WIDTH = PADDING * 4 + BUTTON_WIDTH * 3, WINDOW_HEIGHT = WINDOW_WIDTH;
	
	Font Lato_Light = new Font("Lato-Light", Font.PLAIN, FONT_SIZE);
	Font titleFont = new Font("Lato-Light", Font.PLAIN, 100);
	Font startFont = new Font("Lato-Light", Font.PLAIN, 80);
	Font instructionsFont = new Font("Lato-Light", Font.PLAIN, 20);
	Font Lato_Light_Bold = new Font("Lato-Bold", Font.PLAIN, FONT_SIZE);
}
