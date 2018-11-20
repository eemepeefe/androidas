package dadm.scaffold;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dadm.scaffold.counter.ConfigFragment;
import dadm.scaffold.counter.GameFragment;
import dadm.scaffold.counter.MainMenuFragment;
import dadm.scaffold.engine.GameEngine;

public class ScaffoldActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "content";

    private BaseFragment actualFragment;
    private GameEngine theGameEngine;

    private int score;
    private boolean pausa;
    private int ship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaffold);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainMenuFragment(), TAG_FRAGMENT)
                    .commit();
        }
        ship = R.drawable.ufo_hd2;
    }

    public void startGame() {
        // Navigate the the game fragment, which makes the start automatically
        if(actualFragment != null)
            getSupportFragmentManager().beginTransaction().remove(actualFragment).commit();
        Bundle bundle = new Bundle();
        bundle.putInt("ship", ship);
        actualFragment = new GameFragment();
        actualFragment.setArguments(bundle);
        navigateToFragment(actualFragment);
        System.out.println(actualFragment);
    }

    public void startConfig() {
        if(actualFragment != null)
            getSupportFragmentManager().beginTransaction().remove(actualFragment).commit();
        actualFragment = new ConfigFragment();
        navigateToFragment(actualFragment);
        System.out.println(actualFragment);
    }

    public void backToMenu() {
        if(actualFragment != null)
            getSupportFragmentManager().beginTransaction().remove(actualFragment).commit();
        actualFragment = new MainMenuFragment();
        navigateToFragment(actualFragment);
        System.out.println(actualFragment);
    }

    public void setShip(int ship){
        this.ship = ship;
    }

    public int getShip(){
        return ship;
    }

    public void endGame(GameEngine engine) {
        stopGame(engine);
        if(actualFragment != null)
            getSupportFragmentManager().beginTransaction().remove(actualFragment).commit();
        Bundle bundle = new Bundle();
        bundle.putInt("score", score);
        actualFragment = new EndGameFragment();
        actualFragment.setArguments(bundle);
        navigateToFragment(actualFragment);
        System.out.println(actualFragment);
    }

    public void updateLivesandScore(int[] lives){
        ((GameFragment)actualFragment).setScoreAndLives(lives);
    }

    public void sendScore(int score){
        this.score = score;
    }

    private void navigateToFragment(BaseFragment dst) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, dst, TAG_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        final BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (fragment == null || !fragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void navigateBack() {
        // Do a push on the navigation history
        super.onBackPressed();
    }

    public void stopGame(GameEngine engine){
        engine.stopGame();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE);
            }
            else {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }
}
