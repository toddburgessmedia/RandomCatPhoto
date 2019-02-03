package com.toddburgessmedia.randomcatkotlin

import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val catPhotoModule = module {

    viewModel<RandomCatViewModel>()
}