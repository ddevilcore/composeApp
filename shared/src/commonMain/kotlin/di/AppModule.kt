package di

import org.koin.dsl.module
import repositories.HomeRepository
import viewmodels.HomeViewModel

object AppModule {
    val appModule = module {
        single { HomeRepository() }
        factory {
            HomeViewModel(
                repository = get()
            )
        }
    }
}