package org.example.project.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.data.InMemoryPhotoStorage
import org.example.project.data.KtorPhotoApi
import org.example.project.data.PhotoApi
import org.example.project.data.PhotoRepository
import org.example.project.data.PhotoScreenModel
import org.example.project.data.PhotoStorage
import org.example.project.screen.detail.DetailScreenModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

/**
 * 定义数据模块，用于数据层依赖项的配置。
 */
val dataModule = module {
    /**
     * 使用 Koin 的 [single] 函数定义一个单例依赖项，确保整个应用中只有一个 [HttpClient] 实例。
     * 创建 [HttpClient] 时，同时配置了 JSON 内容协商，以便客户端能够发送和接收 JSON 格式的数据。
     */
    single {
        // 创建用于 JSON 序列化和反序列化的配置对象，允许忽略未知的键。
        val json = Json {
            ignoreUnknownKeys = true // 如果设为 true，则在反序列化时忽略任何未知的 JSON 键。
        }

        // 创建 HTTP 客户端实例，配置内容协商以支持 JSON 数据。
        HttpClient {
            install(ContentNegotiation) {
                // 使用上面创建的 JSON 配置，设置客户端能够接收任意 Content-Type 的响应。
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<PhotoApi> { KtorPhotoApi(get()) }
    single<PhotoStorage> { InMemoryPhotoStorage() }
    single {
        PhotoRepository(get(), get()).apply {
            initalize()
        }
    }
}

/**
 * 屏幕模型模块，定义了应用中屏幕相关的 ViewModel 依赖项。
 * :: 根据模板返回构造函数
 * factoryOf 接收构造函数并创建实例
 */
val screenModelsModule = module {
    factoryOf (::PhotoScreenModel)

    factoryOf (::DetailScreenModel)
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            screenModelsModule,
        )
    }
}