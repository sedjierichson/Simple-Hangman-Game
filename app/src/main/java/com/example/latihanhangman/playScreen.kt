package com.example.latihanhangman

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.StringBuilder
import kotlin.random.Random
import kotlin.streams.asSequence

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [playScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class playScreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val _btnBackToHome = view.findViewById<Button>(R.id.backToHome)
        _btnBackToHome.setOnClickListener {
            val mfHome = homeScreen()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfHome, homeScreen::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
        var currentPoint:Int = 15
        val _textPoint = view.findViewById<TextView>(R.id.textPoint)
        val _btnSurrender = view.findViewById<Button>(R.id.btnSurrender)
        _btnSurrender.setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString("DATA",_textPoint.text.toString())


            val mfResult = resultScreen()
            mfResult.arguments = mBundle
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfResult, resultScreen::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        val _tvRandomWord = view.findViewById<TextView>(R.id.tvRandomWord)
        val _editTextGuess = view.findViewById<EditText>(R.id.editTextGuess)
        val _btnCheck = view.findViewById<Button>(R.id.buttonCheck)
        val randomWords = listOf(
            "apple",
            "orange",
            "computer",
            "kotlin",
            "america",
            "watermelon",
            "indonesia",
            "singapore",
            "zombie",
            "android",
            "google",
            "airplane"
        )
        var randomIndex: Int = 0
        var randomTheWord: String = ""

        fun randomWord() {
            randomIndex = Random.nextInt(0, randomWords.size)
            randomTheWord = randomWords[randomIndex]
            println(randomTheWord)


            val randomString = randomWords[randomIndex].toMutableList()
            println(randomString)


            val randomStringView = randomString.shuffled()
            println(randomStringView)
            //println(randomString)

            _tvRandomWord.text =
                randomStringView.toString().replace("[", "").replace("]", "").replace(",", " ");
        }

        fun checkPoint(){
            if (currentPoint < 0){
                currentPoint = 0
               _tvRandomWord.text = "GAME OVER"
                val mBundle = Bundle()
                mBundle.putString("DATA",_textPoint.text.toString())


                val mfResult = resultScreen()
                mfResult.arguments = mBundle
                val mFragmentManager = parentFragmentManager
                mFragmentManager.beginTransaction().apply {
                    replace(R.id.frameContainer, mfResult, resultScreen::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
            }
        }


        randomWord()
        checkPoint()
        _btnCheck.setOnClickListener {
            val wordGuess = _editTextGuess.text.toString()

            //println(randomString)
            if (randomTheWord == wordGuess) {
                println("TEBAKAN BETUL")
                currentPoint = currentPoint + 10
                _textPoint.text = currentPoint.toString()
                randomWord()
                checkPoint()
            }
            else{
                println("TEBAKAN SALAH")

                currentPoint = currentPoint - 5
                checkPoint()
                _textPoint.text = currentPoint.toString()
                randomWord()

            }
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment playScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            playScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}