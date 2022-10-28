// *********************************************************************************************************************
// *   SUMMARY                                                                                                         *
// *   This class holds the member functions that intiliaze the game and presents a visual board for the players to    *
// *   interact with. In the main function it simply checks the parameters provided in command line then intializes    *
// *   the correct objects (Player type) for the game. StartGame() is passed the two player types and then doesn't end *
// *   until either a player wins or there is a draw. After every move, the move is passed into CheckWin() which makes *
// *   sure the game ends if there is a winner. Finally the PrintBoard() function is used every time a player takes    *
// *   their turn.												       *
// *                                                                                                                   *
// *********************************************************************************************************************


public class TicTacToe
{
	public static void main(String[] args)
	{
		Player player1 = new Player();
		Player player2 = new Player();

		if ( args.length > 0 && args[0].equals("-c") )
		{
			if ( args.length  > 0 )
			{ 	
				if ( args.length > 1 )
				{
					if ( args[1].equals("1") )
					{
						player1 = new ComputerPlayer();
						player2 = new HumanPlayer();
					}
					else if ( args[1].equals("2") )
					{
						player1 = new HumanPlayer();
						player2 = new ComputerPlayer();
					}
				}
				else
				{
					player1 = new ComputerPlayer();
					player2 = new ComputerPlayer();
				}
			}			
		}
		
		else
		{
			player1 = new HumanPlayer();
                        player2 = new HumanPlayer();
		}

		//player1.printType();		
		//player2.printType();

		StartGame(player1, player2);	
	}

	private static void StartGame(Player p1, Player p2)
	{
		char[][] gameBoard = new char[5][11];
		char[][] referenceBoard = new char[5][11];
		int x = '1';
		char y;
		boolean keepPlaying = true;
		int playerFlag = 1; 
		int numTurns = 1;

		for (int r = 0; r < 5; r++)
		{
			for (int c = 0; c < 11; c++)
			{
				if ( r % 2 == 0 ) 
				{
					if (c == 3 || c == 7)
					{
						gameBoard[r][c] = '|';
						referenceBoard[r][c] = '|';
					}
					else
					{
						gameBoard[r][c] = ' ';
						if ( c == 1 || c == 5 || c == 9 )
						{
							y = (char)x;
							referenceBoard[r][c] = y;
							x++;
						}
						else
						{
							referenceBoard[r][c] = ' ';
						}
					}
				}
				if ( r == 1 || r == 3)
				{
					gameBoard[r][c] = '-';
					referenceBoard[r][c] = '-';
				}
			}
		}
		
		do
		{		
			PrintBoard(gameBoard, referenceBoard);
			if (playerFlag == 1)
			{
				playerFlag = 2;
				p1.TakeTurn(gameBoard, playerFlag);
				gameBoard[p1.inputPair[0]][p1.inputPair[1]] = 'X';
			}
			else
			{
				playerFlag = 1;
				p2.TakeTurn(gameBoard, playerFlag);				
				gameBoard[p2.inputPair[0]][p2.inputPair[1]] = 'O';
			}
			if (numTurns == 9 && !CheckWin(gameBoard, playerFlag))
			{
				PrintBoard(gameBoard, referenceBoard);
				System.out.println("This game is a draw!");
				keepPlaying = false;
			}

			if (CheckWin(gameBoard, playerFlag))
			{
				keepPlaying = false;
				if (playerFlag == 1)
                		{	
                        		playerFlag = 2;
                		}
                		else
                		{
                        		playerFlag = 1;
                		}
				PrintBoard(gameBoard, referenceBoard);
				//System.out.println();
                		System.out.print("Congratulations player "+playerFlag+" you won!\n");
			}
			numTurns++;
		} 
		while (keepPlaying == true);
	}	

	public static boolean CheckWin(char[][] game, int flag)
	{
		int winCounter = 0;
		int direction = 0;
		char foo;
		boolean winFound = false;
		if (flag == 2)
		{
			foo = 'X';
		}
		else
		{
			foo = 'O';
		}

		do
		{
			for ( int c = 1; c < 10; c+=4)
			{
				if (game[direction][c] == foo)
				{
					winCounter++;
				}
				if ( winCounter == 3 )
				{
					winFound = true;
				}
			
			}
			winCounter = 0;
			direction += 2;
		} while (direction < 5);
		
		direction = 1;
		
		do
		{
			for ( int r = 0; r < 5; r+=2 )
			{
				if (game[r][direction] == foo)
				{
					winCounter++; 	 		
				}
				if (winCounter == 3)
				{
					winFound = true;
				}
			}
			winCounter = 0;
			direction += 4;
		} while (direction < 10);
		direction = 0;
		winCounter = 0;
		for ( int c = 1; c < 10; c+=4 )
		{
			if (game[direction][c] == foo)
			{
				winCounter++;
			}
			if (winCounter == 3)
			{
				winFound = true;
			}
			direction += 2;
		}
		direction = 4;
		winCounter = 0;
		for (int c = 1; c < 10; c+=4)
		{
			if (game[direction][c] == foo)
			{
				winCounter++;
			}
			if (winCounter == 3)
                        {
                                winFound = true;
                        }
			direction -= 2;
		}

		if (winFound == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static void PrintBoard(char[][] game, char[][] ref)
	{
		System.out.println("Game Board:\t\t\tPositions:\n");
		for (int r = 0; r < 5; r++)
                {
                        for (int c = 0; c < 11; c++)
                        {
                                System.out.print(game[r][c]);
                        }
                        System.out.print("\t\t\t");
                        for (int c = 0; c < 11; c++)
                        {
                                System.out.print(ref[r][c]);
                        }
                        System.out.println();
                }
		System.out.println();
	}	
}
