package cn.starrys.tool.core.constant

import cn.starrys.tool.core.constant.IResultStatus.Series

enum class ResultStatusEnum(
    override val code: Int,
    override val message: String,
    private val series: Series? = null
) : IResultStatus {

// ---------------------------------------- 信息 ----------------------------------------
    /** 服务器已收到请求头，客户端应继续发送请求体（用于大文件上传前的确认）。 */
    CONTINUE(100, "服务器已收到请求头，客户端应继续发送请求体。", Series.INFORMATIONAL),

    /** 服务器根据客户端请求切换协议（如从 HTTP 升级到 WebSocket）。 */
    SWITCHING_PROTOCOLS(101, "服务器根据客户端请求切换协议。", Series.INFORMATIONAL),

    /** 服务器已接受请求但尚未完成处理。 */
    PROCESSING(102, "服务器已接受请求但尚未完成处理。", Series.INFORMATIONAL),

    /** 允许服务器在最终响应前返回部分响应头（用于预加载资源）。 */
    EARLY_HINTS(103, "允许服务器在最终响应前返回部分响应头。", Series.INFORMATIONAL),

// ---------------------------------------- 成功 ----------------------------------------
    /** 请求成功，返回所需内容（如网页、数据等）。 */
    OK(200, "请求成功，返回所需内容。", Series.SUCCESSFUL),

    /** 请求已成功并创建了新资源（如 POST 请求后返回新资源的 URL）。 */
    CREATED(201, "请求已成功并创建了新资源。", Series.SUCCESSFUL),

    /** 请求已接受但尚未处理完成（适用于异步任务）。 */
    ACCEPTED(202, "请求已接受但尚未处理完成。", Series.SUCCESSFUL),

    /** 请求成功，但无返回内容（如 DELETE 请求）。 */
    NO_CONTENT(204, "请求成功，但无返回内容。", Series.SUCCESSFUL),

    /** 服务器返回部分内容（用于断点续传或分块下载）。 */
    PARTIAL_CONTENT(206, "服务器返回部分内容。", Series.SUCCESSFUL),

    /** 多个资源的状态信息（如批量操作）。 */
    MULTI_STATUS(207, "多个资源的状态信息。", Series.SUCCESSFUL),

    /** 资源的状态已重复报告，避免多次响应。 */
    ALREADY_REPORTED(208, "资源的状态已重复报告，避免多次响应。", Series.SUCCESSFUL),

// ---------------------------------------- 重定向 ----------------------------------------
    /** 资源已永久重定向到新 URL（SEO 权重会转移）。 */
    MOVED_PERMANENTLY(301, "资源已永久重定向到新 URL。", Series.REDIRECTION),

    /** 资源临时重定向到新 URL（客户端应保持原请求方法）。 */
    FOUND(302, "资源临时重定向到新 URL。", Series.REDIRECTION),

    /** 重定向到新 URL，且强制客户端使用 GET 方法。 */
    SEE_OTHER(303, "重定向到新 URL，且强制客户端使用 GET 方法。", Series.REDIRECTION),

    /** 资源未修改，客户端可使用缓存版本。 */
    NOT_MODIFIED(304, "资源未修改，客户端可使用缓存版本。", Series.REDIRECTION),

    /** 临时重定向，要求客户端保持原请求方法。 */
    TEMPORARY_REDIRECT(307, "临时重定向，要求客户端保持原请求方法。", Series.REDIRECTION),

    /** 永久重定向，要求客户端保持原请求方法。 */
    PERMANENT_REDIRECT(308, "永久重定向，要求客户端保持原请求方法。", Series.REDIRECTION),

// ---------------------------------------- 客户端错误 ----------------------------------------
    /** 请求语法错误或参数无效（如缺少必填字段）。 */
    BAD_REQUEST(400, "请求语法错误或参数无效。", Series.CLIENT_ERROR),

    /** 需要身份验证（如未提供 Token 或密码错误）。 */
    UNAUTHORIZED(401, "需要身份验证。", Series.CLIENT_ERROR),

    /** 服务器拒绝请求（如权限不足）。 */
    FORBIDDEN(403, "服务器拒绝请求。", Series.CLIENT_ERROR),

    /** 请求的资源不存在。 */
    NOT_FOUND(404, "请求的资源不存在。", Series.CLIENT_ERROR),

    /** 请求方法不被允许（如用 POST 访问只支持 GET 的接口）。 */
    METHOD_NOT_ALLOWED(405, "请求方法不被允许。", Series.CLIENT_ERROR),

    /** 服务器等待请求超时。 */
    REQUEST_TIMEOUT(408, "服务器等待请求超时。", Series.CLIENT_ERROR),

    /** 请求与当前资源状态冲突（如重复提交）。 */
    CONFLICT(409, "请求与当前资源状态冲突。", Series.CLIENT_ERROR),

    /** 请求体超过服务器限制。 */
    PAYLOAD_TOO_LARGE(413, "请求体超过服务器限制。", Series.CLIENT_ERROR),

    /** URL 过长（如 GET 请求参数过多）。 */
    URI_TOO_LONG(414, "URL 过长。", Series.CLIENT_ERROR),

    /** 服务器不支持请求的媒体类型（如上传文件格式错误）。 */
    UNSUPPORTED_MEDIA_TYPE(415, "服务器不支持请求的媒体类型。", Series.CLIENT_ERROR),

    /** 客户端请求频率过高（触发限流）。 */
    TOO_MANY_REQUESTS(429, "客户端请求频率过高。", Series.CLIENT_ERROR),

    /** 因法律原因资源不可访问（如政府审查）。 */
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "因法律原因资源不可访问。", Series.CLIENT_ERROR),

// ---------------------------------------- 服务器错误 ----------------------------------------
    /** 服务器内部错误（如代码异常）。 */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误。", Series.SERVER_ERROR),

    /** 服务器不支持请求的功能。 */
    NOT_IMPLEMENTED(501, "服务器不支持请求的功能。", Series.SERVER_ERROR),

    /** 网关或代理服务器收到无效响应（如后端服务宕机）。 */
    BAD_GATEWAY(502, "网关或代理服务器收到无效响应。", Series.SERVER_ERROR),

    /** 服务器暂时不可用（如维护或过载）。 */
    SERVICE_UNAVAILABLE(503, "服务器暂时不可用。", Series.SERVER_ERROR),

    /** 网关或代理服务器超时。 */
    GATEWAY_TIMEOUT(504, "网关或代理服务器超时。", Series.SERVER_ERROR),

    /** 服务器不支持请求的 HTTP 协议版本。 */
    HTTP_VERSION_NOT_SUPPORTED(505, "服务器不支持请求的 HTTP 协议版本。", Series.SERVER_ERROR),
    ;

    override fun getSeries() = series ?: super.getSeries()

    companion object {
        /**
         * 将给定的状态码解析为[ResultStatusEnum]。
         * @param code 要解析的状态码
         * @return 匹配的的 [ResultStatusEnum]，如果没有找到，则为 `null`
         */
        @JvmStatic
        fun resolve(code: Int) = entries.firstOrNull { rse -> rse.code == code }
    }

}
