/**
The n-queens puzzle is the problem of placing n queens on an n×n chessboard 
such that no two queens attack each other.
*/
class Solution {
	public List<List<String>> solveNQueens(int n) {
		char [][] chess = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				chess[i][j] = '.';
			}
		}
		List<List<String>> res = new ArrayList<List<String>>();
		solve(res, chess, 0);
		return res;
	}

	private void solve(List<List<String>> res, char [][] chess, int row) {
		if (row == chess.length) {
			List<String> path = new ArrayList<>();
			for (int i = 0; i < chess.length; i++) {
				path.add(new String(chess[i]));
			}
			res.add(path);
			return;
		}

		for (int col = 0; col < chess[0].length; col++) {
			if (valid(chess, row ,col)) {
				chess[row][col] = 'Q';
				solve(res, chess, row + 1);
				chess[row][col] = '.';
			}			
		}
	}

	private boolean valid(char [][] chess, int row, int col) {
		for (int i = 0; i < row; i++) {
			if (chess[i][col] == 'Q') 
				return false;
		}

		for (int i = row - 1, j = col + 1; i >= 0 && j < chess[0].length; i--, j++) {
			if (chess[i][j] == 'Q')
				return false;
		}

		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (chess[i][j] == 'Q')
				return false;
		}
		return true;
	}
}