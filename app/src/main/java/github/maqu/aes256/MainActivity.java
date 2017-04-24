package github.maqu.aes256;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Project :  AES256.
 * Package name: github.maqu.aes256
 * Created by :  Benjamin.
 * Created time: 2017/4/23 17:38
 * Changed by :  Benjamin.
 * Changed time: 2017/4/23 17:38
 * Class description: MainActivity
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ciphertext, plaintext;
    private String key = "mynameisBenjamin";
    //这个key一定要用十六位的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.encryption).setOnClickListener(this);
        findViewById(R.id.Decrypt).setOnClickListener(this);
        ciphertext = (EditText) findViewById(R.id.ciphertext);
        plaintext = (EditText) findViewById(R.id.plaintext);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Decrypt:
                encryption();
                break;
            case R.id.encryption:
                Decrypt();
                break;
        }
    }

    /**
     * 加密
     */
    private void Decrypt() {
        Log.d(getClass().getSimpleName(), "加密");
        String plaintextvalue = plaintext.getText().toString().trim();
        if (plaintextvalue.length() > 0) {
            try {
                ciphertext.setText(AES256Cipher.AES_Encode(plaintextvalue, key));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, getClass().getSimpleName() + ":---明文为空", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 解密
     */
    private void encryption() {
        Log.d(getClass().getSimpleName(), "解密");
        String ciphertextvalue = ciphertext.getText().toString().trim();
        if (ciphertextvalue.length() > 0) {
            try {
                plaintext.setText(AES256Cipher.AES_Decode(ciphertextvalue, key));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, getClass().getSimpleName() + ":---密文为空", Toast.LENGTH_SHORT).show();
        }

    }
}
