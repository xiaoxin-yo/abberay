package yuxin.UI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;

public class Interface {
	private String paper = "Sulfate is an easily accessible energy "
			+ "source for sulfate-reducing prokaryotes (SRP). Little is know about SPR.";
	private JTextArea textArea;
	private MessageWindow window;
	
	public static void main(String [] args) {
		Interface myInterface = new Interface();
		JFrame frame = new JFrame("Abberay");
		myInterface.setFrame(frame);
		myInterface.setTextArea(frame);
		myInterface.showWindow(frame);
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
		textArea = new JTextArea(paper);
		textArea.setEditable(false);
		textPanel.add(new JScrollPane(textArea));
		frame.add(textPanel);
		textArea.addMouseListener(new MyMouseListener(frame));
	}
	
	private void showWindow(JFrame frame) {
		this.window = new MessageWindow(frame);
		this.window.setLayout(new BorderLayout());
		JLabel definitionLabel = new JLabel("Intelligent definition");
		JLabel firstOccurLabel = new JLabel("1st sentence");
		JLabel secondOccurLabel = new JLabel("2st sentence");
		JLabel thirdOccurLabel = new JLabel("3st sentence");
		this.window.add(definitionLabel,BorderLayout.NORTH);
		JPanel sentencePanel = new JPanel();
		sentencePanel.setLayout(new BoxLayout(sentencePanel, BoxLayout.Y_AXIS));
		sentencePanel.add(firstOccurLabel);
		sentencePanel.add(secondOccurLabel);
		sentencePanel.add(thirdOccurLabel);
		this.window.add(sentencePanel);
	}
	
	class MyMouseListener extends MouseAdapter{
		JFrame frame;
		public MyMouseListener(JFrame frame) {
			this.frame = frame;
		}
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				System.out.println(textArea.getSelectedText());
				window.setLocation(frame.getX()+e.getX()+70, frame.getY()+e.getY()+60);
				window.setVisible(true);
			}
			else if (e.getClickCount() == 1) {
				window.setVisible(false);
			}
		}
	}
	
	class MessageWindow extends JWindow{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MessageWindow(JFrame parent) { 
			super(parent);
			setSize(200, 300);       
		}

		public void paint(Graphics g) 
		{ 
			super.paint(g);
			g.drawRect(0,0,getSize().width - 1,getSize().height - 1); 
		}
	}
}


