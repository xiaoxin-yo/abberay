// This class manipulates the text of the paper

public class TextManipulation {
	private static String text= "Sulfate (SO42-) is the second-most abundant anion after chloride in the modern ocean. It serves as an easily accessible energy source for sulfate-reducing prokaryotes (SRPs), which are commonly found in organic-rich sediments and play an important role in the decomposition of organic matter. Were these microbes major players in ecosystems during the Archean (before 2.5 billion years ago), when molecular oxygen was virtually absent from both the atmosphere and oceans? Whether this was the case depends on how much sulfate there was in the Archean ocean. Three articles in this issue (1–3) use precise measurements of stable sulfur isotope ratios to investigate how much sulfate there was in the Archean ocean and where that sulfate originated.";

	private int [] indexes;
	
	public static void main(String [] args){
		TextManipulation mtm = new TextManipulation(text);
		mtm.findLoc("in", 3);
		for (int i=0;i<3;i++){
			System.out.println("indexes" + mtm.indexes[i]);
	//	mtm.findSentenceEnd(0);
		}
		mtm.returnResult();
		
	}

	public TextManipulation (String txt){
		this.text = txt;
		this.indexes = new int [3];
	}

	public void findLoc(String abbv, int nthOccurrence){	
		 for (int i=0; i < nthOccurrence; i++){
		 	if (i==0){
		 		indexes[i] = text.indexOf(abbv);
		 	}
		 	else
		 		indexes[i] = text.indexOf(abbv, indexes[i-1]+1);
		 }
	}
	
	public int findSentenceEnd(int loc) {
		int end = this.text.indexOf(". ", loc);
		for (int i=0; i < 3; i++) {
			if (Character.isUpperCase(text.substring(end+2, end+3).charAt(0)))
				return end;
			else 
				end = this.text.indexOf(". ", end+1);
		}
		return end;
	}
	
	public int findSentenceStart(int loc) {
	//	this.text = "aa. Bb. Cc. ";
	//	loc = 5;
		int beg = this.text.lastIndexOf(". ", loc);
		System.out.println("beginning" + beg + "\n loc:" + loc);

		for (int i=0; i < 3; i++) {
			if(beg < 0)
				return beg;
			else if (Character.isUpperCase(text.substring(beg+2, beg+3).charAt(0)))
				return beg;
			else if (beg != 0)
				beg = this.text.lastIndexOf(". ", beg-1);
		}
		return beg;
	}
	
	public void returnResult(){
		String[] results = new String [3];
		for (int i=0; i < 3; i++){
			System.out.println("start: " + findSentenceStart(indexes[i]));
			System.out.println("end: " + findSentenceEnd(indexes[i]));
			System.out.println("kalfj" + i);
			results[i] = text.substring(findSentenceStart(indexes[i])+1, findSentenceEnd(indexes[i])+1);

	
			System.out.println(results[i] + "\n");
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
