package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @BindView(R.id.origin_tv)
    TextView originTextView;
    @BindView(R.id.ingredients_tv)
    TextView ingredientsTetView;
    @BindView(R.id.description_tv)
    TextView descriptionTextView;
    @BindView(R.id.also_known_tv)
    TextView alsoKnownTextView;
    @BindView(R.id.image_iv)
    ImageView sandwichImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(sandwichImageView);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        originTextView.setText(validateText(sandwich.getPlaceOfOrigin()));
        alsoKnownTextView.setText(validateText(convertListToString(sandwich.getAlsoKnownAs())));
        ingredientsTetView.setText(validateText(convertListToString(sandwich.getIngredients())));
        descriptionTextView.setText(validateText(sandwich.getDescription()));
    }

    private String validateText(String text) {
        return TextUtils.isEmpty(text) ? getString(R.string.detail_no_data) : text;
    }

    private String convertListToString(List<String> stringList) {
        StringBuilder sb = new StringBuilder();
        for (String item : stringList) {
            sb.append(item);
            sb.append("\n");
        }
        return sb.toString();
    }
}
