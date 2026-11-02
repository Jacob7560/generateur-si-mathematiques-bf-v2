
package bf.mathematiques.generateur

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(245, 248, 252))
        }

        val titre = TextView(this).apply {
            text = "Générateur SI Maths BF"
            textSize = 26f
            setTextColor(Color.rgb(20, 65, 120))
            gravity = Gravity.CENTER
        }

        val description = TextView(this).apply {
            text = "Créons des situations d'intégration adaptées au Burkina Faso."
            textSize = 17f
            gravity = Gravity.CENTER
            setPadding(0, 24, 0, 24)
        }

        val bouton = Button(this).apply {
            text = "Commencer"
            setOnClickListener {
                description.text =
                    "Bienvenue ! Ton générateur est en construction."
            }
        }

        layout.addView(titre)
        layout.addView(description)
        layout.addView(bouton)

        setContentView(layout)
    }
}
