package com.scaler.lld.projects.tictactoe.models.controller;

import com.scaler.lld.projects.tictactoe.models.Exceptions.InValidConstructionException;
import com.scaler.lld.projects.tictactoe.models.Game;
import com.scaler.lld.projects.tictactoe.models.GameStatus;
import com.scaler.lld.projects.tictactoe.models.Player;
import java.util.ArrayList;
import java.util.List;

public class GameController {

  public Game createGame(List<Player> players) throws InValidConstructionException {
    Game game =
        Game.getBuilder()
            .setPlayers(players)
            .setGameStatus(GameStatus.In_Progress)
            .setMoves(new ArrayList<>())
            .setCurPlayerIndex(0)
            .build();
    return game;
  }

  public void makeMove(Game game, Player player, int row, int col) {
    game.makeMove(player, row, col);
  }

  public GameStatus getGameStatus(Game game) {
    return game.getGameStatus();
  }

  public void undo() {}

  public void displayBoard(Game game) {
    game.getBoard().displayBoard();
  }

  public Player getCurrentPlayer(Game game) {
    return game.getCurPlayer();
  }
}
