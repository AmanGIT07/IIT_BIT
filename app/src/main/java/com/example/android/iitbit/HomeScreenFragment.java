package com.example.android.iitbit;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeScreenFragment extends Fragment{
        TextView receivingAddress,btcAvailablebalance,inrAvailableBalance;
    Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen_fragment,container,false);
        receivingAddress = (TextView)view.findViewById(R.id.tv_receivingAddress);
//        intent = new Intent(getActivity(),Register.class);
//        startActivity();
        return view;
    }
}
