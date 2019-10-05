package com.example.messenger;

import android.widget.ImageView;

public interface IonClickContact {

    void onClickName(String name, int position);
    void onClickPhone(Contact contact);

}
