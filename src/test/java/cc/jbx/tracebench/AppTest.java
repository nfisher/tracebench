package cc.jbx.tracebench;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AppTest {
  private static DropwizardAppExtension<AppConfiguration> EXT = new DropwizardAppExtension<>(
      App.class,
      "test-config.yaml");

  @Test
  void should_fail() {
    Client client = EXT.client();

    Response response = client.target(
        String.format("http://localhost:%d/metrics?entityId=12345", EXT.getLocalPort()))
        .request()
        .get();

    assertThat("12345 is not found", response.getStatus(), is(404));
  }
}
