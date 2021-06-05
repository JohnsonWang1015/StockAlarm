package com.example.stockalarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.example.stockalarm.utils.Stock;
import com.example.stockalarm.utils.StockUtils;

public class StockService extends Service {
    private String channelId = "test_notify_id";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String stockNo = intent.getExtras().getString("stockNo");
        new Thread(){
            @Override
            public void run() {
                StockUtils utils = new StockUtils();
                Stock stock = utils.getStock(stockNo);
                int nid = 1;
                Context context = getApplicationContext();

                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(context);
                builder.setSmallIcon(R.drawable.stock_ticker)
                    .setContentTitle(stock.get名稱() + "股價通知")
                    .setContentText("成交:"+stock.get成交()+" "+stock.get漲跌())
                    .setSubText("昨收:"+stock.get昨收())
                    .setAutoCancel(true);

                //Android 8 Oreo minSDK=26 以上使用 NotificationChannel
                NotificationChannel channel;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    channel = new NotificationChannel(channelId, "notify test", NotificationManager.IMPORTANCE_HIGH);
                    builder.setChannelId(channelId);
                    manager.createNotificationChannel(channel);

                }else{
                    builder.setVisibility(Notification.VISIBILITY_PUBLIC).setPriority(Notification.PRIORITY_HIGH);

                }
                Notification notification = builder.build();
                manager.notify(nid, notification);

            }
        }.start();
        stopSelf();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Log.i("mylog", "onDestroy()");
        super.onDestroy();
    }
}