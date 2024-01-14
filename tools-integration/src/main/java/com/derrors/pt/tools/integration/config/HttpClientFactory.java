package com.derrors.pt.tools.integration.config;

import com.derrors.pt.tools.integration.client.PtHttpClient;
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
    @Value("${https.client.mteam}")
    private String mteamUrl;

    @Value("${https.client.atmos}")
    private String atmosUrl;

    @Value("${https.client.dolby}")
    private String dolbyUrl;

    @Value("${https.client.car}")
    private String carUrl;

    @Value("${https.client.cyan}")
    private String cyanUrl;

    @Bean
    PtHttpClient mteamClient() {
        RestClient restClient = RestClient.builder()
            .baseUrl(mteamUrl)
            .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(PtHttpClient.class);
    }

    @Bean
    PtHttpClient atmosClient() {
        RestClient restClient = RestClient.builder()
            .baseUrl(atmosUrl)
            .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(PtHttpClient.class);
    }

    @Bean
    PtHttpClient dolbyClient() {
        RestClient restClient = RestClient.builder()
            .baseUrl(dolbyUrl)
            .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(PtHttpClient.class);
    }

    @Bean
    PtHttpClient carClient() {
        RestClient restClient = RestClient.builder()
            .baseUrl(carUrl)
            .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(PtHttpClient.class);
    }

    @Bean
    PtHttpClient cyanClient() {
        RestClient restClient = RestClient.builder()
            .baseUrl(cyanUrl)
            .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(PtHttpClient.class);
    }
}
