package rs.tm.siriusx.snowx.space;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import rs.tm.siriusx.snowx.space.data.Rejectos;
import rs.tm.siriusx.snowx.space.data.Space;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static rs.tm.siriusx.snowx.space.attacker.AttackerType.SHIP;
import static rs.tm.siriusx.snowx.space.attacker.AttackerType.TORPEDO;
/**
 * This class is test cases for variety of a Rejectos attackers detection.
 *
 * @author Mohamed Taman
 * @version 0.12
 */
class RejectosDetectorTest {

    private static List<Rejectos> attackers;
    private static Space space;

    @BeforeAll
    static void initialize() {
        space = new Space(getResource("TestData.snw"));
    }

    @AfterAll
    static void tearDown() {
        attackers = null;
    }

    private static String getResource(String fileName) {
        try {
            fileName =
                    Paths.get(Objects
                            .requireNonNull(RejectosDetectorTest
                                    .class
                                    .getClassLoader()
                                    .getResource(fileName))
                            .toURI())
                            .toString();
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
        }
        return fileName;
    }

    @BeforeEach
    public void setup() {
        attackers = null;
    }

    @DisplayName("Test that all patterns are loaded successfully.")
    @Test
    void testValidPatternDataLoaded() {

        attackers =
                List.of(
                        new Rejectos(SHIP, getResource("HPship.snw")),
                        new Rejectos(TORPEDO, getResource("HPTorpedo.snw")));

        assertNotNull(attackers.get(0).getData(), "Ship pattern data is not loaded.");

        assertNotNull(attackers.get(1).getData(), "Torpedo pattern data is not loaded.");

        assertNotNull(space.getData(), "Space pattern data is not loaded.");
    }

    @DisplayName("Test threshold and returned count of Ships")
    @ParameterizedTest(name = "With threshold \"{0}\", detected Ships count should be {1}")
    @CsvSource({"0.60, 5", "0.70, 2", "0.50, 19"})
    void testShipsCountForThreshold(String threshold, int count) {

        attackers = List.of(new Rejectos(SHIP, getResource("HPship.snw"), threshold));

        assertEquals(count, RejectosDetector.detectAttackers(attackers, space).size());
    }

    @DisplayName("Test threshold and returned count of Torpedos")
    @ParameterizedTest(name = "With threshold \"{0}\", detected Torpedos count should be {1}")
    @CsvSource({"0.60, 3", "0.70, 2", "0.50, 25"})
    void testTorpedoCountForThreshold(String threshold, int count) {

        attackers = List.of(new Rejectos(TORPEDO, getResource("HPTorpedo.snw"), threshold));

        assertEquals(count, RejectosDetector.detectAttackers(attackers, space).size());
    }

    @DisplayName("Test threshold and count of attackers")
    @ParameterizedTest(name = "With threshold \"{0}\", detected attackers count should be {1}")
    @CsvSource({"0.60, 8", "0.70, 4", "0.50, 44"})
    void testCountOfAttackersThreshold(String threshold, int count) {

        attackers =
                List.of(
                        new Rejectos(SHIP, getResource("HPship.snw"), threshold),
                        new Rejectos(TORPEDO, getResource("HPTorpedo.snw"), threshold));

        assertEquals(
                count,
                RejectosDetector.detectAttackers(attackers, space).size(),
                "Detected attackers counts should be: " + count + ", for threshold: " + threshold);
    }
}
