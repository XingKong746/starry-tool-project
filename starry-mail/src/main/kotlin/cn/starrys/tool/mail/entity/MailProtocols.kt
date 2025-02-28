package cn.starrys.tool.mail.entity

/**
 * 邮件协议。
 */
enum class MailProtocols(
    /**
     * 协议字符串。
     */
    private val protocol: String
) {
    /**
     * smtp 协议。
     */
    SMTP("smtp"),

    /**
     * imap 协议。
     */
    IMAP("imap"),

    /**
     * pop3 协议。
     */
    POP3("pop3");

    /**
     * 获取该协议的字符串名称。
     *
     * @return 该协议的字符串名称。
     */
    fun get(): String {
        return protocol
    }

    override fun toString(): String {
        return get()
    }
}
