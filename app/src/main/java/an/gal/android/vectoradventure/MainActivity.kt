package an.gal.android.vectoradventure

import an.gal.android.vectoradventure.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import com.devs.vectorchildfinder.VectorChildFinder
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    //private lateinit var vector: VectorChildFinder

    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f;

    private val paths = arrayListOf<VectorElement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

/*        fillPathsArray()

        vector = VectorChildFinder(this, R.drawable.ic_curly_brackets_icon, binding.imageView)*/

        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener());
        setButtonsBehavior()
    }

    private fun setButtonsBehavior() {
        binding.btnSimpleExample.setOnClickListener {
            val vector = VectorChildFinder(this, R.drawable.ic_metro, binding.imageView)
            setLayerActivity(false, vector)

            setColorToRoute("m1", 3, 12, vector)

        }

        binding.btnLongExample.setOnClickListener {
            val vector = VectorChildFinder(this, R.drawable.ic_metro, binding.imageView)

            setLayerActivity(false, vector)
            setColorToRoute("m2", 1, 9, vector)
            setColorToRoute("m3", 5, 12, vector)
        }

        binding.btnVeryLongExample.setOnClickListener {
            val vector = VectorChildFinder(this, R.drawable.ic_metro, binding.imageView)

            setLayerActivity(false, vector)
            setColorToRoute("m3", 1, 6, vector)
            setColorToRoute("m1", 10, 12, vector)
            setColorToRoute("m5", 8, 15, vector)
        }

        binding.btnClear.setOnClickListener {
            val vector = VectorChildFinder(this, R.drawable.ic_metro, binding.imageView)
            setLayerActivity(true, vector)
        }
    }

    private fun setColorToRoute(line: String, from: Int, to: Int, vector: VectorChildFinder) {
        //val vector = VectorChildFinder(this, R.drawable.ic_metro, binding.imageView)
        val color: Int = getLineColor(line)

        /*vector.findPathByName("m1_3_4").fillColor = getColor(R.color.color_red_active)
        vector.findPathByName("m1_4_5").fillColor = getColor(R.color.color_red_active)
        vector.findPathByName("m1_5_6").fillColor = getColor(R.color.color_red_active)*/
        for (i in from until to) {
            Log.d("DrawVector", "For \"${line}_${i}_${i + 1}\" fill color")
            vector.findPathByName("${line}_${i}_${i + 1}").fillColor = color
        }
    }

    private fun getLineColor(line: String): Int {
        return getColor(
            when (line) {
                "m1" -> R.color.color_red_active
                "m2" -> R.color.color_blue_active
                "m3" -> R.color.color_green_active
                "m4" -> R.color.color_orange_active
                "m5" -> R.color.color_violet_active
                else -> getColor(R.color.color_black_inactive)
            }
        )
    }

    private fun setLayerActivity(active: Boolean = true, vector: VectorChildFinder ) {
        //val vector: VectorChildFinder = VectorChildFinder(this, R.drawable.ic_metro, binding.imageView)

        var colorOff =
            if (active) getColor(R.color.color_red_active) else getColor(R.color.color_red_inactive)
        //m1 - 19 - red
        for (i in 1..18) {
            vector.findPathByName("m1_${i}_${i + 1}").fillColor = colorOff
        }
        //m2 - 18 - blue
        colorOff =
            if (active) getColor(R.color.color_blue_active) else getColor(R.color.color_blue_inactive)
        for (i in 1..17) {
            vector.findPathByName("m2_${i}_${i + 1}").fillColor = colorOff
        }
        //m3 - 12 - green
        colorOff =
            if (active) getColor(R.color.color_green_active) else getColor(R.color.color_green_inactive)
        for (i in 1..11) {
            vector.findPathByName("m3_${i}_${i + 1}").fillColor = colorOff
        }
        //m4 - 8 - orange
        colorOff =
            if (active) getColor(R.color.color_orange_active) else getColor(R.color.color_orange_inactive)
        for (i in 1..7) {
            vector.findPathByName("m4_${i}_${i + 1}").fillColor = colorOff
        }
        //m5 - 15 - violet
        colorOff =
            if (active) getColor(R.color.color_violet_active) else getColor(R.color.color_violet_inactive)
        for (i in 1..14) {
            vector.findPathByName("m5_${i}_${i + 1}").fillColor = colorOff
        }

        //setColorToRoute("m1", 3, 9, vector)
       /* vector.findPathByName("m1_3_4").fillColor = getColor(R.color.color_red_active)
        vector.findPathByName("m1_4_5").fillColor = getColor(R.color.color_red_active)
        vector.findPathByName("m1_5_6").fillColor = getColor(R.color.color_red_active)*/
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.OnScaleGestureListener {

        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            scaleFactor = (detector?.scaleFactor ?: 1.0f)
            scaleFactor = max(0.1f, min(scaleFactor, 10.0f));
            binding.imageView.scaleX = scaleFactor
            binding.imageView.scaleY = scaleFactor
            return true;
        }

        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {

        }
    }
}



/*
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }
*/


/*
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {
   private ScaleGestureDetector scaleGestureDetector;
   private float mScaleFactor = 1.0f;
   private ImageView imageView;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      imageView=findViewById(R.id.imageView);
      scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
   }
   @Override
   public boolean onTouchEvent(MotionEvent motionEvent) {
      scaleGestureDetector.onTouchEvent(motionEvent);
      return true;
   }
   private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
      @Override
      public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
         mScaleFactor *= scaleGestureDetector.getScaleFactor();
         mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
         imageView.setScaleX(mScaleFactor);
         imageView.setScaleY(mScaleFactor);
         return true;
      }
   }
}
* */