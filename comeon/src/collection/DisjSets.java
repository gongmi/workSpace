package collection;

/**
 * Disjoint set class, using union by rank and path compression.
 */
public class DisjSets {
	private int[] s;

	public DisjSets(int numElements) {
		s = new int[numElements];
		for (int i = 0; i < s.length; i++)
			s[i] = -1;
	}

	public void union(int root1, int root2) {
		if (s[root2] < s[root1]) // root2 is deeper
			s[root1] = root2; // Make root2 new root
		else {
			if (s[root1] == s[root2])
				s[root1]--; // Update height if same
			s[root2] = root1; // Make root1 new root
		}
	}

	/**
	 * Perform a find with path compression.
	 */
	public int find(int x) {
		if (s[x] < 0)
			return x;
		else
			return s[x] = find(s[x]);
	}
}
