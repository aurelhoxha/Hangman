import java.io.IOException;
import java.util.*;
/*Description: This Class Test the Hangman Game using different inputs and strategies
 *Author: Aurel Hoxha
 *Date: June 16, 2017
 */

public class HangmanTest 
{
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner( System.in);
		
		//Variable to be used during test
		String userInput;
		boolean playGame;
		int tryOutput;
		
		//Initialize playGame to true
		playGame = true;
		
		//Welcome the user
		System.out.println("************************************");
		System.out.println("|*** WELCOME TO MY HANGMAN GAME ***|");
		System.out.println("************************************");
		System.out.println();
		
		//Play the Game
		do
		{
			Hangman game = new Hangman();
			while(!game.isGameOver())
			{
				game.showMainMenu();
				userInput = scan.next();
				System.out.println();
				tryOutput = game.tryThis(userInput.charAt(0));
				if(tryOutput == -1 )
					System.out.println("This letter is used. Try another one.\n");
				while(tryOutput == -2)
				{
					System.out.print("Enter a letter from the available ones:\n");
					game.showMainMenu();
					userInput = scan.next();
					System.out.println();
					tryOutput = game.tryThis(userInput.charAt(0));	
				}
			}
			if(game.hasLost())
			{
				System.out.println("The secret word was: " + game.getSecretWord());
				System.out.println("Sorry Dude. No Luck this time. Try Again if you want! ");
			}
			else
			{
				System.out.println("Congratulation the word was " + game.getSecretWord() + ". You found it!");
			}
			System.out.println("Do you want to quit the game:(Y if you want to quit);");
			System.out.print("Any other input will start a new game: ");
			userInput = scan.next();
			if(userInput.equals("Y") || userInput.equals("y"))
				playGame = false;
			else
				playGame = true;
			System.out.println();
			
		}while(playGame == true);
		System.out.println("Thank You Playing My HangMan Game. See you in the future.");
	}

}
