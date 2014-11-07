package com.iceman.yangtze.util;

import java.io.*;

public class FileUtil {
	public static String FOLDER = android.os.Environment.getExternalStorageDirectory().getPath() + "/carcheck/";

	/**
	 * 将文件读取成string
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static String readStringFromFile(String fileName) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		String str = "";
		try {
			reader = new BufferedReader(new FileReader(FOLDER + fileName));
			str = reader.readLine();
			while (str != null) {
				builder.append(str);
				str = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			return "";
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return builder.toString();
	}

	/**
	 * 写入文件
	 */
	public static void printToFile(String fileData, String filename) {
		fileData += "\r\n";
		printToFile(fileData, filename, true);

	}

	/**
	 * 清空并写入
	 */
	public static void printToNewFile(String fileData, String filename) {
		printToFile(fileData, filename, false);

	}

	public static void printToFile(String fileData, String filename, boolean isAppend) {
		try {
			File fileDir = new File(FileUtil.FOLDER);
			File file = new File(FileUtil.FOLDER + filename);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
				osw.write(fileData);
				osw.close();
			} else if (file.exists()) {
				FileOutputStream fos = new FileOutputStream(FileUtil.FOLDER + filename, isAppend);
				PrintWriter pw = new PrintWriter(fos);
				pw.write(fileData);
				pw.flush();
				pw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
