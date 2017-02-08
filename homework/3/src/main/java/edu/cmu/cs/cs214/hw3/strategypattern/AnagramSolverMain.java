package edu.cmu.cs.cs214.hw3.strategypattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnagramSolverMain {

	public static void main(String[] args) {
		long start = System.currentTimeMillis(); // 获取开始时
		String filePath = "src/main/resources/" + args[0];
		for (int i = 1; i < args.length; i++) {
			AnagramSolver ana = new AnagramSolver(filePath);
			HeapGenerator<String> generator = new HeapGenerator<>(ana);
			String[] temp = args[i].split("");
			generator.perm(temp, temp.length);
			if (ana.getResult().size() == 0) {
				System.out.println(args[i] + ": no solution!");
			} else {
				List<String> result = new ArrayList<String>();
				Set<String> resultSet = ana.getResult();
				result.addAll(resultSet);
				System.out.println(args[i] + ": " + result);
			}

		}
	}
}
