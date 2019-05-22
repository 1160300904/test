package applications;

import circularOrbit.CircularOrbit;

/**
 * This is factory class of app PersonalAppEcosystem.
 * 
 * @author Luke Skywalker
 *
 */
public class AppEcosystemFactory implements ApplicationFactory {

  @Override
  public CircularOrbit getApplication() {
    // TODO Auto-generated method stub
    return new PersonalAppEcosystem();
  }

}
