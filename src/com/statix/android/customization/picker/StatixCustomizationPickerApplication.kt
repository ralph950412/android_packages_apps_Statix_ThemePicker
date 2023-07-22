package com.statix.android.customization.picker

import android.app.Application
import com.android.wallpaper.module.InjectorProvider
import com.statix.android.customization.module.StatixCustomizationInjector

public class StatixCustomizationPickerApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    InjectorProvider.setInjector(StatixCustomizationInjector())
  }
}
