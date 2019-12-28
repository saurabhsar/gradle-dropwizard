package saurabh.araiyer.test;

import saurabh.araiyer.test.resources.SampleResource;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

public class ManagerModule implements Module {

  @Override
  public void configure(Binder binder) {
    binder.bind(SampleResource.class).in(Singleton.class);
  }
}
