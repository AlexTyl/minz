package by.kmmq.features.products.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.kmmq.features.products.presentation.list.DishListFragment
import by.kmmq.features.products.presentation.R
import by.kmmq.features.products.presentation.databinding.DishListItemBinding
import by.kmmq.features.products.presentation.model.DishUI
import com.bumptech.glide.Glide

class DishAdapter(
    private val onDishClickListener: DishListFragment.OnDishSelected,
    private val onDishSwitchListener: DishListFragment.OnDishSelected
) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    private val dishes: MutableList<DishUI> = mutableListOf()

    class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding by viewBinding(DishListItemBinding::bind)
    }

    fun setList(dishes: List<DishUI>) {
        val diff = DiffUtil.calculateDiff(DiffCallback(this.dishes, dishes))
        this.dishes.clear()
        this.dishes.addAll(dishes)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DishViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.dish_list_item, parent, false)
    )

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishes[position]

        holder.itemView.setOnClickListener {
            onDishClickListener.onSelected(dish)
        }

        with(holder.binding) {
            name.text = dish.name
            description.text = dish.description

            switchButton.setOnCheckedChangeListener { _, _ -> }
            switchButton.isChecked = dish.isSwitches
            switchButton.setOnCheckedChangeListener { _, isChecked ->
                dish.isSwitches = isChecked
                onDishSwitchListener.onSelected(dish)
            }

            price.text = dish.price
            Glide.with(holder.itemView).load(dish.image)
                .placeholder(R.drawable.ic_android_black_24dp).into(image)
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    private class DiffCallback(val old: List<DishUI>, val new: List<DishUI>) : DiffUtil.Callback() {

        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition].id == new[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition].id == new[newItemPosition].id
                    && old[oldItemPosition].isSwitches == new[newItemPosition].isSwitches

    }

}