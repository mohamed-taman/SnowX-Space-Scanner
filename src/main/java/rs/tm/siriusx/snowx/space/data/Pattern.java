package rs.tm.siriusx.snowx.space.data;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

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
    final var i = new AtomicInteger(0);

    testLines.forEach(line ->{
      data[i.getAndIncrement()] = line.toCharArray();
      // Calculate the number if '+' sign
      totalPoints += line
              .chars()
              .filter(ch -> ch == '+')
              .count();
    });

    // Assign pattern height and length.
    width = data[0].length;
    height = data.length;
  }
}
