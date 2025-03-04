package cn.starrys.tool.mail

import java.util.*
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertTrue

class MailToolsTest() {
    private lateinit var props: Properties

    @BeforeTest
    fun init() {
        val properties = Properties()
        val inputStream = javaClass.classLoader.getResourceAsStream("mail.properties")
        properties.load(inputStream)
        props = properties
    }

    @Test
    @Ignore("依据需要使用")
    fun sendTest() {
        val from = props.getProperty("from")
        val password = props.getProperty("password")
        val addressee = props.getProperty("addressee")

        // 创建邮件工具
        val mailTools = MailTools("smtp.163.com", 465, from, password, "⭐昵称")

        // 发送邮件
        val sendStatus = mailTools.send(addressee, "标题你好呀", "内容QwQ")

        // 断言发送成功
        assertTrue(sendStatus)
    }
}
