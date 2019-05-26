package rs.tm.siriusx.snowx.space.attacker;

import lombok.Data;
import rs.tm.siriusx.snowx.space.data.Rejectos;

/**
 * This class is the parent of all attackers information.
 *
 * @author Mohamed Taman
 * @version 0.7
 */
@Data
public abstract class Attacker {

  private Rejectos pattern;

  private AttackerType type;

  /*
  Where is found in the space at x coordinates
  */
  private int xSpaceCoordinate;

  /*
  Where is found in the space at y coordinates
  */
  private int ySpaceCoordinate;

  @Override
  public String toString() {

    return String.format(
        "Rejectos Attacker { Type is: %s, found at Space coordinates [ X= %d, Y= %d], "
            + "Pattern [Height = %d, width = %d]}",
        getType(),
        getXSpaceCoordinate(),
        getYSpaceCoordinate(),
        getPattern().getHeight(),
        getPattern().getWidth());
  }
}
