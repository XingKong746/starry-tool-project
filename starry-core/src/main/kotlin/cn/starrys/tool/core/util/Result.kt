package cn.starrys.tool.core.util

import cn.starrys.tool.core.constant.IResultStatus
import org.jetbrains.annotations.Contract
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Serial
import java.io.Serializable
import java.time.Instant
import java.util.*

/**
 * 一个通用类，用于封装操作结果。
 * 包含时间戳、操作状态、状态码、消息和特定数据等信息。
 * <br></br>
 * Created: 2024/12/6 21:23 .
 *
 * @param <T> 结果中携带的数据类型
 * @author XingKong
</T> */
// class Result<T> private constructor() : IResultStatus, Serializable {
//     /**
//      * 结果创建的时间戳
//      */
//     val timestamp: Instant = Instant.now()
//
//     /**
//      * 操作状态，true 表示成功，false 表示失败,
//      * 用于快速获取执行操作的状态。
//      */
//     var status: Boolean? = null
//         private set
//
//     /**
//      * 状态码
//      */
//     var code: Int? = null
//         private set
//
//     /**
//      * 消息
//      */
//     var message: String? = null
//         private set
//
//     /**
//      * 数据
//      */
//     var data: T? = null
//         private set
//
//     /**
//      * 构造函数，初始化时间戳、状态、状态码、消息和数据
//      *
//      * @param status  操作状态
//      * @param code    状态码
//      * @param message 消息
//      * @param data    数据
//      */
//     constructor(status: Boolean?, code: Int?, message: String?, data: T) : this() {
//         this.status = status
//         this.code = code
//         this.message = message
//         this.data = data
//     }
//
//     /**
//      * 构造函数，初始化时间戳、状态、状态码、消息和数据
//      *
//      * @param resultStatus 结果状态
//      * @param data         数据
//      */
//     constructor(resultStatus: IResultStatus, data: T) : this(
//         resultStatus.getStatus(),
//         resultStatus.getCode(),
//         resultStatus.getMessage(),
//         data
//     )
//
//
//     /**
//      * 构建器，用于构建 Result 对象
//      *
//      * @param <T> 数据类型
//     </T> */
//     class ResultBuilder<T>(data: T) : Serializable {
//         private var status: Boolean? = null
//         private var code: Int? = null
//         private var message: String? = null
//         private var data: T? = null
//
//         /**
//          * 构建器构造函数，初始化数据
//          *
//          * @param data 初始数据
//          */
//         init {
//             data(data)
//         }
//
//         /**
//          * 设置操作状态
//          *
//          * @param status 操作状态
//          * @return 当前构建器对象
//          */
//         fun status(status: Boolean): ResultBuilder<T> {
//             this.status = status
//             return this
//         }
//
//         /**
//          * 设置状态码
//          *
//          * @param code 状态码
//          * @return 当前构建器对象
//          */
//         fun code(code: Int?): ResultBuilder<T> {
//             this.code = code
//             return this
//         }
//
//         /**
//          * 设置消息
//          *
//          * @param message 消息
//          * @return 当前构建器对象
//          */
//         fun message(message: String?): ResultBuilder<T> {
//             this.message = message
//             return this
//         }
//
//         /**
//          * 设置数据
//          *
//          * @param data 数据
//          * @return 当前构建器对象
//          */
//         fun data(data: T): ResultBuilder<T> {
//             this.data = data
//             return this
//         }
//
//         /**
//          * 设置状态
//          *
//          * @param status 状态
//          * @return 当前构建器对象
//          */
//         fun status(status: IResultStatus): ResultBuilder<T> {
//             return status(status.getStatus()).code(status.getCode()).message(status.getMessage())
//         }
//
//         /**
//          * 设置状态和数据
//          *
//          * @param status 状态
//          * @param data   数据
//          * @return 当前构建器对象
//          */
//         fun status(status: IResultStatus, data: T): ResultBuilder<T> {
//             return status(status).data(data)
//         }
//
//         /**
//          * 构建并返回 Result 对象
//          *
//          * @return 构建的 Result 对象
//          */
//         fun build(): Result<T?> {
//             return Result(status, code, message, data)
//         }
//
//         companion object {
//             @Serial
//             private const val serialVersionUID = 4327958315156040L
//         }
//     }
//
//     /**
//      * 返回结果对象的字符串表示形式
//      *
//      * @return 结果对象的字符串表示形式
//      */
//     override fun toString(): String {
//         return StringJoiner(", ", Result::class.java.simpleName + "{", "}")
//             .add("timestamp=$timestamp")
//             .add("status=$status")
//             .add("code=$code")
//             .add("message='$message'")
//             .add("data=$data")
//             .toString()
//     }
//
//     companion object {
//         @Serial
//         private const val serialVersionUID = 4327958315156039L
//
//         private val log: Logger = LoggerFactory.getLogger(Result::class.java)
//
//         /**
//          * 创建一个带有状态、消息和数据的成功结果
//          *
//          * @param resultStatus 结果状态
//          * @param data         数据
//          * @param <T>          数据类型
//          * @return 带有状态、消息和数据的成功结果对象
//         </T> */
//         fun <T> success(resultStatus: IResultStatus, data: T): Result<T> {
//             if (resultStatus.isError()) log.error("结果状态异常：使用的 IResultStatus 似乎并不是成功状态")
//             return result(resultStatus, data)
//         }
//
//         /**
//          * 创建一个带有消息和数据的成功结果
//          *
//          * @param message 成功消息
//          * @param data    成功数据
//          * @param <T>     数据类型
//          * @return 带有消息和数据的成功结果对象
//         </T> */
//         fun <T> success(message: String?, data: T): Result<T> {
//             return success(null, message, data)
//         }
//
//         /**
//          * 创建一个带有状态、消息和数据的成功结果
//          *
//          * @param code    状态码
//          * @param message 成功消息
//          * @param data    成功数据
//          * @param <T>     数据类型
//          * @return 带有状态、消息和数据的成功结果对象
//         </T> */
//         @Contract("_, _, _ -> new")
//         fun <T> success(code: Int?, message: String?, data: T): Result<T> {
//             return result(true, code, message, data)
//         }
//
//
//         /**
//          * 创建一个指定失败状态的结果
//          *
//          * @param resultStatus 结果状态
//          * @return 指定状态的结果
//          */
//         fun failure(resultStatus: IResultStatus): Result<Void?> {
//             if (!resultStatus.isError()) log.error("结果状态异常：使用的 IResultStatus 似乎并不是失败状态")
//             return result(resultStatus, null)
//         }
//
//         /**
//          * 创建一个带有消息的失败结果
//          *
//          * @param message 失败消息
//          * @return 带有消息的失败结果对象
//          */
//         fun failure(message: String?): Result<Void?> {
//             return failure(null, message)
//         }
//
//         /**
//          * 创建一个带有状态码和消息的失败结果
//          *
//          * @param code    状态码
//          * @param message 失败消息
//          * @return 带有状态码和消息的失败结果对象
//          */
//         @Contract("_, _ -> new")
//         fun failure(code: Int?, message: String?): Result<Void?> {
//             return result(false, code, message, null)
//         }
//
//
//         /**
//          * 创建一个带有状态、状态码、消息和数据的结果
//          *
//          * @param status  结果状态
//          * @param code    状态码
//          * @param message 结果消息
//          * @param data    结果数据
//          * @param <T>     数据类型
//          * @return 带有状态、状态码、消息和数据的结果对象
//         </T> */
//         @Contract("_, _, _, _ -> new")
//         fun <T> result(status: Boolean?, code: Int?, message: String?, data: T): Result<T> {
//             return Result(status, code, message, data)
//         }
//
//         /**
//          * 创建一个带有结果状态和数据的结果
//          *
//          * @param resultStatus 结果状态
//          * @param data         结果数据
//          * @param <T>          数据类型
//          * @return 带有结果状态和数据的结果对象
//         </T> */
//         fun <T> result(resultStatus: IResultStatus, data: T): Result<T> {
//             return Result(resultStatus, data)
//         }
//
//
//         /**
//          * 用于创建不包含数据的Result建造者实例。<br></br>
//          * 若需要使用包含数据的构建者，请使用 [Result.builder] 方法。
//          *
//          * @return ResultBuilder<Void>，不包含 data 属性的 ResultBuilder
//         </Void> */
//         @Contract(" -> new")
//         fun builder(): ResultBuilder<Void?> {
//             return ResultBuilder(null)
//         }
//
//         /**
//          * 用于创建包含数据的Result建造者实例。
//          *
//          * @param <T>  泛型，表示结果中包含的数据的数据类型。
//          * @param data Result 中包含的数据。
//          * @return ResultBuilder<T>，包含指定 data 属性的 ResultBuilder
//         </T></T> */
//         @Contract("_ -> new")
//         fun <T> builder(data: T): ResultBuilder<T> {
//             return ResultBuilder(data)
//         }
//     }
// }
