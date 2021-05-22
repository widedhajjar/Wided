package com.example.wided.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.wided.FoodRepository.Singleton.foodList
import com.example.wided.MainActivity
import com.example.wided.R
import com.example.wided.adapter.FoodAdapter
import com.example.wided.adapter.FoodItemDecoration


class HomeFragment(
   private val context: MainActivity

) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)





        //enregistrer un premier "food" dans notre liste (Lait)
        //foodList.add(
        //  FoodModel(
        //  "lait",
        // "Le lait est un liquide biologique comestible généralement de couleur blanchâtre produit par les glandes mammaires des mammifères femelles.",
        // "https://cdn.pixabay.com/photo/2016/12/06/18/27/milk-1887234__480.jpg",
        //false


        //)
        //)

        //enregistrer un second "food" dans notre liste (Avocat)
        //foodList.add(
        // FoodModel(
        // "Avocat",
        // "L'avocat est le fruit de l'avocatier (Persea americana), un arbre de la famille des Lauraceae, originaire du Mexique. Il en existe trois grandes variétés.",
        // "https://cdn.pixabay.com/photo/2017/08/15/14/34/avocado-2644150__480.jpg",
        //true
        //)
        //)
        //enregistrer un troisieme "food" dans notre liste (Flocon)
        //foodList.add(
        //   FoodModel(
        //   "Flocon d'avoine",
        //  " Et pour cause, l'avoine est une céréale très complète. Elle contient des substances essentielles nécessaires pour une bonne alimentation. ",
        //  "https://media.istockphoto.com/photos/rolled-oats-or-oat-flakes-in-wooden-bowl-on-stone-background-picture-id874247746?b=1&k=6&m=874247746&s=170667a&w=0&h=qmMJnRkdCb6x88KG4wR34ur_RrOOn-feRdY9XdnrcF4=",
        // false
        //)
        //)
        //enregistrer un quatrieme "food" dans notre liste (Fromage)
        //foodList.add(
        //    FoodModel(
        //     "Fromage Blanc",
        //   "grand avantage du fromage blanc à 0% est qu'il ne contient qu'une très faible teneur en matière grasse.",
        //  "https://cdn.pixabay.com/photo/2016/11/12/10/08/goat-1818552__340.jpg",
        //  false
        // )
        // )
        //enregistrer un cinquieme "food" dans notre liste (Oeuf)
        // foodList.add(
        //   FoodModel(
        //    "Oeuf",
        //   " IL A PLUS D’UN ATOUT NUTRITIONNEL DANS SA COQUILLE !",
        //  "https://media.istockphoto.com/photos/closeup-group-of-raw-egg-put-on-wooden-timber-boardvintage-and-art-picture-id1024789902?k=6&m=1024789902&s=612x612&w=0&h=ycxCUe1kfdqy0uUkX8vg-tUV8nwz9ff33M5BpGgq7Ts=",
        // false
        // )
        // )

        //->Déja inserer dans la base de données

        



        //recuperer le recyclerview
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter =
            FoodAdapter(context, foodList.filter { !it.liked }, R.layout.item_horizental_food)

        //recuperer le second recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = FoodAdapter(context, foodList, R.layout.item_vertical_food)
        verticalRecyclerView.addItemDecoration(FoodItemDecoration())


        return view

    }



}






