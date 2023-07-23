package com.scaler.lld.projects.tictactoe.models;

import com.scaler.lld.projects.tictactoe.models.Exceptions.InValidConstructionException;
import com.scaler.lld.projects.tictactoe.models.strategies.Order1WinningStrategy;
import com.scaler.lld.projects.tictactoe.models.strategies.WinningStrategy;

import java.util.Collections;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private GameStatus gameStatus;
    private int curPlayerIndex;
    private List<Move> moves;
    private WinningStrategy strategy;

    public Game(GameBuilder builder){
        this.players = builder.players;
        this.board = builder.board;
        this.gameStatus = builder.gameStatus;
        this.curPlayerIndex = builder.curPlayerIndex;
        this.moves = builder.moves;
        this.strategy = new Order1WinningStrategy(this.players.size()+1);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getCurPlayer() {
        return this.players.get(curPlayerIndex);
    }

    public List<Move> getMoves() {
        return moves;
    }

    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public void makeMove(Player player, int row, int col) {
        this.board.setPlayer(player,row,col);
        moves.add(new Move(getCurPlayer(),new Cell(row,col,CellState.OCCUPIED)));
        boolean playerHasWon = this.strategy.updateAndCheckIfWon(player.getSymbol(),row,col,this.players.size()+1);
        if(playerHasWon){
            gameStatus = GameStatus.Ended;
            return;
        }
        this.curPlayerIndex = (this.curPlayerIndex+1)% players.size();
    }


    public static class GameBuilder{
        private List<Player> players;
        private Board board;
        private GameStatus gameStatus;
        private int curPlayerIndex;
        private List<Move> moves;

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder setGameStatus(GameStatus gameStatus) {
            this.gameStatus = gameStatus;
            return this;
        }

        public GameBuilder setCurPlayerIndex(int curPlayerIndex) {
            this.curPlayerIndex = curPlayerIndex;
            return this;
        }

        public GameBuilder setMoves(List<Move> moves) {
            this.moves = moves;
            return this;
        }
        public Game build() throws InValidConstructionException{
            if (this.players == null || this.players.size() <= 1) {
                throw new InValidConstructionException("Atleast two players required!");
            }
            Collections.shuffle(this.players);
            this.board = new Board(this.players.size()+1);
            return new Game(this);
        }
    }
}
