package com.example.covid_19tracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.covid_19tracker.api.CountryModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context context;
    private ArrayList<CountryModel> countryModelList;

    public CountryAdapter(Context context, ArrayList<CountryModel> countryModelList) {
        this.context = context;
        this.countryModelList = countryModelList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country_list , parent , false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        CountryModel countryModel = countryModelList.get(position);

        holder.recyclerTextViewSerialNumber.setText(Integer.toString(position+1));

        Map<String , String> countryFlag = countryModel.getCountryInfo();
        Glide.with(context).load(countryFlag.get("flag")).into(holder.recyclerImageViewCountryFlag);

        holder.recyclerTextViewCountryName.setText(countryModel.getCountry());

        holder.recyclerTextViewCountryCases.setText(NumberFormat.getInstance().format(Integer.parseInt(countryModel.getCases())));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context , MainActivity.class);
            intent.putExtra("COUNTRY_NAME" , countryModel.getCountry());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return countryModelList.size();
    }

    public void searchCountry(ArrayList<CountryModel> countryModelArrayList) {
        countryModelList = countryModelArrayList;
        notifyDataSetChanged();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView recyclerTextViewSerialNumber , recyclerTextViewCountryName , recyclerTextViewCountryCases;
        private ImageView recyclerImageViewCountryFlag;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerTextViewSerialNumber = itemView.findViewById(R.id.recycler_item_serial_number);
            recyclerImageViewCountryFlag = itemView.findViewById(R.id.recycler_item_country_flag);
            recyclerTextViewCountryName = itemView.findViewById(R.id.recycler_item_country_name);
            recyclerTextViewCountryCases = itemView.findViewById(R.id.recycler_item_country_cases);
        }
    }
}
