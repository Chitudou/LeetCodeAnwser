200.岛屿数量
1254.统计封闭岛屿的数量
1020.飞地的数量
695.岛屿的最大面积
1905.统计子岛屿
694.不同岛屿的数量(会员题，但要做一下，压轴题)

130.被围绕的区域
79.单词搜索
733.图像渲染


采用labuladong的算法模板


void dfs(int[][] grid,int i,int j,boolean[][] visited){
	int m = grid.length,n = grid[0].length;
	if(i<0||j<0||i>=m||j>=n){
		return ;
	}
	if(visited[i][j]){
		//已遍历过(i,j)
		return ;
	}
	//进入节点(i,j)
	visited[i][j] = true;
	dfs(grid,i-1,j,visited);//上
	dfs(grid,i+1,j,visited);//下
	dfs(grid,i,j-1,visited);//左
	dfs(grid,i,j+1,visited);//右
}



130. 被围绕的区域
class Solution{
	public void solve(char[][] board){
		if(board==null||board.length==0||board[0]==null||board[0].length==0)
			return ;
		int m = board.length;
		int n = board[0].length;
		//处理边缘岛屿
		for(int j=0;j<n;j++){
			//把靠上面的岛屿淹了
			dfs(board,0,j);
			//把靠下面的岛屿淹了
			dfs(board,m-1,j);
		}
		for(int i=0;i<m;i++){
			//把靠左边的岛屿淹了
			dfs(board,i,0);
			//把靠右边的岛屿淹了
			dfs(board,i,n-1);
		}
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(board[i][j]=='O')
					board[i][j] = 'X';
				if(board[i][j]=='#')
					board[i][j] = 'O';
			}
		}
	}

	public void dfs(char[][] board,int i,int j){
		int m = board.length,n=board[0].length;
		if(i<0||j<0||i>=m||j>=n){
			return ;
		}
		if(board[i][j]!='O'){
			return ;
		}
		board[i][j] = '#';
		dfs(board,i-1,j);
		dfs(board,i+1,j);
		dfs(board,i,j-1);
		dfs(board,i,j+1);

	}
}

200.岛屿数量
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length,n = grid[0].length;
        int res = 0;
        //遍历grid
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                	//每发现一个岛屿，岛屿数量加一
                    res++;
                    //然后使用DFS将岛屿淹了
                    dfs(i,j,grid);
                }
            }
        }
        return res;
    }

    //从(i,j)开始，将与之相邻的陆地都变成海水
    public void dfs(int i,int j,char[][] grid){
        if(i<0||j<0||i>=grid.length||j>=grid[0].length){
            //超出索引边界
            return ;
        }
        if(grid[i][j]=='0'){
        	//已经是海水了
        	return ;
        }
        //将(i,j)变成海水
        grid[i][j] = '0';
        dfs(i-1,j,grid);
        dfs(i+1,j,grid);
        dfs(i,j-1,grid);
        dfs(i,j+1,grid);
    }
}




1254. 统计封闭岛屿的数目
class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int res = 0;
        for(int i=0;i<m;i++){
        	//把靠上边的岛屿淹掉
            dfs(i,0,grid);
            //把靠下边的岛屿淹掉
            dfs(i,n-1,grid);
        }
        for(int j=0;j<n;j++){
        	//把靠左边的岛屿淹掉
            dfs(0,j,grid);
            //把靠右边的岛屿淹掉
            dfs(m-1,j,grid);
        }
        //遍历grid，剩下的岛屿都是封闭岛屿
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0){
                    res++;
                    dfs(i,j,grid);
                }
            }
        }
        return res;
    }


    //从(i,j)开始，将与之相邻的陆地都变成海水
    public void dfs(int i,int j,int[][] grid){
    	//超出边界或已经是海水了
        if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]==1)
            return ;
        //将(i,j)变成海水
        grid[i][j] = 1;
        //淹没上下左右的陆地
        dfs(i+1,j,grid);
        dfs(i-1,j,grid);
        dfs(i,j-1,grid);
        dfs(i,j+1,grid);
    }
}


1020.飞地的数量
class Solution {
    public int numEnclaves(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int res = 0;
        for(int i=0;i<m;i++){
        	//把靠上边的岛屿淹掉
            dfs(i,0,grid);
            //把靠下边的岛屿淹掉
            dfs(i,n-1,grid);
        }
        for(int j=0;j<n;j++){
        	//把靠左边的岛屿淹掉
            dfs(0,j,grid);
            //把靠右边的岛屿淹掉
            dfs(m-1,j,grid);
        }
        //遍历grid，剩下的岛屿都是封闭岛屿
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs(int i,int j,int[][] grid){
    	//超出边界或已经是海水了
        if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]==0)
            return ;
        //将(i,j)变成海水
        grid[i][j] = 0;
        //淹没上下左右的陆地
        dfs(i+1,j,grid);
        dfs(i-1,j,grid);
        dfs(i,j-1,grid);
        dfs(i,j+1,grid);
    }
}


695.岛屿的最大面积
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int res = 0;
        //遍历grid
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    res =  Math.max(res,dfs(i,j,grid));                    
                }
            }
        }
        return res;
    }

    public int dfs(int i,int j,int[][] grid){
        if(i<0||j<0||i>=grid.length||j>=grid[0].length){
            //超出索引边界
            return 0;
        }
        if(grid[i][j]==0){
        	//已经是海水了
        	return 0;
        }
        //将(i,j)变成海水
        grid[i][j] = 0;
        return dfs(i-1,j,grid)+dfs(i+1,j,grid)+dfs(i,j-1,grid)+dfs(i,j+1,grid)+1;
    }
}


1905.统计子岛屿
class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
    	int m = grid1.length,n = grid1[0].length;
    	for(int i=0;i<m;i++){
    		for(int j=0;j<n;j++){
    			//当岛屿B中所在陆地在岛屿A中也是陆地时，岛屿B是岛屿A的子岛屿
    			//反过来说，如果岛屿B中存在一片陆地，在岛屿A的对应位置是海水，那么岛屿B就不是岛屿A的子岛屿
    			if(grid1[i][j]==0&&grid2[i][j]==1){
    				//这个岛屿肯定不是子岛，淹掉
    				dfs(i,j,grid2);
    			}
    		}
    	}

    	//现在grid2中剩下的岛屿都是子岛，计算岛屿数量
    	int res = 0;
    	for(int i=0;i<m;i++){
    		for(int j=0;j<n;j++){
    			if(grid2[i][j]==1){
    				res++;
    				dfs(i,j,grid2);
    			}
    		}
    	}
    	return res;
    }

    //从(i,j)开始，将与之相邻的陆地都变成海水
    public void dfs(int i,int j,int[][] grid){
    	//超出边界或已经是海水了
        if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]==0)
            return ;
        //将(i,j)变成海水
        grid[i][j] = 0;
        //淹没上下左右的陆地
        dfs(i+1,j,grid);
        dfs(i-1,j,grid);
        dfs(i,j-1,grid);
        dfs(i,j+1,grid);
    }
}

694.不同岛屿的数量
