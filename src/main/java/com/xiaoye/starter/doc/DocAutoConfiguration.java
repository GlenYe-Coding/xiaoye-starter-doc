package com.xiaoye.starter.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * Doc 模块自动配置
 * <p>
 * P0 功能：
 * - OpenAPI 3.0 文档聚合
 * - Swagger UI 集成
 * </p>
 */
@AutoConfiguration
public class DocAutoConfiguration {

    /**
     * OpenAPI 配置
     */
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("XiaoYe Platform API")
                        .version("1.0.0")
                        .description("XiaoYe Boot Starter 标准 API 文档"));
    }
}