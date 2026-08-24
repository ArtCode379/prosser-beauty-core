package prosserco.cosmetics.prosserbeautycore.data.repository

import prosserco.cosmetics.prosserbeautycore.data.datastore.TSDIKOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TSDIKOnboardingRepo(
    private val tsdikOnboardingStoreManager: TSDIKOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return tsdikOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            tsdikOnboardingStoreManager.setOnboardedState(state)
        }
    }
}