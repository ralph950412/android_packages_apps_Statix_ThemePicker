package com.statix.android.customization.module

import android.app.Activity
import android.content.Context

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment

import com.android.wallpaper.model.WallpaperInfo
import com.android.wallpaper.module.CustomizationSections
import com.android.wallpaper.picker.MonetPreviewFragment

import com.android.customization.module.ThemePickerInjector
import com.android.customization.picker.notifications.ui.viewmodel.NotificationSectionViewModel

public class StatixCustomizationInjector : ThemePickerInjector() {

    private var customizationSections: CustomizationSections? = null

    override fun getPreviewFragment(
            context: Context,
            wallpaperInfo: WallpaperInfo,
            mode: Int,
            viewAsHome: Boolean,
            viewFullScreen: Boolean,
            testingModeEnabled: Boolean): Fragment {
        return MonetPreviewFragment.newInstance(wallpaperInfo, mode, viewAsHome, viewFullScreen, testingModeEnabled);
    }

    override fun getCustomizationSections(activity: ComponentActivity): CustomizationSections {
        return customizationSections
            ?: StatixCustomizationSections(
                    getColorPickerViewModelFactory(
                        context = activity,
                        wallpaperColorsViewModel = getWallpaperColorsViewModel(),
                    ),
                    getKeyguardQuickAffordancePickerInteractor(activity),
                    getKeyguardQuickAffordancePickerViewModelFactory(activity),
                    NotificationSectionViewModel.Factory(
                        interactor = getNotificationsInteractor(activity),
                    ),
                    getFlags(),
                    getClockCarouselViewModel(activity),
                    getClockViewFactory(activity),
                    getDarkModeSnapshotRestorer(activity),
                    getThemedIconSnapshotRestorer(activity),
                    getThemedIconInteractor(),
                )
                .also { customizationSections = it }
    }
}
