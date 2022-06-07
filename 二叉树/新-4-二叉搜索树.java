


114.二叉树展开为链表
897.递增顺序搜索树










897.递增顺序搜索树
class Solution{
	TreeNode head = new TreeNode(-1);
	TreeNode pre = head;

	public TreeNode increasingBST(TreeNode root){
		helper(root);
		return head.right;
	}

	public void helper(TreeNode root){
		if(root==null)
			return;
		helper(root.left);
		root.left = null;
		pre.right = root;
		pre = root;
		helper(root.right);
	}
}
我们只需要一个变量 prev 保存在中序遍历时，上一次被访问的节点。那么我们每次遍历的时候：

把当前节点 root.left 设置为 null；
把 prev.right 设置为当前遍历的节点 root；
把当前 root 设置为 prev。

看到BST就要考虑用pre指针

https://leetcode-cn.com/problems/increasing-order-search-tree/solution/fu-xue-ming-zhu-fen-xiang-er-cha-shu-san-hljt/


