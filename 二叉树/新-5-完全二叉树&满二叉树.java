662.二叉树最大宽度(完全二叉树)
222.完全二叉树的节点个数
894.所有可能的满二叉树(满二叉树)
919.完全二叉树插入器(完全二叉树)
958.二叉树的完全性检验(完全二叉树)


662.二叉树最大宽度(完全二叉树)
将二叉树的下标存储到数组中，根节点下标为 1，左子树结点为 2 * i，右子树下标为 2 * i+1。
class Solution{
	public int widthOfBinaryTree(TreeNode root){
		if(root==null)
			return 0;
		Queue<TreeNode> queue = new LinkedList<>();
		LinkedList<Integer> list = new LinkedList<>();
		queue.add(root);
		list.add(1);
		int res = 1;
		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i=0;i<size;i++){
				TreeNode node = queue.poll();
				Integer curIndex = list.removeFirst();
				if(node.left!=null){
					queue.add(node.left);
					list.add(curIndex*2);
				}
				if(node.right!=null){
					queue.add(cur.right);
					list.add(curIndex*2+1);
				}
			}
			if(list.size()>2){
				res = Math.max(res,list.getLast()-list.getFirst()+1);
			}
		}
		return res;
	}
}


222.完全二叉树的节点个数

对于没有约束的二叉树而言，可以很简单地想到使用下面这个递归的解法：
public int countNodes(TreeNode root) {
    if (root == null){
        return 0;
    }
    return countNodes(root.left) + countNodes(root.right) + 1;
}

但这是一个普适的解法，对于此题给的完全二叉树的特点没有利用起来，进一步考虑如何使用完全二叉树的特点更快解出此题。

首先需要明确完全二叉树的定义：它是一棵空树或者它的叶子节点只出在最后两层，若最后一层不满则叶子节点只在最左侧。
再来回顾一下满二叉的节点个数怎么计算，如果满二叉树的层数为h，则总节点数为：2^h - 1.
那么我们来对 root 节点的左右子树进行高度统计，分别记为 left 和 right，有以下两种结果：
1.left == right。这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。所以左子树的节点总数我们可以直接得到，是 2^left - 1，加上当前这个 root 节点，则正好是 2^left。再对右子树进行递归统计。
2.left != right。说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。同理，右子树节点 +root 节点，总数为 2^right。再对左子树进行递归查找。

关于如何计算二叉树的层数，可以利用下面的递归来算，当然对于完全二叉树，可以利用其特点，不用递归直接算，具体请参考最后的完整代码。
private int countLevel(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(countLevel(root.left),countLevel(root.right)) + 1;
}

如何计算 2^left，最快的方法是移位计算，因为运算符的优先级问题，记得加括号哦。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null){
           return 0;
        } 
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return countNodes(root.right) + (1<<left);
        }else{
            return countNodes(root.left) + (1<<right);
        }
    }
    //完全二叉树计算高度
    private int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }
}
https://leetcode.cn/problems/count-complete-tree-nodes/solution/chang-gui-jie-fa-he-ji-bai-100de-javajie-fa-by-xia/






894.所有可能的满二叉树(满二叉树)
919.完全二叉树插入器(完全二叉树)
958.二叉树的完全性检验(完全二叉树)