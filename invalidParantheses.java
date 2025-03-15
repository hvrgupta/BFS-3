// Time Complexity : O(N*2^N) -- N for valid check, 2^N for BFS
// Space Complexity : O(N*2^N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    List<String> res;
    HashSet<String> set;

    public List<String> removeInvalidParentheses(String s) {
        this.res = new ArrayList<>();
        this.set = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(s);
        set.add(s);
        boolean flag = false;
        while (!q.isEmpty() && !flag) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (isValid(curr)) {
                    flag = true;
                    res.add(curr);
                } else {
                    if (!flag) {
                        for (int k = 0; k < curr.length(); k++) {
                            char c = curr.charAt(k);
                            if (Character.isAlphabetic(c))
                                continue;
                            String child = curr.substring(0, k) + curr.substring(k + 1);
                            if (!set.contains(child)) {
                                q.add(child);
                                set.add(child);
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

    private boolean isValid(String s) {
        int cnt = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                cnt++;
            else if (s.charAt(i) == ')') {
                if (cnt == 0)
                    return false;
                else
                    cnt--;
            }
        }

        return cnt == 0;
    }
}
