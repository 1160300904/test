package physicalObject;

/**
 * This is a factory interface of Electron.
 *
 */
public class ElectronFactory {
  /**
   * get an instance of an Electron.
   */
  public static Electron getInstance(int track) {
    return new ConcreteElectron(track);
  }
}
