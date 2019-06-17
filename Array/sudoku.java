/**
37. Sudoku Solver
*/

class Solution {
	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0) return;
		solve(board, 0, 0);
	}

	public boolean solve(char[][] board, int row, int col) {
		for (int i = row; i < board.length; i++) {
			for (int j = col; j < board[0].length; j++) {
				if (board[i][j] == '.') {
					for (char c = '1'; c <= '9'; c++) {
						if (isValid(board, i, j, c)) {
							board[i][j] = c;
							if 
								(solve(board, row, col)) return true;
							else 
								board[i][j] = '.';
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValid(char[][] board, int row, int col, char c) {
		for (int i = 0; i < 9; i++) {
			if (board[i][col] != '.' && board[i][col] == c) return false;
			if (board[row][i] != '.' && board[row][i] == c) return  false;
			if (board[3*(row/3) + i/3][3*(col/3) + i % 3] != '.' && 
				board[3*(row/3) + i/3][3*(col/3) + i % 3] == c) {
				return false;
			}
		}
		return true;
	}
}

public boolean isValidSudoku(char[][] board) {
    Set seen = new HashSet();
    for (int i=0; i<9; ++i) {
        for (int j=0; j<9; ++j) {
            char number = board[i][j];
            if (number != '.')
                if (!seen.add(number + " in row " + i) ||
                    !seen.add(number + " in column " + j) ||
                    !seen.add(number + " in block " + i/3 + "-" + j/3))
                    return false;
        }
    }
    return true;
}