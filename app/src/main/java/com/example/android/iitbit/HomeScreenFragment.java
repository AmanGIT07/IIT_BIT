package com.example.android.iitbit;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class HomeScreenFragment extends Fragment{

    TextView tv_receivingAddress,tv_btcAvailablebalance,tv_inrAvailableBalance;
    String receivingAddress = "To Be Fetched";
    Intent intent;
    ImageView iv_qrCode;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen_fragment,container,false);
        tv_receivingAddress = (TextView)view.findViewById(R.id.tv_receivingAddress);
       // receivingAddress = tv_receivingAddress.getText().toString().trim();

        iv_qrCode = (ImageView)view.findViewById(R.id.iv_qrCode);

        com.google.zxing.MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(receivingAddress, BarcodeFormat.QR_CODE,220,220);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            iv_qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

//        intent = new Intent(getActivity(),Register.class);
//        startActivity();
        if(!checkConnected())
        {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error!")
                    .setMessage("Device not connected to network..")
                    .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Fragment newFragment= new HomeScreenFragment();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.frame,newFragment).commit();

                        }
                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().moveTaskToBack(true);
                            getActivity().finish();
                            Intent intent = new  Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            System.exit(0);
                        }
                    })
                    .setCancelable(false)
                    .show();
        }

        return view;
    }

    public boolean checkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }
}
