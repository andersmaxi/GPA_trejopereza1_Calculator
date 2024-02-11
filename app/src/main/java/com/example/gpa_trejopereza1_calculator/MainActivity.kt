package com.example.gpa_trejopereza1_calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lscourse1 : EditText = findViewById<EditText>(R.id.txtcourse1)
        val lscourse2 : EditText = findViewById<EditText>(R.id.txtcourse2)
        val lscourse3 : EditText = findViewById<EditText>(R.id.txtcourse3)
        val lscourse4 : EditText = findViewById<EditText>(R.id.txtcourse4)
        val lscourse5 : EditText = findViewById<EditText>(R.id.txtcourse5)

        val lsgpatext : TextView = findViewById<TextView>(R.id.txtgpa)


        val calculate_gpa: Button = findViewById<Button>(R.id.btngpa)

        val  card_gpa :RelativeLayout   =  findViewById<RelativeLayout>(R.id.card_gpa)

        //*****************************************************
        //  COURSE #1
        //*****************************************************
        BackGPAOnChangeText(lscourse1,card_gpa,calculate_gpa,lsgpatext)

        //*****************************************************
        //  COURSE #2
        //*****************************************************
        BackGPAOnChangeText(lscourse2,card_gpa,calculate_gpa,lsgpatext)

        //*****************************************************
        //  COURSE #3
        //*****************************************************
        BackGPAOnChangeText(lscourse3,card_gpa,calculate_gpa,lsgpatext)

        //*****************************************************
        //  COURSE #4
        //*****************************************************
        BackGPAOnChangeText(lscourse4,card_gpa,calculate_gpa,lsgpatext)

        //*****************************************************
        //  COURSE #5
        //*****************************************************
        BackGPAOnChangeText(lscourse5,card_gpa,calculate_gpa,lsgpatext)




        calculate_gpa.setOnClickListener()
        {

            if (calculate_gpa.tag=="GPA" ) {
                if (!validate_grades(lscourse1)) {
                    return@setOnClickListener

                }

                if (!validate_grades(lscourse2)) {
                    return@setOnClickListener

                }

                if (!validate_grades(lscourse3)) {
                    return@setOnClickListener

                }

                if (!validate_grades(lscourse4)) {
                    return@setOnClickListener

                }

                if (!validate_grades(lscourse5)) {
                    return@setOnClickListener

                }


                //*********************************************
                // Calculate GPA
                //*********************************************
                val gpa1: Int = lscourse1.text.toString().toInt();
                val gpa2: Int = lscourse2.text.toString().toInt();
                val gpa3: Int = lscourse3.text.toString().toInt();
                val gpa4: Int = lscourse4.text.toString().toInt();
                val gpa5: Int = lscourse5.text.toString().toInt();

                val finalgpa: Double = ((gpa1 + gpa2 + gpa3 + gpa4 + gpa5) / 5.0).toDouble()

                lsgpatext.text = finalgpa.toString();

                if (finalgpa <= 60) {

                    card_gpa.setBackgroundColor(Color.rgb(191, 54, 12))
                    lsgpatext.setTextColor(Color.rgb(191, 54, 12))


                } else if ((finalgpa >= 61) and (finalgpa <= 79)) {
                    card_gpa.setBackgroundColor(Color.rgb(255, 171, 0))
                    lsgpatext.setTextColor(Color.rgb(255, 171, 0))
                } else if (finalgpa >= 80) {
                    card_gpa.setBackgroundColor(Color.rgb(0, 105, 92))
                    lsgpatext.setTextColor(Color.rgb(0, 105, 92))
                }


                calculate_gpa.text = "Clear Form"
                calculate_gpa.tag  = "Clear"
            }
            else
            {
                lscourse1.setText("")
                lscourse2.setText("")
                lscourse3.setText("")
                lscourse4.setText("")
                lscourse5.setText("")

                lsgpatext.setText("")
                card_gpa.setBackgroundColor(Color.rgb(255, 255, 255))

                calculate_gpa.text = "Calculate GPA"
                calculate_gpa.tag  = "GPA"

            }


        }
    }

    fun  validate_grades(edittextobj :EditText) : Boolean
    {
        val lscourse_txt =  edittextobj.text.toString().trim();

        if (lscourse_txt.isEmpty())
        {
            edittextobj.error ="Grade is required"
            return false
        }


        if (! isNumeric(lscourse_txt))
        {

            edittextobj.error ="Incorrect Numeric Format"
            return false

        }

        if ((lscourse_txt.toInt()>100) or  (lscourse_txt.toInt()< 0) )
        {
            edittextobj.error ="Incorrect Grade. Valid Range(0-100)"
            return false
        }


        return true

    }


    private fun isNumeric(str: String): Boolean {
        return str.matches("-?\\d+(\\.\\d+)?".toRegex())
    }

    private fun BackGPAOnChangeText(txtcourse : EditText,card_gpa:RelativeLayout,calculate_gpa:Button,lsgpatext:TextView)
    {

        txtcourse.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                card_gpa.setBackgroundColor(Color.rgb(255, 255, 255))
                lsgpatext.setText("")
                calculate_gpa.text = "Calculate GPA"
                calculate_gpa.tag  = "GPA"
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })

    }



}