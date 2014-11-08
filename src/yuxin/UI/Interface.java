package yuxin.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Interface {
	private String paper = "Sulfate is an easily accessible energy "
			+ "source for sulfate-reducing prokaryotes (SRP). Little is know about SPR.";
	private JTextArea textArea;
	
	public static void main(String [] args) {
		Interface myInterface = new Interface();
		JFrame frame = new JFrame("Abberay");
		myInterface.setFrame(frame);
		myInterface.setTextArea(frame);
		
		frame.setVisible(true);
	}
	
	private void setFrame(JFrame frame) {
		// initial setup
		frame.setLocation(20, 20);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
//		frame.setMinimumSize(new Dimension(800,600));
		frame.pack();
		frame.setSize(1000,800);
		
//		frame.setResizable(false);
	}
	
	private void setTextArea(JFrame frame) {
		// text area
		JPanel textPanel = new JPanel();
		textArea = new JTextArea(paper,30,30);
//		textPanel.add(textArea);
		textPanel.add(new JScrollPane(textArea));
		frame.add(textPanel);
//		frame.add(textArea);
		textArea.setVisible(true);
		textArea.addMouseListener(new MyMouseListener());
	}
	
	class MyMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				System.out.println(textArea.getSelectedText());
			}
		}
	}
}


