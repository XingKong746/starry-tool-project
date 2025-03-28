package cn.starrys.tool.mail.autoconfigure

import cn.starrys.tool.mail.MailTools
import cn.starrys.tool.mail.entity.MailProps
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

/**
 * MailTools 自动配置类。
 */
@AutoConfiguration
@ConditionalOnMissingBean(MailTools::class)
@EnableConfigurationProperties(MailProperties::class)
class MailToolsAutoConfiguration {

    /**
     * 创建邮件工具加入ioc容器
     *
     * @param properties 邮件配置
     * @return 邮件工具
     */
    @Bean
    @ConditionalOnProperty(
        prefix = MailProperties.PROPERTIES_PREFIX,
        name = ["host", "port", "from", "password"]
    )
    fun createMailTools(properties: MailProperties): MailTools {
        return MailTools(
            MailProps(
                properties.host,
                properties.port,
                properties.from,
                properties.password,
                properties.nickname,
                properties.ssl,
                properties.debug,
                properties.charset,
                properties.auth,
            )
        )
    }
}
