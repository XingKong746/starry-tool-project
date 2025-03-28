package cn.starrys.tool.mail.entity

import java.io.File

/**
 * 邮件内容。
 */
data class Mail(
    /**
     * 收件人(主要收件人)。
     */
    var to: List<Addressee>,
    /**
     * 抄送(carbon copy)收件人。
     */
    var cc: List<Addressee>?,
    /**
     * 密送(blind carbon copy)收件人。
     */
    var bcc: List<Addressee>?,
    /**
     * 邮件类型（HTML,TEXT）
     */
    var type: Type = Type.HTML,
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
    constructor(to: List<Addressee>, subject: String?, body: String?) :
            this(to, null, null, Type.HTML, subject, body, null)

    /**
     * 邮件收件人。
     */
    data class Addressee(
        /**
         * 收件人。
         */
        var addressee: String,
        /**
         * 收件者昵称。
         */
        var nickname: String?
    )

    /**
     * 邮件类型。
     */
    enum class Type { HTML, TEXT }

}
