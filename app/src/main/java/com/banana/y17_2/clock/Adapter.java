package com.banana.y17_2.clock;

import android.support.v7.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    MainActivity activity;

    public Adapter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.price_layout, parent, false );
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CurrencyConstructor curr = DataBase.currencyArr.get(position);
        //заполняем поля макета
        String date = "Date:  " + curr.date;
        holder.date.setText(date);
        String PriceString = "Price:  " + curr.price;
        holder.price.setText(PriceString);
        String CurrencyString = "Currency:  " + curr.currency;
        holder.curren.setText(CurrencyString);


        //при нажатии на макет откроется фрагмент с информацией о определенной валюте
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCoinFragment(curr);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataBase.currencyArr.size();

    }
    //метод, рисующий фрагмент с иформацией о валюте
    private void showCoinFragment(CurrencyConstructor curr) {
        ActionBarFragment fragment = ActionBarFragment.newInstance(curr);
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FrameLayout, fragment).commit();

    }

}
