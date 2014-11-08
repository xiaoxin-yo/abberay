package edu.usc;

// This class manipulates the text of the paper

public class TextManipulation {
	private String paper;
	private String abbv;
	private int [] indexes;
	String[] sentences;

	public TextManipulation (String txt){
		this.paper = txt;
		this.indexes = new int [3];
	}
	
	public void setAbbr(String abb) {
		this.abbv = abb;
	}

	public void findLoc(int nthOccurrence){	
		for (int i=0; i < nthOccurrence; i++){
			// first search of occurrence starts from the beginning of the whole text
			if (i==0){
				indexes[i] = paper.indexOf(this.abbv);
			}
			// later search start from the last successful search
			else
				indexes[i] = paper.indexOf(this.abbv, indexes[i-1]+1);
		}
	}

	public int findSentenceEnd(int loc) {
		int end = this.paper.indexOf(". ", loc);
		// while period+space+uppercase letter is not found, keep searching!
		while(true) {
			if (Character.isUpperCase(paper.substring(end+2, end+3).charAt(0)))
				return end;
			else 
				end = this.paper.indexOf(". ", end+1);
		}
	}

	public int findSentenceStart(int loc) {
		// beg for beginning
		int beg = this.paper.lastIndexOf(". ", loc);

		while(true) {
			if(beg < 0)
				return beg;
			else if (Character.isUpperCase(paper.substring(beg+2, beg+3).charAt(0)))
				return beg;
			else if (beg != 0)
				beg = this.paper.lastIndexOf(". ", beg-1);
		}
	}

	public void returnResult(){
		sentences = new String [3];
		for (int i=0; i < 3; i++){
			sentences[i] = paper.substring(findSentenceStart(indexes[i])+1, findSentenceEnd(indexes[i])+1);
		}
	}

	/*	public String returnDef(){
		private int [] qualities = int[indexes.length];
		for (int i: indexes)
		{

		}
		return "";
	}*/

	public int getDefQuality(int loc){
		if (paper.indexOf("mean", loc) - loc < 20 )
			return 0;
		if (loc - paper.lastIndexOf("mean", loc) < 20)
			return 1;
		if (paper.indexOf("is", loc) - loc < 20)
			return 2;
		if (loc - paper.lastIndexOf("is", loc) < 20)
			return 3;
		return 4;
	}
}
