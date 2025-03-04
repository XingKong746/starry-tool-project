package cn.starrys.tool.core.constant

import java.io.Serializable

/**
 * 一个接口，用于表示请求结果的状态。
 * 该接口内包含了一个默认方法来判断请求结果的不同系列，根据具体需要使用。
 * 需要创建一个枚举实现
 * @author XingKong
 */
interface IResultStatus : Serializable {
    /**
     * 获取状态码。
     *
     * @return 状态码。
     */
    val code: Int?

    /**
     * 获取状态信息。
     *
     * @return 状态信息。
     */
    val message: String?

    /**
     * 状态系列的枚举。
     */
    enum class Series(
        /**
         * 获取此状态系列的整数值。
         */
        val value: Int
    ) {
        /**
         * 信息，服务器收到请求，需要请求者继续执行操作
         */
        INFORMATIONAL(1),

        /**
         * 成功，操作被成功接收并处理
         */
        SUCCESSFUL(2),

        /**
         * 重定向，需要进一步的操作以完成请求
         */
        REDIRECTION(3),

        /**
         * 客户端错误，请求包含语法错误或无法完成请求
         */
        CLIENT_ERROR(4),

        /**
         * 服务器错误，服务器在处理请求的过程中发生了错误
         */
        SERVER_ERROR(5),

        /**
         * 保留
         */
        OTHER(6),
        ;

        companion object {
            /**
             * 将给定的状态码解析为[Series]序列状态。
             *
             * @param code 要解析的状态码
             * @return 匹配的的 [Series]，如果没有找到，则为`null`
             */
            @JvmStatic
            fun resolve(code: Int?): Series? {
                if (code == null || code < 0) return null
                var temp = code
                while (temp >= 10) temp /= 10
                return entries.firstOrNull { it.value == temp }
            }
        }
    }

    /**
     * 根据需求扩展使用。
     * 若要使用请必须重写该方法。
     *
     * @return 状态系列
     */
    fun getSeries(): Series? = Series.resolve(code)

    /**
     * 判断状态系列是否为信息性(1xx)系列。
     *
     * @return 如果状态系列为信息性系列，则返回true；否则返回false。
     */
    fun isInformational(): Boolean = getSeries() == Series.INFORMATIONAL

    /**
     * 判断状态系列是否为成功(2xx)系列。
     *
     * @return 如果状态系列为成功系列，则返回true；否则返回false。
     */
    fun isSuccessful(): Boolean = getSeries() == Series.SUCCESSFUL

    /**
     * 判断状态系列是否为重定向(3xx)系列。
     *
     * @return 如果状态系列为重定向系列，则返回true；否则返回false。
     */
    fun isRedirection(): Boolean = getSeries() == Series.REDIRECTION

    /**
     * 判断状态系列是否为客户端错误(4xx)系列。
     *
     * @return 如果状态系列为客户端错误系列，则返回true；否则返回false。
     */
    fun isClientError(): Boolean = getSeries() == Series.CLIENT_ERROR

    /**
     * 判断状态系列是否为服务器错误(5xx)系列。
     *
     * @return 如果状态系列为服务器错误系列，则返回true；否则返回false。
     */
    fun isServerError(): Boolean = getSeries() == Series.SERVER_ERROR

    /**
     * 判断状态系列是否为错误系列（客户端错误或服务器错误）。
     *
     * @return 如果状态系列为错误系列，则返回true；否则返回false。
     */
    fun isError(): Boolean = isClientError() || isServerError()
}
