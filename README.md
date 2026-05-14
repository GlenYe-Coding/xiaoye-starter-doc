# XiaoYe Starter Doc - API文档模块

## 概述

`xiaoye-starter-doc` 基于 Knife4j/Swagger 提供自动化的 API 文档生成功能。

## 特性

- ✅ **OpenAPI 3.0**: 支持最新的 OpenAPI 规范
- ✅ **Knife4j 增强**: 更友好的 UI 界面
- ✅ **自动聚合**: 自动聚合多个服务的 API 文档
- ✅ **在线调试**: 支持在线接口测试

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>com.xiaoye</groupId>
    <artifactId>xiaoye-starter-doc</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 2. 配置文档

```yaml
springdoc:
  api-docs:
    enabled: true
    path: /v3/api-docs
  swagger-ui:
    enabled: true
    path: /swagger-ui.html

knife4j:
  enable: true
  setting:
    language: zh_cn
```

### 3. 访问文档

- Swagger UI: http://localhost:8080/swagger-ui.html
- Knife4j: http://localhost:8080/doc.html

## License

MIT License
