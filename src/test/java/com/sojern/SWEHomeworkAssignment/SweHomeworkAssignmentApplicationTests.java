package com.sojern.SWEHomeworkAssignment;

import com.sojern.SWEHomeworkAssignment.exception.FileDoesNotExists;
import com.sojern.SWEHomeworkAssignment.service.CompareVersionsService;
import com.sojern.SWEHomeworkAssignment.service.ServerService;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SweHomeworkAssignmentApplicationTests {

	@Autowired
	ServerService serverService;

	@Autowired
	CompareVersionsService compareVersionsService;


	@Test
	void contextLoads() {
		assertAll("Context",
				()->assertNotNull(serverService),
				()->assertNotNull(compareVersionsService));
	}

	@Test
	public void serverPing_whenExceptionThrown_thenAssertionSucceeds() throws IOException {
		delete_Server_directory();
		Exception exception = assertThrows(FileDoesNotExists.class, () -> {
			serverService.ping();
		});

		String expectedMessage = "/tmp/ok directory is not exists";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	public void delete_Server_directory() throws IOException {
		Path resourceDirectory = Paths.get("src","main","resources","tmp","ok");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File file = new File(absolutePath);
		FileUtils.deleteDirectory(file);
	}

	@Test
	public void serverPing_success() throws FileDoesNotExists, IOException, URISyntaxException {
		createServerDirectories();
		String actualMessage= serverService.ping();
		assertEquals("OK", actualMessage);
	}
	public void createServerDirectories(){
		Path resourceDirectory = Paths.get("src","main","resources","tmp","ok");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		File theDir = new File(absolutePath);
		if (!theDir.exists()){
			theDir.mkdirs();
		}
	}

	@Test
	public void compareVersions_Control(){
		int actualResult= compareVersionsService.compareVersions("1.1","1.2");
		assertTrue(actualResult < 0);

		int actualResult2= compareVersionsService.compareVersions("1.3","1.2" );
		assertTrue(actualResult2 > 0);

		int actualResult3=compareVersionsService.compareVersions("1.1.0","1.1.0" );
		assertEquals(0, actualResult3);
	}
}
