import java.util.Scanner;

/**
 * 九宫格的输出：九宫格就是每一行，每一列，斜着的一列和反斜着的一列的所在的数字之和均相等； 最基本的是三行三列=9格就是很出名的九宫格；还可以推广到5*5=
 * 25个格；只要行和列的个数均相等并且是奇数就可以；
 */

public class Sudoku {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入一个大于等于3的奇数");
		int length = s.nextInt();
		if (length < 3 || length % 2 == 0) {
			System.out.println("输入的数字不合法！");
			return;
		}
		int[][] nineTable = new int[length][length];
		int indexX = 0;
		int indexY = 0;
		indexY = (nineTable.length - 1) / 2;
		nineTable[indexX][indexY] = 1;
		for (int i = 1; i < nineTable.length * nineTable.length; i++) {
			indexX--;
			indexY++;
			if (indexY >= nineTable.length && indexX >= 0) {
				indexY = 0;
			} else if (indexX < 0 && indexY < nineTable.length) {
				indexX = nineTable.length - 1;
			} else if (indexY >= nineTable.length && indexX < 0) {
				indexY--;
				indexX = indexX + 2;
			} else if (nineTable[indexX][indexY] != 0) {
				indexY--;
				indexX = indexX + 2;
			}
			nineTable[indexX][indexY] = i + 1;

		}
		for (int i = 0; i < nineTable.length; i++) {
			for (int j = 0; j < nineTable[i].length; j++) {
				if (nineTable[i][j] < 10)
					System.out.print(" " + nineTable[i][j] + "   ");
				else {
					System.out.print(nineTable[i][j] + "   ");
				}
			}
			System.out.println();
			System.out.println();
		}

	}

}
