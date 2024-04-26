package di

import data.repository.ArticleRepository
import data.repository.impl.ArticleRepositoryImpl
import data.service.ArticleService
import data.service.impl.ArticleServiceImpl
import db.DatabaseDriverFactory
import provide.api.LocalDatabaseApi
import db.provideDatabaseDriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import provide.api.RemoteApi
import util.OkHttpClientFactory

private val networkModule = module {
    single {
        OkHttpClientFactory().createOkHttpClient()
    }
}

private val serviceModule = module {
    single<ArticleService> {
        ArticleServiceImpl(get())
    }
}

private val repositoryModule = module {
    single<ArticleRepository> {
        ArticleRepositoryImpl(get())
    }
}

private val databaseModule = module {
    single<DatabaseDriverFactory>{
        provideDatabaseDriverFactory()
    }

}

private val provideApiModule = module{
    singleOf(::RemoteApi)

    single<LocalDatabaseApi> {
        LocalDatabaseApi(get())
    }
}

val allModules = listOf(
    networkModule,
    serviceModule,
    repositoryModule,
    databaseModule,
    provideApiModule
)