import javax.swing.*;

public class TTTButton extends JButton {
	
	private int num;
	
	public TTTButton(int num) {
		this.num = num;
		setText("");
	}
	
	public int getNum() {
		return num;
	}

}
