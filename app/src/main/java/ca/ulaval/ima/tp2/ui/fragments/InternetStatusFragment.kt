package ca.ulaval.ima.tp2.ui.fragments

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.R
import ca.ulaval.ima.tp2.utils.NetworkUtils

class InternetStatusFragment : Fragment() {

    private lateinit var statusTextView: TextView
    private lateinit var indicatorView: View

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_internet_status, container, false)

        statusTextView = view.findViewById(R.id.status_text)
        indicatorView = view.findViewById(R.id.status_indicator)
        val checkButton: Button = view.findViewById(R.id.check_status_button)
        checkButton.setBackgroundColor(Color.parseColor("#808080"))

        checkButton.setOnClickListener {
            val status = NetworkUtils.getConnectionStatus(requireContext())
            statusTextView.text = status

            // Changez la couleur de l'indicateur en fonction du statut
            val background = indicatorView.background as GradientDrawable
            if (status == "Aucune connexion") {
                background.setColor(Color.RED)
            } else {
                background.setColor(Color.GREEN)
            }
        }

        return view
    }
}
