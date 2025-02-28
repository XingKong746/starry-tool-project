package cn.starrys.tool.mail.entity

import java.nio.charset.Charset

/**
 * 邮件配置。
 */
data class MailProps(
    /**
     * 主机。
     */
    var host: String,
    /**
     * 主机端口。
     */
    var port: Int,
    /**
     * 发件邮箱。
     */
    var from: String,
    /**
     * 发件邮箱授权码。
     */
    var password: String,
    /**
     * 昵称。
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
    constructor(host: String, port: Int, from: String, password: String) : this(host, port, from, password, null)
}
