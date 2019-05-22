package physicalObject;

/**
 * This is an implementation of Interface Electron.
 *
 */
class ConcreteElectron implements Electron {

  private int track;

  /*
   * Abstract function: 1.track represents the track number the electron on.
   */
  /*
   * Rep Invariant: 1.track must be a positive number.
   */
  void checkRep() {
    assert track > 0;
  }

  /**
   * create an instance of an electron.
   */
  ConcreteElectron(int track) {
    assert track > 0;
    this.track = track;
    checkRep();
  }

  @Override
  public String getName() {
    return "";
  }

  @Override
  public int getTrackItOn() {
    return this.track;
  }
}
