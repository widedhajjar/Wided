package com.example.wided.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.wided.FoodModel
import com.example.wided.FoodRepository.Singleton.foodList
import com.example.wided.MainActivity
import com.example.wided.R
import com.example.wided.adapter.FoodAdapter
import com.example.wided.adapter.FoodItemDecoration

class HomeFragment(
   private val context: MainActivity
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)







        //recuperer le recyclerview
        val horizontalRecyclerView=view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter= FoodAdapter(context, foodList.filter { !it.liked }, R.layout.item_horizental_food)

        //recuperer le second recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = FoodAdapter(context, foodList, R.layout.item_vertical_food)
        verticalRecyclerView.addItemDecoration(FoodItemDecoration())

        return view
    }


}

