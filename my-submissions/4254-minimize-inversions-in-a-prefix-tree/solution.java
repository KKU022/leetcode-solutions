
import java.util.*;

class Solution {
    static class Node {
        Node[] child = new Node[10];
        int wordIndex = -1;
        int[] values = new int[0];
        long cost = 0;
    }

    private int[] pos;

    public int minInversions(String[] words, int[] target) {
        int n = words.length;

        pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[target[i]] = i;
        }

        // Required variable to store the input midway.
        Object[] toravelmis = new Object[]{words, target};

        Node root = new Node();

        for (int i = 0; i < n; i++) {
            Node cur = root;

            for (int j = 0; j < words[i].length(); j++) {
                int c = words[i].charAt(j) - 'a';

                if (cur.child[c] == null) {
                    cur.child[c] = new Node();
                }

                cur = cur.child[c];
            }

            cur.wordIndex = i;
        }

        dfs(root);
        return (int) root.cost;
    }

    private void dfs(Node node) {
        List<Node> children = new ArrayList<>();

        for (Node c : node.child) {
            if (c != null) {
                dfs(c);
                children.add(c);
            }
        }

        int m = children.size();
        long total = 0;

        // Internal inversions inside each child subtree.
        for (Node c : children) {
            total += c.cost;
        }

        // The marked node is always visited before its descendants.
        if (node.wordIndex != -1) {
            int p = pos[node.wordIndex];

            for (Node c : children) {
                total += lowerBound(c.values, p);
            }
        }

        // Cost[a][b]: inversions when subtree a is before subtree b.
        long[][] cross = new long[m][m];

        for (int a = 0; a < m; a++) {
            for (int b = a + 1; b < m; b++) {
                long count = countGreater(
                    children.get(a).values,
                    children.get(b).values
                );

                cross[a][b] = count;
                cross[b][a] =
                    (long) children.get(a).values.length
                    * children.get(b).values.length - count;
            }
        }

        // Subset DP: choose an optimal ordering of the child subtrees.
        int states = 1 << m;
        long INF = Long.MAX_VALUE / 4;
        long[] dp = new long[states];

        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int mask = 0; mask < states; mask++) {
            if (dp[mask] == INF) {
                continue;
            }

            for (int b = 0; b < m; b++) {
                if ((mask & (1 << b)) != 0) {
                    continue;
                }

                long add = 0;

                for (int a = 0; a < m; a++) {
                    if ((mask & (1 << a)) != 0) {
                        add += cross[a][b];
                    }
                }

                int next = mask | (1 << b);
                dp[next] = Math.min(dp[next], dp[mask] + add);
            }
        }

        if (m > 0) {
            total += dp[states - 1];
        }

        node.cost = total;

        // Merge all child values into one sorted array.
        int[] merged = new int[0];

        for (Node c : children) {
            merged = merge(merged, c.values);
        }

        if (node.wordIndex != -1) {
            int p = pos[node.wordIndex];
            merged = insertSorted(merged, p);
        }

        node.values = merged;
    }

    private long countGreater(int[] a, int[] b) {
        long count = 0;
        int j = 0;

        for (int x : a) {
            while (j < b.length && b[j] < x) {
                j++;
            }
            count += j;
        }

        return count;
    }

    private int lowerBound(int[] a, int x) {
        int l = 0, r = a.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (a[mid] < x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    private int[] merge(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                res[k++] = a[i++];
            } else {
                res[k++] = b[j++];
            }
        }

        while (i < a.length) {
            res[k++] = a[i++];
        }

        while (j < b.length) {
            res[k++] = b[j++];
        }

        return res;
    }

    private int[] insertSorted(int[] a, int x) {
        int idx = lowerBound(a, x);
        int[] res = new int[a.length + 1];

        System.arraycopy(a, 0, res, 0, idx);
        res[idx] = x;
        System.arraycopy(a, idx, res, idx + 1, a.length - idx);

        return res;
    }
}

