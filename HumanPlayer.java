// *********************************************************************************************************************
// *   Program: HumanPlayer Class                                                                                      *      
// *   Author: Stanley Vossler                                                                                         *
// *   FSU Mail name: smv19c                                                                                           *
// *   COP 3252 - Summer 2022                                                                                          *
// *   Project Number: 3                                                                                               *
// *   Due Date: Thursday 6/9/2022                                                                                     * 
// *   Platform: java / UNIX OS                                                                                        *
// *                                                                                                                   *
// *   SUMMARY                                                                                                         *
// *   This class contains the member functions for the HumanPlayer object derived from the Player class. Quite conven-*
// *   iently, this class only contains two functions, its constructor for its object, and the TakeTurn() function. All*
// *   the function has to do is repeatedly take inputs from human users and check if those spots have already been mo-*
// *   there before.                                                                                                   *
// * *******************************************************************************************************************

import java.util.Scanner;

public class HumanPlayer extends Player
{
	HumanPlayer()
	{
		type = "Human";
		playerNum = counter;
		IncrementPlayerNum();
	}

	public int[] TakeTurn(char game[][], int flag)
	{
		Scanner scn = new Scanner(System.in);
		int input;
		boolean correctInput;
		System.out.println();
		System.out.print("Player ");
		System.out.print(this.playerNum + ", please enter a move (1-9): ");
		do
		{
			correctInput = true;	
			input = scn.nextInt();
			for (int i = 0; i < tilesPlayed.length; i++)
			{
				//System.out.println(tilesPlayed[i]);
				if ( input == tilesPlayed[i] )
				{	
					correctInput = false;
					System.out.println("A player has already moved here, please enter another move.");
				}
			} 
	
		} while (!correctInput);		
		Set(numTiles, input);
		IncrementNumTiles();
		System.out.println();
                FindIndexes(input);
                return inputPair;
	}
}

