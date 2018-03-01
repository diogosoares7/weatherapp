package mangrove.soares.diogo.weatherapp.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import mangrove.soares.diogo.weatherapp.R;

/**
 * Created by diogo.soares on 24/02/2018.
 */

public class TextviewBoldStyle extends android.support.v7.widget.AppCompatTextView {

    public TextviewBoldStyle(Context context) {
        this(context, null);
    }

    public TextviewBoldStyle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextviewBoldStyle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "Comfortaa-Bold.ttf");
        this.setTypeface(face);
        int padding = (int) getResources().getDimension(R.dimen.generic_padding);
        int topAndBottom = (int) getResources().getDimension(R.dimen.top_bottom_padding);
        this.setPadding(padding,topAndBottom,padding,topAndBottom);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if(text.toString().length() > 0){
            String upperString = text.toString().substring(0,1).toUpperCase() + text.toString().substring(1);
            super.setText(upperString, type);
        }
        else {
            super.setText(text, type);
        }
    }

}