package edu.usc;
// This class manipulates the text of the paper

import java.util.ArrayList;

public class TextManipulation {
	private static String text= " hello. alto Testing All Ways (TAW) hello. TAW";
	private String abbv; 
	private String longForm;
	String[] results;

	private ArrayList<Integer> indexes;
	
//	public static void main(String [] args){
//		TextManipulation mtm = new TextManipulation(text, "the");
//		mtm.prepare(mtm);
//				
//	}

	public TextManipulation (String txt){
		this.text = txt;
	}
	
	public void setAbbv(String abbv) {
		this.abbv = abbv;
		// moved to here because we now have abbv set
		if (countOccur() < 3)
			this.results = new String[countOccur()];	
		else	
			this.results = new String[3];
		this.indexes = new ArrayList<Integer>();
	}

	public void findLoc(){	
		int count = countOccur();
		 for (int i=0; i < count; i++){
		 	if (i==0){
		 		indexes.add(text.indexOf(abbv));
		 	}
		 	else
		 		indexes.add(text.indexOf(abbv, indexes.get(i-1)+1));
		 }
	}
	
	public void prepare(TextManipulation mtm){
		mtm.findLoc();
		mtm.returnResult();
	}
	
	public int countOccur(){
		String source = text;
		int count = 0;
		while (source.contains(abbv) && source.indexOf(abbv) > 0){
			
			source = source.substring(source.indexOf(abbv) + abbv.length());
			count++;
		}
//		System.out.println(count);
		return count;

	}
	
	public int findSentenceEnd(int loc) {
		
		int end = 0;
		int qLoc, pLoc = 0;  // question mark location and period location
		qLoc = this.text.indexOf("? ", loc);
		pLoc = this.text.indexOf(". ", loc); 
		if (pLoc == -1) {
			end = qLoc;
		}
		else if (qLoc == -1) {
			end = pLoc;
		}
		else if (pLoc <= qLoc) {
			end = pLoc;
		}
		else if (pLoc > qLoc) {
			end = qLoc;
		}
		while(true) {
			if (Character.isUpperCase(text.substring(end+2, end+3).charAt(0)) || Character.isDigit(text.substring(end+2, end+3).charAt(0)))
				return end;
			else{
				if (end == -1) {
					return text.length()-1;
				}
				qLoc = this.text.indexOf("? ", end+2);
				pLoc = this.text.indexOf(". ", end+2); 
				if (pLoc == -1) {
					end = qLoc;
				}
				else if (qLoc == -1) {
					end = pLoc;
				}
				else if (pLoc <= qLoc) {
					end = pLoc;
				}
				else if (pLoc > qLoc) {
					end = qLoc;
				}
//				if(this.text.indexOf(". ", end+1) < this.text.indexOf("? ", end+1)) {
//					end = this.text.indexOf(". ", end+1);
//					if (end == -1) {
//						return this.text.length();
//					}
//				}
//				else {
//					end = this.text.indexOf("? ", end+1);
//					if (end == -1) {
//						return this.text.length();
//					}
//				}
			}
		}
	}
	
	public int findSentenceStart(int loc) {
		int beg;
		if  (this.text.lastIndexOf(". ", loc) > this.text.lastIndexOf("? ", loc))
			beg = this.text.lastIndexOf(". ", loc);
		else
			beg = this.text.lastIndexOf("? ", loc);
		for (int i=0; i < 3; i++) {
			if(beg < 0){
				return beg;
			}
			
			else if (Character.isUpperCase(text.substring(beg+2, beg+3).charAt(0)) || Character.isDigit(text.substring(beg+2, beg+3).charAt(0))){
				return beg;
			}
			else if (beg != 0){
				if (this.text.lastIndexOf(". ", beg-1) > this.text.lastIndexOf("? ", beg-1))
					beg = this.text.lastIndexOf(". ", beg-1);
				else 
					beg = this.text.lastIndexOf("? ", beg-1);
			}	
		}
		return beg;
	}
	
	public void returnResult(){
		int count = 0;	
		int start, end;
		for (int i=0; count < results.length; i++){
			if (count==0){
				start = findSentenceStart(indexes.get(i))+1;
				end = findSentenceEnd(indexes.get(i))+1;
				results[count] = text.substring(start, end);
				count++;
			}
			// adding 1 to findSentenceStart parameter to avoid -1 index
			// adding 2 to substring parameter to avoid extra period and space
			else{
				start = findSentenceStart(indexes.get(i))+1;
				end = findSentenceEnd(indexes.get(i)+1)+1;
				if (!text.substring(start, end).equals(results[count-1])){
					results[count] = text.substring(start, end).trim();
					count++;
				}
				
			}
			 
		}
		for (String s: results)
			//System.out.println(s + "\n");
		findLongForm();
		String test = findBestLongForm ("TAW", longForm);
		System.out.println(test);
	}
	
	
	public void findLongForm() {
		int count = countOccur();
		
		for (int i=0; i < count; i++) {
			int index = indexes.get(i);
			int start = findSentenceStart(index) + 1;
			int current = indexes.get(i);
			longForm = longForm + text.substring(start, current);
			//System.out.println("i" + i + "x" + x + "y" + y);
		}
	} 
	
	public String findBestLongForm(String shortForm, String longForm) { 
		int sIndex; 
		int lIndex; 
		char currChar;  
		sIndex = shortForm.length() - 1; 
		lIndex = longForm.length() - 1; 
		for ( ; sIndex >= 0; sIndex--) { 
		    currChar = Character.toLowerCase(shortForm.charAt(sIndex)); 
		 if (!Character.isLetterOrDigit(currChar)) 
		     continue; 
		
		 while ( 
		((lIndex >= 0) && 
		(Character.toLowerCase(longForm.charAt(lIndex)) != currChar)) 
		|| 
		 ((sIndex == 0) && (lIndex > 0) && 
		(Character.isLetterOrDigit(longForm.charAt(lIndex - 1))))) 
		     lIndex--; 
		
		    if (lIndex < 0) 
		        return null; 
		 
		    lIndex--; 
		} 
		
		lIndex = longForm.lastIndexOf(" ", lIndex) + 1; 
		return longForm.substring(lIndex); 
		} 
	
/*	public String returnDef(){
		private int [] qualities = int[indexes.length];
		for (int i: indexes)
		{
			
		}
		return "";
	}*/
	
	public int getDefQuality(int loc){
		if (text.indexOf("mean", loc) - loc < 20 )
			return 0;
		if (loc - text.lastIndexOf("mean", loc) < 20)
			return 1;
		if (text.indexOf("is", loc) - loc < 20)
			return 2;
		if (loc - text.lastIndexOf("is", loc) < 20)
			return 3;
		return 4;
	}
}