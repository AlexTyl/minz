package by.kmmq.features.products.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import by.kmmq.features.products.presentation.R
import by.kmmq.features.products.presentation.databinding.FragmentDishDetailsBinding
import by.kmmq.features.products.presentation.model.DishUI
import com.bumptech.glide.Glide

class DishDetailsFragment : Fragment(R.layout.fragment_dish_details) {

    private val binding by viewBinding(FragmentDishDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<DishUI>(DishUI::javaClass.name)?.let { dish ->
            with(binding) {
                name.text = dish.name
                description.text = dish.description
                price.text = dish.price
                Glide.with(this@DishDetailsFragment).load(dish.image)
                    .placeholder(R.drawable.ic_android_black_24dp).into(image)
            }
        }

    }
}