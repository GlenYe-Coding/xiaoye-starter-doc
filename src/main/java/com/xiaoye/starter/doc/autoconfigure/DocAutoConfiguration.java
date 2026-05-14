package com.xiaoye.starter.doc.autoconfigure;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.servers.ServerVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Doc 模块自动配置
 * <p>
 * P0 功能：
 * - OpenAPI 3.0 文档聚合
 * - Swagger UI 集成
 * - 安全方案配置（JWT Bearer / API Key）
 * - 多服务器配置
 * </p>
 */
@AutoConfiguration
@EnableConfigurationProperties(DocProperties.class)
@RequiredArgsConstructor
public class DocAutoConfiguration {

    private final DocProperties docProperties;

    /**
     * OpenAPI 配置
     */
    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();

        // 配置服务器信息
        configureServers(openAPI);

        // 配置 API 信息
        openAPI.info(buildInfo());

        // 配置安全方案
        if (docProperties.isSecurityEnabled()) {
            configureSecurity(openAPI);
        }

        return openAPI;
    }

    /**
     * 配置服务器信息
     */
    private void configureServers(OpenAPI openAPI) {
        List<Server> servers = new ArrayList<>();

        // 添加配置的服务器
        if (docProperties.getServers() != null && !docProperties.getServers().isEmpty()) {
            for (DocProperties.ServerInfo serverInfo : docProperties.getServers()) {
                Server server = new Server()
                        .url(serverInfo.getUrl())
                        .description(serverInfo.getDescription());

                // 添加服务器变量
                if (serverInfo.getVariables() != null && !serverInfo.getVariables().isEmpty()) {
                    serverInfo.getVariables().forEach(var -> {
                        ServerVariable serverVariable = new ServerVariable();
                        serverVariable.setDefault(var.getDefaultValue());
                        serverVariable.setDescription(var.getDescription());
                        if (var.getEnumValues() != null) {
                            serverVariable.setEnum(var.getEnumValues());
                        }
                        server.getVariables().put(var.getName(), serverVariable);
                    });
                }

                servers.add(server);
            }
        }

        if (!servers.isEmpty()) {
            openAPI.servers(servers);
        }
    }

    /**
     * 配置 API 信息
     */
    private Info buildInfo() {
        Info info = new Info()
                .title(docProperties.getTitle())
                .version(docProperties.getVersion())
                .description(docProperties.getDescription());

        // 服务条款
        Optional.ofNullable(docProperties.getTermsOfService())
                .filter(s -> !s.isEmpty())
                .ifPresent(info::termsOfService);

        // 联系信息
        if (hasContactInfo()) {
            Contact contact = new Contact();
            Optional.ofNullable(docProperties.getContactName()).ifPresent(contact::name);
            Optional.ofNullable(docProperties.getContactUrl()).ifPresent(contact::url);
            Optional.ofNullable(docProperties.getContactEmail()).ifPresent(contact::email);
            info.contact(contact);
        }

        // 许可证信息
        if (hasLicenseInfo()) {
            License license = new License();
            Optional.ofNullable(docProperties.getLicenseName()).ifPresent(license::name);
            Optional.ofNullable(docProperties.getLicenseUrl()).ifPresent(license::url);
            info.license(license);
        }

        return info;
    }

    /**
     * 配置安全方案
     */
    private void configureSecurity(OpenAPI openAPI) {
        Components components = openAPI.getComponents();

        String schemeName = docProperties.getSecuritySchemeName();
        String schemeType = docProperties.getSecuritySchemeType();

        SecurityScheme securityScheme = createSecurityScheme(schemeType, schemeName);

        // 添加安全方案到 components
        components.addSecuritySchemes(schemeName, securityScheme);

        // 添加全局安全要求
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList(schemeName);
        openAPI.security(List.of(securityRequirement));
    }

    /**
     * 创建安全方案
     */
    private SecurityScheme createSecurityScheme(String schemeType, String schemeName) {
        if ("apiKey".equalsIgnoreCase(schemeType)) {
            return new SecurityScheme()
                    .name(docProperties.getApiKeyName())
                    .type(SecurityScheme.Type.APIKEY)
                    .in(SecurityScheme.In.valueOf(docProperties.getApiKeyIn().toUpperCase()))
                    .description(schemeName);
        } else if ("oauth2".equalsIgnoreCase(schemeType)) {
            // OAuth2 配置（简化版）
            return new SecurityScheme()
                    .type(SecurityScheme.Type.OAUTH2)
                    .description(schemeName);
        } else {
            // 默认 Bearer Token
            return new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme(docProperties.getBearerPrefix().toLowerCase())
                    .bearerFormat("JWT")
                    .description(schemeName);
        }
    }

    /**
     * 是否有联系信息
     */
    private boolean hasContactInfo() {
        return hasValue(docProperties.getContactName())
                || hasValue(docProperties.getContactUrl())
                || hasValue(docProperties.getContactEmail());
    }

    /**
     * 是否有许可证信息
     */
    private boolean hasLicenseInfo() {
        return hasValue(docProperties.getLicenseName())
                || hasValue(docProperties.getLicenseUrl());
    }

    /**
     * 判断字符串是否有值
     */
    private boolean hasValue(String value) {
        return value != null && !value.trim().isEmpty();
    }
}