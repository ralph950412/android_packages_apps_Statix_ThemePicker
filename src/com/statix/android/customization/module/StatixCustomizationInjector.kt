package com.statix.android.customization.module

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import com.android.customization.module.ThemePickerInjector
import com.android.customization.picker.notifications.ui.viewmodel.NotificationSectionViewModel
import com.android.wallpaper.dispatchers.BackgroundDispatcher
import com.android.wallpaper.dispatchers.MainDispatcher
import com.android.wallpaper.module.CustomizationSections
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

@Singleton
open class StatixCustomizationInjector @Inject constructor(
    @MainDispatcher mainScope: CoroutineScope,
    @MainDispatcher mainDispatcher: CoroutineDispatcher,
    @BackgroundDispatcher bgDispatcher: CoroutineDispatcher,
) : ThemePickerInjector(
    mainScope,
    mainDispatcher,
    bgDispatcher,
) {

  private var customizationSections: CustomizationSections? = null

  override fun getCustomizationSections(activity: ComponentActivity): CustomizationSections {
    val wallpaperColorsViewModel = getWallpaperColorsViewModel()
    return customizationSections
        ?: StatixCustomizationSections(
                getColorPickerViewModelFactory(
                    context = activity,
                    wallpaperColorsViewModel = wallpaperColorsViewModel,
                ),
                getKeyguardQuickAffordancePickerInteractor(activity),
                getKeyguardQuickAffordancePickerViewModelFactory(activity),
                getNotificationSectionViewModelFactory(activity),
                getFlags(),
                getClockCarouselViewModelFactory(
                    getClockPickerInteractor(activity.applicationContext),
                ),
                getClockViewFactory(activity),
                getDarkModeSnapshotRestorer(activity),
                getThemedIconSnapshotRestorer(activity),
                getThemedIconInteractor(),
                getColorPickerInteractor(activity, wallpaperColorsViewModel),
            )
            .also { customizationSections = it }
  }
}
