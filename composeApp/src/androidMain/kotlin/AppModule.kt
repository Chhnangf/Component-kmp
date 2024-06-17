import org.example.project.ImageSDK
import org.koin.dsl.module

val appModule = module {
    single<ImageSDK> {
        ImageSDK(
            databaseDriverFactory = get()
        )
    }
}