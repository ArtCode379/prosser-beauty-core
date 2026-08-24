package prosserco.cosmetics.prosserbeautycore.di

import prosserco.cosmetics.prosserbeautycore.data.datastore.TSDIKOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { TSDIKOnboardingPrefs(androidContext()) }
}