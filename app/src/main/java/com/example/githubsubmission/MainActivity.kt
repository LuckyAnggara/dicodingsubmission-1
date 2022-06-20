package com.example.githubsubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvUsers : RecyclerView
    private val list = ArrayList<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_user_github)
        rvUsers.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()
    }

    private val listUsers:ArrayList<User>
    get(){
        val dataUserName = resources.getStringArray(R.array.username)
        val dataName = resources.getStringArray(R.array.name)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataFollowing = resources.getStringArray(R.array.username)
        val dataFollowers = resources.getStringArray(R.array.username)
        val dataAvatar = resources.obtainTypedArray(R.array.avatar)
        val listUser = ArrayList<User>()
        for(i in dataName.indices){
            val user = User(dataName[i], dataUserName[i], dataAvatar.getResourceId(i, -1));
            listUser.add(user)
        }
        return listUser
    }

    private fun showRecyclerList(){
        rvUsers.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvUsers.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(user:User){
        Toast.makeText(this, "kamu memilih" + user.name, Toast.LENGTH_SHORT ).show()
    }
}