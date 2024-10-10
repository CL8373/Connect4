//******
//Connect4.java
//
// Purpose: Simulate a connect4 game against the computer
//R0: Dec-18-2023 Chris Lin Orginial Version
//R1: Jan-3-2023 Chris Lin. Changed the computer to a second player to play against player 1
//********
import java.util.Scanner;
public class Connect4
{
   final static int LENGTH = 8;
   final static int HEIGHT = 8;
   final static int BOTTOM = LENGTH - 1;
   static char[][] BOARD = new char[LENGTH][HEIGHT];
   static Scanner scan = new Scanner(System.in);
   public static void main(String[] args)
   {
      System.out.println("To play, Enter a number between 0-7 to drop on your turn!");
      Board();
      DisplayBoard();
      boolean play = true;
      while(play)
      {
         XDrop();
         DisplayBoard();
         if(XHorizontalFourCheck() || XVerticalFourCheck() || XDiagonalFrontFourCheck() || XDiagonalBackFourCheck())
         {
            play = false;
            break;
         }
         ODrop();
         DisplayBoard();
         if(OHorizontalFourCheck() || OVerticalFourCheck() || ODiagonalFrontFourCheck() || ODiagonalBackFourCheck())
         {
            play = false;
            break;
         }
      }
   }
   
   public static void Board() // creates the board
   {
      for(int i = 0; i < LENGTH; i++)
      {
         for(int j = 0; j < HEIGHT; j++)
         {
            BOARD[i][j] = '*';
         }
      }
   }
   
   public static void DisplayBoard() // displays the board each time someone drops
   {
      for(int i = 0; i < LENGTH; i++)
      {
         for(int j = 0; j < HEIGHT; j++)
         {
            System.out.print(BOARD[i][j]);
         }
         System.out.print("\n");
      }
   }
   
   public static void XDrop() // drops an X on the input column
   {
      System.out.print("Player 1's turn:");
      int col = scan.nextInt();
      boolean drop = false;
      int rowcount = 1;
      while(!drop)
      {
         if(col > LENGTH || col < 0)
         {
            System.out.print("Invalid column");
            drop = true;
         }
         if(BOARD[BOTTOM][col] == '*')
         {
            BOARD[BOTTOM][col] = 'X';
            drop = true;
            break;
         }
         else if(BOARD[BOTTOM][col] == 'X' || BOARD[BOTTOM][col] == 'O')
         {
            if(BOARD[BOTTOM - rowcount][col] == '*')
            {
               BOARD[BOTTOM - rowcount][col] = 'X';
               drop = true;
               break;
            }
         }
         rowcount++;
         if(rowcount == LENGTH)
         {
            System.out.print("Column full\n");
            drop = true;
         }
      }    
   }
   
   public static void ODrop() // Drops an O on the input column
   {
      System.out.print("Player 2's turn:");
      int col = scan.nextInt(); // Changed to get the 2nd player's input for the connect 4 game
      boolean drop = false;
      int rowcount = 1;
      while(!drop)
      {
         if(col > LENGTH || col < 0)
         {
            System.out.print("Invalid column");
            drop = true;
         }
         {
            BOARD[BOTTOM][col] = 'O';
            drop = true;
         }
         else if(BOARD[BOTTOM][col] == 'X' || BOARD[BOTTOM][col] == 'O')
         {
            if(BOARD[BOTTOM - rowcount][col] == '*')
            {
               BOARD[BOTTOM - rowcount][col] = 'O';
               drop = true;
            }
         }
         rowcount++;
         if(rowcount == LENGTH)
         {
            System.out.print("Column full\n");
            drop = true;
         }
      }    

   }

   public static boolean XHorizontalFourCheck() // checks X horizontally for any fours to win
   {
      boolean four = false;
      int Xcount = 0;
      while(!four)
      {
         for(int i = 0; i < LENGTH; i++)
         {
            for(int k = 0; k < HEIGHT; k++)
            {
               if(BOARD[i][k] == 'X')
               {
                  Xcount++;
               }
               else
               {
                  Xcount = 0;
               }
               if(Xcount == 4)
               {
                  System.out.print("Player 1 Wins");
                  four = true;
               }
            }
         }
         break;
      }
      return four;
   }
   
   public static boolean XVerticalFourCheck() // checks X vertically for any fours to win
   {
      boolean four = false;
      int Xcount = 0;
      while(!four)
      {
         for(int i = 0; i < HEIGHT; i++)
         {
            for(int k = 0; k < LENGTH; k++)
            {
               if(BOARD[k][i] == 'X')
               {
                  Xcount++;
               }
               else
               {
                  Xcount = 0;
               }
               if(Xcount == 4)
               {
                  System.out.print("Player 1 Wins");
                  four = true;
               }
            }
         }
         break;
      }
      return four;
   }
   
   public static boolean XDiagonalFrontFourCheck() // checks X diagonally in front facing direction for any fours to win
   {
      boolean four = false;
      int Xcount = 0;
      int row = 1;
      int column = 1;
      while(!four)
      {
         for(int i = 0; i < LENGTH; i++)
         {
            for(int k = 0; k < HEIGHT; k++)
            {
               if(BOARD[i][k] == 'X') // When X is found, The loop below will find an X at one column and one row over
               {
                  Xcount++;
                  boolean dcheck = true;
                  while(dcheck)
                  {
                     if(column + i <= LENGTH - 1 && row + k <= HEIGHT - 1)
                     {
                        if(BOARD[i + column][k + row] == 'X'){
                           Xcount++;
                        }
                     }
                     row++;
                     column++;
                     if(column == LENGTH - 1 || row == HEIGHT - 1) // makes sure it does not go out of bounds
                     {
                        dcheck = false;
                        break;
                     }
                     if(Xcount > 4)
                     {                              
                        System.out.print("Player 1 Wins");
                        dcheck = false;
                        four = true;
                        break;
                     }
                  }
                     
               }
               if(Xcount > 4)
               {
                  four = true;
                  break;
               }
               Xcount = 0;
               row = 0;
               column = 0;
            }
         }
         break;
      }
      return four;
   }
   
   public static boolean XDiagonalBackFourCheck() // checks X diagonally in backward facing direction for any fours to win
   {
      boolean four = false;
      int Xcount = 0;
      int row = 1;
      int column = 1;
      while(!four)
      {
         for(int i = 0; i < LENGTH; i++)
         {
            for(int k = 0; k < HEIGHT; k++)
            {
               if(BOARD[i][k] == 'X') // When X is found, The loop below will find an X at one column and one row before
               {
                  Xcount++;
                  boolean dcheck = true;
                  while(dcheck)
                  {
                     if(i - column >= 0 && k - row >= 0)
                     {
                        if(BOARD[i - column][k - row] == 'X'){
                           Xcount++;
                        }
                     }
                     row++;
                     column++;
                     if(column == 0 || row == HEIGHT - 1) // makes sure no out of bounds occur
                     {
                        dcheck = false;
                        break;
                     }
                     if(Xcount > 4)
                     {                              
                        System.out.print("Player 1 Wins");
                        dcheck = false;
                        four = true;
                        break;
                     }
                  }
                     
               }
               if(Xcount > 4)
               {
                  four = true;
                  break;
               }
               Xcount = 0;
               row = 0;
               column = 0;
            }
         }
         break;
      }
      return four;
   }
   
   public static boolean OHorizontalFourCheck() // Checks if computer wins horizontally
   {
      boolean four = false;
      int Ocount = 0;
      while(!four)
      {
         for(int i = 0; i < LENGTH; i++)
         {
            for(int k = 0; k < HEIGHT; k++)
            {
               if(BOARD[i][k] == 'O')
               {
                  Ocount++;
               }
               else
               {
                  Ocount = 0;
               }
               if(Ocount == 4)
               {
                  System.out.print("Player 2 Wins");
                  four = true;
               }
            }
         }
         break;
      }
      return four;
   }
   
   public static boolean OVerticalFourCheck() // Check if computer wins vertically
   {
      boolean four = false;
      int Ocount = 0;
      while(!four)
      {
         for(int i = 0; i < HEIGHT; i++)
         {
            for(int k = 0; k < LENGTH; k++)
            {
               if(BOARD[k][i] == 'O')
               {
                  Ocount++;
               }
               else
               {
                  Ocount = 0;
               }
               if(Ocount == 4)
               {
                  System.out.print("Computer Wins");
                  four = true;
               }
            }
         }
         break;
      }
      return four;
   }
   
   public static boolean ODiagonalFrontFourCheck() // Checks if computer wins diagonally frontward
   {
      boolean four = false;
      int Ocount = 0;
      int row = 1;
      int column = 1;
      while(!four)
      {
         for(int i = 0; i < LENGTH; i++)
         {
            for(int k = 0; k < HEIGHT; k++)
            {
               if(BOARD[i][k] == 'O') // When O is found, The loop below will find an O at one column and one row over
               {
                  Ocount++;
                  boolean dcheck = true;
                  while(dcheck)
                  {
                     if(column + i <= LENGTH - 1 && row + k <= HEIGHT - 1)
                     {
                        if(BOARD[i + column][k + row] == 'X'){
                           Ocount++;
                        }
                     }
                     row++;
                     column++;
                     if(column == LENGTH - 1 || row == HEIGHT - 1) // prevent out of bounds
                     {
                        dcheck = false;
                        break;
                     }
                     if(Ocount > 4)
                     {                              
                        System.out.print("Player 2 Wins");
                        dcheck = false;
                        four = true;
                        break;
                     }
                  }
                     
               }
               if(Ocount > 4)
               {
                  four = true;
                  break;
               }
               Ocount = 0;
               row = 0;
               column = 0;
            }
         }
         break;
      }
      return four;
   }
   
   public static boolean ODiagonalBackFourCheck() // Check if computer wins diagonally backward
   {
      boolean four = false;
      int Ocount = 0;
      int row = 1;
      int column = 1;
      while(!four)
      {
         for(int i = 0; i < LENGTH; i++)
         {
            for(int k = 0; k < HEIGHT; k++)
            {
               if(BOARD[i][k] == 'O') // When O is found, The loop below will find an O at one column and one row over
               {
                  Ocount++;
                  boolean dcheck = true;
                  while(dcheck)
                  {
                     if(i - column >= 0 && k - row >= 0)
                     {
                        if(BOARD[i - column][k - row] == 'X'){
                           Ocount++;
                        }
                     }
                     row++;
                     column++;
                     if(column == 0 || row == HEIGHT - 1) // prevent out of bounds
                     {
                        dcheck = false;
                        break;
                     }
                     if(Ocount > 4)
                     {                              
                        System.out.print("Player 2 Wins");
                        dcheck = false;
                        four = true;
                        break;
                     }
                  }
                     
               }
               if(Ocount > 4)
               {
                  four = true;
                  break;
               }
               Ocount = 0;
               row = 0;
               column = 0;
            }
         }
         break;
      }
      return four;
   }
}