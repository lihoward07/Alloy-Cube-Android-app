package com.alloycube.app;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_SPEECH = 99;
    public static final int RESULT_SPEECH_SCHOOL = 199;
    public static final int RESULT_SPEECH_COMPANY = 299;
    public static final int RESULT_SPEECH_EMAIL = 399;
    public static final int RESULT_SPEECH_DES = 499;

    EditText nameEditText;
    EditText companyEditText;
    EditText schoolEditText;
    EditText emailEditText;
    @BindView(R.id.descriptionEditText)
    EditText desEditText;

    int ag = 0;
    int ins = 0;
    int head = 0;
    int speaker = 0;
    int sunglass = 0;

    @BindView(R.id.audioGlassNumText)
    TextView agNumText;
    @BindView(R.id.insNumText)
    TextView insText;
    @BindView(R.id.headphoneNumText)
    TextView headText;
    @BindView(R.id.speakersNumText)
    TextView speakerText;
    @BindView(R.id.csunNumText)
    TextView sunglassText;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-52-53-152-26.us-west-1.compute.amazonaws.com:5000/")
            .build();

    private ProductService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = retrofit.create(ProductService.class);

        ButterKnife.bind(this);

        nameEditText = findViewById(R.id.nameEditText);
        companyEditText = findViewById(R.id.companyEditText);
        schoolEditText = findViewById(R.id.schoolEditText);
        emailEditText = findViewById(R.id.emailEditText);

        Button nameButton = findViewById(R.id.nameButton);
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please tell me your name:");
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn’t support Speech to Text",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button companyButton = findViewById(R.id.companyButton);
        companyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please tell me your company:");
                try {
                    startActivityForResult(intent, RESULT_SPEECH_COMPANY);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn’t support Speech to Text",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button schoolButton = findViewById(R.id.schoolButton);
        schoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please tell me your school:");
                try {
                    startActivityForResult(intent, RESULT_SPEECH_SCHOOL);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn’t support Speech to Text",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button emailButton = findViewById(R.id.emailButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please tell me your email:");
                try {
                    startActivityForResult(intent, RESULT_SPEECH_EMAIL);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn’t support Speech to Text",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button descriptionButton = findViewById(R.id.descriptionButton);
        descriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please tell me the description:");
                try {
                    startActivityForResult(intent, RESULT_SPEECH_DES);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn’t support Speech to Text",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    nameEditText.setText(text.get(0));
                }
                break;
            }
            case RESULT_SPEECH_SCHOOL: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    schoolEditText.setText(text.get(0));
                }
                break;
            }
            case RESULT_SPEECH_COMPANY: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    companyEditText.setText(text.get(0));
                }
                break;
            }
            case RESULT_SPEECH_EMAIL: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String email = text.get(0);
                    email = email.replace(" at ", "@");
                    email = email.replace(" ", "");
                    emailEditText.setText(email);
                }
                break;
            }
            case RESULT_SPEECH_DES: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String description = text.get(0);
                    desEditText.setText(description);
                }
                break;
            }
        }
    }

    @OnClick(R.id.addAudioGlassesButton)
    public void addAudioGlass() {
        ag++;
        agNumText.setText(ag + "");
    }

    @OnClick(R.id.addInsButton)
    public void addIns() {
        ins++;
        insText.setText(ins + "");
    }

    @OnClick(R.id.addHeadphonesButton)
    public void addheadphone() {
        head++;
        headText.setText(head + "");
    }

    @OnClick(R.id.addSpeakersButton)
    public void addSpeakers() {
        speaker++;
        speakerText.setText(speaker + "");
    }

    @OnClick(R.id.addCSunButton)
    public void addCSun() {
        sunglass++;
        sunglassText.setText(sunglass + "");
    }

    @OnClick(R.id.reduceAudioGlassesButton)
    public void reduceAudioGlass() {
        if (ag > 0) {
            ag--;
            agNumText.setText(ag + "");
        }
    }

    @OnClick(R.id.reduceInsButton)
    public void reduceIns() {
        if (ins > 0) {
            ins--;
            insText.setText(ins + "");
        }
    }

    @OnClick(R.id.reduceHeadphonesButton)
    public void reduceheadphone() {
        if (head > 0) {
            head--;
            headText.setText(head + "");
        }
    }

    @OnClick(R.id.reduceSpeakersButton)
    public void reduceSpeakers() {
        if (speaker > 0) {
            speaker--;
            speakerText.setText(speaker + "");
        }
    }

    @OnClick(R.id.reduceCSunButton)
    public void reduceCSun() {
        if (sunglass > 0) {
            sunglass--;
            sunglassText.setText(sunglass + "");
        }
    }

    @OnClick(R.id.orderButton)
    public void orderButton() {
        String productStr = "";
        if (ag > 0) {
            productStr += ag + ",Audio Glasses;";
        }
        if (ins > 0) {
            productStr += ins + ",Insurance;";
        }
        if (head > 0) {
            productStr += head + ",Headphones;";
        }
        if (speaker > 0) {
            productStr += speaker + ",Speakers;";
        }
        if (sunglass > 0) {
            productStr += sunglass + ",Sunglasses";
            if (!desEditText.getText().toString().isEmpty()) {
                productStr += "(" + desEditText.getText().toString() + ")";
            }
            productStr += ";";
        }
        if (productStr.endsWith(";")) {
            productStr = productStr.substring(0, productStr.length() - 1);
        }

        Call<ResponseBody> call = service.postOrder(nameEditText.getText().toString(),
                companyEditText.getText().toString(), schoolEditText.getText().toString(), emailEditText.getText().toString(), productStr
        );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result = null;
                try {
                    result = response.body().string();
                    Log.i("test", result);
                    Intent resIntent = new Intent(MainActivity.this, ResultActivity.class);
                    resIntent.putExtra("res", result);
                    startActivity(resIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("test", e.toString());
                    Toast.makeText(MainActivity.this, "Failed to create the order. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to create and send the sales order.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
