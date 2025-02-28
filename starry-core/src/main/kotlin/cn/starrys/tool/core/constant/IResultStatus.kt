package cn.starrys.tool.core.constant

import java.io.Serializable

/**
 * 一个接口，用于表示请求结果的状态。
 * 该接口内包含了一个默认方法来判断请求结果的不同系列，根据具体需要使用。
 * 需要创建一个枚举实现
 */
interface IResultStatus : Serializable {
}
