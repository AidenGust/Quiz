package space.kvest.quiz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level14 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; //переменная для левой картинки + текст
    public int numRight; // переменная для правой кртинки + текст
    Array array = new Array(); // создали новый объект из класса Array
    Random random = new Random(); //для генерации случайных чисел
    public int count = 0;//счётчик правельных ответов

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        //Создаём переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level14); //Установили текст


        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        //код который скругляет углы левой картиник
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        //код который скругляет углы правой картинки
        img_right.setClipToOutline(true);

        //Путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);
        //Путь к правой TextView
        final TextView text_right = findViewById(R.id.text_right);


        //Развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Развернуть игру на весь экран - конец

        //Устанавливаем фон - начало
        ImageView background = (ImageView)findViewById(R.id.background);
        background.setImageResource(R.drawable.level6);
        //Устанавливаем фон - конец

        //Вызов диалогового окна в начале игры
        dialog = new Dialog(this); //создаём новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); //путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialog.setCancelable(false);// Нельзя закрыть кнопкой "назад"

        //Устанавливаем картинку в диалоговое окно - начало
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimg14);
        //Устанавливаем картинку в диалоговое окно - конец

        //Устанавливаем фон диалогового окна - начало
        LinearLayout dialogfon = (LinearLayout)dialog.findViewById(R.id.dialogfon);
        dialogfon.setBackgroundResource(R.drawable.previewbackground6);
        //Устанавливаем фон диалогового окна - конец

        //Устанавливаем описание задания - начало
        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.levelfourteen);
        //Устанавливаем описание задания - конец


        //кнопка которая закрывает диалоговое окно - начало
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки - начало
                try {
                    //Вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level14.this, GameLevels.class); //создали намерение для перехода
                    startActivity(intent); // Старт намерения
                    finish();// закрыть этот класс
                    //Вернуться назад к выбору уровня - конец
                }catch (Exception e){
                    //Здесь кода не будет
                }
                dialog.dismiss(); //закрываем диалоговое окно
                //обрабатываем нажатие кнопки - конец
            }
        });
        //кнопка которая закрывает диалоговое окно - конец

        //Кнопка "Продолжить" - начало
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();//Закрываем диалоговое окно
            }
        });
        //Кнопка "Продолжить" - конец

        dialog.show();//показать диалоговое окно

        //____________________________________________________________________________________________________________________________________________________
        //Вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this); //создаём новое диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialogEnd.setContentView(R.layout.dialogend); //путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);// Нельзя закрыть кнопкой "назад"

        //Устанавливаем фон диалогового окна - начало
        LinearLayout dialogfonEnd = (LinearLayout)dialogEnd.findViewById(R.id.dialogfon);
        dialogfonEnd.setBackgroundResource(R.drawable.previewbackground6);
        //Устанавливаем фон диалогового окна - конец

        //Интересный факт - начало
        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionEnd.setText(R.string.levelfourteenEnd);
        //Интересный факт - конец

        //кнопка которая закрывает диалоговое окно - начало
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки - начало
                try {
                    //Вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level14.this, GameLevels.class); //создали намерение для перехода
                    startActivity(intent); // Старт намерения
                    finish();// закрыть этот класс
                    //Вернуться назад к выбору уровня - конец
                }catch (Exception e){
                    //Здесь кода не будет
                }
                dialogEnd.dismiss(); //закрываем диалоговое окно
                //обрабатываем нажатие кнопки - конец
            }
        });
        //кнопка которая закрывает диалоговое окно - конец

        //Кнопка "Продолжить" - начало
        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level14.this, Level15.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    //Здесь кода не будет
                }

                dialogEnd.dismiss();//Закрываем диалоговое окно
            }
        });
        //Кнопка "Продолжить" - конец


        //______________________________________________________________________________________________________________________________________________________

        //Масств для прогресса игры - начало
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };
        //Масств для прогресса игры - конец


        //Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level14.this, R.anim.alpha);
        //Подключаем анимацию - конец

        numLeft = random.nextInt(16); // генерируем случайное число
        img_left.setImageResource(array.images14[numLeft]); //достаём из массива картинку
        text_left.setText(array.texts14[numLeft]); //достаём из массива текст

        numRight = random.nextInt(16); // генерируем случайное число

        //Цикл с предусловием,проверяющий равенство чисел - начало
        while (array.strong8[numLeft]==array.strong8[numRight]){
            numRight = random.nextInt(16);
        }
        //Цикл с предусловием,проверяющий равенство чисел - конец

        img_right.setImageResource(array.images14[numRight]);//достаём из массива картинку
        text_right.setText(array.texts14[numRight]);//Достаём из массива текст

        //Обрабатываем нажатие на левую картинку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //Если коснулся картинки - начало
                    img_right.setEnabled(false);//Блокируем правую картинку
                    if (array.strong8[numLeft]>array.strong8[numRight]){
                        img_left.setImageResource(R.drawable.img_true);
                    }else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if (array.strong8[numLeft]>array.strong8[numRight]){
                        //Если левая картинка больше
                        if (count<20){
                            count= count+1;
                        }

                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правельные ответы и закрашиваем зелёным - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правельные ответы и закрашиваем зелёным - конец
                    }else {
                        //Если левая картнка меньше
                        if (count>0){
                            if (count==1){
                                count=0;
                            }else {
                                count=count-2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правельные ответы и закрашиваем зелёным - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правельные ответы и закрашиваем зелёным - конец
                    }
                    //Если отпустил палец - конец
                    if (count==20){
                        //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level>14){
                            //пусто
                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",15);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else {
                        numLeft = random.nextInt(16); // генерируем случайное число
                        img_left.setImageResource(array.images14[numLeft]); //достаём из массива картинку
                        text_left.setText(array.texts14[numLeft]); //достаём из массива текст

                        numRight = random.nextInt(16); // генерируем случайное число

                        //Цикл с предусловием,проверяющий равенство чисел - начало
                        while (array.strong8[numLeft]==array.strong8[numRight]){
                            numRight = random.nextInt(16);
                        }
                        //Цикл с предусловием,проверяющий равенство чисел - конец

                        img_right.setImageResource(array.images14[numRight]);//достаём из массива картинку
                        text_right.setText(array.texts14[numRight]);//Достаём из массива текст

                        img_right.setEnabled(true);//включаем обратно правую картинку
                    }
                }
                //Условие касания картинки - конец

                return true;
            }
        });
        //Обрабатываем нажатие на левую картинку - конец

        //Обрабатываем нажатие на правую картинку - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //Если коснулся картинки - начало
                    img_left.setEnabled(false);//Блокируем левую картинку
                    if (array.strong8[numLeft]<array.strong8[numRight]){
                        img_right.setImageResource(R.drawable.img_true);
                    }else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if (array.strong8[numLeft]<array.strong8[numRight]){
                        //Если правая картинка больше
                        if (count<20){
                            count= count+1;
                        }

                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правельные ответы и закрашиваем зелёным - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правельные ответы и закрашиваем зелёным - конец
                    }else {
                        //Если правая картнка меньше
                        if (count>0){
                            if (count==1){
                                count=0;
                            }else {
                                count=count-2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правельные ответы и закрашиваем зелёным - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правельные ответы и закрашиваем зелёным - конец
                    }
                    //Если отпустил палец - конец
                    if (count==20){
                        //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level>14){
                            //пусто
                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",15);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else {
                        numLeft = random.nextInt(16); // генерируем случайное число
                        img_left.setImageResource(array.images14[numLeft]); //достаём из массива картинку
                        text_left.setText(array.texts14[numLeft]); //достаём из массива текст

                        numRight = random.nextInt(16); // генерируем случайное число

                        //Цикл с предусловием,проверяющий равенство чисел - начало
                        while (array.strong8[numLeft]==array.strong8[numRight]){
                            numRight = random.nextInt(16);
                        }
                        //Цикл с предусловием,проверяющий равенство чисел - конец

                        img_right.setImageResource(array.images14[numRight]);//достаём из массива картинку
                        text_right.setText(array.texts14[numRight]);//Достаём из массива текст

                        img_left.setEnabled(true);//включаем обратно левую картинку
                    }
                }
                //Условие касания картинки - конец

                return true;
            }
        });
        //Обрабатываем нажатие на правую картинку - конец


    }
    //Системная кнопка "Назад" - начало
    //Системная кнопка "Назад" - конец

}