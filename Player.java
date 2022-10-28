// *********************************************************************************************************************
// *   Program: Player Class                                                                                           *      
// *   Author: Stanley Vossler                                                                                         *
// *   FSU Mail name: smv19c                                                                                           *
// *   COP 3252 - Summer 2022                                                                                          *
// *   Project Number: 3                                                                                               *
// *   Due Date: Thursday 6/9/2022                                                                                     * 
// *   Platform: java / UNIX OS                                                                                        *
// *                                                                                                                   *
// *   SUMMARY                                                                                                         *
// *   This class contains the member functions for the Player object. This class utilizes inheritance as its children *
// *   HumanPlayer and ComputerPlayer have access to its functions.                                                    *
// *                                                                                                                   *
// *********************************************************************************************************************


public class Player 
{
	String type;
	int[] inputPair = new int[2];
	static int[] tilesPlayed = new int[10];
	static int counter = 1;	
	static int numTiles = 0;
	int playerNum;

	public Player()
	{
	}

	public void printType()
	{
		System.out.println(this.type + " Player " + this.playerNum);
	}			

	public static void IncrementPlayerNum()
	{
		counter++;
	}
	
	public static void IncrementNumTiles()
	{
		++numTiles;
	}

	public static void Set(int index, int foo)
	{
		tilesPlayed[index] = foo;
	}

	public int[] TakeTurn(char game[][], int flag)
	{
		System.out.println("This should never be accessed");
		return inputPair;
	}

	public int[] FindIndexes(int num)
	{
		switch(num)
		{
			case (1):
			{
				inputPair[0] = 0;
				inputPair[1] = 1;
				break;
			}
			case (2):
			{
				inputPair[0] = 0;
				inputPair[1] = 5;
				break;
			}
			case (3):
			{
                                inputPair[0] = 0;
                                inputPair[1] = 9;
				break;
			}
			case (4):
			{
                                inputPair[0] = 2;
                                inputPair[1] = 1;
				break;
			}
			case (5):
			{
                                inputPair[0] = 2;
                                inputPair[1] = 5;
				break;
			}
			case (6):
			{
                                inputPair[0] = 2;
                                inputPair[1] = 9;
				break;
			}
			case (7):
			{
                                inputPair[0] = 4;
                                inputPair[1] = 1;
				break;
			}
			case (8):
			{
                                inputPair[0] = 4;
                                inputPair[1] = 5;
				break;
			}
			case (9):
			{
                                inputPair[0] = 4;
                                inputPair[1] = 9;
				break;
			}
		default:
			System.out.print("This should never be accessed");
		}
		return inputPair;
	}
	
	public int IndexToNumber(int[] input)
        {
                int num = 0;
                switch(input[0] + "|" + input[1])
                {
                        case "0|1":
                        {
                                num = 1;
                                break;
                        }
                        case "0|5":
                        {
                                num = 2;
                                break;

                        }
                        case "0|9":
                        {
                                num = 3;
                                break;

                        }
                        case "2|1":
                        {
                                num = 4;
                                break;

                        }
                        case "2|5":
                        {
                                num = 5;
                                break;

                        }
                        case "2|9":
                        {
                                num = 6;
                                break;
        
                        }
                        case "4|1":
                        {
                                num = 7;
                                break;
                
                        }
                        case "4|5":
                        {
                                num = 8;
                                break;
                
                        }
                        case "4|9":
                        {
                                num = 9;
                                break;                  
                        }
                }
                return num;
        }
}

