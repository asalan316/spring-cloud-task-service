package com.ak.cloud.task.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.ZonedDateTime;

@Component
public class GetDataCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory
            .getLogger(GetDataCommandLineRunner.class);

    @Value("${application.url}")
    private String httpServiceUrl;

    @Override
    public void run(String... args) throws Exception {
        for (String arg: args) {
            logger.info("Arguments received" + arg);
        }
        logger.info("start time (UTC): " + Instant.now());
        logger.info("start time (zoned): " + ZonedDateTime.now());
        try {
            final HttpResponse<String> response = httpGet(httpServiceUrl);
            logger.info(String.valueOf(response.statusCode()));
            logger.info(response.body());
        } catch (final Exception e) {
            logger.error("" + e.getMessage());
        }
        logger.info("finish time (UTC): " + Instant.now());
        logger.info("finish time (zoned): " + ZonedDateTime.now());
    }

    private HttpResponse<String> httpGet(final String uri) throws Exception {

        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        final HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
