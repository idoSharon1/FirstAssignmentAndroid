package com.example.tictactoe

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.Button
import androidx.core.view.WindowInsetsCompat
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class PlayerTurnOptions
    {
        X,
        O
    }

    private var firstTurn: PlayerTurnOptions = PlayerTurnOptions.X
    private var currentTurn: PlayerTurnOptions = PlayerTurnOptions.X

    private var boardList = mutableListOf<Button>()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initBoard()
    }

    private fun initBoard()
    {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
        updateCurrentTurnLabel(true)
    }

    fun boardTapped(view: View) {
        if (view !is Button) return
        addToBoard(view)
        updateCurrentTurnLabel()
    }

    private fun addToBoard(button: Button) {
        if (button.text != "") return

        if (currentTurn == PlayerTurnOptions.X) {
            button.text = "X"
            currentTurn = PlayerTurnOptions.O
        } else {
            button.text = "0"
            currentTurn = PlayerTurnOptions.X
        }
    }

    private fun updateCurrentTurnLabel(firstTurn: Boolean = false) {
        var updatedText: String = ""

        updatedText = if (firstTurn) {
             "Current Turn: X"
        } else {
             if (currentTurn == PlayerTurnOptions.X) "Current Turn: X"  else  "Current Turn: O"
        }
        binding.turnText.text = updatedText
    }

    private fun resetBoard() {
        for (currentButton in boardList) {
            currentButton.text = ""
        }
    }
}