package com.example.wided.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wided.*

class FoodAdapter(
    internal val context: MainActivity,
    private val foodList: List<FoodModel>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<FoodAdapter.ViewHolder>(){
    //boite pour ranger tout les composants à controler
    class ViewHolder( view: View) : RecyclerView.ViewHolder(view){
        val foodImage = view.findViewById<ImageView>(R.id.image_item)
        val foodName : TextView?= view.findViewById(R.id.name_item)
        val foodDescription : TextView?= view.findViewById(R.id.description_item)
        val starIcon= view.findViewById<ImageView>(R.id.star_icon)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view = LayoutInflater
         .from(parent.context)
         .inflate(layoutId,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //recuperer les informations  du "food"
        val currentFood=foodList[position]

        //recuperer le repository
        val repo = FoodRepository()



        //utiliser glide pour recuperer l'image à partir de son lien ->composant
       Glide.with(context).load(Uri.parse(currentFood.imageUrl)).into(holder.foodImage)

        //mettre à jour le nom
        holder.foodName?.text = currentFood.name


        //mettre à jour la description
        holder.foodDescription?.text = currentFood.description
       // verifier si le food a été liké
        if(currentFood.liked){
            holder.starIcon.setImageResource(R.drawable.ic_star)
        }
        else{
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

        //rajouter une interaction sur l'etoile
        holder.starIcon.setOnClickListener{
            //inverser si le bouton est like ou non
            currentFood.liked= !currentFood.liked
            //mettre à jour l'objet food
            repo.updateFood(currentFood)
        }

        //interaction lors du clic sur un food
        holder.itemView.setOnClickListener{
            //afficher la popup
            FoodPopup(this,currentFood).show()
        }

    }

    override fun getItemCount(): Int = foodList.size
    }
