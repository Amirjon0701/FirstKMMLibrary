package util

import di.allModules
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(allModules)
    }
}