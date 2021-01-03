package cc.jbx.tracebench.metrics;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;

@ExtendWith(DropwizardExtensionsSupport.class)
public class MetricsResourceTest {
  static {
    Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    root.setLevel(Level.WARN);
  }
  private static final ResourceExtension EXT = ResourceExtension.builder()
      .bootstrapLogging(false)
      .addResource(new MetricsResource(entityId -> {
        Map<String, Metric> m = Map.of("1234", new Metric());
        Metric metric = m.get(entityId);
        if (metric == null) {
          return Optional.empty();
        }
        return Optional.of(metric);
      }))
      .build();

  @Test
  void should_be_bad_request_when_entityId_is_absent() {
    Response response = EXT.target("/metrics")
        .request()
        .get();
    assertThat("entityId param absent", response.getStatusInfo(), is(BAD_REQUEST));
  }

  @Test
  void should_be_not_found_when_entityId_does_not_exist() {
    Response response = EXT.target("/metrics")
        .queryParam("entityId", "notFound")
        .request()
        .get();
    assertThat("entityId not found", response.getStatusInfo(), is(NOT_FOUND));
  }

  @Test
  void should_be_ok_when_entityId_exists() {
    Response response = EXT.target("/metrics")
        .queryParam("entityId", "1234")
        .request()
        .get();
    assertThat("entityId found", response.getStatusInfo(), is(OK));
  }
}
