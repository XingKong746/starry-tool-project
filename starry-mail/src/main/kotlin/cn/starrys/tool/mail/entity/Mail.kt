package cn.starrys.tool.mail.entity

import java.io.File

/**
 * 邮件内容。
 */
data class Mail(
    /**
     * 收件人(主要收件人)。
     */
    var to: List<MailAddressee>,
    /**
     * 抄送(carbon copy)收件人。
     */
    var cc: List<MailAddressee>?,
    /**
     * 密送(blind carbon copy)收件人。
     */
    var bcc: List<MailAddressee>?,
    /**
     * 邮件类型（HTML,TEXT）
     */
    var type: MailType = MailType.HTML,
    /**
     * 邮件主题（标题）。
     */
    var subject: String?,
    /**
     * 邮件内容（主体）。
     */
    var body: String?,
    /**
     * 附件。
     */
    var attachments: List<File>?,
) {
    constructor(to: List<MailAddressee>, subject: String?, body: String?) : this(
        to,
        null,
        null,
        MailType.HTML,
        subject,
        body,
        null
    )
}
