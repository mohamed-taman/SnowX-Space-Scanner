package rs.tm.siriusx.snowx.space.data;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * This class is parent class for all shapes patterns with all common data.
 *
 * @author Mohamed Taman
 * @version 0.8
 */
abstract class Pattern {

  @Getter private char[][] data;
  @Getter private int width;
  @Getter private int height;
  @Getter private float totalPoints;

  void loadPatternDataFrom(String filePath) {

    // read test patternData
    List<String> testLines;

    try {
      // read lines of the text file
      testLines = Files.readAllLines(Path.of(Objects.requireNonNull(filePath)));
    } catch (IOException e) {
      System.err.printf("Invalid file or file is not exist: %s %n", e.getMessage());
      return;
    }
    // Initialize data array
    data = new char[testLines.size()][];

    for (int i = 0; i < testLines.size(); i++) {
      data[i] = testLines.get(i).toCharArray();

      // Calculate the number if '+' sign
      for (char element : testLines.get(i).toCharArray()) {
        if (element == '+') {
          totalPoints++;
        }
      }
    }

    // Assign pattern height and length.
    width = data[0].length;
    height = data.length;
  }
}
