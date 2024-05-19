package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.entity.JsonTestEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class JsonTests {
  private final ClassLoader cl = JsonTests.class.getClassLoader();

  @Test
  void checkJson() throws Exception {
    try (Reader reader = new InputStreamReader(Objects.requireNonNull(cl.getResourceAsStream("test.json")))) {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonTestEntity jsonTest = objectMapper.readValue(reader, JsonTestEntity.class);

      Assertions.assertEquals("0001", jsonTest.getId());
      Assertions.assertEquals("donut", jsonTest.getType());
      Assertions.assertEquals("Cake", jsonTest.getName());

      Assertions.assertEquals("images/0001.jpg", jsonTest.getImage().getUrl());
      Assertions.assertEquals(200, jsonTest.getImage().getWidth());
      Assertions.assertEquals(200, jsonTest.getImage().getHeight());

      Assertions.assertEquals("Molecule Man", jsonTest.getMembers()[0].getName());
      Assertions.assertEquals("Dan Jukes", jsonTest.getMembers()[0].getSecretIdentity());
      Assertions.assertEquals("Radiation resistance", jsonTest.getMembers()[0].getPowers()[0]);


    }
  }
}
