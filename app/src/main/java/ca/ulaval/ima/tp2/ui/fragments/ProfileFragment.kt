package ca.ulaval.ima.tp2.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.R
import ca.ulaval.ima.tp2.model.User

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Récupère l'objet User depuis les arguments
        val user = arguments?.getParcelable<User>("user")

        val firstName = if (user?.firstName.isNullOrBlank()) "Ramatoulaye" else user?.firstName
        val lastName = user?.lastName ?: "Barry"

        view.findViewById<TextView>(R.id.profile_name).text = "$firstName $lastName"
        view.findViewById<TextView>(R.id.profile_birthdate).text = "Date de naissance: ${user?.birthDate ?: "24-01-2000"}"
        view.findViewById<TextView>(R.id.profile_gender).text = "Genre: ${user?.gender ?: "Femme"}"
        view.findViewById<TextView>(R.id.profile_department).text = "Département: ${user?.department ?: "Informatique"}"

        return view
    }
}
