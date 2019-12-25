package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class Num_3 {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int max = 0;
            for (int i = 0; i < s.length(); i++) {
                Set<Character> set = new HashSet<Character>();
                for (int j = i; j < s.length(); j++) {
                    if (!set.contains(s.charAt(j))) {
                        set.add(s.charAt(j));
                    } else {
                        break;
                    }
                }
                if (max < set.size()) {
                    max = set.size();
                }
            }
            return max;
        }
        }
    }