package com.example.antsmasherkotlin

interface Contract {

    interface GameView{
        fun showAnt(ant: Ant)
        fun hideAnt(ant: Ant)
        fun showScore(score: Int)
        fun setIntroTextVisibility(visibility: Boolean)
        fun setGameOverTextVisibility(visibility: Boolean)
        fun setPlayButtonVisibility(visibility: Boolean)
        fun clearView()
    }

    interface GameEngine {
        fun onGameViewReady(view: GameView)
        fun onViewDestroyed()
        fun onPlayButtonClicked()
        fun onAntClicked(ant: Ant)
    }
}