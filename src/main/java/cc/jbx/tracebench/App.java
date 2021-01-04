package cc.jbx.tracebench;

import java.util.Optional;

import cc.jbx.tracebench.metrics.Metric;
import cc.jbx.tracebench.metrics.MetricDAO;
import cc.jbx.tracebench.metrics.MetricsHealthCheck;
import cc.jbx.tracebench.metrics.MetricsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration> {
  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void run(AppConfiguration appConfiguration, Environment environment) {
    MetricDAO metricsDAO = new MetricDAO() {
      @Override
      public Optional<Metric> getByEntityId(String entityId) {
        return Optional.empty();
      }

      @Override
      public boolean isHealthy() {
        return true;
      }
    };
    final MetricsHealthCheck metricsHealthCheck = new MetricsHealthCheck(metricsDAO);
    environment.healthChecks().register("metrics", metricsHealthCheck);
    final MetricsResource metricsResource = new MetricsResource(metricsDAO);
    environment.jersey().register(metricsResource);
  }
}
