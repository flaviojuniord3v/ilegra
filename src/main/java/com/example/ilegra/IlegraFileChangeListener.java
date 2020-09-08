package com.example.ilegra;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;

@Component
public class IlegraFileChangeListener implements FileChangeListener {

	private static final Logger log = LoggerFactory.getLogger(FileWatcherConfig.class);

	@Autowired
	private IlegraService service;

	@Override
	public void onChange(Set<ChangedFiles> changeSet) {
		for (ChangedFiles cfiles : changeSet) {
			for (ChangedFile cfile : cfiles.getFiles()) {
				if (!isLocked(cfile.getFile().toPath())) {

					service.processInputFile(cfile.getFile());

					log.info("Operation: " + cfile.getType() + " On file: " + cfile.getFile().getName() + " is done");
				}
			}
		}
	}

	private boolean isLocked(Path path) {
		try (FileChannel ch = FileChannel.open(path, StandardOpenOption.WRITE); FileLock lock = ch.tryLock()) {
			return lock == null;
		} catch (IOException e) {
			return true;
		}
	}

	

}