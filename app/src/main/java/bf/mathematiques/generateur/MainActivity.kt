
package bf.mathematiques.generateur

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private val bleu = Color.rgb(20, 65, 120)
    private val fond = Color.rgb(245, 248, 252)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        afficherAccueil()
    }

    private fun creerLayout(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(fond)
        }
    }

    private fun creerTexte(
        contenu: String,
        taille: Float
    ): TextView {
        return TextView(this).apply {
            text = contenu
            textSize = taille
            gravity = Gravity.CENTER
            setTextColor(bleu)
            setPadding(8, 16, 8, 16)
        }
    }

    private fun afficherAccueil() {
        val layout = creerLayout()

        val titre = creerTexte(
            "Générateur SI Maths BF", 26f
        )

        val description = creerTexte(
            "Créons des situations d'intégration " +
            "adaptées au Burkina Faso.", 17f
        )

        val bouton = Button(this).apply {
            text = "Commencer"
            setOnClickListener {
                afficherChoixMode()
            }
        }

        layout.addView(titre)
        layout.addView(description)
        layout.addView(bouton)

        setContentView(layout)
    }

    private fun afficherChoixMode() {
        val layout = creerLayout()

        val titre = creerTexte(
            "Choisir le mode de fonctionnement", 22f
        )

        val description = creerTexte(
            "Comment souhaites-tu créer tes situations ?", 16f
        )

        val boutonIA = Button(this).apply {
            text = "Intelligence artificielle en ligne"
            setOnClickListener {
                afficherConfirmation(
                    "Mode IA en ligne sélectionné"
                )
            }
        }

        val boutonHorsLigne = Button(this).apply {
            text = "Banque hors connexion"
            setOnClickListener {
                afficherConfirmation(
                    "Mode hors connexion sélectionné"
                )
            }
        }

        val retour = Button(this).apply {
            text = "Retour"
            setOnClickListener {
                afficherAccueil()
            }
        }

        layout.addView(titre)
        layout.addView(description)
        layout.addView(boutonIA)
        layout.addView(boutonHorsLigne)
        layout.addView(retour)

        setContentView(layout)
    }

    private fun afficherConfirmation(message: String) {
        val layout = creerLayout()

        val texte = creerTexte(message, 22f)

        val information = creerTexte(
            "Cette fonctionnalité sera développée " +
            "dans les prochaines étapes.", 16f
        )

        val retour = Button(this).apply {
            text = "Revenir aux modes"
            setOnClickListener {
                afficherChoixMode()
            }
        }

        layout.addView(texte)
        layout.addView(information)
        layout.addView(retour)

        setContentView(layout)
    }
}
