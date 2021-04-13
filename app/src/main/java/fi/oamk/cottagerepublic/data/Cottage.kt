package fi.oamk.cottagerepublic.data

import android.os.Parcelable
import fi.oamk.cottagerepublic.R
import kotlinx.parcelize.Parcelize


@Parcelize
data class Cottage (
    var cottageId: Long = 0L,
    var cottageLabel: String = "Test",
    var images:MutableList<String> = mutableListOf<String>(
        R.drawable.cottage_image_sample.toString(),
        R.drawable.cottage_image_sample1.toString(),
        R.drawable.cottage_image_sample2.toString()
    ),
    var rating: Float = 0.0F,
    var price: Float = 0.0F,
    var location: String = "",
    var hottub: Boolean = true,
    var kitchen: Boolean = true,
    var pets: Boolean = true,
    var sauna: Boolean = true
) : Parcelable {
}