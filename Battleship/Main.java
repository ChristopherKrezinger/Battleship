package Battleship;

//Dear developer:
//if you are in the coding section first and change to the command line, you have to press enter twice. this can't be fixed, since its probably on Intellij
//I hope it's ok, if I sometimes used "_" for methods and sometimes not
//all sounds are royalty free and edited in blender
//credit for sounds: qubodup, LordSonny, Squeeb. All artist can be found on pixabay.com
//commented out code, because I ran out of time. for now AI still fires just randomly even on hit



public class Main {
   //Control object
   private static final Control game = new Control();
   //sound object
   static Sound player = new Sound();
   //Filepath of background music
   static String background_music = "resources/submarine.wav";

   public static void newgame(Board board) {
      board.makeboard();
   }
      //Game is running here:
      public static void main (String[]args){
      player.loopSound(background_music);
      game.game();
      game.end_game();
      }
   }

                
