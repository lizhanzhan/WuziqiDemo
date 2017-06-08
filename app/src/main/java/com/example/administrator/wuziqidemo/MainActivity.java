package com.example.administrator.wuziqidemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private Wuziqi_Panel mPanel;
    private AlertDialog.Builder mBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPanel = (Wuziqi_Panel) findViewById(R.id.main_panel);
        mBuilder  = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("游戏结束");
        mBuilder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });
        mBuilder.setPositiveButton("再来一局", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPanel.restartGame();
            }
        });
        mPanel.setOnGameListener(new Wuziqi_Panel.onGameListener() {
            @Override
            public void onGameOver(int i) {
                String str = "";
                if (i==Wuziqi_Panel.WHITE_WIN){
                    str = "白棋胜";
                }else if (i==Wuziqi_Panel.BLACK_WIN){
                    str = "黑棋胜";
                }
                mBuilder.setMessage(str);
                mBuilder.setCancelable(false);//不可用返回键取消
                 AlertDialog dialog = mBuilder.create();
              Window dialogWindow = dialog.getWindow();
                 WindowManager.LayoutParams params = new WindowManager.LayoutParams();
              params.x = 0;
              params.y = mPanel.getUnder();
                dialogWindow.setAttributes(params);//设置Dialog显示的位置
           dialog.setCanceledOnTouchOutside(false);//不可点击取消
                  dialog.show();
            }
        });
    }
}
