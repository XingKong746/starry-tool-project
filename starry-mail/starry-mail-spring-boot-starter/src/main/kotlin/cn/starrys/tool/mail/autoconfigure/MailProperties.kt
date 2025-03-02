package cn.starrys.tool.mail.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import java.nio.charset.Charset

/**
 * 邮件配置。
 */
@ConfigurationProperties(prefix = MailProperties.PROPERTIES_PREFIX)
data class MailProperties @ConstructorBinding constructor(
    /**
     * 主机。
     */
    var host: String,
    /**
     * 主机端口。
     */
    var port: Int,
    /**
     * 邮箱地址。
     */
    var from: String,
    /**
     * 邮箱授权码。
     */
    var password: String,
    /**
     * 昵称。（Unicode字符请包围双引号，否则会被转换为Unicode码）
     */
    var nickname: String?,
    /**
     * 启用 ssl。
     */
    var ssl: Boolean = true,
    /**
     * 开启 debug 模式。
     */
    var debug: Boolean = false,
    /**
     * 邮件编码。
     */
    var charset: Charset = Charsets.UTF_8,
    /**
     * 开启鉴权。
     */
    var auth: Boolean = true,
) {
    companion object {
        /**
         * 配置属性前缀。
         */
        const val PROPERTIES_PREFIX = "starry.mail"
    }

    constructor(host: String, port: Int, from: String, password: String) : this(host, port, from, password, null)
}
