package huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
//更改原版后的上传的答案 
class Job {
	int id;
	int time;
	int left;
	int start;
	int pry;
}

public class diaodu2 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		try {
			s = br.readLine();

			String[] jobs = s.split("\\|");
			Job[] Jobs = new Job[jobs.length];
			for (int i = 0; i < jobs.length; i++) {
				String[] cs = jobs[i].substring(1, jobs[i].length() - 1).split("\\.");
				Jobs[i] = new Job();
				Jobs[i].id = Integer.parseInt(cs[0]);
				Jobs[i].pry = Integer.parseInt(cs[1]);
				Jobs[i].start = Integer.parseInt(cs[2]);
				Jobs[i].time = Integer.parseInt(cs[3]);
				Jobs[i].left = Jobs[i].time;
			}

			int[] queue = new int[200];
			for (int i = 0; i < 200; i++) {
				Job nowJob = null;
				for (Job job : Jobs) {
					if (job.start <= i && job.left > 0) {
						if (nowJob == null || job.pry > nowJob.pry)
							nowJob = job;
					}
				}
				if (nowJob != null) {
					queue[i] = nowJob.id;
					nowJob.left--;
				}
			}
			int prev = -1;
			int count = 0;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 200; i++) {
				if (queue[i] != prev) {
					sb.append(count + "|");
					count = 1;
					sb.append(queue[i] + ".");
					prev = queue[i];
				} else
					count++;

			}
			sb.append(count);
			sb.delete(0, 2);
			System.out.println(sb);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
