package by.collider.draganddrop;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageFragment extends Fragment {
    private static Map<Integer, List> cardTitles;

    static {
        cardTitles = new HashMap<>();
        cardTitles.put(CardsPagerAdapter.Categories.PRONOUNS.ordinal(), Arrays.asList("Я", "Мы", "Он"));
        cardTitles.put(CardsPagerAdapter.Categories.MODALS.ordinal(), Arrays.asList("хочу", "могу", "должен"));
        cardTitles.put(CardsPagerAdapter.Categories.ACTIONS.ordinal(), Arrays.asList("спать", "есть", "идти"));
        cardTitles.put(CardsPagerAdapter.Categories.OBJECTS.ordinal(), Arrays.asList("яблоко", "чай", "вода"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout resource that'll be returned
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.page, container, false);
        List<String> values = cardTitles.get(getArguments().getInt("page_position"));
        for (String value : values) {
            TextView cardView = (TextView) inflater.inflate(R.layout.card, container, false);
            cardView.setText(value);
            cardView.setOnTouchListener(new View.OnTouchListener() {
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
            rootView.addView(cardView);
        }
        rootView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENDED:
                        ((TextView) event.getLocalState()).setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });
        return rootView;
    }
}
