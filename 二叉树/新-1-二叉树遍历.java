二叉树遍历：
		94.中序遍历
		144.前序遍历
		145.后序遍历
		102.层次遍历(层数/完全二叉树/满二叉树暂时归类到这里)
			107.层次遍历II
			103.Z字形遍历
			116.填充每个节点的下一个右侧节点指针
			117.填充每个节点的下一个右侧节点指针II
			199.二叉树的右视图
			513.找树左下角的值
			515.在每个树行中找最大值
			637.二叉树的层平均值
			1302.层数最深叶子节点的和

			662.二叉树最大宽度(完全二叉树)
			222.完全二叉树的节点个数
			894. 所有可能的满二叉树(满二叉树)
			919.完全二叉树插入器(完全二叉树)
			958.二叉树的完全性检验(完全二叉树)
			987.二叉树的垂序遍历
	N叉树的遍历
		429.N叉树的层序遍历
		590.N叉树的后序遍历		
		589.N叉树的前序遍历
	遍历与构造
		105.从前序与中序遍历序列构造二叉树
		106.从中序与后序遍历序列构造二叉树
		889.从前序与后序遍历序列构造二叉树
		654.最大二叉树
		998.最大二叉树II
		655.输出二叉树
		1008.先序遍历构造二叉树
		1130.叶值最小代价生成树



94.中序遍历(递归，迭代，莫里斯遍历，颜色标记法)(迭代法前中后序三种遍历是一个模板)
(1)迭代
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        while(!list.isEmpty()||root!=null){//1.第二个条件是root!=null
            //不断往左子树方向走，每走一次就将当前节点保存到栈中
			//这是模拟递归的调用
            if(root!=null){//2.判断条件是root!=null
                list.addFirst(root);//3.添加到链表头部，模拟栈，先进后出
                root = root.left;
            }else{
                //当前节点为空，说明左边走到头了，从栈中弹出节点并保存
			    //然后转向右边节点，继续上面整个过程
                TreeNode node = list.removeFirst();//4.从链表头部删除，模拟栈，先进后出
                res.add(node.val);
                root = node.right;//5.注意root为空，要指向node.right，不要写成root.right
            }
        }
        return res;
    }
}
(2-1)莫里斯遍历——借助空闲指针
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
		while(root!=null){
			if(root.left!=null){
				//找到根节点左子树的最右节点
				TreeNode temp = root.left;
				while(temp.right!=null&&temp.right!=root){
					temp = temp.right;
				}
                //最右节点.right为空，说明是第一次访问
				if(temp.right==null){
					//并将右节点指向根
					temp.right = root;
					//根节点继续往左访问
					root = root.left;
					
				}else{
					//最右节点不为空，说明访问过了，要断开
                    res.add(root.val);
					temp.right = null;
					root = root.right;
				}
			}else{
				//访问根的右边
				res.add(root.val);
				root = root.right;
			}
		}
		return res;
    }
}
来自公众号【大话算法】
https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/dong-hua-yan-shi-94-er-cha-shu-de-zhong-xu-bian-li/
(2-2)莫里斯遍历——破坏树结构
class Solution{
	public List<Integer> inorderTraversal(TreeNode root){
		List<Integer> res = new ArrayList<>();
		TreeNode pre = null;
		while(root!=null){
			//如果左节点不为空，就将当前节点连带右子树全部挂到
			//左节点的最右子树下面
			if(root.left!=null){
				pre = root.left;
				while(pre.right!=null){
					pre = pre.right;
				}
				pre.right = root;
				//将root指向root的left
				TreeNode temp = root;
				root = root.left;
				temp.left = null;
			}else{
				//左子树为空，则打印这个节点，并向右边遍历
				res.add(root.val);
				root = root.right;
			}
		}
		return res;
	}
}
来自公众号【大话算法】
https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/dong-hua-yan-shi-94-er-cha-shu-de-zhong-xu-bian-li/
(3)颜色标记法
https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/yan-se-biao-ji-fa-yi-chong-tong-yong-qie-jian-ming/
(4)递归
class Solution{
	public List<Integer> inorderTraversal(TreeNode root){
		List<Integer> res = new ArrayList<>();
		helper(res,root);
		return res;
	}

	public void helper(List<Integer> res,TreeNode root){
		if(root==null){
			return ;
		}
		helper(res,root.left);
		res.add(root.val);
		helper(res,root.right);
	}
}





144.前序遍历
(1)迭代
class Solution{
	public List<Integer> preorderTraversal(TreeNode root){
		List<Integer> res = new LinkedList<>();
		LinkedList<TreeNode> list = new LinkedList<>();
		//当节点为空，且堆栈也为空的时候遍历结束
		while(!list.isEmpty()||root!=null){
			//当前节点不为空，压入堆栈
			if(root!=null){
				//1.记录节点值
				res.add(root.val);
				list.addFirst(root);//2.加入链表头，模拟栈调用，先进后出
				//按照前序遍历顺序进行
				root = root.left;
			}else{
				TreeNode node = list.removeFirst();//3.从链表头删除，模拟栈调用，先进后出
				root = node.right;
			}
		}
		return res;
	}
}
(2-1)莫里斯遍历——借助空闲指针
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
		while(root!=null){
			if(root.left!=null){
				//找到根节点左子树的最右节点
				TreeNode temp = root.left;
				while(temp.right!=null&&temp.right!=root){
					temp = temp.right;
				}
                //最右节点.right为空，说明是第一次访问
				if(temp.right==null){
					//第一次访问，加入到结果集中
					res.add(root.val);
					//并将右节点指向根
					temp.right = root;
					//根节点继续往左访问
					root = root.left;
					
				}else{
					//最右节点不为空，说明访问过了，要断开
					temp.right = null;
					root = root.right;
				}8/7
			}else{
				//访问根的右边
				root = root.right;
			}
		}
		return res;
    }
}
(2-2)莫里斯遍历——破坏树结构
class Solution{
	public List<Integer> preorderTraversal(TreeNode root){
		List<Integer> res = new ArrayList<>();
		TreeNode pre = null;
		while(root!=null){
			//如果左节点不为空，就将当前节点连带右子树全部挂到
			//左节点的最右子树下面
			if(root.left!=null){
				pre = root.left;
				while(pre.right!=null){
					pre = pre.right;
				}
				//将左子树最右节点.right指向根节点的右子树
				pre.right = root.right;
				//将左子树调整为右子树，并将左边置空
				root.right = root.left;
				root.left = null;
			}else{
				//此时树变成链表，不断访问右节点即可
				res.add(root.val);
				root = root.right;
			}
		}
		return res;
	}
}
(3)递归
class Solution{
	public List<Integer> preorderTraversal(TreeNode root){
		List<Integer> res = new ArrayList<>();
		helper(res,root);
		return res;
	}

	public void helper(List<Integer> res,TreeNode root){
		if(root==null){
			return ;
		}
		res.add(root.val);
		helper(res,root.left);
		helper(res,root.right);
	}
}

145.后序遍历
(1)迭代
class Solution{
	public List<Integer> postorderTraversal(TreeNode root){
		List<Integer> res = new LinkedList<>();
		LinkedList<TreeNode> list = new LinkedList<>();
		TreeNode pre = null;//记录上次访问过的节点
		//当root为空并且栈为空时遍历结束
		while(!list.isEmpty()||root!=null){
			//根节点不为空时直接入栈
			if(root!=null){
				list.addFirst(root);
				root = root.left;
			}else{
				TreeNode node = list.getFirst();
				if(node.right!=null&&pre!=node.right){
					//该节点的右孩子不为空，且上一个访问的不是右孩子(证明这是从左孩子回溯过来的)
					root = node.right;
				}else{
					pre = list.removeFirst();
					res.add(pre.val);
				}
			}
		}
		return res;
	}
}
作者这个思路(先序遍历并倒置)得到后序遍历的非递归是没有问题的。但是有时候二叉树的非递归我们不仅仅是为了解决遍历问题，
例如后序遍历堆栈里保存的始终是该节点的祖先节点这一性质可以用于解决寻找公共祖先的问题。
遇到这种问题作者的这种反转先序遍历的思路就不行了。所以最好还是可以快速写出常规的非递归遍历比较好。
懒得写题解了，借作者宝地写一个本人写的三种非递归的统一模板式写法。 二叉树的非递归难点其实就在于后序遍历，
因为后序需要两次路过根节点。遍历的时候需要根据第几次路过来决定是否访问根节点。解决的办法是增加一个pre指针，
指向上次访问的节点。因为后序遍历LRN最后一定是遍历根节点，所以当pre指向root.right的时候那么就是该遍历root的时候了。
(2-1)莫里斯遍历——借助空闲指针
//构造[根，右，左]的模式，然后取反
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
		while(root!=null){
			if(root.right!=null){
				//找到根节点右子树的最左节点
				TreeNode temp = root.right;
				while(temp.left!=null&&temp.left!=root){
					temp = temp.left;
				}
                //最右节点.left为空，说明是第一次访问
				if(temp.left==null){
					//第一次访问，加入到结果集中
					res.add(0,root.val);
					//并将左节点指向根
					temp.left = root;
					//根节点继续往右访问
					root = root.right;
				}else{
					//最左节点不为空，说明访问过了，要断开
					temp.left = null;
					root = root.left;
				}
			}else{
				//访问根的左边
				res.add(0,root.val);
				root = root.left;
			}
		}
		return res;
    }
}
(2-2)莫里斯遍历——破坏树结构
class Solution{
	public List<Integer> postorderTraversal(TreeNode root){
		List<Integer> res = new ArrayList<>();
		while(root!=null){
			//如果右子树不为空，找到右子树的最左节点
			if(root.right!=null){
				TreeNode temp = root.right;
				while(temp.left!=null){
					temp = temp.left;
				}
				//将右子树最左节点.left指向根节点的左子树
				temp.left = root.left;
				//将右子树调整为左子树，并将右边置空
				root.left = root.right;
				root.right = null;
			}else{
				//此时树变成链表，不断访问左节点即可
				res.add(0,root.val);
				root = root.left;
			}
		}
		return res;
	}
}

(3)递归
class Solution{
	public List<Integer> postorderTraversal(TreeNode root){
		List<Integer> list = new ArrayList<>();
		Helper(root,list);
		return list;
	}

	public void Helper(TreeNode root,List<Integer> list){
		if(root==null)
			return ;
		Helper(root.left,list);
		Helper(root.right,list);
		list.add(root.val);
	}
}


102.层次遍历(层数/完全二叉树/满二叉树暂时归类到这里)
107.层次遍历II
103.Z字形遍历
	116.填充每个节点的下一个右侧节点指针
	117.填充每个节点的下一个右侧节点指针II
	199.二叉树的右视图
	404.左叶子之和(简单题)
	513.找树左下角的值
	515.在每个树行中找最大值
	637.二叉树的层平均值

	662.二叉树最大宽度(完全二叉树)
	222.完全二叉树的节点个数
	894. 所有可能的满二叉树(满二叉树)
	919.完全二叉树插入器(完全二叉树)
	958.二叉树的完全性检验(完全二叉树)

	1302.层数最深叶子节点的和
987.二叉树的垂序遍历


102.二叉树的层序遍历
(1)BFS-迭代
class Solution{
	public List<List<Integer>> levelOrder(TreeNode root){
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> res = new LinkedList<List<Integer>>();
		
		if(root==null)	return res;//1.记得边界条件，很重要

		queue.add(root);
		while(!queue.isEmpty()){
			int levelNum = queue.size();
			List<Integer> subList = new LinkedList<Integer>();
			for(int i=0;i<levelNum;i++){
				TreeNode node = queue.remove();
				if(node.left!=null)	queue.add(node.left);
				if(node.right!=null) queue.add(node.right);
				subList.add(node.val);
			}
			res.add(subList);
		}
		return res;
	}
}
https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/

(2)DFS-递归
class Solution {
	public List<List<Integer>> levelOrder(TreeNode root) {
	    List<List<Integer>> ans = new ArrayList<>(); 
	    DFS(root, 0, ans);
	    return ans;
	}

	private void DFS(TreeNode root, int level, List<List<Integer>> ans) {
	    if(root == null){
	        return;
	    }
	    //当前层数还没有元素，先 new 一个空的列表
	    if(ans.size()<=level){
	        ans.add(new ArrayList<>());
	    }
	    //当前值加入
	    ans.get(level).add(root.val);

	    DFS(root.left,level+1,ans);
	    DFS(root.right,level+1,ans);
	} 
}
https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--23/

107.二叉树的层序遍历 II
(1)BFS-迭代
class Solution{
	public List<List<Integer>> levelOrderBottom(TreeNode root){
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> res = new LinkedList<List<Integer>>();
		
		if(root==null)	return res;//记住边界条件，很重要

		queue.add(root);
		while(!queue.isEmpty()){
			int levelNum = queue.size();
			List<Integer> subList = new LinkedList<Integer>();
			for(int i=0;i<levelNum;i++){
				TreeNode node = queue.remove();
				if(node.left!=null)	queue.add(node.left);
				if(node.right!=null) queue.add(node.right);
				subList.add(node.val);
			}
			res.add(0,subList);
		}
		return res;
	}
}

(2)DFS-递归
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        DFS(root, 0, ans);
        return ans;
    }

    private void DFS(TreeNode root, int level, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }
        // 当前层数还没有元素，先 new 一个空的列表
        if (ans.size() <= level) {
            ans.add(0, new ArrayList<>());
        }
        // 当前值加入
        ans.get(ans.size() - 1 - level).add(root.val);

        DFS(root.left, level + 1, ans);
        DFS(root.right, level + 1, ans);
    }

}
https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--23/

103.Z字形遍历
class Solution{
	public List<List<Integer>> zigzagLevelOrder(TreeNode root){
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> res = new LinkedList<List<Integer>>();
		
		if(root==null)	return res;

		int leval = 1;
		queue.add(root);
		while(!queue.isEmpty()){
			int levelNum = queue.size();
			List<Integer> subList = new LinkedList<Integer>();
			for(int i=0;i<levelNum;i++){
				TreeNode node = queue.remove();
				if(node.left!=null)	queue.add(node.left);
				if(node.right!=null) queue.add(node.right);
				if(level%2==1){
					subList.add(node.val);
				}else{
					subList.add(0,node.val);
				}
			}
			res.add(subList);
			
		}
		return res;
	}
}

(2)DFS-递归
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        DFS(root, 0, ans);
        return ans;
    }

    private void DFS(TreeNode root, int level, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }
        // 当前层数还没有元素，先 new 一个空的列表
        if (ans.size() <= level) {
            ans.add(new ArrayList<>());
        }
        // 当前值加入
        if(level%2==0){
			ans.get(level).add(root.val);
        }else{
        	ans.get(level).add(0,root.val);
        }

        DFS(root.left, level + 1, ans);
        DFS(root.right, level + 1, ans);
    }
}

116.填充每个节点的下一个右侧节点指针(和117的题解一起看)(完全二叉树)
https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/dong-hua-yan-shi-san-chong-shi-xian-116-tian-chong/
117.填充每个节点的下一个右侧节点指针II
https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/solution/bfsjie-jue-zui-hao-de-ji-bai-liao-100de-yong-hu-by/

199.二叉树的右视图
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root==null)
            return res;
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.remove();
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
                if(i==size-1){//最右边的节点值
                    res.add(node.val);
                }
            }
        }
        return res;
    }
}

513.找树左下角的值
(1)BFS
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root.left==null&&root.right==null)
            return root.val;
        queue.add(root);
        TreeNode temp = root;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.remove();
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
                if(i==0){//最左边的节点值
                    temp = node;
                }
            }
        }
        return temp.val;
    }   
}
(2)DFS  
使用depth记录深度进行比较
class Solution {
    private int curMaxDepth=-1,curVal=0;
    public int findBottomLeftValue(TreeNode root) {
        help(root,0);
        return curVal;
    }
    private void help(TreeNode root,int depth){
        if(root==null){return;}
        if(depth>curMaxDepth){
            curMaxDepth=depth;
            curVal=root.val;
        }
        help(root.left,depth+1);
        help(root.right,depth+1);
    }
}
https://leetcode-cn.com/problems/find-bottom-left-tree-value/solution/javati-jie-o1kong-jian-de-qian-xu-bian-l-t8dj/
其他思路：
如果需要遍历整颗树，递归函数就不能有返回值。如果需要遍历某一条固定路线，递归函数就一定要有返回值！
https://leetcode-cn.com/problems/find-bottom-left-tree-value/solution/dai-ma-sui-xiang-lu-dai-ni-xue-tou-er-ch-w3og/


515.在每个树行中找最大值
(1)BFS
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root==null)
            return res;
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i=0;i<size;i++){
                TreeNode node = queue.remove();
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
                max = Math.max(max,node.val);
            }
            res.add(max);
        }
        return res;
    }
}
(2)DFS 
class Solution {
    List<Integer> list = new ArrayList<>();
    public List<Integer> largestValues(TreeNode root) {
        helper(root,0);
        return list;
    }

    public void helper(TreeNode root,int depth){
        if(root==null){
            return ;
        }

        if(list.size()-1<depth){
            list.add(Integer.MIN_VALUE);
        }
        int num = list.get(depth);
        num = Math.max(num,root.val);
        list.set(depth,num);

        helper(root.left,depth+1);
        helper(root.right,depth+1);
        return ;
    }
}


637.二叉树的层平均值
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Double> res = new ArrayList();
        if(root==null){
            return res;
        }
        if(root.left==null&&root.right==null){
            res.add(Double.valueOf(root.val));
            return res;
        }
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            long sum = 0;//1.注意此处用int会有特殊用例超出int范围
            for(int i=0;i<size;i++){
                TreeNode node = queue.remove();
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
                sum+=node.val;
            }
            res.add(sum*1.0/size);
        }
        return res;
    }
}
662.二叉树最大宽度(完全二叉树)
222.完全二叉树的节点个数
894. 所有可能的满二叉树(满二叉树)
919.完全二叉树插入器(完全二叉树)
958.二叉树的完全性检验(完全二叉树)
1302.层数最深叶子节点的和


987.二叉树的垂序遍历
class Solution{
	PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->{
		if(a[0]!=b[0])
			return a[0]-b[0];
		if(a[1]!=b[1])
			return a[1]-b[1];
		return a[2]-b[2];
	});

	public List<List,Integer>> vertivalTraversal(TreeNode root){
		int[] info = new int[]{0,0,root.val};
		q.add(info);
		dfs(root,info);
		List<List<Integer>> ans = new ArrayList<>();
		while(!q.isEmpty()){
			List<Integer> temp = new ArrayList<>();
			int[] poll = q.peek();
			while(!q.isEmpty()&&q.peek()[0]==poll[0])
				temp.add(q.poll()[2]);
			ans.add(temp);
		}
		return ans;
	}

	public void dfs(TreeNode root,int[] fa){
		if(root.left!=null){
			int[] linfo = new int[]{fa[0]-1,fa[1]+1,root.left.val};
			q.add(linfo);
			dfs(root.left,linfo);
		}
		if(root.right!=null){
			int[] rinfo = new int[]{fa[0]+1,fa[1]+1,root.right.val};
			q.add(rinfo);
			dfs(root.right,rinfo);
		}
	}
}
https://leetcode.cn/problems/vertical-order-traversal-of-a-binary-tree/solution/gong-shui-san-xie-yi-ti-shuang-jie-dfs-h-wfm3/