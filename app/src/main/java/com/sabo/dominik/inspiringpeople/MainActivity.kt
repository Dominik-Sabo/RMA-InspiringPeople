package com.sabo.dominik.inspiringpeople

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.Toast


const val OPERATION_CANCELED = -1

class MainActivity() : AppCompatActivity(), PersonClickInterface {

    lateinit var adapter: RecyclerAdapter
    lateinit var rvRecyclerView : RecyclerView
    lateinit var btnAdd: Button
    private val repository: PeopleRepository = PeopleRepository.instance


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        rvRecyclerView = findViewById(R.id.rvRecyclerView)
        btnAdd = findViewById(R.id.btnAdd)
        adapter = RecyclerAdapter(this)

        rvRecyclerView.layoutManager = LinearLayoutManager(this)
        rvRecyclerView.adapter = adapter

        addFirstPeople()
    }

    fun addOnClick(view: View?) {
        val intent = Intent(this@MainActivity, AddActivity::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onPersonClick(position: Int) {
        Toast.makeText(this, repository.quotePerson(position), Toast.LENGTH_SHORT).show()
    }

    override fun onRemoveClick(position: Int) {
        adapter.removeItem(position)
        repository.remove(position)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == OPERATION_CANCELED) return
        else{
            adapter.addItem(repository.people.last())
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun addFirstPeople(){
        var bitmap: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.bill_gates)

        val billQuotes: ArrayList<String> = ArrayList(0)
        billQuotes.add("Success is a lousy teacher. It seduces smart people into thinking they can't lose.")
        billQuotes.add("Your most unhappy customers are your greatest source of learning.")
        billQuotes.add("It's fine to celebrate success but it is more important to heed the lessons of failure.")

        repository.add(InspiringPerson("Bill Gates", "1955", "An American business magnate, software developer, investor, and philanthropist. He is best known as the co-founder of Microsoft Corporation.", billQuotes, bitmap))

        val steveQuotes: ArrayList<String> = ArrayList(0)

        bitmap = BitmapFactory.decodeResource( this.resources, R.drawable.steve_jobs)
        steveQuotes.add("Design is not just what it looks like and feels like. Design is how it works.")
        steveQuotes.add("Innovation distinguishes between a leader and a follower.")
        steveQuotes.add("I want to put a ding in the universe.")

        repository.add(InspiringPerson("Steve Jobs", "1955 - 2011", "An American inventor, designer and entrepreneur who was the co-founder, chief executive and chairman of Apple Computer.", steveQuotes, bitmap))

        adapter.addData(repository.people)
    }

}
