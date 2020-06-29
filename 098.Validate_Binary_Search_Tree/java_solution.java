//Recursion, setting 2 boundaries for each check, beats 100%
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isBST(root.left, (long)Integer.MIN_VALUE - 1, root.val) &&
            isBST(root.right, root.val, (long)Integer.MAX_VALUE + 1);
    }
    
    private boolean isBST(TreeNode node, long lowerBund, long upperBund){
        if(node == null) return true;
        if(node.val <= lowerBund || node.val >= upperBund) return false;
        return(isBST(node.left, lowerBund, node.val) && 
              isBST(node.right, node.val, upperBund));
    }
}

//Iterative in-order traverse
class Solution {
    public boolean isValidBST(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pollFirst();
            if(pre!=null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }
}
