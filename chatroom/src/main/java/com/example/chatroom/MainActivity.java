package com.example.chatroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Message> messageList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView messageRecyclerView;
    private MessageAdapter messageAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText=(EditText)findViewById(R.id.input_text);
        send=(Button)findViewById(R.id.send);
        messageRecyclerView=(RecyclerView)findViewById(R.id.msg_chat);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        messageRecyclerView.setLayoutManager(layoutManager);
        messageAdapter=new MessageAdapter(messageList);
        messageRecyclerView.setAdapter(messageAdapter);
        initMessage();
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                String content=inputText.getText().toString();
                if(!"".equals(content))
                {
                    Message message=new Message(content, Message.TYPE_SEND);
                    messageList.add(message);
                    //将收到的信息减去,刷新显示
                    messageAdapter.notifyItemInserted(messageList.size()-1);
                    //将RecyclerView定位到最后一行
                    messageRecyclerView.scrollToPosition(messageList.size()-1);
                    //清空输入内容
                    inputText.setText("");
                }

            }
        });



    }


    private void initMessage()
    {
        Message message1=new Message("早上好", Message.TYPE_RECEIVED);
        messageList.add(message1);
        Message message2=new Message("你也好啊", Message.TYPE_SEND);
        messageList.add(message2);
        Message message3=new Message("我喜欢你", Message.TYPE_RECEIVED);
        messageList.add(message3);

    }





}
