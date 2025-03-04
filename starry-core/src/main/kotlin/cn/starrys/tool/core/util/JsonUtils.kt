package cn.starrys.tool.core.util

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Json 工具。
 */
object JsonUtils {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    /**
     * 拆分keyPath
     *
     * @param keyPath 节点路径
     * @return 节点数组
     */
    @JvmStatic
    private fun split(keyPath: String): List<String> {
        // 根据“.”进行拆分
        return keyPath.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
    }

    /**
     * 获取Json串中指定节点的值。<br></br>
     * FastJson2 实现
     *
     * @param jsonStr Json 字符串
     * @param keyPath 节点路径
     * @param type    转换类型
     * @param <T>     返回类型
     * @return 指定类型的值
    </T> */
    @JvmStatic
    fun <T> getValue(jsonStr: String, keyPath: String, type: Class<T>?): T? {
        var currentJson: String = jsonStr
        // 节点数组
        val keys = split(keyPath)

        // 遍历节点数组获得节点
        for (key in keys) {
            // Json 对象
            val jsonObject = JSON.parseObject(jsonStr)

            val begin = key.indexOf('[')
            val end = key.lastIndexOf(']')
            // 如果key中包含[]，则表示是数组
            if (begin != -1 && end != -1) {
                // key
                val k = key.substring(0, begin)
                // index
                val i = key.substring(begin + 1, end).toInt()

                try {
                    // 获取数组中的值
                    currentJson = jsonObject.getJSONArray(k).getString(i)
                } catch (e: IndexOutOfBoundsException) {
                    logger.error("数组越界", e)
                }
            } else {
                // 获取对象中的值
                currentJson = jsonObject.getString(key)
            }
        }
        return try {
            JSON.to(type, currentJson)
        } catch (e: JSONException) {
            logger.error("类型转换异常", e)
            null
        }
    }
}
