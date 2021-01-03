package cc.jbx.tracebench.metrics;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

@Path("/metrics")
@Produces(MediaType.APPLICATION_JSON)
public class MetricsResource {
  private final MetricDAO metrics;

  public MetricsResource(MetricDAO metrics) {
    this.metrics = metrics;
  }

  @GET
  public Metric getMetric(@QueryParam("entityId") String entityId) {
    if (entityId == null || entityId.isEmpty()) {
      throw new WebApplicationException(BAD_REQUEST);
    }

    Optional<Metric> metricValue = metrics.getByEntityId(entityId);
    if (!metricValue.isPresent()) {
      throw new WebApplicationException(NOT_FOUND);
    }

    return metricValue.get();
  }
}

