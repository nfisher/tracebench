package cc.jbx.tracebench;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration> {
  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {

  }
}
