package org.example.project.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.CancellableContinuation

/**
 * 定义了获取照片数据的接口。
 * 这个接口规定了所有实现类必须提供获取照片列表的方法。
 */
interface PhotoApi {

    /**
     * 异步获取照片对象列表。
     * @return 一个包含照片对象的列表，这些对象从远程数据源获取。
     */
    suspend fun getData(): List<PhotoObject>
}

/**
 * KtorPhotoApi 类实现了 [PhotoApi] 接口，负责使用 Ktor HTTP 客户端从远程 API 获取照片数据。
 *
 * @param client Ktor [HttpClient] 实例，用于发起 HTTP 请求。
 * 实例化时应提供已配置的 HTTP 客户端，例如设置了超时、重试策略等。
 */
class KtorPhotoApi(private val client: HttpClient): PhotoApi {
    companion object {
        /**
         * 存储 API 的 URL 地址，用于访问 JSON 格式的照片列表。
         * 该 URL 应指向一个返回照片数据的端点。
         */
        private const val API_URL =
            "https://raw.githubusercontent.com/Kotlin/KMP-App-Template/main/list.json"
    }

    /**
     * 根据定义的 API_URL 从远程服务器获取照片数据。
     * 该方法使用 Ktor 的 HTTP 客户端发起 GET 请求，并尝试解析响应体为照片对象列表。
     * 如果请求失败或响应体不是预期格式，将捕获异常并返回空列表，防止应用崩溃。
     *
     * @return 解析得到的 [List] 类型的照片对象列表。
     * @throws CancellationException 如果协程被取消，将重新抛出这个异常。
     */
    override suspend fun getData(): List<PhotoObject> {
        return try {
            // 使用 Ktor 客户端发起 GET 请求，并获取响应体。
            client.get(API_URL).body()
        } catch (e: Exception) {
            // 检查捕获的异常是否为 CancellationException，如果是，则重新抛出。
            if (e is CancellationException) throw e

            // 打印异常堆栈信息，有助于调试和日志记录。
            e.printStackTrace()

            // 在发生错误时返回空列表，保证方法的调用者总能收到一个列表类型的返回
            emptyList()
        }
    }
}