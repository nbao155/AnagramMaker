import java.util.ArrayList;
/**
 *	AnagramMaker - <description goes here>
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	
 *	@since	
 */
public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	
	private WordUtils wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
	private ArrayList<String> anagrams = new ArrayList<String>();
	private int counter = 0;
	private ArrayList<String> resultArray = new ArrayList<String>();
		
	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		wu = new WordUtils();
		wu.loadWords(FILE_NAME);
		wu.sortWords();
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		runAnagramMaker();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then create anagrams from those
	 *	characters.
	 */
	public void runAnagramMaker() {
		//printIntroduction();
		System.out.println("");
		boolean isRunning = true;
		String input= "";
		while(isRunning){
			input = Prompt.getString("Word(s), name or phrase (q to quit)");
			if(input.equals("q")==false){
				numWords = Prompt.getInt("Number of words in anagram");
				maxPhrases = Prompt.getInt("Maximum number of anagrams to print");
				input = input.toLowerCase();
				findAnagrams(input);
				System.out.println("");
				for(int i = 0;i<Math.min(maxPhrases, resultArray.size());i+=numWords){
					for(int a = 0;a<numWords;a++){
						System.out.print(resultArray.get(i+a)+" ");
					}	
					System.out.println("");
				}
				System.out.println("\nStopped at "+maxPhrases+" anagrams.\n");
			}
			else{
				isRunning = false;
			}
		}
	}
	/**
	 * Seeks out all anagrams of an input String
	 * @param input		The string to decode for anagrams
	 * @return			all anagrams of input
	 */
	public void findAnagrams(String input){
		String newPhrase = "";
		String input1 = input;
		ArrayList<String> allWord = new ArrayList<String>();
		if(input.length()<=0){
			for(int i = 0;i<anagrams.size();i++){
				resultArray.add(anagrams.get(i));
			}
			return;
		}
		else{
			for(int a = 0;a<input1.length();a++){
				if(Character.isAlphabetic(input1.charAt(a))==false){
					if(a==0)
						input1 = input1.substring(1);
					else
						input1 = input1.substring(0, a)+input.substring(a+1);
				}
			}
			if(input1.length()!=0){
				allWord = wu.allWords(input1);
				for(int a = 0;a<allWord.size();a++){
					anagrams.add(allWord.get(a));
					//System.out.println(anagrams.get(0)+" aaaaaa "+input1);
					String str = input1;	
					for(int i = 0;i<anagrams.get(0).length();i++){
						newPhrase = removeChar(newPhrase, anagrams.get(counter).charAt(i));
					}
					counter++;
					//System.out.println(anagrams.get()+" vbbbbbv "+newPhrase);
					//System.out.println(input1+"\t"+allWord.get(a)+"\t"+newPhrase);
					findAnagrams(newPhrase);
					anagrams.remove(0);
					counter--;
				}
			}
		}
	}
	/**
	 * Removes a specified character from a String
	 * @param	str		The input string
	 * @param	ch		The input char
	 * @return			The modified string
	 */
	public String removeChar(String str, char ch){
		String ret = "";
		boolean found = false;
        for(int i = 0; i<str.length();i++){
                if(str.charAt(i)!=ch||found)
                    ret += str.charAt(i);
                else 
					found = true;
        }
        return ret;
    }
}
