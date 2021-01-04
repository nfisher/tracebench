package cc.jbx.tracebench.metrics;

import com.codahale.metrics.health.HealthCheck;

public class MetricsHealthCheck extends HealthCheck {
  private final MetricDAO metricDAO;

  public MetricsHealthCheck(MetricDAO metricsDAO) {
    this.metricDAO = metricsDAO;
  }

  @Override
  protected Result check() throws Exception {
    if(!metricDAO.isHealthy()) {
      return Result.unhealthy("error connecting to store");
    }
    return Result.healthy();
  }
}
