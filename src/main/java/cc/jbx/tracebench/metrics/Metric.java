package cc.jbx.tracebench.metrics;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metric {
  Map<String, BigDecimal> metrics;

  public Metric() {
  }

  public Metric(Map<String, BigDecimal> metrics) {
    this.metrics = metrics;
  }

  @JsonProperty
  public Map<String, BigDecimal> getMetrics() {
    return metrics;
  }
}
