//723. Candy Crush


public int[][] candyCrush(int[][] board) {
  int m = board.length;
  int n = board[0].length;

  boolean shouldContinue = false;

  // Crush horizontally
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n - 2; j++) {
      int v = Math.abs(board[i][j]);
      if (v > 0 && v == Math.abs(board[i][j + 1]) && v == Math.abs(board[i][j + 2])) {
        board[i][j] = board[i][j + 1] = board[i][j + 2] = -v;
        shouldContinue = true;
      }
    }
  }

  // Crush vertically
  for (int i = 0; i < m - 2; i++) {
    for (int j = 0; j < n; j++) {
      int v = Math.abs(board[i][j]);
      if (v > 0 && v == Math.abs(board[i + 1][j]) && v == Math.abs(board[i + 2][j])) {
        board[i][j] = board[i + 1][j] = board[i + 2][j] = -v;
        shouldContinue = true;
      }
    }
  }

  // Drop vertically
  for (int j = 0; j < n; j++) {
    int r = m - 1;
    for (int i = m - 1; i >= 0; i--) {
      if (board[i][j] >= 0) {
        board[r--][j] = board[i][j];
      }
    }
    for (int i = r; i >= 0; i--) {
      board[i][j] = 0;
    }
  }

  return shouldContinue ? candyCrush(board) : board;
}