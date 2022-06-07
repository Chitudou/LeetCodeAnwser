

329. 矩阵中的最长递增路径
class Solution {
    public int longestIncreasingPath(int[][] matrix){
		if(matrix==null||matrix.length==0||matrix[0].length==0){
			return 0;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] visited = new int[m][n];
		int res = 0;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				res = Math.max(res,dfs(i, j, matrix, visited));
			}
		}
		return res;
	}

	public int dfs(int i,int j,int[][] matrix,int[][] visited){
		int m = matrix.length,n = matrix[0].length;
		if(i<0||j<0||i>=m||j>=n){
			return 0;
		}
		if(visited[i][j]!=0){
			return visited[i][j];
		}
		int max = 0;
		//分别去判断4周是否比当前数小，然后去递归遍历
		if(i - 1 >= 0 && matrix[i-1][j] < matrix[i][j]){
            max = Math.max(max, dfs(i-1, j, matrix, visited));
        }
        if(i + 1 < matrix.length && matrix[i+1][j] < matrix[i][j]){
            max = Math.max(max, dfs(i+1, j, matrix, visited));
        }
        if(j - 1 >= 0 && matrix[i][j-1] < matrix[i][j]){
            max = Math.max(max, dfs(i, j-1, matrix, visited));
        }
        if(j + 1 < matrix[0].length && matrix[i][j+1] < matrix[i][j]){
            max = Math.max(max, dfs(i, j+1, matrix, visited));
        }
		visited[i][j] = max+1;
		return max+1;

	}
}

https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/solution/javashi-xian-shen-du-you-xian-chao-ji-jian-dan-yi-/
