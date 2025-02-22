package ca.ulaval.ima.tp2.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.R

class AbacusFragment : Fragment() {

    private lateinit var firstSeekBar: SeekBar
    private lateinit var secondSeekBar: SeekBar
    private lateinit var resultSeekBar: SeekBar
    private lateinit var firstValueText: TextView
    private lateinit var secondValueText: TextView
    private lateinit var resultText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_abacus, container, false)

        firstSeekBar = view.findViewById(R.id.first_seekbar)
        secondSeekBar = view.findViewById(R.id.second_seekbar)
        resultSeekBar = view.findViewById(R.id.result_seekbar)
        firstValueText = view.findViewById(R.id.first_seekbar_value)
        secondValueText = view.findViewById(R.id.second_seekbar_value)
        resultText = view.findViewById(R.id.result_text)

        firstSeekBar.max = 8
        secondSeekBar.max = 10
        resultSeekBar.max = 20
        resultSeekBar.isEnabled = false

        val seekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val firstValue = firstSeekBar.progress + 1
                val secondValue = secondSeekBar.progress + 2
                val result = firstValue + secondValue

                firstValueText.text = "Valeur: $firstValue"
                secondValueText.text = "Valeur: $secondValue"
                resultSeekBar.progress = result - 1
                resultText.text = "Valeur: $result"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }

        firstSeekBar.setOnSeekBarChangeListener(seekBarChangeListener)
        secondSeekBar.setOnSeekBarChangeListener(seekBarChangeListener)

        return view
    }
}
