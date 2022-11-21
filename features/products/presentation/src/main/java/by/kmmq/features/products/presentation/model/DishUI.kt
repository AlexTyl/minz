package by.kmmq.features.products.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DishUI(
    val id: String,
    val name: String,
    val description: String?,
    val image: String?,
    val price: String,
    var isSwitches: Boolean = false
) : Parcelable