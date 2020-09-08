package com.example.ilegra;

import java.io.File;
import java.time.Duration;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileWatcherConfig {

	private static final Logger log = LoggerFactory.getLogger(FileWatcherConfig.class);

	@Value("${in-folder}")
	private String folderPath;

	@Bean
	public FileSystemWatcher fileSystemWatcher() {
		FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(5000L),
				Duration.ofMillis(3000L));
		fileSystemWatcher.addSourceDirectory(new File(folderPath));
		fileSystemWatcher.addListener(new IlegraFileChangeListener());
		fileSystemWatcher.start();
		log.info("started fileSystemWatcher");
		return fileSystemWatcher;
	}

	@PreDestroy
	public void onDestroy() throws Exception {
		fileSystemWatcher().stop();
	}
}
