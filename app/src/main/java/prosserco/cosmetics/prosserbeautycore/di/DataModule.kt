package prosserco.cosmetics.prosserbeautycore.di

import prosserco.cosmetics.prosserbeautycore.data.repository.CartRepository
import prosserco.cosmetics.prosserbeautycore.data.repository.TSDIKOnboardingRepo
import prosserco.cosmetics.prosserbeautycore.data.repository.OrderRepository
import prosserco.cosmetics.prosserbeautycore.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        TSDIKOnboardingRepo(
            tsdikOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}