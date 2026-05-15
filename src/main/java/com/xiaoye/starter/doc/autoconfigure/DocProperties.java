package com.xiaoye.starter.doc.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * API文档配置属性
 */
@Data
@Validated
@ConfigurationProperties(prefix = "xiaoye.doc")
public class DocProperties {

    /**
     * 是否启用文档（默认 true）
     */
    private boolean enabled = true;

    /**
     * API标题
     */
    @NotBlank(message = "API标题不能为空")
    private String title = "XiaoYe Platform API";

    /**
     * API版本
     */
    @NotBlank(message = "API版本不能为空")
    private String version = "1.0.0";

    /**
     * API描述
     */
    @NotBlank(message = "API描述不能为空")
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
    @Pattern(regexp = "^(https?://.*)?$", message = "联系信息URL格式不正确")
    private String contactUrl;

    /**
     * 联系信息 - Email
     */
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email格式不正确")
    private String contactEmail;

    // ==================== 许可证信息 ====================

    /**
     * 许可证名称
     */
    private String licenseName;

    /**
     * 许可证URL
     */
    @Pattern(regexp = "^(https?://.*)?$", message = "许可证URL格式不正确")
    private String licenseUrl;

    // ==================== 安全方案配置 ====================

    /**
     * 是否启用安全方案（默认 false）
     */
    private boolean securityEnabled = false;

    /**
     * 安全方案名称
     */
    @NotBlank(message = "安全方案名称不能为空")
    private String securitySchemeName = "Bearer Authentication";

    /**
     * 安全方案类型：bearer、apiKey、oauth2
     */
    @NotBlank(message = "安全方案类型不能为空")
    private String securitySchemeType = "bearer";

    /**
     * Bearer Token 前缀（默认 Bearer）
     */
    @NotBlank(message = "Bearer Token前缀不能为空")
    private String bearerPrefix = "Bearer";

    /**
     * API Key 名称（当 securitySchemeType=apiKey 时使用）
     */
    @NotBlank(message = "API Key名称不能为空")
    private String apiKeyName = "X-API-Key";

    /**
     * API Key 位置（header/query - 当 securitySchemeType=apiKey 时使用）
     */
    @NotBlank(message = "API Key位置不能为空")
    private String apiKeyIn = "header";

    // ==================== 路径配置 ====================

    /**
     * Swagger UI 路径
     */
    @NotBlank(message = "Swagger UI路径不能为空")
    private String swaggerUiPath = "/swagger-ui.html";

    /**
     * API Docs 路径
     */
    @NotBlank(message = "API Docs路径不能为空")
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
    @NotBlank(message = "ReDoc路径不能为空")
    private String redocPath = "/redoc.html";

    // ==================== 服务器信息 ====================

    /**
     * 服务器配置列表
     */
    @Valid
    private List<ServerInfo> servers = new ArrayList<>();

    /**
     * 服务器信息
     */
    @Data
    public static class ServerInfo {
        /**
         * 服务器 URL
         */
        @NotBlank(message = "服务器URL不能为空")
        private String url;

        /**
         * 服务器描述
         */
        private String description;

        /**
         * 服务器变量（可选）
         */
        @Valid
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
        @NotBlank(message = "变量名不能为空")
        private String name;

        /**
         * 默认值
         */
        @NotBlank(message = "默认值不能为空")
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
