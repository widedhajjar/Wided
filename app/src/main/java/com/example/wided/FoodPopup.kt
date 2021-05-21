package com.example.wided

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.wided.adapter.FoodAdapter

class FoodPopup (
    private val adapter: FoodAdapter,
    private val currentFood: FoodModel
    ): Dialog(adapter.context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_foods_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupStarbutton()

    }
    private fun updateStar(button: ImageView){
        if(currentFood.liked) {
            button.setImageResource(R.drawable.ic_star)
        }
        else{
            button.setImageResource((R.drawable.ic_unstar))
        }
    }

    private fun setupStarbutton() {
        //recuperer
        val startButton =findViewById<ImageView>(R.id.start_button)
        updateStar(startButton)
        //interaction
        startButton.setOnClickListener{
            currentFood.liked = !currentFood.liked
            val repo = FoodRepository()
            repo.updateFood(currentFood)
            updateStar(startButton)

        }
    }

    private fun setupDeleteButton() {
findViewById<ImageView>(R.id.delete_button).setOnClickListener{
    //supprimer le food de la base
    val repo = FoodRepository()
    repo.deleteFood(currentFood)
    dismiss()

} }

    private fun setupCloseButton() {
findViewById<ImageView>(R.id.close_button).setOnClickListener{
    //fermer la fenetre popup
    dismiss()
}   }

    private fun setupComponents() {
        //actualiser l'image du food
        val foodImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentFood.imageUrl)).into(foodImage)
        //actualiser le nom du food
        findViewById<TextView>(R.id.popup_food_name).text=currentFood.name
        //actualiser la description du food
        findViewById<TextView>(R.id.popup_food_description_subtitle).text=currentFood.description
        //actualiser les calories du food
        findViewById<TextView>(R.id.popup_food_calorie_subtitle).text=currentFood.calorie
        //actualiser la digestion du food
        findViewById<TextView>(R.id.popup_food_digestion_subtitle).text=currentFood.digestion


    }




}