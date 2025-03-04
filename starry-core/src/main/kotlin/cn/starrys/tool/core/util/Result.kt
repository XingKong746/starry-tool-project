package cn.starrys.tool.core.util

import cn.starrys.tool.core.constant.IResultStatus
import cn.starrys.tool.core.constant.ResultStatusEnum
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Serial
import java.time.Instant

/**
 * 通用请求响应结果封装类。
 *
 * 用于统一封装返回格式，包含状态码、消息、数据和时间戳。
 * @param T 响应数据的类型
 * @property code 状态码
 * @property message 状态消息
 * @property data 响应数据主体
 * @property timestamp 响应生成时间戳（默认当前时间）
 */
@ConsistentCopyVisibility
@Suppress("MemberVisibilityCanBePrivate")
data class Result<T> private constructor(
    /**
     * 状态码
     */
    override val code: Int?,
    /**
     * 消息
     */
    override val message: String?,
    /**
     * 数据
     */
    val data: T?,
    /**
     * 结果创建的时间戳
     */
    val timestamp: Instant = Instant.now()
) : IResultStatus {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    companion object {
        @Serial
        private const val serialVersionUID = 4327958315156039L

        /**
         * 基础构建方法，直接指定所有参数
         * @param code 状态码
         * @param message 状态消息
         * @param data 响应数据
         * @return 完整的[Result]对象
         */
        @JvmStatic
        fun <T> result(code: Int?, message: String?, data: T?): Result<T> {
            return Result(code, message, data)
        }

        /**
         * 通过[IResultStatus]状态对象构建结果
         * @param resultStatus 预定义的状态对象
         * @param data 响应数据
         * @return 包含状态码和消息的[Result]对象
         */
        @JvmStatic
        fun <T> result(resultStatus: IResultStatus?, data: T?): Result<T> {
            return Result(resultStatus?.code, resultStatus?.message, data)
        }

        /**
         * 构建带自定义消息的成功响应
         * @param message 自定义成功消息
         * @param data 响应数据
         * @return 状态码为[ResultStatusEnum.NO_CONTENT.code]的[Result]对象
         */
        @JvmStatic
        fun <T> success(message: String, data: T): Result<T> {
            return Result(ResultStatusEnum.OK.code, message, data)
        }

        /**
         * 构建仅包含消息的成功响应（无数据）
         * @param message 自定义成功消息
         * @return 状态码为[ResultStatusEnum.NO_CONTENT.code]的[Result]对象
         */
        @JvmStatic
        fun success(message: String): Result<Unit> {
            return Result(ResultStatusEnum.NO_CONTENT.code, message, Unit)
        }

        /**
         * 构建带默认消息的数据成功响应
         * @param data 响应数据
         * @return 状态为[ResultStatusEnum.OK]的[Result]对象
         */
        @JvmStatic
        fun <T> success(data: T): Result<T> {
            return Result(ResultStatusEnum.OK.code, ResultStatusEnum.OK.message, data)
        }

        /**
         * 构建空内容成功响应
         * @return 状态为[ResultStatusEnum.NO_CONTENT]的[Result]对象
         */
        @JvmStatic
        fun success(): Result<Unit> {
            return Result(ResultStatusEnum.NO_CONTENT.code, ResultStatusEnum.NO_CONTENT.message, Unit)
        }

        /**
         * 构建带自定义消息的失败响应
         *
         * @param message 自定义错误消息
         * @return 状态码为[ResultStatusEnum.SERVICE_UNAVAILABLE.code]的[Result]对象
         */
        @JvmStatic
        fun failure(message: String): Result<Unit> {
            return Result(ResultStatusEnum.SERVICE_UNAVAILABLE.code, message, Unit)
        }

        /**
         * 构建默认失败响应
         * @return 状态为[ResultStatusEnum.SERVICE_UNAVAILABLE]的[Result]对象
         */
        @JvmStatic
        fun failure(): Result<Unit> {
            return Result(ResultStatusEnum.SERVICE_UNAVAILABLE.code, ResultStatusEnum.SERVICE_UNAVAILABLE.message, Unit)
        }

        /**
         * 创建空数据Builder实例
         */
        @JvmStatic
        fun builder(): Builder<Unit> {
            return Builder(Unit)
        }

        /**
         * 创建带初始数据的Builder实例
         * @param data 初始数据对象
         * @return 初始化后的[Builder]实例
         */
        @JvmStatic
        fun <T> builder(data: T): Builder<T> {
            return Builder(data)
        }
    }

    /**
     * 结果对象构建器，支持链式调用
     *
     * 使用示例：
     * ```
     * Result.builder(userData)
     *     .status(ResultStatusEnum.CREATED)
     *     .message("用户创建成功")
     *     .build()
     * ```
     * @property data 构建器持有的数据对象
     */
    class Builder<T> internal constructor(
        private var data: T
    ) {
        private var code: Int? = null
        private var message: String? = null

        /**
         * 设置状态枚举
         *
         * @param status 预定义的状态枚举
         * @return 当前Builder实例
         */
        fun status(status: IResultStatus): Builder<T> = apply {
            code = status.code
            message = status.message
        }

        /**
         * 自定义状态码
         *
         * @param code 自定义状态码数值
         * @return 当前Builder实例
         */
        fun code(code: Int): Builder<T> = apply { this.code = code }

        /**
         * 自定义状态消息
         * @param message 自定义消息内容
         * @return 当前Builder实例
         */
        fun message(message: String): Builder<T> = apply { this.message = message }

        /**
         * 替换数据对象
         * @param data 新的数据对象
         * @return 当前Builder实例
         */
        fun data(data: T): Builder<T> = apply { this.data = data }

        /**
         * 构建最终结果对象
         * @return 配置完成的[Result]对象
         */
        fun build(): Result<T> {
            return Result(code, message, data)
        }
    }
}
