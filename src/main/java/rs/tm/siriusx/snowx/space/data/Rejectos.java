package rs.tm.siriusx.snowx.space.data;

import lombok.Getter;
import rs.tm.siriusx.snowx.space.attacker.AttackerType;

/**
 * This class represent common specific data for Rejectos attackers.
 *
 * @author Mohamed Taman
 * @version 0.2
 */
public final class Rejectos extends Pattern {

  @Getter private AttackerType type;
  @Getter private float threshold = 0.6f;

  public Rejectos(AttackerType type, String filePath) {
    this.type = type;
    loadPatternDataFrom(filePath);
  }

  public Rejectos(AttackerType type, String filePath, String threshold) {
    this(type, filePath);

    if (threshold != null && !threshold.isEmpty()) {
      this.threshold = Float.parseFloat(threshold);
    }
  }
}
