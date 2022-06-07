二、排列、组合、子集相关问题
46.全排列
47.全排列II
39.组合总和
40.组合总和II
77.组合
78.子集
90.子集II
60.第k个排列
93.复原IP地址


46.全排列
class Solution{
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		LinkedList<Integer> path = new LinkedList<>();
		boolean[] used = new boolean[nums.length];

		if(nums.length==0){
			return res;
		}
		helper(nums,res,path,used);
		return res;
    }

    public void helper(int[] nums,List<List<Integer>> res,
    	LinkedList<Integer> path,boolean[] used){
    	if(path.size()==nums.length){
    		res.add(new ArrayList<>(path));
    	}
    	for(int i=0;i<nums.length;i++){
    		if(used[i]){
    			continue;
    		}
    		used[i] = true;
    		path.add(nums[i]);
    		helper(nums,res,path,used);
    		path.removeLast();
    		used[i] = false;
    	}
    }
}
47.全排列II
39.组合总和
40.组合总和II
77.组合
78.子集
90.子集II
60.第k个排列
93.复原IP地址

















