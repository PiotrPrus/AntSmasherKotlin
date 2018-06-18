package com.example.antsmasherkotlin

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Contract.GameView {

    private val engine: GameEngine

    init {
        this.engine = GameEngine()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        play_fab?.setOnClickListener { engine.onPlayButtonClicked() }
        this.engine.onGameViewReady(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.engine.onViewDestroyed()
    }

    override fun showAnt(ant: Ant) {
        val antView = ImageView(this)
        antView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_ant))
        antView.scaleType = ImageView.ScaleType.FIT_CENTER
        antView.tag = ant
        antView.requestLayout()
        antView.setOnClickListener {view: View? ->
            view?.let { engine.onAntClicked(view.tag as Ant) }
        }
        val antSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56f, resources.displayMetrics)
        val layoutParams = FrameLayout.LayoutParams(antSize.toInt(), antSize.toInt())
        val screenWidth = game_layout!!.width
        val screenHeight = game_layout!!.height
        layoutParams.leftMargin = (ant.x * screenWidth).toInt()
        layoutParams.topMargin = (ant.y * screenHeight).toInt()
        game_layout?.addView(antView)
    }

    override fun hideAnt(antToHide: Ant) {
        game_layout?.let {
            for (i: Int in 0..game_layout!!.childCount) {
                val view = game_layout!!.getChildAt(i)
                val ant = view.tag
                if (ant == antToHide) {
                    game_layout!!.removeView(view)
                    break
                }
            }
        }
    }

    override fun showScore(score: Int) {
        score_tv?.text = "Score: $score"
    }

    override fun setIntroTextVisibility(visibility: Boolean) {
        intro_text_tv?.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun setGameOverTextVisibility(visibility: Boolean) {
        outro_text_tv?.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun setPlayButtonVisibility(visibility: Boolean) {
        play_fab?.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun clearView() {
        game_layout?.removeAllViews()
        intro_text_tv.visibility = View.GONE
        outro_text_tv.visibility = View.GONE
    }
}