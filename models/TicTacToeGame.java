package com.scaler.lld.projects.tictactoe.models;

import com.scaler.lld.projects.tictactoe.models.controller.GameController;
import java.util.*;

public class TicTacToeGame {
  private static GameController gameController = new GameController();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("How many human players are going to play?");
    int humanPlayers = scanner.nextInt();
    List<Player> players = new ArrayList<>();
    for (int i = 0; i < humanPlayers; i++) {
      System.out.println("Give player " + (i + 1) + " name:");
      String name = scanner.next();
      System.out.println("Give player " + (i + 1) + " symbol:");
      Character symbol = scanner.next().charAt(0);
      // Todo check if we are getting unique symbols.
      players.add(new Player(name, symbol));
    }
    System.out.println("Do you want to play with bot too? (y/n)");
    char botReply = scanner.next().charAt(0);
    if (botReply == 'y' || botReply == 'Y') {
      System.out.println("Choose bot level: (h/m/e)");
      Character botLevel = scanner.next().charAt(0);
      Map<Character, BotLevel> hashMap = new HashMap<Character, BotLevel>();
      hashMap.put('h', BotLevel.Hard);
      hashMap.put('m', BotLevel.Medium);
      hashMap.put('e', BotLevel.Easy);
      players.add(new Bot("Bot-1", 'B', hashMap.get(botLevel)));
    }
    Game game = null;
    try {
      game = gameController.createGame(players);
    } catch (Exception e) {
      System.out.println("Error while creating game: " + e.getMessage());
    }
    while (gameController.getGameStatus(game).equals(GameStatus.In_Progress)) {
      gameController.displayBoard(game);
      Player currentPlayer = gameController.getCurrentPlayer(game);
      System.out.println(currentPlayer.getName() + "'s turn, give row and col:");
      int row = scanner.nextInt();
      int col = scanner.nextInt();
      if (game.getBoard().cells.get(row).get(col).getCellState().equals(CellState.OCCUPIED)) {
        System.out.println(
            "The cell you're trying to select is occupied. please select an empty cell.");
        while (true) {
          System.out.println(currentPlayer.getName() + "'s turn again, give row and col:");
          int row2 = scanner.nextInt();
          int col2 = scanner.nextInt();
          if (game.getBoard().cells.get(row2).get(col2).getCellState().equals(CellState.EMPTY)) {
            row = row2;
            col = col2;
            break;
          }
          System.out.println(
              "The cell you're trying to select is occupied. please select an empty cell.");
        }
      }
      gameController.makeMove(game, currentPlayer, row, col);
    }
    if (gameController.getGameStatus(game).equals(GameStatus.Ended)) {
      Player winnerPlayer = gameController.getCurrentPlayer(game);
      gameController.displayBoard(game);
      System.out.println(winnerPlayer.getName() + " has won!");
    }
  }
}
