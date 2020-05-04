package com.sabo.dominik.inspiringpeople

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.io.FileNotFoundException
import java.io.InputStream


const val OPERATION_SUCCESSFUL = 1

class AddActivity : AppCompatActivity() {

    var returnValue: Int = -1
    private var hasPicture: Boolean = false
    private lateinit var bitmap: Bitmap


    lateinit var etName: EditText
    lateinit var etDates: EditText
    lateinit var etDescription: EditText
    lateinit var etQuote: EditText
    lateinit var btnQuote: Button
    lateinit var btnCancel: Button
    lateinit var btnOK: Button
    lateinit var btnGallery: Button
    lateinit var ivGallery: ImageView

    private var quotes: ArrayList<String> = ArrayList(0)

    private val repository: PeopleRepository = PeopleRepository.instance

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setUpViews()
        setUpListeners()
    }

    private fun setUpViews(){
        etName = findViewById(R.id.etName)
        etDates = findViewById(R.id.etDates)
        etDescription = findViewById(R.id.etDescription)
        etQuote = findViewById(R.id.etQuote)
        btnQuote = findViewById(R.id.btnQuote)
        btnCancel = findViewById(R.id.btnCancel)
        btnOK = findViewById(R.id.btnOK)
        btnGallery = findViewById(R.id.btnGallery)
        ivGallery = findViewById(R.id.ivGallery)
    }

    private fun setUpListeners(){
        btnQuote.setOnClickListener {
            if(etQuote.text.toString() != ""){
                quotes.add(etQuote.text.toString())
                etQuote.setText("")
            }
            else Toast.makeText(this, "Fill in the quote field", Toast.LENGTH_SHORT).show()
        }

        btnCancel.setOnClickListener{
            returnValue = OPERATION_CANCELED
            finish()
        }

        btnOK.setOnClickListener{
            if(etName.text.toString() == "" || etDates.text.toString() == "" || etDescription.text.toString() == "" || quotes.isEmpty() || !hasPicture){
                Toast.makeText(this, "Fill in all fields and add at least one quote!", Toast.LENGTH_SHORT).show()
            }
            else{
                repository.add(InspiringPerson(etName.text.toString(), etDates.text.toString(), etDescription.text.toString(), quotes, bitmap))
                returnValue = OPERATION_SUCCESSFUL
                finish()
            }
        }

        btnGallery.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (reqCode == 1) {
                try {
                    val imageUri: Uri = data?.data!!
                    val imageStream: InputStream? = contentResolver.openInputStream(imageUri)
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    bitmap = selectedImage
                    hasPicture = true
                    ivGallery.setImageBitmap(bitmap)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun finish() {
        setResult(returnValue)
        super.finish()
    }
}
