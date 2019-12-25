package Summary;

import java.util.ArrayList;

/**
 * DFSÇóºÍ
 */
public class DFS_Sum {
    private ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    private ArrayList<Integer> level = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null)
            return list;
        target -= root.val;
        level.add(root.val);
        if (target == 0 && root.left == null && root.right == null) {
            list.add(new ArrayList<>(level));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        level.remove(level.size() - 1);
        list.sort((o1, o2) -> o2.size() - o1.size());
        return list;
    }
}