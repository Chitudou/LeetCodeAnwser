

100.相同的树    
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }
        if(p==null||q==null){
            return false;
        }
        if(p.val!=q.val)
            return false;
        
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
}
101.对称二叉树
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null)
            return true;
        
        return helper(root.left,root.right);
        
    }
    public boolean helper(TreeNode left,TreeNode right){
        if(left==null&&right==null)
            return true;
        if(left==null||right==null||left.val!=right.val)
            return false;
       
        
        return helper(left.left,right.right)&&helper(left.right,right.left);
    }
}    
965.单值二叉树
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if(root==null)
            return true;
        
        if(root.left!=null&&root.val!=root.left.val){
            return false;
        }
        if(root.right!=null&&root.val!=root.right.val){
            return false;
        }
      
        return isUnivalTree(root.left)&&isUnivalTree(root.right);
    }
}


子结构：
    100.相同的树 
    101.对称二叉树
	965.单值二叉树
    
    剑指 Offer 26. 树的子结构(与572.另一个树的子树相同)
	572.另一个树的子树
	814. 二叉树剪枝
	1367. 二叉树中的列表
        上面4题思路见：https://leetcode-cn.com/problems/binary-tree-pruning/solution/814er-cha-shu-jian-zhi-zi-shu-wen-ti-tao-lu-zhi-di/
    404.左叶子之和(10min)


572.另一个树的子树(与剑指 Offer 26. 树的子结构相同)
class Solution{
    public boolean isSubtree(TreeNode root,TreeNode subRoot){
        if(root==null)
            return false;
        return isSameTree(root,subRoot)||isSubtree(root.left,subRoot)||isSubtree(s.right,subRoot);
    }

    public boolean isSameTree(TreeNode root,TreeNode subRoot){
        if(root==null&&subRoot==null){
            return true;
        }
        if(root==null||subRoot==null){
            return false;
        }
        if(root.val!=subRoot.val){
            return false;
        }
        return isSameTree(root.left,subRoot.left)&&isSameTree(root.right,subRoot.right);
    }
}
先能够判断树是否相同，写出isSameTree递归函数，然后在原问题中，对自身调用isSameTree进行判断，并对左右子树递归调用原函数。


814. 二叉树剪枝
class Solution {
    public TreeNode pruneTree(TreeNode root) {

        if(root==null)
            return null;

        if(helper(root)){
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        return root;
    }

    public boolean helper(TreeNode root){
        if(root==null){
            return true;
        }

        if(root.val==1){
            return false;
        }
        return helper(root.left)&&helper(root.right);
    }
}
思路：
子树相关的题基本是一个套路，重点在于先写出满足条件的子树的递归函数。然后对自身调用这个递归函数，并且对左右子树递归调用原问题函数。
本题是为了剪不包含1的子树，那么我们的子问题就是判断子树是否不包含1。这个函数应该可以直接写出：
public boolean helper(TreeNode root){
        if(root==null){
            return true;
        }

        if(root.val==1){
            return false;
        }
        return helper(root.left)&&helper(root.right);
    }
如果树为空则肯定满足不包含1，如果树的根的值为1则不满足不包含1，否则进一步检查左右子树，需要同时成立。
子问题解决后，我们看原问题中如何处理。首先对于当前的root节点，如果满足不包含1，则该节点作为根的子树需要修剪掉。
那么怎么修剪呢？所谓修剪就是将指向该子树根节点的指针置空，如果修剪的是原树的根节点，则直接返回NULL即可。
如果修剪的是子树的根节点，那么子树修剪后要将修剪的结果赋值给left或right，这是通过递归调用原问题后赋值给left或right实现的。
具体代码如下。
https://leetcode-cn.com/problems/binary-tree-pruning/solution/814er-cha-shu-jian-zhi-zi-shu-wen-ti-tao-lu-zhi-di/



1367. 二叉树中的列表
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        
        if (root == null) {
            return false;
        }
        
        return DfsSame(head,root) || isSubPath(head,root.left) || isSubPath(head,root.right);
    }
    
    private boolean DfsSame(ListNode head,TreeNode root) {
        if(head == null) {
            return true;
        }
        
        if (root == null) {
            return false;
        }
        
        if (root.val != head.val) {
            return false;
        }
        return DfsSame(head.next,root.left) || DfsSame(head.next,root.right);
    }
}




617.合并二叉树
class Solution {
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if(t1==null || t2==null) {
			return t1==null? t2 : t1;
		}
		return dfs(t1,t2);
	}
	
	TreeNode dfs(TreeNode r1, TreeNode r2) {
		// 如果 r1和r2中，只要有一个是null，函数就直接返回
		if(r1==null || r2==null) {
			return r1==null? r2 : r1;
		}
		//让r1的值 等于  r1和r2的值累加，再递归的计算两颗树的左节点、右节点
		r1.val += r2.val;
		r1.left = dfs(r1.left,r2.left);
		r1.right = dfs(r1.right,r2.right);
		return r1;
	}
}

class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1==null||root2==null){
            return root1==null?root2:root1;
            
        }

        return helper(root1,root2) ;
    }


    //leftRight=0,左节点，否则右节点
    public TreeNode helper(TreeNode root1,TreeNode root2){
        if(root1==null||root2==null){
            return root1==null?root2:root1;
            
        }

        TreeNode newRoot = new TreeNode(root1.val+root2.val);
        newRoot.left = helper(root1.left,root2.left);
        newRoot.right = helper(root1.right,root2.right);
        return newRoot;
    }   
}
思路：
https://leetcode-cn.com/problems/merge-two-binary-trees/solution/shou-hua-tu-jie-by-xiao_ben_zhu-3/


404.左叶子之和(简单题)
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int res = 0;
        //判断节点是否是左叶子节点，如果是则将它的和累计起来
        if(root.left != null && root.left.left == null && root.left.right == null){
            res += root.left.val;
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right) + res;
    }
}