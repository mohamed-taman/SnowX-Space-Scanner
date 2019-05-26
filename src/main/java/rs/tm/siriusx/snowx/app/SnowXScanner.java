package rs.tm.siriusx.snowx.app;

import rs.tm.siriusx.snowx.app.handler.ArgumentHandler;
import rs.tm.siriusx.snowx.app.handler.ArgumentHandler.Flag;
import rs.tm.siriusx.snowx.space.RejectosDetector;
import rs.tm.siriusx.snowx.space.attacker.Attacker;
import rs.tm.siriusx.snowx.space.data.Rejectos;
import rs.tm.siriusx.snowx.space.data.Space;

import java.nio.file.Path;
import java.util.List;

import static java.lang.System.out;
import static rs.tm.siriusx.snowx.space.attacker.AttackerType.SHIP;
import static rs.tm.siriusx.snowx.space.attacker.AttackerType.TORPEDO;

/**
 * This class is the main application runner, accepts the 2 ships files and try to detect them in
 * the third space file with a specific threshold if supplied.
 *
 * <p>Example about the arguments:
 *
 * <p>--ship "HPship.snw" --torpedo "HPTorpedo.snw" --space "TestData.snw" --threshold 0.60 Or short
 * version:
 *
 * <p>-s "TestData.snw" -t 0.60 -sf "HPship.snw" -tf "HPTorpedo.snw"
 *
 * @author Mohamed Taman
 * @version 0.1
 * @since 0.1
 */
public class SnowXScanner {

  public static void main(String[] args) {

    // read and parse arguments
    var handler = ArgumentHandler.parse(args);

    // Load Rejectos attackers from files
    List<Rejectos> attackers =
        List.of(
            new Rejectos(SHIP, Path.of(handler.valueOf(Flag.SHIP)).toString(),
                    handler.valueOf(Flag.THRESHOLD)),
            new Rejectos(TORPEDO, Path.of(handler.valueOf(Flag.TORPEDO)).toString()));

    // search for Rejectos attackers
    List<Attacker> allAttackers =
        RejectosDetector.detectAttackers(
            attackers, new Space(Path.of(handler.valueOf(Flag.SPACE)).toString()));

    // Print the results
    printRejectosData(allAttackers);
  }

  private static void printRejectosData(List<Attacker> attackers) {

    out.println("\n Detected attackers:");
    out.println("--------------------");

    // Print attackers information
    attackers.forEach(out::println);

    out.println("-----------------------------------------");
    out.printf("Number of Rejectos attackers found = %d %n%n", attackers.size());
  }
}
