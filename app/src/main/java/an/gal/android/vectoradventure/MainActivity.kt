package an.gal.android.vectoradventure

import an.gal.android.vectoradventure.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devs.vectorchildfinder.VectorChildFinder
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vector: VectorChildFinder

    private val paths = arrayListOf<VectorElement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fillPathsArray()

        vector = VectorChildFinder(this, R.drawable.ic_curly_brackets_icon, binding.imageView)

        setButtonsBehavior()
    }

    private fun fillPathsArray() {
        val vector = VectorChildFinder(this, R.drawable.ic_curly_brackets_icon, binding.imageView)

        paths.add(VectorElement("first", R.color.color_yellow_active, R.color.color_yellow_inactive))
        paths.add(VectorElement("second", R.color.color_red_active, R.color.color_red_inactive))
        paths.add(VectorElement("third", R.color.color_blue_active, R.color.color_blue_inactive))
        paths.add(VectorElement("fourth", R.color.color_black_active, R.color.color_black_inactive))
    }

    private fun setButtonsBehavior() {
        binding.btnMakeInactive.setOnClickListener {
            val vector = VectorChildFinder(this, R.drawable.ic_curly_brackets_icon, binding.imageView)

            for (i in 0 until paths.size){
                vector.findPathByName(paths[i].path).fillColor = getColor(paths[i].colorInactive)
            }
        }

        binding.btnMakeActive.setOnClickListener {
            val vector = VectorChildFinder(this, R.drawable.ic_curly_brackets_icon, binding.imageView)

            for (i in 0 until paths.size){
                vector.findPathByName(paths[i].path).fillColor = getColor(paths[i].colorActive)
            }
        }

        binding.btnMakeRandom.setOnClickListener {
            // array of randoms
            val vector = VectorChildFinder(this, R.drawable.ic_curly_brackets_icon, binding.imageView)
            val booleanArray = BooleanArray(paths.size) { Random.nextBoolean() }

            // set random activity status
            for (i in 0 until paths.size){
                val color = if (booleanArray[i]) paths[i].colorActive else paths[i].colorInactive
                vector.findPathByName(paths[i].path).fillColor = getColor(color)
            }
        }
    }


}