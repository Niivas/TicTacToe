package com.scaler.lld.projects.tictactoe.models;

public class Cell {
    private int row;
    private int col;
    private Player player;
    private CellState cellState;

    public Cell(int row, int col, CellState state){
        this.row = row;
        this.col = col;
        this.cellState = state;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Player getPlayer() {
        return player;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.cellState = CellState.OCCUPIED;
    }
}

