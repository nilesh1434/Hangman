import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	@Test
	void testReadFromClient() {
		ClientGui readerGui = new ClientGui();
		readerGui.reader.setGamePhase("I Was Sent By Server");
		assertEquals(readerGui.reader.getGamePhase(), "I Was Sent By Server");
	}
	
	@Test
	void testReadFromClient2() {
		ClientGui readerGui = new ClientGui();
		readerGui.setReader("I Was Sent By Server");
		readerGui.updatedMessage();
		assertEquals(readerGui.reader.getGamePhase(), "I Was Sent By Server");
	}

}
