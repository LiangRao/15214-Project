package edu.cmu.cs.cs214.hw3.strategypattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class AnagramSolver implements ProblemSolver<String> {
	private String filePath;
	private Set<String> result;

	public AnagramSolver(String filePath) {
		this.filePath = filePath;
		result = new HashSet<String>();
	}

	/**
	 * return the value of result variable
	 * 
	 * @return the value of result variable
	 */
	public Set<String> getResult() {
		return result;
	}

	@Override
	public void solveProblem(String[] array) {
		try {
			StringBuilder temp = new StringBuilder();
			for (int i = 0; i < array.length; i++) {
				temp.append(array[i]);
			}
			String cmp = temp.toString();

			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (lineTxt.equals(cmp)) {
						if (!result.contains(lineTxt)) {
							result.add(lineTxt);
							// System.out.println(lineTxt);
						}

					}
				}
				read.close();
			} else {
				System.out.println("Can not find the specific file");
			}
		} catch (Exception e) {
			System.out.println("Error occurred in reading file");
			e.printStackTrace();
		}
	}
}
