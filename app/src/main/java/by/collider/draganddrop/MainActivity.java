package by.collider.draganddrop;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout cards = (LinearLayout) findViewById(R.id.cards);
        for (int i = 0; i < cards.getChildCount(); i++) {
            LinearLayout category = (LinearLayout) cards.getChildAt(i);
            for (int j = 0; j < category.getChildCount(); j++) {
                TextView card = (TextView) category.getChildAt(j);
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
        LinearLayout sentence = (LinearLayout) findViewById(R.id.sentence);
        sentence.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });
    }

    public void resetClick(View v) {
        finish();
        startActivity(getIntent());
    }
}
