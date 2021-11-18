package com.cursosant.android.userssp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursosant.android.userssp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)

        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("SP", "${getString(R.string.sp_first_time)} = $isFirstTime")

        if (isFirstTime) {
            val dialgoView = layoutInflater.inflate(R.layout.dialog_register, null)
            MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.dialog_title)
                    .setView(dialgoView)
                    .setCancelable(false)
                    .setPositiveButton(R.string.dialog_confirm, { dialogInterface, i ->
                        val username = dialgoView.findViewById<TextInputEditText>(R.id.etUsername)
                                .text.toString()
                        with(preferences.edit()){
                            putBoolean(getString(R.string.sp_first_time), false)
                            putString(getString(R.string.sp_username), username)
                                    .apply()
                        }
                        Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT)
                                .show()
                    })
                    .show()

        } else {
            val username = preferences.getString(getString(R.string.sp_username), getString(R.string.hint_username))
            Toast.makeText(this, "Bienvenido $username", Toast.LENGTH_SHORT).show()
        }

        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    private fun getUsers(): MutableList<User>{
        val users = mutableListOf<User>()

        val diana = User(1, "diana", "Ramirez", "data:image/jpeg;base64,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")
        val samanta = User(2, "Samanta", "Meza", "https://upload.wikimedia.org/wikipedia/commons/b/b2/Samanta_villar.jpg")
        val javier = User(3, "Javier", "Gómez", "https://live.staticflickr.com/974/42098804942_b9ce35b1c8_b.jpg")
        val emma = User(4, "Emma", "Cruz", "https://upload.wikimedia.org/wikipedia/commons/d/d9/Emma_Wortelboer_%282018%29.jpg")

        users.add(diana)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(diana)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(diana)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(diana)
        users.add(samanta)
        users.add(javier)
        users.add(emma)

        return users
    }

    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "$position: ${user.getFullName()}" , Toast.LENGTH_SHORT).show()
    }
}