package Battleship_1_0;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class GameFlow {
    public enum GameState {
        PLACEMENT,
        PLAYING,
        GAME_OVER,
        PLAYER_TURN,
        AI_TURN,
    }

    public enum AIState {
        NORMAL,
        SEARCH,
        SEEK_AND_DESTROY
    }
    private final PropertyChangeSupport support;

    private GameState currentState;
    private AIState AI;

    public GameFlow() {
        currentState = GameState.PLACEMENT;
        support = new PropertyChangeSupport(this);
    }

    public void setGameState(GameState newState) {
        GameState oldState = this.currentState;
        this.currentState = newState;
        support.firePropertyChange("GameState", oldState, newState);
    }
    public void setAIState(){

    }






    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }


}

