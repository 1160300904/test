package physicalObject;

/**
 * This is an interface represents the electrons around the nucleus. It is
 * IMMUTABLE.
 *
 */
public interface Electron extends PhysicalObject {
    /**
     * Get the track number this electron is currently on.
     * 
     * @return the track number this electron is currently on.
     */
    public int getTrackItOn();
}
