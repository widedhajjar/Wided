package com.example.wided

import android.net.Uri
import com.example.wided.FoodRepository.Singleton.databaseRef
import com.example.wided.FoodRepository.Singleton.downloadUri
import com.example.wided.FoodRepository.Singleton.foodList
import com.example.wided.FoodRepository.Singleton.storageReference
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.*

class FoodRepository {
    object Singleton {

        //donner le lien pour acceder au backet
        private val BUCKET_URL: String = "gs://healthy-fodd.appspot.com"

        //se connecter à notre espace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        //se connecter à la reference "foods"
        val databaseRef = FirebaseDatabase.getInstance().getReference("foods")

        //créer une liste qui va contenir nos foods
        val foodList = arrayListOf<FoodModel>()

        //contenir le lien de l'image courante
        var downloadUri: Uri? = null
    }
    fun updateData(callback:()->Unit){
        //absorber les données depuis la databaseref ->liste de foods
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer les anciens
                foodList.clear()
               //recolter la liste
                for(ds in snapshot.children){
                    //construire un objet food
                    val food = ds.getValue(FoodModel::class.java)

                    //verifier que le food n'est pas null
                    if(food != null){
                        //ajouter le food à notre liste
                        foodList.add(food)
                    }
                }
                //actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {}
        })


    }
    // créer une fonction pour envoyer des fichiers sur le storage

    fun uploadImage(file: Uri, callback: () -> Unit){
        //verifier que ce fichier n'est pas null
        if(file !=null){
            val fileName = UUID.randomUUID().toString()+ ".jpg"
            val ref = storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            //demarrer la tache d'envoi
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                //si il ya eu un probleme lors de l'envoi du fichier
                if (!task.isSuccessful){
                    task.exception?.let{throw it }
                }
                return@Continuation ref.downloadUrl

            }).addOnCompleteListener { task ->
                //verifier si tout a bien fonctionné
                if(task.isSuccessful){
                    //recuperer l'image
                 downloadUri= task.result
                    callback()
                }
            }
        }

    }
    //mettre à jour un objet food en bdd
    fun updateFood(food: FoodModel) = databaseRef.child(food.id).setValue(food)

    //inserer un nouvel food en bdd
    fun insertFood(food: FoodModel) = databaseRef.child(food.id).setValue(food)


    //supprimer une plante de la base
    fun deleteFood(food:FoodModel)= databaseRef.child(food.id).removeValue()
   }

