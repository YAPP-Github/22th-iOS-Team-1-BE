package kr.co.yapp._22nd.coffice.infrastructure.kakao;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;


@Configuration
public class KakaoConfig {
    @Bean
    public WebClient kakaoWebClient() {
        return WebClient.builder()
                .clientConnector(
                        new ReactorClientHttpConnector(
                                HttpClient.create()
                                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                                        .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(1)))
                        )
                )
                .baseUrl("https://kapi.kakao.com")
                .build();
    }
}
