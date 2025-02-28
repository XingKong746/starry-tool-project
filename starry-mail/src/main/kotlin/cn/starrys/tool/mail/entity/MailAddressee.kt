package cn.starrys.tool.mail.entity

/**
 * 邮件收件人。
 */
data class MailAddressee(
    /**
     * 收件人。
     */
    var addressee: String,
    /**
     * 收件者昵称。
     */
    var nickname: String?
) {
    constructor(addressee: String) : this(addressee, null)
}
