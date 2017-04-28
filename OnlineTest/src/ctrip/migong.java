package ctrip;

import java.util.Arrays;
import java.util.Scanner;

public class migong {
	public static int MAXSTATE = 1000000;

	static class state {
		int[] state = new int[9];

		public state(int[] state) {
			this.state = state;
		}
	}

	static state[] st = new state[MAXSTATE];
	static state goal;
	static int[] dist = new int[MAXSTATE];

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int front = 1, rear = 2;

	static int bfs() {
		init_lookup_table();
		while (front < rear) {
			state s = st[front];
			if (Arrays.equals(s.state, goal.state)) {
				return front;
			}

			int z;
			for (z = 0; z < 9; z++) {
				if (0 == s.state[z]) {
					break;
				}
			}
			int x = z / 3;
			int y = z % 3;

			for (int d = 0; d < 4; d++) {
				int newx = x + dx[d];
				int newy = y + dy[d];
				int newz = newx * 3 + newy;
				if (newx >= 0 && newx < 3 && newy >= 0 && newy < 3) {
					int[] temp = Arrays.copyOf(s.state, 9);
					st[rear] = new state(temp);
					st[rear].state[newz] = s.state[z];
					st[rear].state[z] = s.state[newz];
					dist[rear] = dist[front] + 1;
					if (try_to_insert(st[rear])) {
						rear++;
					}
				} else {
				}
			}
			front++;
		}
		return 0;
	}

	static boolean[] vis = new boolean[362880];
	static int[] fact = new int[9];

	static boolean try_to_insert(state t) {
		int code = 0;
		for (int i = 0; i < 9; i++) {
			int cnt = 0;
			for (int j = i + 1; j < 9; j++) {
				if (t.state[j] < t.state[i])
					cnt++;
			}
			code += fact[8 - i] * cnt;
		}
		if (vis[code]) {
			return false;
		} else {
			vis[code] = true;
			return true;
		}
	}

	static void init_lookup_table() {
		fact[0] = 1;
		for (int i = 1; i < 9; i++)
			fact[i] = fact[i - 1] * i;
	}

	public static void main(String[] args) {
		dist[1] = 0;
		Scanner scan = new Scanner(System.in);
		int[] a = new int[9];
		String[] s;
		int i = 0;
		while (i < 3) {
			s = scan.nextLine().split(" ");
			a[0 + 3 * i] = Integer.valueOf(s[0]);
			a[1 + 3 * i] = Integer.valueOf(s[1]);
			a[2 + 3 * i] = Integer.valueOf(s[2]);
			++i;
		}
		st[1] = new state(a);
		goal = new state(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 0 });
		System.out.println(dist[bfs()]);
	}
}