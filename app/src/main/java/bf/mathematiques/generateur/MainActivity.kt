
package bf.mathematiques.generateur

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ScrollView

class MainActivity : Activity() {

    private val bleu = Color.rgb(20, 65, 120)
    private val fond = Color.rgb(245, 248, 252)

    private var modeChoisi = ""
    private var classeChoisie = ""

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
                modeChoisi = "IA en ligne"
                afficherChoixClasse()
            }
        }

        val boutonHorsLigne = Button(this).apply {
            text = "Banque hors connexion"
            setOnClickListener {
                modeChoisi = "Hors connexion"
                afficherChoixClasse()
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

    private fun afficherChoixClasse() {
        val contenu = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
            setBackgroundColor(fond)
        }

        contenu.addView(
            creerTexte("Choisir une classe", 24f)
        )

        contenu.addView(
            creerTexte(
                "Mode sélectionné : $modeChoisi", 16f
            )
        )

        ajouterSection(contenu, "ENSEIGNEMENT PRIMAIRE")

        ajouterClasses(
            contenu,
            listOf("CP1", "CP2", "CE1", "CE2", "CM1", "CM2")
        )

        ajouterSection(contenu, "PREMIER CYCLE")

        ajouterClasses(
            contenu,
            listOf("6e", "5e", "4e", "3e")
        )

        ajouterSection(contenu, "SECOND CYCLE")

        ajouterClasses(
            contenu,
            listOf(
                "2nde A", "2nde C",
                "1ère A", "1ère C", "1ère D",
                "Tle A", "Tle C", "Tle D"
            )
        )

        val retour = Button(this).apply {
            text = "Retour aux modes"
            setOnClickListener {
                afficherChoixMode()
            }
        }

        contenu.addView(retour)

        val defilement = ScrollView(this).apply {
            addView(contenu)
        }

        setContentView(defilement)
    }

    private fun ajouterSection(
        layout: LinearLayout,
        titre: String
    ) {
        layout.addView(
            creerTexte(titre, 18f)
        )
    }

    private fun ajouterClasses(
        layout: LinearLayout,
        classes: List<String>
    ) {
        for (classe in classes) {
            val bouton = Button(this).apply {
                text = classe
                setOnClickListener {
                    classeChoisie = classe
                    afficherConfirmation()
                }
            }

            layout.addView(bouton)
        }
    }

    private fun afficherConfirmation() {
        val layout = creerLayout()

        val titre = creerTexte(
            "Sélection réussie !", 24f
        )

        val mode = creerTexte(
            "Mode : $modeChoisi", 18f
        )

        val classe = creerTexte(
            "Classe : $classeChoisie", 22f
        )

        val information = creerTexte(
            "Nous allons maintenant préparer " +
            "les notions mathématiques de cette classe.", 16f
        )

        val retour = Button(this).apply {
            text = "Changer de classe"
            setOnClickListener {
                afficherChoixClasse()
            }
        }

        layout.addView(titre)
        layout.addView(mode)
        layout.addView(classe)
        layout.addView(information)
        layout.addView(retour)

        setContentView(layout)
    }
}
