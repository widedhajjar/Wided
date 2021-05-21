package com.example.wided.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.wided.FoodModel
import com.example.wided.FoodRepository
import com.example.wided.FoodRepository.Singleton.downloadUri
import com.example.wided.MainActivity
import com.example.wided.R
import java.util.*

@Suppress("DEPRECATION")
class AddFoodFragment(
    private val context: MainActivity
):Fragment() {
    private var file: Uri? = null
    private  var uploadedImage:ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view= inflater?.inflate(R.layout.fragment_add_food, container,false)

        //recuperer le bouton pour charger l'image
        uploadedImage = view.findViewById(R.id.preview_image)

        //recuperer le bouton pour charger l'image
        val pickupImageButton= view.findViewById<Button>(R.id.upload_button)

        //lorsqu'on clique dessus ça ouvre les images du telephone
        pickupImageButton.setOnClickListener { pickupImage()}

        //recuperer le bouton confirmer
        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener{ sendForm(view) }

        return view
    }

    private fun sendForm(view: View) {
        val repo = FoodRepository()
        repo.uploadImage(file!!) {

            val foodName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val foodDescription = view.findViewById<EditText>(R.id.description_input).text.toString()
            val calorie = view.findViewById<Spinner>(R.id.calorie_spinner).selectedItem.toString()
            val digestion = view.findViewById<Spinner>(R.id.digestion_spinner).selectedItem.toString()
            val downloadImageUri = downloadUri

            //creer un nouvel food FoodModel
            val food = FoodModel(
                UUID.randomUUID().toString(),
                foodName,
                foodDescription,
                downloadImageUri.toString(),
                calorie,
                digestion

            )
            //envoyer en bdd
            repo.insertFood(food)
        }



    }

    private fun pickupImage(){
        val intent= Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 47)

    }
      override fun onActivityResult(requestCode:Int, resultCode: Int, data: Intent?){
          super.onActivityResult(requestCode, resultCode, data)
          if(requestCode==47 && resultCode== Activity.RESULT_OK){

              //verifier si les données sont nulles
              if(data == null || data.data == null) return

              //recuperer l'image
              file = data.data

              //mettre à jour l'aperçu de l'image
              uploadedImage?.setImageURI(file)



          }
      }


}
