package by.kmmq.features.products.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import by.kmmq.features.products.presentation.R
import by.kmmq.features.products.presentation.adapter.DishAdapter
import by.kmmq.features.products.presentation.databinding.FragmentDishListBinding
import by.kmmq.features.products.presentation.model.DishUI
import by.kmmq.minz.utils.disposeOnDestroyLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class DishListFragment : Fragment(R.layout.fragment_dish_list) {

    private val viewModel by viewModel<BaseDishListViewModel>()
    private val binding by viewBinding(FragmentDishListBinding::bind)

    interface OnDishSelected {
        fun onSelected(dish: DishUI)
    }

    private val onDishClickListener = object : OnDishSelected {
        override fun onSelected(dish: DishUI) {
            viewModel.goToDetails(dish)
        }
    }

    private val onDishSwitchListener = object : OnDishSelected {
        override fun onSelected(dish: DishUI) {
            viewModel.switchDish(dish)
        }
    }

    private val adapter = DishAdapter(onDishClickListener, onDishSwitchListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dishList.adapter = adapter

        viewModel.dishes.doOnNext {
            binding.swipe.isRefreshing = false
            adapter.setList(it)
        }
            .subscribe()
            .disposeOnDestroyLifecycle(this.viewLifecycleOwner)

        viewModel.state.doOnNext {
            when (it) {
                BaseDishListViewModel.ScreenEventState.Download -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.deleteButton.visibility = View.GONE
                    binding.swipe.isEnabled = false
                }
                BaseDishListViewModel.ScreenEventState.Normal -> {
                    binding.progress.visibility = View.GONE
                    binding.deleteButton.visibility = View.VISIBLE
                    binding.swipe.isEnabled = true
                }
            }
        }
            .subscribe()
            .disposeOnDestroyLifecycle(this.viewLifecycleOwner)

        viewModel.deleteButtonState.doOnNext {
            binding.deleteButton.isEnabled = it
        }
            .subscribe()
            .disposeOnDestroyLifecycle(this.viewLifecycleOwner)

        viewModel.error.doOnNext {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT)
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteSelected()
        }

        binding.swipe.setOnRefreshListener {
            viewModel.downloadDishes()
        }

    }
}