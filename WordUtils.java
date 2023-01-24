import java.util.Scanner;
/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author Nathan Bao
 *	@since 10/20
 */
 
public class WordUtils
{
	private String[] words;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "wordList.txt";
	
	/* Constructor */
	public WordUtils() { }
	
	/**
	 * Checks whether the given word is in the dictionary
	 * @param input			The input word
	 * @return 				Whether the word is in the dictionary or not
	 */
	public boolean checkWord(String input){
		for(int i = 0;i<90934;i++){
			if(input.equals(words[i]))
				return true;
		}
		return false;
	}
	
	/**	Load all of the dictionary from a file into words array. */
	public void loadWords () {
		Scanner sc = new Scanner(System.in);
		int arrayLength = 0;
		sc = FileUtils.openToRead(WORD_FILE);
		while(sc.hasNextLine()&&arrayLength<90934){
			arrayLength++;
		//	System.out.println(arrayLength);
		}
		words = new String[arrayLength];
		arrayLength = 0;
		FileUtils.openToRead(WORD_FILE);
		while(sc.hasNextLine()&&arrayLength<90934){
			words[arrayLength] = sc.next();
		//	System.out.println(words[arrayLength]);
			arrayLength++;
		}
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{		
		int arrLength = (int)(Math.pow(letters.length(), 3));
		String returnString [] = new String[arrLength];
		int numLetters [] = new int[26];
		int numLettersInput [] = new int[26];
		int passedCheck = 0;
		int increment = 0;
		letters = letters.toLowerCase();
		for(int i = 0;i<letters.length();i++){
			int charValue = (int)(letters.charAt(i));
			if(charValue>96)
				numLettersInput[charValue-97]++;
		}
		for(int i = 0;i<words.length;i++){
			int charValue = 0;
			String allLower = words[i].toLowerCase();
			for(int a = 0;a<words[i].length();a++){
				charValue = (int)(allLower.charAt(a));
				//System.out.println(words[i]);
				if(charValue>96)
					numLetters[charValue-97]++;
			}
			for(int b = 0;b<26;b++){
				if(numLettersInput[b]<numLetters[b]){
					
				}
				else{
					passedCheck++;
				}
			}
			if(passedCheck>=26){
				returnString[increment] = words[i];
				increment++;
			}
			passedCheck = 0;
			for(int c = 0;c<26;c++){
				numLetters[c] = 0;
			}
			//System.out.println(wordsFound);
		}
		return returnString;
	}
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) {
		int numSpaces = 0;
		for(int i = 1;i<=wordList.length;i++){
			if(!(wordList[i-1]==null)){
				System.out.print(wordList[i-1]);
				numSpaces = 16-wordList[i-1].length();
				for(int a = 0;a<numSpaces;a++){
					System.out.print(" ");
				}
				if(i%5==0)
					System.out.println("");
			}
		}
	}
	
	/**	Finds the highest scoring word according to a score table.
	 *
	 *  @param word  		An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return   			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		boolean doDouble = false;
		int score = 0;
		int score1 = 0;
		String best = wordList[0];
		score = getScore(wordList[0], scoreTable, 1);
		score1 = getScore(wordList[1], scoreTable, 1);
		if(score>score1)
			best = wordList[0];
		else
			best = wordList[1];
		for(int i = 2;i<wordList.length;i++){
			if(wordList[i]!=null){
				score = getScore(best, scoreTable, 1);
				score1 = getScore(wordList[i], scoreTable, 1);
				if(score>score1){
				}
				else
					best = wordList[i];
			}
		}
		return best;
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable, int printDouble)
	{
		int printedTimes = printDouble;
		int score = 0;
		boolean doDouble = false;
		if(word!=null){
			for(int a = 0;a<word.length();a++){
				if((int)(word.charAt(a))>96){
					score += scoreTable[(int)(word.charAt(a))-97];
					if(a!=word.length()-1&&(word.charAt(a)==word.charAt(a+1))&&doDouble == false){
						doDouble = true; 
					}
				}
			}
			if(doDouble){
				score *= 2;
				if(printedTimes == 0){
					System.out.println("BONUS WORD SCORE!!!");
					printedTimes++;
				}
			}
		}
		return score;
	}
	
	private boolean wordMatch(String word, String letters) {
		// if the word is longer than letters return false
		if (word.length() > letters.length()) return false;
		
		// while there are still characters in word, check each word character
		// with letters
		while (word.length() > 0) {
			// using the first character in word, find the character's index inside letters
			// and ignore the case
			int index = letters.toLowerCase().indexOf(Character.toLowerCase(word.charAt(0)));
			// if the word character is not in letters, then return false
			if (index < 0) return false;
			
			// remove character from word and letters
			word = word.substring(1);
			letters = letters.substring(0, index) + letters.substring(index + 1);
		}
		// all word letters were found in letters
		return true;
	}
	
	/**
	 *	finds all words that match some or all of a group of alphabetic characters
	 *	Precondition: letters can only contain alphabetic characters a-z and A-Z
	 *	@param letters		group of alphabetic characters
	 *	@return				an ArrayList of all the words that match some or all
	 *						of the characters in letters
	 */
	public ArrayList<String> allWords(String letters) {
		ArrayList<String> wordsFound = new ArrayList<String>();
		// check each word in the database with the letters
		for (String word: words)
			if (wordMatch(word, letters))
				wordsFound.add(word);
		return wordsFound;
	}
	
	/**
	 *	Sort the words in the database
	 */
	public void sortWords() {
		SortMethods sm = new SortMethods();
		sm.mergeSort(words);
	}

	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	public void run() {
		loadWords();
		String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
						//   a b c d e f g h i j k l m n o p q  r s t u v w x y z
		String best = bestWord(word,scoreTable);
		System.out.println("\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable, 1) + "\n");
	}
}
