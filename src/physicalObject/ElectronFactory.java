package physicalObject;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a factory interface of Electron.
 *
 */
public class ElectronFactory {
  
  private static Map<Integer,Electron> prototypes=new HashMap<>();
  
  static {
    Electron e1= new ConcreteElectron(1);
    Electron e2= new ConcreteElectron(2);
    Electron e3= new ConcreteElectron(3);
    Electron e4= new ConcreteElectron(4);
    Electron e5= new ConcreteElectron(5);
    Electron e6= new ConcreteElectron(6);
    Electron e7= new ConcreteElectron(7);
    Electron e8= new ConcreteElectron(8);
    Electron e9= new ConcreteElectron(9);
    Electron e10= new ConcreteElectron(10);
    prototypes.put(1,e1);prototypes.put(2,e2);prototypes.put(3,e3);
    prototypes.put(4,e4);prototypes.put(5,e5);prototypes.put(6,e6);
    prototypes.put(7,e7);prototypes.put(8,e8);prototypes.put(9,e9);prototypes.put(10,e10);
  }
  /**
   * get an instance of an Electron.
   */
  public static Electron getInstance(int track) {
    Electron e=prototypes.get(track);
    if(e!=null) {
      try {
        return (Electron)((ConcreteElectron)e).clone();
      } catch (CloneNotSupportedException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    e = new ConcreteElectron(track);Electron e1 = null;
    try {
      e1=(Electron)((ConcreteElectron)e).clone();
    } catch (CloneNotSupportedException e11) {
      // TODO Auto-generated catch block
      e11.printStackTrace();
    }
    prototypes.put(track, e1);
    return e;
  }
}
