package rs.tm.siriusx.snowx.space.attacker.impl;

import rs.tm.siriusx.snowx.space.attacker.Attacker;
import rs.tm.siriusx.snowx.space.data.Rejectos;

import static rs.tm.siriusx.snowx.space.attacker.AttackerType.SHIP;

/**
 * This class is the a Rejectos Ship attackers.
 *
 * @author Mohamed Taman
 * @version 0.12
 */
public class HPShip extends Attacker {

  public HPShip() {
    setType(SHIP);
  }

  public HPShip(Rejectos pattern, int xSpaceCoordinate, int ySpaceCoordinate) {
    setType(SHIP);
    setPattern(pattern);
    setXSpaceCoordinate(xSpaceCoordinate);
    setYSpaceCoordinate(ySpaceCoordinate);
  }
}
