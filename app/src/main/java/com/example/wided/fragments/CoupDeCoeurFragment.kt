package com.example.wided.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wided.FoodRepository.Singleton.foodList
import com.example.wided.MainActivity
import com.example.wided.R
import com.example.wided.adapter.FoodAdapter
import com.example.wided.adapter.FoodItemDecoration

class CoupDeCoeurFragment (
    private val context: MainActivity
    ) : Fragment(){
    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         val view = inflater?.inflate(R.layout.fragment_coup_de_coeur,container,false)



        //recuperer ma recyclerview
        val coupdecoeurRecyclerView = view.findViewById<RecyclerView>(R.id.coup_de_coeur_recycler_list)
        coupdecoeurRecyclerView.adapter = FoodAdapter(context,foodList.filter { it.liked }, R.layout.item_vertical_food)
        coupdecoeurRecyclerView.layoutManager= LinearLayoutManager(context)
        coupdecoeurRecyclerView.addItemDecoration(FoodItemDecoration())
        return view

      }

    }
