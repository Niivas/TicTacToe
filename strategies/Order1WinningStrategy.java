package com.scaler.lld.projects.tictactoe.models.strategies;

import java.util.HashMap;
import java.util.Map;

public class Order1WinningStrategy implements WinningStrategy {

  private Map<Integer, Map<Character, Integer>> rowMap =
      new HashMap<Integer, Map<Character, Integer>>();
  private Map<Integer, Map<Character, Integer>> colMap =
      new HashMap<Integer, Map<Character, Integer>>();
  private Map<Character, Integer> diagMap1 = new HashMap<Character, Integer>();
  private Map<Character, Integer> diagMap2 = new HashMap<Character, Integer>();

  public Order1WinningStrategy(int size) {
    for (int i = 0; i < size; i++) {
      rowMap.put(i, new HashMap<Character, Integer>());
      colMap.put(i, new HashMap<Character, Integer>());
    }
  }

  @Override
  public boolean updateAndCheckIfWon(Character symbol, int row, int col, int size) {
    if (row == col) {
      diagMap1.put(symbol, diagMap1.getOrDefault(symbol, 0) + 1);
    }
    if (row + col == size - 1) {
      diagMap2.put(symbol, diagMap2.getOrDefault(symbol, 0) + 1);
    }
    rowMap.get(row).put(symbol, rowMap.get(row).getOrDefault(symbol, 0) + 1);
    colMap.get(col).put(symbol, colMap.get(col).getOrDefault(symbol, 0) + 1);

    return diagMap1.getOrDefault(symbol, 0) == size
        || diagMap2.getOrDefault(symbol, 0) == size
        || rowMap.get(row).getOrDefault(symbol, 0) == size
        || colMap.get(col).getOrDefault(symbol, 0) == size;
  }
}
