package prosserco.cosmetics.prosserbeautycore.di

import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.AppViewModel
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.CartViewModel
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.CheckoutViewModel
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.TSDIKOnboardingVM
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.OrderViewModel
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.ProductDetailsViewModel
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.ProductViewModel
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.TSDIKSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        TSDIKSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        TSDIKOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}