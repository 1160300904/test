package GUI;

import centralObject.*;
import circularOrbit.*;
import physicalObject.*;

public class Memento {

  private CircularOrbit<Nucleus, Electron> state;

  // rep exposure
  Memento(CircularOrbit<Nucleus, Electron> state) {
    this.state = state;
  }

  CircularOrbit<Nucleus, Electron> getState() {
    return this.state;
  }
}
