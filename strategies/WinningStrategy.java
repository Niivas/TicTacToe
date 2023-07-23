package com.scaler.lld.projects.tictactoe.models.strategies;

public interface WinningStrategy {
    boolean updateAndCheckIfWon(Character symbol, int row, int col, int size);
}
