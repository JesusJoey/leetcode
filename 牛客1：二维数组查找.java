/*
二维数组查找
在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
*/

public class Solution {
    public boolean Find(int target, int [][] array) {
        if (array == null) return false;
        int row = array.length;
        int col = array[0].length;
        if (row == 0 || col == 0) return false;
        for (int i=0;i < row; i++) {
            if (array[i][0] > target)
                break;
            if (array[i][col-1] < target )
                continue;
            for (int j=0; j<= col-1; j++) {
                if (array[i][j] == target)
                    return true;
            }
        }
        return false;
    }
}

public class Solution {
    public boolean Find(int target, int [][] array) {
        if (array == null) return false;
        int row = array.length;
        int col = array[0].length;
        int i = 0, j = col - 1;
        while (i < col && j >= 0) {
            if (array[i][j] == target)
                return true;
            else if (array[i][j] < target)
                i++;
            else j--;
        }
        return false;
    }
}