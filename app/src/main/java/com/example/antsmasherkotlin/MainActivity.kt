package com.example.antsmasherkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity: AppCompatActivity(), Contract.GameView {

    private val engine: GameEngine

    init {
        this.engine = GameEngine()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.engine.onGameViewReady(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.engine.onViewDestroyed()
    }

    override fun showAnt(ant: Ant) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideAnd(ant: Ant) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showScore(score: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setIntroTextVisibility(visibility: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setGameOverTextVisibility(visibility: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPlayButtonVisibility(visibility: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}