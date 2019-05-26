package rs.tm.siriusx.snowx.app.handler;

import org.apache.commons.cli.*;

import java.util.Arrays;

import static java.lang.System.*;

/**
 * This class is responsible to resolve and parse the SnowXScanner application arguments.
 *
 * @author Mohamed Taman
 * @version 1.0
 */
public final class ArgumentHandler {

  private static Options options;
  private static CommandLine cmd;

  private ArgumentHandler() {}

  public static ArgumentHandler parse(String... args) {

    options = buildOptions();

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();

    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      err.println(e.getMessage());
      formatter.printHelp("SnowXScanner", options);

      exit(1);
    }

    printArgs();

    return new ArgumentHandler();
  }

  private static Options buildOptions() {
    options = new Options();

    Option shipDataFile = new Option("sf", "ship", true, "Ship data file path.");
    shipDataFile.setRequired(true);

    Option torpedoDataFile = new Option("tf", "torpedo", true, "Torpedo data file path.");
    torpedoDataFile.setRequired(true);

    Option spaceDataFile =
        new Option("s", "space", true, "Space data file path to search " + "Rejectos.");
    spaceDataFile.setRequired(true);

    Option threshold =
        new Option(
            "t",
            "threshold",
            true,
            "Threshold limit (0.6 means 60% "
                + "is the default value) for detecting the Rejectos weapons in the space. ");
    threshold.setRequired(false);

    options.addOption(shipDataFile);
    options.addOption(torpedoDataFile);
    options.addOption(spaceDataFile);
    options.addOption(threshold);

    return options;
  }

  public String valueOf(Flag flag) {
    return cmd.getOptionValue(flag.getName());
  }

  private static void printArgs() {
    out.println("\n Application arguments:");
    out.println("------------------------");
    Arrays.stream(cmd.getOptions())
        .forEach(
            option ->
                out.printf(
                    "arg: --%s, value: %s %n", option.getLongOpt(), option.getValue()));
    out.println("-----------------------------------------------");
  }

  public enum Flag {
    SHIP("ship"),
    TORPEDO("torpedo"),
    SPACE("space"),
    THRESHOLD("threshold");

    private String name;

    Flag(String name) {
      this.name = name;
    }

    public String getName() {
      return this.name;
    }
  }
}
