import java.util.*;
import java.io.*;

/*Description:This is Hangman class. The player must find the word chosen by the computer secretly.
 *Player chooses one letter per time and the place it occurs is made from blank to the letter.
 * Author: Aurel Hoxha
 * Date: June 16, 2017
 */
public class Hangman 
{
	//Properties of the class
	private static final int maxAllowedIncorrectTries = 6;
	private StringBuffer secretWord;
	private StringBuffer allLetters;
	private StringBuffer usedLetters;
	private int numberOfIncorrectTries;
	private StringBuffer knownSoFar;
	private FileInputStream fileStream;
	private BufferedReader bReader;
	private ArrayList<String> words;
	//Constructor
	public Hangman() throws IOException
	{
		fileStream = new FileInputStream("dictionary.txt");
		bReader = new BufferedReader(new InputStreamReader(fileStream));
		
		words = new ArrayList<>();
		while((bReader.readLine()) != null)
			words.add(bReader.readLine());
		
		//Create a random number
		Random rand = new Random();

		//Choose a random from the arrayList
		int randomIndex = rand.nextInt(words.size()-1);
		
		//Initialize the instances
		secretWord 			   = new StringBuffer(words.get(randomIndex));
		knownSoFar 			   = new StringBuffer("");
		allLetters   		   = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
		usedLetters 			   = new StringBuffer("");
		numberOfIncorrectTries = 0;
		
		//Make the letters of knownSoFar '*'
		for(int i = 0; i < secretWord.length(); i++)
			knownSoFar.append("*");
	}
	
	//Methods
	
	//Return allLeter that user can enter to check(alphabet)
	public String getAllLeters()
	{
		return allLetters.toString();
	}
	
	//Return used the letter to let the user known what letter it has used
	public String getUsedLetters()
	{
		return usedLetters.toString();
	}
	
	//Return number of incorrectTries
	public int getNumberOfIncorrectTries()
	{
		return numberOfIncorrectTries;
	}
	
	//Return maximum number of allowed incorrect tries
	public int getMaxAllowedIncorrectTries()
	{
		return maxAllowedIncorrectTries;
	}
	
	//Return the current state of the word we have found
	public String getKnownSoFar()
	{
		return knownSoFar.toString();
	}
	
	public String getSecretWord()
	{
		return secretWord.toString();
	}
	//Return whether the game is Over
	public boolean isGameOver()
	{
		if(knownSoFar.toString().equals(secretWord.toString()) || numberOfIncorrectTries >= maxAllowedIncorrectTries)
			return true;
		return false;
	}
	
	//Return whether the user has lost or won the game
	public boolean hasLost()
	{
		if( (numberOfIncorrectTries >= maxAllowedIncorrectTries) && (!knownSoFar.toString().equals(secretWord.toString())))
			return true;
		return false;
	}
	
	//Try one letter and make the necessary changes
	public int tryThis(char letter)
	{
		//First make the character lowerCase
		char newLetter = Character.toLowerCase(letter);
		boolean foundUsed = false;
		boolean foundAtLetters = false;
		for(int k = 0; k < allLetters.length();k++)
		{
			if(allLetters.charAt(k) == newLetter)
				foundAtLetters = true;
		}
		
		if(!foundAtLetters)
			return -2;
			
		for(int j = 0; j < usedLetters.length();j++ )
		{
			if(usedLetters.charAt(j) == newLetter)
			{
				foundUsed = true;
			}

		}
		if(foundUsed)
			return -1;

			usedLetters.append(newLetter);
			int letterCount = 0;
			for(int i = 0;i<secretWord.length();i++)
			{
				if (secretWord.charAt(i) == newLetter)
			      {
			        //demonstrates the known letters
			        knownSoFar.setCharAt(i, newLetter);
			        letterCount++;
			      }
			}
			if(letterCount == 0)
				numberOfIncorrectTries++;
			return letterCount;
	}
	
	public void showMainMenu()
	{
		System.out.println("You are looking for: " + getKnownSoFar());
		System.out.println("Tries Left: " + (getMaxAllowedIncorrectTries() -  getNumberOfIncorrectTries()));
		System.out.println("Letter that can be used: " + getAllLeters());
		System.out.println("Letter used so far: " + getUsedLetters());
		System.out.print("Enter the letter you think is hidden: ");
	}
}
