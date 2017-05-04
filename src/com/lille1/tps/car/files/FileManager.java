package com.lille1.tps.car.files;

import java.util.HashMap;
import java.util.Map;

public class FileManager {
	private static FileManager instance;


	protected Map<String, Boolean> writingFiles;
	protected Map<String, Integer> readFiles;

	private FileManager() {
		writingFiles = new HashMap<>();
		readFiles = new HashMap<>();
	}

	public static FileManager getInstance() {
		if(instance == null) {
			instance = new FileManager();
		}
		return instance;
	}
	public boolean writing(String fileName) {
		return this.writingFiles.containsKey(fileName) && this.writingFiles.get(fileName);
	}

	public boolean reading(String fileName) {
		return readFiles.get(fileName) != null && readFiles.get(fileName) > 1;
	}

	public void startWriting(String fileName) {
		this.writingFiles.put(fileName, true);
	}

	public void startReading(String fileName) {
		if (readFiles.get(fileName) == null) {
			this.readFiles.put(fileName, 0);
		}
		this.readFiles.put(fileName, readFiles.get(fileName) + 1);
	}

	public void stopWriting(String fileName) {
		this.writingFiles.put(fileName, false);
	}

	public void stopReading(String fileName) {
		this.readFiles.put(fileName, readFiles.get(fileName) - 1);
		if (readFiles.get(fileName) == 0) {
			this.readFiles.remove(fileName);
		}
	}
}
