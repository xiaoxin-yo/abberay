package edu.usc;

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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Interface {
	private String paper = "Sulfate (SO42-) is the second-most abundant anion after chloride in the modern ocean. It serves as an easily accessible energy source for sulfate-reducing prokaryotes (SRPs), which are commonly found in organic-rich sediments and play an important role in the decomposition of organic matter. Were these microbes major players in ecosystems during the Archean (before 2.5 billion years ago), when molecular oxygen was virtually absent from both the atmosphere and oceans? Whether this was the case depends on how much sulfate there was in the Archean ocean. Three articles in this issue (1-3) use precise measurements of stable sulfur isotope ratios to investigate how much sulfate there was in the Archean ocean and where that sulfate originated. Scientists use the ratio between two stable sulfur isotopes, 34S and 32S, to trace SRP activity in ancient sedimentary rocks. In sediments, the microbes preferentially reduce 32SO42- compared to 34SO42-, producing iron sulfide minerals that are depleted in 34S (a process called mass-dependent isotopic fractionation). 34S depletion recorded in pyrite (FeS2) minerals is thus a good indicator of SRP activity. Large 34S depletions are seen in the geological records starting about 2.3 billion years ago, but not before (4). One possible interpretation of these data is that there was little SRP activity in the Archean. Another possibility is that these microbes appeared much earlier, but that the Archean sulfate levels were too low for them to selectively reduce 32SO42-; low sulfate levels are known to reduce the degree of isotopic fractionation (5).";
//	private String paper = "Renal dendritic cells (DCs) and macrophages represent a constitutive, extensive and contiguous network of innate immune cells that provide sentinel and immune-intelligence activity; they induce and regulate inflammatory responses to freely filtered antigenic material and protect the kidney from infection. Tissue-resident or infiltrating DCs and macrophages are key factors in the initiation and propagation of renal disease, as well as essential contributors to subsequent tissue regeneration, regardless of the aetiological and pathogenetic mechanisms. The identification, and functional and phenotypic distinction of these cell types is complex and incompletely understood, and the same is true of their interplay and relationships with effector and regulatory cells of the adaptive immune system. In this Review, we discuss the common and distinct characteristics of DCs and macrophages, as well as key advances that have identified the renal-specific functions of these important phagocytic, antigen-presenting cells, and their roles in potentiating or mitigating intrinsic kidney disease. We also identify remaining issues that are of priority for further investigation, and highlight the prospects for translational and therapeutic application of the knowledge acquired.";
	private JTextArea textArea;
	private MessageWindow window;
	private final int windowWidth = 800;
	private final int windowHeight = 1000;
	private TextManipulation tmp;
	private JLabel [] sentenceLabels;
	private JPanel sentencePanel;
	
	public static void main(String [] args) {
		try {
			// Set System L&F - L&F stands for look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}
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
		frame.pack();
		frame.setSize(windowWidth,windowHeight);
	}
	
	private void setTextArea(JFrame frame) {
		// text area
		JPanel textPanel = new JPanel();
		textArea = new JTextArea(paper);
		textArea.setSize(windowWidth-50, windowHeight);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		// textArea -> JScrollPane -> textPanel
		textPanel.add(new JScrollPane(textArea));
		// textPanel -> frame
		frame.add(textPanel);
		textArea.addMouseListener(new MyMouseListener(frame));
	}
	
	private void showWindow(JFrame frame) {
		this.window = new MessageWindow(frame);
		this.window.setLayout(new BorderLayout());

		// to edit when intelligent definition is implemented
		JTextArea definitionLabel = new JTextArea("Intelligent definition");
		definitionLabel.setEditable(false);
		//
		sentenceLabels = new JLabel[3];
		tmp = new TextManipulation(paper);
		for (int i=0;i<3;i++) {
			sentenceLabels[i] = new JLabel();
		}
		this.window.add(definitionLabel,BorderLayout.NORTH);
		sentencePanel = new JPanel();
		sentencePanel.setLayout(new BoxLayout(sentencePanel, BoxLayout.Y_AXIS));
		for (int i=0;i<3;i++) {
			sentencePanel.add(sentenceLabels[i]);
		}
		this.window.add(sentencePanel,BorderLayout.CENTER);
		this.window.pack();
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
				tmp.setAbbv(textArea.getSelectedText());
				tmp.prepare(tmp);
				int minDisplay = 100;
				if (tmp.countOccur()<3) {
					minDisplay = tmp.countOccur();
				}
				else {
					minDisplay = 3;
				}
				for (int i=0;i<minDisplay;i++) {
					String s = (i+1) + ". " + tmp.results[i];
					String html1 = "<html><body style='width: ";
					String html2 = "px'>\n";
//					System.out.println(s);
					sentenceLabels[i].setText(html1+"200"+html2+s);
				}
				window.pack();
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
//			setSize(200, 300);
		}

		public void paint(Graphics g) 
		{ 
			super.paint(g);
			g.drawRect(0,0,getSize().width - 1,getSize().height - 1); 
		}
	}
}


