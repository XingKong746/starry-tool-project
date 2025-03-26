package cn.starrys.tool.mail

import cn.starrys.tool.mail.entity.*
import jakarta.mail.*
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeBodyPart
import jakarta.mail.internet.MimeMessage
import jakarta.mail.internet.MimeMultipart
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.util.*

/**
 * 邮件工具。
 */
@Suppress("MemberVisibilityCanBePrivate")
class MailTools(
    /**
     * 邮件配置。
     */
    private val mailProps: MailProps
) {
    // Logger
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    // 缓存发信协议的 Session
    private val smtpSession: Session by lazy { getSession(MailProtocols.SMTP) }


    /**
     * 重载构造。
     *
     * @param host     主机。
     * @param port     主机端口。
     * @param from     发件邮箱。
     * @param password 发件邮箱授权码。
     * @param nickname 昵称。
     * @param ssl      启用 ssl。
     */
    constructor(
        host: String,
        port: Int,
        from: String,
        password: String,
        nickname: String?,
        ssl: Boolean
    ) : this(MailProps(host, port, from, password, nickname, ssl, false, Charsets.UTF_8, true))

    /**
     * 重载构造。
     *
     * @param host     主机。
     * @param port     主机端口。
     * @param from     发件邮箱。
     * @param password 发件邮箱授权码。
     * @param nickname 昵称。
     */
    constructor(
        host: String,
        port: Int,
        from: String,
        password: String,
        nickname: String?
    ) : this(host, port, from, password, nickname, true)

    /**
     * 重载构造。
     *
     * @param host     主机。
     * @param port     主机端口。
     * @param from     发件邮箱。
     * @param password 发件邮箱授权码。
     */
    constructor(
        host: String,
        port: Int,
        from: String,
        password: String
    ) : this(host, port, from, password, null)


    /**
     * 获取 [Session] 对象.
     *
     * @param protocol 指定协议
     * @return [Session]对象。
     */
    fun getSession(protocol: MailProtocols): Session {
        val properties = Properties().apply {
            when (protocol) {
                // 接收邮件时分配给协议的名称
                MailProtocols.POP3 -> put("mail.store.protocol", protocol)
                MailProtocols.IMAP -> {
                    // TODO I don't know x_x
                    put("mail.transport.protocol", protocol)
                    put("mail.store.protocol", protocol)
                }
                // 发送邮件时分配给协议的名称
                MailProtocols.SMTP -> put("mail.transport.protocol", protocol)
            }

            // 邮箱服务器地址
            put("mail.host", mailProps.host)
            // 端口
            put("mail.${protocol}.port", mailProps.port)
            // 发件邮箱
            put("mail.from", mailProps.from)
            // 发件邮箱授权码
            put("mail.password", mailProps.password)
            // 发信昵称
            mailProps.nickname?.let { put("mail.user", it) }

            // 开启 ssl
            if (mailProps.ssl) {
                put("mail.${protocol}.ssl.enable", "true")
                put("mail.${protocol}.ssl.socketFactory", "javax.net.ssl.SSLSocketFactory")
            }

            // 开启鉴权
            if (mailProps.auth) put("mail.${protocol}.auth", "true")

            // Debug
            if (mailProps.debug) put("mail.debug", "true")
        }

        if (mailProps.auth) return Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(mailProps.from, mailProps.password)
            }
        })
        return Session.getInstance(properties)
    }

    /**
     * 创建 [InternetAddress] 对象。
     *
     * @param addresseeList [MailAddressee] 邮件收件人列表。
     * @return [InternetAddress] 对象数组。
     */
    private fun cia(addresseeList: List<MailAddressee>): Array<InternetAddress> {
        return addresseeList.map { mailAddressee ->
            InternetAddress(
                mailAddressee.addressee,
                mailAddressee.nickname,
                mailProps.charset.name()
            )
        }.toTypedArray()
    }

    /**
     * 创建邮件。
     *
     * @param session [Session]对象。
     * @param mail    [Mail] 邮件内容。
     * @return 一封邮件。
     */
    fun createMimeMessage(session: Session, mail: Mail): MimeMessage {
        val mimeMessage = MimeMessage(session)
        // 发件人
        mimeMessage.setFrom(InternetAddress(mailProps.from, mailProps.nickname, mailProps.charset.name()))
        // 收件人
        mimeMessage.setRecipients(Message.RecipientType.TO, cia(mail.to))
        // 抄送人
        mail.cc?.let { mimeMessage.setRecipients(Message.RecipientType.CC, cia(it)) }
        // 密送人
        mail.bcc?.let { mimeMessage.setRecipients(Message.RecipientType.BCC, cia(it)) }
        // 邮件主题
        mimeMessage.setSubject(mail.subject, mailProps.charset.name())
        // 邮件内容
        val mimeBodyPart = MimeBodyPart()
        when (mail.type) {
            // text格式
            MailType.TEXT -> mimeBodyPart.setText(mail.body, mailProps.charset.name())
            // html格式
            MailType.HTML -> mimeBodyPart.setContent(mail.body, "text/html;charset=${mailProps.charset.name()}")
        }
        // 邮件体
        val mimeMultipart = MimeMultipart("mixed")
        // 添加邮件内容
        mimeMultipart.addBodyPart(mimeBodyPart)
        // 附件
        mail.attachments?.forEach { attachment ->
            MimeBodyPart().apply {
                attachFile(attachment)
                mimeMultipart.addBodyPart(this)
            }
        }
        // 设置邮件体
        mimeMessage.setContent(mimeMultipart)
        // 发送时间
        mimeMessage.sentDate = Date()
        // 保存上面设置的邮件
        mimeMessage.saveChanges()
        return mimeMessage
    }


    /**
     * 最终执行发送方法。
     * @param msg 一封邮件。
     * @return 发送结果。
     */
    fun send(msg: MimeMessage): Boolean {
        return try {
            if (mailProps.auth) {
                Transport.send(msg)
            } else {
                msg.session.transport.use {
                    it.connect(mailProps.host, mailProps.port, mailProps.from, mailProps.password)
                    it.sendMessage(msg, msg.allRecipients)
                }
            }
            true
        } catch (err: MessagingException) {
            logger.error("邮件发送失败！", err)
            false
        }
    }

    /**
     * 发送邮件。
     *
     * @param mail [Mail] 邮件内容。
     * @return 发送结果。
     */
    fun send(mail: Mail): Boolean {
        return send(createMimeMessage(smtpSession, mail))
    }

    /**
     * 发送邮件。
     *
     * @param to          收件人(主要收件人)。
     * @param subject     邮件主题（标题）。
     * @param body        邮件内容（主体）。
     * @param attachments 附件。
     * @return 发送结果。
     */
    fun send(to: List<MailAddressee>, subject: String?, body: String?, attachments: List<File>?): Boolean {
        return send(Mail(to, null, null, MailType.HTML, subject, body, attachments))
    }

    /**
     * 发送邮件。
     *
     * @param addressee   收件人。
     * @param nickname    收件者昵称。
     * @param subject     邮件主题（标题）。
     * @param body        邮件内容（主体）。
     * @param attachments 附件。
     * @return 发送结果。
     */
    fun send(addressee: String, nickname: String?, subject: String?, body: String?, attachments: List<File>?): Boolean {
        val to = ArrayList<MailAddressee>()
        to.add(MailAddressee(addressee, nickname))
        return send(to, subject, body, attachments)
    }

    /**
     * 发送邮件。
     *
     * @param addressee 收件人。
     * @param nickname  收件者昵称。
     * @param subject   邮件主题（标题）。
     * @param body      邮件内容（主体）。
     * @return 发送结果。
     */
    fun send(addressee: String, nickname: String?, subject: String?, body: String?): Boolean {
        return send(addressee, nickname, subject, body, null)
    }

    /**
     * 发送邮件。
     *
     * @param addressee 收件人。
     * @param subject   邮件主题（标题）。
     * @param body      邮件内容（主体）。
     * @return 发送结果。
     */
    fun send(addressee: String, subject: String?, body: String?): Boolean {
        return send(addressee, null, subject, body)
    }
}
