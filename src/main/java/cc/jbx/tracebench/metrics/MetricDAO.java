package cc.jbx.tracebench.metrics;

import java.util.Optional;

public interface MetricDAO {
  Optional<Metric> getByEntityId(String entityId);

  boolean isHealthy();
}
