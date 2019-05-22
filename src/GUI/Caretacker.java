package GUI;

import centralObject.Nucleus;
import circularOrbit.CircularOrbit;
import physicalObject.Electron;
import java.util.*;

public class Caretacker {

  private Stack<Memento> list = new Stack<>();

  void addMemento(Memento m) {
    list.push(m);
  }

  Memento getMemento() {
    return this.list.pop();
  }
}
