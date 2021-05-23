package com.example.wided


import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wided.fragments.AddFoodFragment
import com.example.wided.fragments.CoupDeCoeurFragment
import com.example.wided.fragments.HomeFragment
import com.example.wided.repository.Repository
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessagingService


class MainActivity : AppCompatActivity() {






//MVVM
    private lateinit var viewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Push
     val myFirebaseMessagingService = FirebaseMessagingService()


//MVVM

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this , viewModelFactory).get(MainViewModel::class.java)
        viewModel.getFood()
        viewModel.myResponse.observe(this, Observer { response->
            if (response.isSuccessful){
                Log.d("Response", response.body()?.id.toString())
                Log.d("Response", response.body()?.name!!)
                Log.d("Response", response.body()?.image!!)
                Log.d("Response", response.body()?.content!!)
                Log.d("Response", response.body()?.link!!)



            }else{
                Log.d("Response", response.errorBody().toString())


            }

        })



        loadFragment(HomeFragment(this), R.string.home_page_title)


        //importer la bottomnavigationview
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
             when (it.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_title)
                     return@setOnNavigationItemSelectedListener true


                }
                R.id.coup_de_coeur_page -> {
                    loadFragment(CoupDeCoeurFragment(this), R.string.Coup_de_coeur_page_food_title)
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.add_food_page -> {
                    loadFragment(AddFoodFragment(this), R.string.add_food_title)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }

        }





    }

    private fun loadFragment(fragment:Fragment,string: Int) {
        //charger notre FoodRepository
        val repo= FoodRepository()

        //actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        //mettre à jour la liste du foods

        repo.updateData {

        // injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }




}

