package com.momo2x.momobank.loans.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

import static org.h2.tools.Server.createTcpServer;
import static org.h2.tools.Server.createWebServer;

@Configuration
public class H2LoansServerConfiguration {

    // TCP port for remote connections, default 9092
    @Value("${h2.tcp.port}")
    private String h2TcpPort;

    // Web port, default 8082
    @Value("${h2.web.port}")
    private String h2WebPort;

    @Bean
    @ConditionalOnExpression("${h2.tcp.enabled}")
    public Server h2TcpServer() throws SQLException {
        return createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", h2TcpPort).start();
    }

    @Bean
    @ConditionalOnExpression("${h2.web.enabled}")
    public Server h2WebServer() throws SQLException {
        return createWebServer("-web", "-webAllowOthers", "-webPort", h2WebPort).start();
    }

}
