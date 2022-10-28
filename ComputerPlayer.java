// *********************************************************************************************************************
// *   Program: ComputerPlayer Class                                                                                   *
// *   Author: Stanley Vossler                                                                                         *
// *   FSU Mail name: smv19c                                                                                           *
// *   COP 3252 - Summer 2022                                                                                          *
// *   Project Number: 3                                                                                               *
// *   Due Date: Thursday 6/9/2022                                                                                     *
// *   Platform: java / UNIX OS                                                                                        *
// *                                                                                                                   *
// *   SUMMARY                                                                                                         *
// *   This class contains all of the member functions for the ComputerPlayer object, derived from the Player class    *
// *   The computer is designed to make decisions based on a priority (1) take a win if available, (2) block an enemy  * 
// *   from winning, (3) take the center tile if available, (4) finally it will just randomly pick a tile if current   *
// *   position doesn't fit the previous criteria.                                                                     *
// *                                                                                                                   *
// *********************************************************************************************************************

import java.util.Random;

public class ComputerPlayer extends Player
{

	ComputerPlayer()
	{
		type = "Computer";
		playerNum = counter;
		IncrementPlayerNum();
	}

	public int[] TakeTurn(char game[][], int flag)
	{
		Random randomNumber = new Random();
		int input;
		boolean correctInput;
		int alreadyMovedFlag = 1;

		inputPair = WinOrBlock(game, flag, 1); // checks for an available win 
		if (inputPair[1] != 0)
		{		
                        int numFromIndex = IndexToNumber(inputPair);
                        for (int i = 0; i < tilesPlayed.length; i++)
                        {
                                        if ( numFromIndex == tilesPlayed[i] )
                                        {
                                                alreadyMovedFlag = 0;
                                        }  
                        }
                        
                        switch(alreadyMovedFlag)
                        {
                                case (0):
                                {
                                        break;
                                }
                                case (1):
                                {
                                        Set(numTiles, numFromIndex);
                                        System.out.println();
                                        System.out.println("Player " + this.playerNum + " (computer) strategically chooses " + numFromIndex + "\n");
                                        IncrementNumTiles();        
                                        return inputPair;   
                                }
                        }
		}
	
		inputPair[0] =0;
                inputPair[1] =0;     
		alreadyMovedFlag = 1;
		inputPair = WinOrBlock(game, flag, 2); // checks for a space to block
		if (inputPair[1] != 0)
		{
			int numFromIndex = IndexToNumber(inputPair);
			for (int i = 0; i < tilesPlayed.length; i++)
                        {
                                        if ( numFromIndex == tilesPlayed[i] )
                                        {
                                                alreadyMovedFlag = 0;
                                        }  
                        }
			
			switch(alreadyMovedFlag)
			{
				case (0):
				{
					break;
				}
				case (1):
				{
					Set(numTiles, numFromIndex);
                                        System.out.println();
                                        System.out.println("Player " + this.playerNum + " (computer) blocks with selection " + numFromIndex + "\n");
                                        IncrementNumTiles();        
                                        return inputPair;   
				}
			}
		}

		inputPair[0] =0;
		inputPair[1] =0;		
		inputPair = TakeCenter(game, flag);
		if (inputPair[1] != 0)
		{
			int foo = IndexToNumber(inputPair);
                        Set(numTiles, foo);
                        System.out.println();
                        System.out.println("Player " + this.playerNum + " (computer) chooses " + foo + "\n");
                        IncrementNumTiles();
                        return inputPair;		
		}
		else		
			do
			{
				correctInput = true;
				input = randomNumber.nextInt((9-1)+1)+1;
				for (int i = 0; i < tilesPlayed.length; i++)
                        	{
                                	if ( input == tilesPlayed[i] )
                                	{
                                        	correctInput = false;
                                	}
                        	}

                	} while (!correctInput);
		
		Set(numTiles, input);
		IncrementNumTiles();
 		System.out.println();
		System.out.print("Player " + this.playerNum + " (computer) randomly chooses "+ input + "\n"); 
		System.out.println();
		FindIndexes(input);
		return inputPair;
	}

	public int[] TakeCenter(char game[][], int flag)
	{
		char playerToken;
		char enemyToken;		
		if (flag == 2)
                {
 	               playerToken = 'X';
                       enemyToken = 'O';
                }
                else
                {
  	        	playerToken = 'O';
               		enemyToken = 'X';
                }
		if (game[2][5] == ' ' && game[2][5] != enemyToken)
		{
			inputPair[0] = 2;
			inputPair[1] = 5;
		}
		return inputPair;
	}

	public int[] WinOrBlock(char game[][], int flag, int option)
	{
		
		char playerToken;
		char enemyToken;
		int[] missingTile = new int[2];
		int tokenCounter = 0;
		boolean tileFound = false;
		boolean findWin = false;
		missingTile[0] = 0;
		missingTile[1] = 0;
	
		if (flag == 2)
		{
			playerToken = 'X';
			enemyToken = 'O';
		}
		else
		{
			playerToken = 'O';
			enemyToken = 'X';
		}

		for (int r = 0; r < 5; r+=2) // HORIZONTALLY
		{
			for (int c = 1; c < 10; c+=4)
			{
				if ( game[r][c] == ' ')
				{
					missingTile[0] = r;
					missingTile[1] = c;
					tileFound = true;
					if (c == 1)
					{
						continue;
					}
				}

				if (option == 1) // win
				{
					if ( game[r][c] == playerToken )
					{
						tokenCounter++;
					}
					if ( game[r][c] == enemyToken )
					{
						tokenCounter = 0;
						tileFound = false;
						continue;
					}
				}
				else //block
				{
					if ( game[r][c] == enemyToken )
                                	{
                                        	tokenCounter++;
                                	}

                                	if ( game[r][c] == playerToken )
                                	{
						tokenCounter = 0;
                                        	tileFound = false; 
                                        	continue;             
                                	}
				}

				if ( tokenCounter == 2 && tileFound == true )
				{
					tokenCounter = 0;
					return missingTile;
				}
			}
			tokenCounter =0;
			tileFound = false;
		}	
		tokenCounter = 0;
		tileFound = false;
	
		for (int c = 1; c < 10; c+=4) // vertically
                {
                        for (int r = 0; r < 5; r+=2)
                        {
                                if ( game[r][c] == ' ')
                                {
                                        missingTile[0] = r;
                                        missingTile[1] = c;
                                        tileFound = true;
                                        if (r == 0)
                                        {
                                                continue;
                                        }
                                }

                                if (option == 1) // win
                                {
                                        if ( game[r][c] == playerToken )
                                        {
                                                tokenCounter++;
                                        }
                                        if ( game[r][c] == enemyToken )
                                        {
                                                tokenCounter = 0;
                                                tileFound = false;
                                                continue;
                                        }
                                }
                                else //block
                                {
                                        if ( game[r][c] == enemyToken )
                                        {
                                                tokenCounter++;
                                        }

                                        if ( game[r][c] == playerToken )
                                        {
                                                tokenCounter = 0;
                                                tileFound = false; 
                                                continue;             
                                        }
                                }
                                if ( tokenCounter == 2 && tileFound == true )
                                {
                                        tokenCounter = 0;
                                        return missingTile;
                                }
                        }
			tileFound = false;
                        tokenCounter =0;
			
			if (option == 1)
			{
				enemyToken = playerToken;
			} 
				
			if ( (game[0][1] == enemyToken && game[4][9] == enemyToken) || (game[4][1] == enemyToken && game[0][9] == enemyToken ) )
			{
				missingTile[0] = 2;
				missingTile[1] = 5;
				return missingTile;
			}

	
			if (game[0][1] == enemyToken && game[2][5] == enemyToken)
			{
				missingTile[0] = 4;
				missingTile[1] = 9;
				return missingTile;
			}	

			if (game[2][5] == enemyToken && game[4][9] == enemyToken)
			{
				missingTile[0] = 0;
				missingTile[1] = 1;
				return missingTile;
			}

			if (game[4][1] == enemyToken && game[2][5] == enemyToken)
			{
				missingTile[0] = 0;
				missingTile[1] = 9;
				return missingTile;
			}

			if(game[2][5] == enemyToken && game[0][9] == enemyToken)
			{
				missingTile[0] = 4;
				missingTile[1] = 1;
				return missingTile;
			}
                }       
	missingTile[0] = 0;
	missingTile[1] = 0;
	return missingTile;
	}
}
