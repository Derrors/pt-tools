package com.derrors.pt.tools.integration.config;

import com.derrors.pt.tools.integration.client.AtmosClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author derrors
 * @date 2024/1/9
 */
@Configuration
public class HttpClientFactory {

    @Value("${http.client.atmos.url}")
    private String atmosUrl;

    @Bean
    AtmosClient atmosClient() {
        RestClient restClient = RestClient.builder()
            .baseUrl(atmosUrl)
            .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(AtmosClient.class);
    }

}
