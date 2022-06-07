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



