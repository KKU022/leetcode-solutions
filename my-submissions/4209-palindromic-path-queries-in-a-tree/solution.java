import java.util.*;

class Solution {
    private int timer = 0;
    private int[] tin, tout;
    private int[][] up;
    private int[] depth;
    private List<Integer>[] adj;

    // Binary Indexed Tree for Range XOR Update / Point XOR Query
    static class FenwickTree {
        int n;
        int[] tree;

        FenwickTree(int n) {
            this.n = n;
            this.tree = new int[n + 2];
        }

        void update(int i, int delta) {
            for (; i <= n; i += i & -i) {
                tree[i] ^= delta;
            }
        }

        int query(int i) {
            int sum = 0;
            for (; i > 0; i -= i & -i) {
                sum ^= tree[i];
            }
            return sum;
        }

        void rangeUpdate(int l, int r, int delta) {
            update(l, delta);
            update(r + 1, delta);
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int log = 1;
        while ((1 << log) <= n) log++;

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[n][log];

        dfs(0, 0, 0, log);

        char[] chars = s.toCharArray();
        FenwickTree bit = new FenwickTree(n);

        // Initialize Fenwick tree with initial path XOR values
        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');
            bit.rangeUpdate(tin[i], tout[i], mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);
                if (chars[u] != newChar) {
                    int delta = (1 << (newChar - 'a')) ^ (1 << (chars[u] - 'a'));
                    chars[u] = newChar;
                    bit.rangeUpdate(tin[u], tout[u], delta);
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lca = getLCA(u, v, log);

                int xorU = bit.query(tin[u]);
                int xorV = bit.query(tin[v]);
                int lcaCharMask = 1 << (chars[lca] - 'a');

                int pathMask = xorU ^ xorV ^ lcaCharMask;

                // At most one bit set means palindrome can be formed
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private void dfs(int u, int p, int d, int log) {
        tin[u] = ++timer;
        up[u][0] = p;
        depth[u] = d;

        for (int i = 1; i < log; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, d + 1, log);
            }
        }

        tout[u] = timer;
    }

    private int getLCA(int u, int v, int log) {
        if (depth[u] < depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }

        for (int i = log - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }

        if (u == v) return u;

        for (int i = log - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }

        return up[u][0];
    }
}
