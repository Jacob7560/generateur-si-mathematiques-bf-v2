
package bf.mathematiques.generateur

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ScrollView

class MainActivity : Activity() {

    private val bleu = Color.rgb(20, 65, 120)
    private val fond = Color.rgb(245, 248, 252)

    private var modeChoisi = ""
    private var classeChoisie = ""
    private var baremeChoisi = 10
    
    private lateinit var listeNotions: LinearLayout
    private lateinit var precisions: EditText

    private val champsNotions = mutableListOf<EditText>()

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

        layout.addView(
            creerTexte("Générateur SI Maths BF", 26f)
        )

        layout.addView(
            creerTexte(
                "Créons des situations d'intégration " +
                "adaptées au Burkina Faso.", 17f
            )
        )

        val bouton = Button(this).apply {
            text = "Commencer"
            setOnClickListener {
                afficherChoixMode()
            }
        }

        layout.addView(bouton)
        setContentView(layout)
    }

    private fun afficherChoixMode() {
        val layout = creerLayout()

        layout.addView(
            creerTexte(
                "Choisir le mode de fonctionnement", 22f
            )
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

        layout.addView(boutonIA)
        layout.addView(boutonHorsLigne)

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
            creerTexte("Mode : $modeChoisi", 16f)
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

        val defilement = ScrollView(this).apply {
            addView(contenu)
        }

        setContentView(defilement)
    }

    private fun ajouterSection(
        layout: LinearLayout,
        titre: String
    ) {
        layout.addView(creerTexte(titre, 18f))
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
                    afficherChoixBareme()
                }
            }

            layout.addView(bouton)
        }
    }

    private fun afficherChoixBareme() {
    val contenu = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(24, 24, 24, 24)
        setBackgroundColor(fond)
    }

    contenu.addView(
        creerTexte("Choisir le barème", 24f)
    )

    contenu.addView(
        creerTexte("Classe : $classeChoisie", 18f)
    )

    contenu.addView(
        creerTexte(
            "Sélectionnez le nombre de points de votre situation.",
            16f
        )
    )

    val baremes = listOf(4, 5, 10, 20)

    for (bareme in baremes) {
        val bouton = Button(this).apply {
            text = "$bareme points"

            setOnClickListener {
                baremeChoisi = bareme
                afficherSaisieNotions()
            }
        }

        contenu.addView(bouton)
    }

    val retour = Button(this).apply {
        text = "Changer de classe"
        setOnClickListener {
            afficherChoixClasse()
        }
    }

    contenu.addView(retour)

    val defilement = ScrollView(this).apply {
        addView(contenu)
    }

    setContentView(defilement)
    }
    
    private fun afficherSaisieNotions() {
        champsNotions.clear()

        val contenu = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
            setBackgroundColor(fond)
        }

        contenu.addView(
            creerTexte("Notions mathématiques", 24f)
        )

        contenu.addView(
            creerTexte("Classe : $classeChoisie", 18f)
        )

        contenu.addView(
            creerTexte(
                "Saisissez les notions que vous souhaitez " +
                "mobiliser dans votre situation.", 16f
            )
        )

        listeNotions = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        contenu.addView(listeNotions)

        ajouterChampNotion()

        val ajouter = Button(this).apply {
            text = "+ Ajouter une notion"
            setOnClickListener {
                ajouterChampNotion()
            }
        }

        contenu.addView(ajouter)

        contenu.addView(
            creerTexte(
                "Précisions ou orientations pédagogiques " +
                "(facultatif)", 17f
            )
        )

        precisions = EditText(this).apply {
            hint = "Ajoutez vos indications pédagogiques..."
            minLines = 3
            gravity = Gravity.TOP
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.WHITE)
        }

        contenu.addView(precisions)

        val continuer = Button(this).apply {
            text = "Continuer"
            setOnClickListener {
                val notions = champsNotions
                    .map { it.text.toString().trim() }
                    .filter { it.isNotEmpty() }

                if (notions.isEmpty()) {
                    champsNotions.first().error =
                        "Saisissez au moins une notion"
                } else {
                    afficherConfirmationNotions(
                        notions,
                        precisions.text.toString().trim()
                    )
                }
            }
        }

        contenu.addView(continuer)

        val retour = Button(this).apply {
            text = "Changer de classe"
            setOnClickListener {
                afficherChoixClasse()
            }
        }

        contenu.addView(retour)

        val defilement = ScrollView(this).apply {
            addView(contenu)
        }

        setContentView(defilement)
    }

    private fun ajouterChampNotion() {
        val numero = champsNotions.size + 1

        val champ = EditText(this).apply {
            hint = "Notion mathématique $numero"
            textSize = 16f
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.WHITE)
        }

        champsNotions.add(champ)
        listeNotions.addView(champ)

        val supprimer = Button(this).apply {
            text = "Supprimer cette notion"
            setOnClickListener {
                if (champsNotions.size > 1) {
                    champsNotions.remove(champ)
                    listeNotions.removeView(champ)
                    listeNotions.removeView(this)
                }
                else {
                    champ.text.clear()
                    champ.error = "Conservez au moins un champ"
                }
            }
        }

        listeNotions.addView(supprimer)
    }

    private fun afficherConfirmationNotions(
    notions: List<String>,
    indications: String
) {
    val layout = creerLayout()

    layout.addView(
        creerTexte("Préparation de la situation", 23f)
    )

    layout.addView(
        creerTexte("Mode : $modeChoisi", 17f)
    )

    layout.addView(
        creerTexte("Classe : $classeChoisie", 18f)
    )
    
    layout.addView(
    creerTexte("Barème : $baremeChoisi points", 18f)
)
    
    layout.addView(
        creerTexte("Notions à mobiliser :", 19f)
    )

    notions.forEachIndexed { index, notion ->
        layout.addView(
            creerTexte("${index + 1}. $notion", 16f)
        )
    }

    if (indications.isNotEmpty()) {
        layout.addView(
            creerTexte("Précisions : $indications", 15f)
        )
    }

    layout.addView(
        creerTexte(
            "Vos choix sont prêts. Vous pouvez passer " +
            "à la préparation de la situation.", 16f
        )
    )

    val generer = Button(this).apply {
        text = "Générer la situation"
        setOnClickListener {
            afficherGenerationEnPreparation(notions, indications)
        }
    }

    layout.addView(generer)

    val retour = Button(this).apply {
        text = "Modifier les notions"
        setOnClickListener {
            afficherSaisieNotions()
        }
    }

    layout.addView(retour)
    setContentView(layout)
}

private fun afficherGenerationEnPreparation(
    notions: List<String>,
    indications: String
) {
    val layout = creerLayout()

    layout.addView(
        creerTexte("Génération de la situation", 23f)
    )

    layout.addView(
        creerTexte("Classe : $classeChoisie", 18f)
    )
    
    layout.addView(
    creerTexte("Barème : $baremeChoisi points", 18f)
)
    
    layout.addView(
        creerTexte("Mode choisi : $modeChoisi", 16f)
    )

    layout.addView(
        creerTexte(
            "Notions à traiter :\n" +
            notions.joinToString("\n") { "• $it" },
            16f
        )
    )

    layout.addView(
        creerTexte(
            "La banque de situations et le service d'IA " +
            "seront intégrés dans les prochaines étapes. " +
            "Aucune situation n'a encore été générée.",
            16f
        )
    )

    if (indications.isNotEmpty()) {
        layout.addView(
            creerTexte("Précisions : $indications", 15f)
        )
    }

    val retour = Button(this).apply {
        text = "Retour aux notions"
        setOnClickListener {
            afficherSaisieNotions()
        }
    }

       layout.addView(retour)
    setContentView(layout)
}

private fun afficherGrilleCorrection() {

    val layout = creerLayout()

    layout.gravity = Gravity.TOP

    layout.addView(
        creerTexte("GRILLE DE CORRECTION", 23f)
    )

    layout.addView(
        creerTexte("Barème : $baremeChoisi points", 18f)
    )

    val grille = listOf(
        Triple("CM1 – Pertinence", 1.5, listOf(
            "Conversion" to 0.5,
            "Addition des volumes" to 0.5,
            "Conclusion : note au président" to 0.5
        )),
        Triple("CM2 – Utilisation correcte des outils mathématiques", 4.5, listOf(
            "Écriture de l'expression mathématique" to 0.25,
            "Mise des nombres sous une forme permettant l'opération" to 0.5,
            "Étapes dans un ordre logique" to 0.5,
            "Transformation des puissances de 10" to 0.5,
            "Factorisation par une puissance de 10" to 0.5,
            "Égalités successives correctes" to 0.25,
            "Conversion de 3,6 × 10³ en 0,36 × 10⁴" to 0.5,
            "Calcul de 2,4 + 0,36" to 0.5,
            "Obtention de 2,76 × 10⁴" to 0.5,
            "Conversion en 27 600 litres" to 0.5
        )),
        Triple("CM3 – Cohérence du raisonnement et du résultat", 3.0, listOf(
            "Enchaînement logique des étapes" to 1.25,
            "Adéquation entre conclusion et démarche" to 0.75,
            "Conformité de la production finale à la demande" to 1.0
        )),
        Triple("CP – Perfectionnement", 1.0, listOf(
            "Travail lisible et bien organisé" to 0.5,
            "Absence de ratures" to 0.25,
            "Résultat final correctement encadré" to 0.25
        ))
    )

    val totaux = if (baremeChoisi == 4) {
        listOf(0.5, 1.75, 1.25, 0.5)
    } else {
        listOf(
            1.5 * baremeChoisi / 10.0,
            4.5 * baremeChoisi / 10.0,
            3.0 * baremeChoisi / 10.0,
            1.0 * baremeChoisi / 10.0
        )
    }

    for (i in grille.indices) {

        val critere = grille[i]
        val totalCritere = totaux[i]

        layout.addView(
            creerTexte(critere.first, 19f)
        )

        for (indicateur in critere.third) {

            val points = indicateur.second *
                totalCritere / critere.second

            layout.addView(
                creerTexte(
                    "${indicateur.first} : " +
                    "%.3f".format(points) + " pt",
                    15f
                )
            )
        }

        layout.addView(
            creerTexte(
                "Total : $totalCritere points",
                17f
            )
        )
    }

    layout.addView(
        creerTexte("TOTAL GÉNÉRAL : $baremeChoisi points", 20f)
    )

    val retour = Button(this).apply {
        text = "Retour aux notions"
        setOnClickListener {
            afficherSaisieNotions()
        }
    }

    layout.addView(retour)

    val defilement = ScrollView(this).apply {
        addView(layout)
    }

    setContentView(defilement)
}
}
