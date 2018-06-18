package com.example.antsmasherkotlin

import android.os.Handler
import java.util.*

class GameEngine : Contract.GameEngine {

    private var gameView: Contract.GameView? = null

    private val random: Random = Random()
    private val handler: Handler = Handler()
    private var score = 0

    private var ants: MutableList<Ant> = ArrayList()
    private val showNewAntRunnable = object : Runnable {
        override fun run() {
            if (ants.isNotEmpty()) {
                handler.removeCallbacks(this)
                gameView?.clearView()
                gameView?.setGameOverTextVisibility(true)
                gameView?.setPlayButtonVisibility(true)
            } else {
                val ant = Ant((ants.size + 1).toLong(), random.nextFloat(), random.nextFloat())
                ants.add(ant)
                gameView?.showAnt(ant)
                handler.postDelayed(this, 1500)
            }
        }
    }

    override fun onGameViewReady(view: Contract.GameView) {
        gameView = view
    }

    override fun onViewDestroyed() {
        gameView = null
    }

    override fun onPlayButtonClicked() {
        gameView?.setPlayButtonVisibility(false)
        gameView?.clearView()
        ants.clear()
        showNewAntRunnable.run()
    }

    override fun onAntClicked(ant: Ant) {
        ants.remove(ant)
        gameView?.hideAnt(ant)
        score++
    }
}