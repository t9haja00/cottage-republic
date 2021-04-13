package fi.oamk.cottagerepublic.ui.home

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import fi.oamk.cottagerepublic.data.Cottage
import fi.oamk.cottagerepublic.ui.account.NewCottage

@BindingAdapter("image")
fun ImageView.setDestinationImage(item: Destination) = setImageResource(item.image)


@BindingAdapter("destinationName")
fun TextView.setDestinationName(item: Destination) {
    text = item.destinationName
}

@BindingAdapter("image")
fun ImageView.setCottageImage(item: Cottage) = setImageResource(item.image)

@BindingAdapter("cottageLabel")
fun TextView.setCottageName(item: Cottage) {
    text = item.cottageLabel
}

@BindingAdapter("rating")
fun RatingBar.setCottageRating(item: Cottage) {
    rating = item.rating
}

@BindingAdapter("location")
fun TextView.setCottageLocation(item: Cottage) {
    text = item.location
}

@BindingAdapter("price")
fun TextView.setCottagePrice(item: Cottage) {
    text = "${item.price} €/night"
}

@BindingAdapter("cottageName")
fun TextView.setCottageName(item: NewCottage) {
    text = item.cottageName
}