package rs.tm.siriusx.snowx.space.attacker.impl;

import rs.tm.siriusx.snowx.space.attacker.Attacker;
import rs.tm.siriusx.snowx.space.data.Rejectos;

import static rs.tm.siriusx.snowx.space.attacker.AttackerType.TORPEDO;

/**
 * This class is the a Rejectos Torpedo attackers.
 *
 * @author Mohamed Taman
 * @version 0.12
 */
public class HPTorpedo extends Attacker {

  public HPTorpedo() {
    setType(TORPEDO);
  }

  public HPTorpedo(Rejectos pattern, int xSpaceCoordinate, int ySpaceCoordinate) {
    setType(TORPEDO);
    setPattern(pattern);
    setXSpaceCoordinate(xSpaceCoordinate);
    setYSpaceCoordinate(ySpaceCoordinate);
  }
}
