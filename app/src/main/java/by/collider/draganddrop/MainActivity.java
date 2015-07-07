package by.collider.draganddrop;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View card = findViewById(R.id.card);
        card.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData clipData = ClipData.newPlainText("label", "text");
                    View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(clipData, dragShadowBuilder, v, 0);
                    v.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
