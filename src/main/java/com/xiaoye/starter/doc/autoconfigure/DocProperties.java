package com.xiaoye.starter.doc.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * API文档配置属性
 */
@Data
@ConfigurationProperties(prefix = "xiaoye.doc")
public class DocProperties {

    /**
     * 是否启用文档（默认 true）
     */
    private boolean enabled = true;

    /**
     * API标题
     */
    private String title = "XiaoYe Platform API";

    /**
     * API版本
     */
    private String version = "1.0.0";

    /**
     * API描述
     */
    private String description = "XiaoYe Boot Starter Standard API Documentation";

    /**
     * 服务条款URL
     */
    private String termsOfService;

    // ==================== 联系信息 ====================

    /**
     * 联系信息 - 名称
     */
    private String contactName;

    /**
     * 联系信息 - URL
     */
    private String contactUrl;

    /**
     * 联系信息 - Email
     */
    private String contactEmail;

    // ==================== 许可证信息 ====================

    /**
     * 许可证名称
     */
    private String licenseName;

    /**
     * 许可证URL
     */
    private String licenseUrl;

    // ==================== 安全方案配置 ====================

    /**
     * 是否启用安全方案（默认 false）
     */
    private boolean securityEnabled = false;

    /**
     * 安全方案名称
     */
    private String securitySchemeName = "Bearer Authentication";

    /**
     * 安全方案类型：bearer、apiKey、oauth2
     */
    private String securitySchemeType = "bearer";

    /**
     * Bearer Token 前缀（默认 Bearer）
     */
    private String bearerPrefix = "Bearer";

    /**
     * API Key 名称（当 securitySchemeType=apiKey 时使用）
     */
    private String apiKeyName = "X-API-Key";

    /**
     * API Key 位置（header/query - 当 securitySchemeType=apiKey 时使用）
     */
    private String apiKeyIn = "header";

    // ==================== 路径配置 ====================

    /**
     * Swagger UI 路径
     */
    private String swaggerUiPath = "/swagger-ui.html";

    /**
     * API Docs 路径
     */
    private String apiDocsPath = "/v3/api-docs";

    /**
     * 是否启用 Swagger UI（默认 true）
     */
    private boolean swaggerUiEnabled = true;

    /**
     * 是否启用 ReDoc（默认 false）
     */
    private boolean redocEnabled = false;

    /**
     * ReDoc 路径
     */
    private String redocPath = "/redoc.html";

    // ==================== 服务器信息 ====================

    /**
     * 服务器配置列表
     */
    private List<ServerInfo> servers = new ArrayList<>();

    /**
     * 服务器信息
     */
    @Data
    public static class ServerInfo {
        /**
         * 服务器 URL
         */
        private String url;

        /**
         * 服务器描述
         */
        private String description;

        /**
         * 服务器变量（可选）
         */
        private List<ServerVariable> variables = new ArrayList<>();
    }

    /**
     * 服务器变量
     */
    @Data
    public static class ServerVariable {
        /**
         * 变量名
         */
        private String name;

        /**
         * 默认值
         */
        private String defaultValue;

        /**
         * 描述
         */
        private String description;

        /**
         * 可选枚举值
         */
        private List<String> enumValues;
    }
}
