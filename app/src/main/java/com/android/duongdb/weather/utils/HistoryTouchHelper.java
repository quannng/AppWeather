package com.android.duongdb.weather.utils;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.android.duongdb.weather.adapters.HistoryRecyclerAdapter;

public class HistoryTouchHelper extends ItemTouchHelper.SimpleCallback {
   private HistoryRecyclerAdapter historyRecyclerAdapter;

   public HistoryTouchHelper(HistoryRecyclerAdapter historyRecyclerAdapter){
      super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT); 
      this.historyRecyclerAdapter = historyRecyclerAdapter;
   }  
 
   @Override 
   public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
      //TODO: Not implemented here
      return false;  
   }

   @Override
   public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
      //Remove item
      historyRecyclerAdapter.remove(viewHolder.getAdapterPosition());
   }
}