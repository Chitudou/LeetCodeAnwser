508.出现次数最大的子树元素和
class Solution {
    int max = 0;
    Map<Integer,Integer> map = new HashMap<>();
    public int[] findFrequentTreeSum(TreeNode root) {
        if(root==null){
            return new int[0];
        }
        helper(root);
        ArrayList<Integer> list = new ArrayList<>(); 
        for(int i : map.keySet()){
            if(map.get(i) == max){
                list.add(i);
            }
        }
        int[] result = new int[list.size()];
        for(int i = 0; i<result.length; i++){
            result[i] = list.get(i);
        }
        return result;

    }

    public int helper(TreeNode root){
        if(root==null)
            return 0;
        int leftCount = helper(root.left);
        int rightCount = helper(root.right);
        int count = root.val+leftCount+rightCount;
        map.put(count,map.getOrDefault(count,0)+1);
        max = Math.max(map.get(count),max);
        return max;
    }
}
我们可以观察一下helper，这不是就是二叉树的后序遍历吗？所以一般来说，二叉树的题目基本就以下几种思路：

先序遍历（深度优先搜索）
中序遍历（深度优先搜索）（尤其二叉搜索树）
后序遍历（深度优先搜索）
层序遍历（广度优先搜索）
基本树的题跑不了要这样。

思路：
https://leetcode-cn.com/problems/most-frequent-subtree-sum/solution/508-chu-xian-ci-shu-zui-duo-de-zi-shu-yuan-su-he-b/



623.在二叉树中增加一行
class Solution{
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(root==null)
            return root;
        if(depth==1){
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        if(depth==2){
            TreeNode leftNode = new TreeNode(val);
            TreeNode rightNode = new TreeNode(val);

            leftNode.left = root.left;
            rightNode.right = root.right;

            root.left = leftNode;
            root.right = rightNode;
            return root;
        }
        addOneRow(root.left,val,depth-1);
        addOneRow(root.right,val,depth-1);
        return root;
    }
}







872.叶子相似的树
思路：将递归写成迭代的形式
https://leetcode-cn.com/problems/leaf-similar-trees/solution/gong-shui-san-xie-yi-ti-shuang-jie-di-gu-udfc/



104.二叉树的最大深度
class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1+Math.max(left,right);
    } 
}

111.二叉树的最小深度
class Solution {
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(root.left==null&&root.right!=null){
            return right+1;
        }
        if(root.left!=null&&root.right==null){
            return left+1;
        }
        return Math.min(left,right)+1;
    }
}
来自代码随想录

110.平衡二叉树
class Solution {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getHeight(root.right);
        if (right == -1) {
            return -1;
        }
        // 左右子树高度差大于1，return -1表示已经不是平衡树了
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
来自代码随想录

559.N叉树的最大深度
class Solution {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        for (Node node : root.children) {
            max = Math.max(max, maxDepth(node));
        }
        return max + 1;
    }
}
来自代码随想录

235.二叉搜索树的最近公共祖先
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root,p,q);
    }

    public TreeNode helper(TreeNode root,TreeNode p,TreeNode q){
        if(root==null){
            return null;
        }

        if(root.val>p.val&&root.val>q.val){
            TreeNode left = helper(root.left,p,q);
            if(left!=null){
                return left;
            }
        }
        
        if(root.val<p.val&&root.val<q.val){
            TreeNode right = helper(root.right,p,q);
            if(right!=null){
                return right;
            }
        }

        return root;
    }
}
来自代码随想录

236.二叉树的最近公共祖先
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==p||root==q||root==null){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left==null&&right==null){
            return null;
        }
        if(left==null){
            return right;
        }
        if(right==null){
            return left;
        }
        return root;
    }
}来自代码随想录

865.具有所有最深结点的最小子树/1123.最深叶节点的最近公共祖先
class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if(left==right){
            return root;
        }else if(left<right){
            return lcaDeepestLeaves(root.right);
        }else{
            return lcaDeepestLeaves(root.left);
        }
    }

    public int maxDepth(TreeNode root){
        if(root==null)
            return 0;
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}
https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves/solution/liang-chong-si-lu-yi-chong-qian-xu-bian-li-yi-chon/