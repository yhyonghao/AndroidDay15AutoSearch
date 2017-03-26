package com.qf.androidday15.autosearch;
import java.util.*;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
    private AutoCompleteTextView auto;
    private ArrayAdapter<String> adapter;
    private List<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auto=(AutoCompleteTextView)findViewById(R.id.autoView);
        list=getData();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        auto.setAdapter(adapter);
    }

    public void search(View view){
    	MySQLiteOpenHelper helper=new MySQLiteOpenHelper(this,"searchlog.db",null,1);
    	SQLiteDatabase db=helper.getReadableDatabase();
    	String input=auto.getText().toString();   // ��ȡ�û���������
    	if("".equals(input) || input==null){
    		AlertDialog dialog=new AlertDialog.Builder(this).setTitle("��ʾ")
    				.setMessage("�������ݲ���Ϊ�գ�").create();
    		dialog.show();
    	}else{
    		if(!list.contains(input)){   // ���֮ǰ�Ĳ�ѯ��¼�в�����Ŀǰ�Ĳ�ѯ����
        		db.execSQL("insert into log(content)values(?)",new String[]{input});
        		list=getData();
        		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        	//	adapter.notifyDataSetChanged();
        		auto.setAdapter(adapter);
        	}
    	}
    }
    
    public List<String> getData(){    // �����ݿ��в�ѯ����
    	MySQLiteOpenHelper helper=new MySQLiteOpenHelper(this,"searchlog.db",null,1);
    	SQLiteDatabase db=helper.getReadableDatabase();
    	Cursor cursor=db.query("log", null, null, null, null, null, null);
    	
    	list.clear();
    	while(cursor.moveToNext()){
    		String content=cursor.getString(cursor.getColumnIndex("content"));
    		list.add(content);
    	}
    	for(String str:list){
    		Log.i("===========>",str);
    	}
    	
    	return list;
    }
    
}
