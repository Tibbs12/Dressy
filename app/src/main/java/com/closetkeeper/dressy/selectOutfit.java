package com.closetkeeper.dressy;



import static com.closetkeeper.dressy.home.Items;
import static com.closetkeeper.dressy.home.Outfits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.closetkeeper.dressy.databinding.ActivitySelectClosetBinding;
import com.closetkeeper.dressy.databinding.ActivitySelectOutfitBinding;
import com.closetkeeper.dressy.dto.Item;
import com.closetkeeper.dressy.dto.Outfit;

import java.util.ArrayList;
import java.util.List;

public class selectOutfit extends AppCompatActivity {

    ActivitySelectOutfitBinding binding;
    private ImageButton outfitBackBtn;           /**Instantiating the ImageButtons*/
    private ImageButton addItemsBtn;
    private TextView outfitNameInput;
    private LinearLayout ItemsScroll;
    private AppCompatButton outfitFwdBtn;
    //private ListView adapterView;

    public static List<ImageView> viewList = new ArrayList<ImageView>();

    /** Used for permissions to access camera*/
    public static final int RequestPermissionCode = 1;

    /** Need static variables to pass data using intent */
    public final static String OUTFITNAME = "OUTFITNAME";

    private String outfitName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectOutfitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Navigation menu code
        binding.bottomNavBar.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.homeNavBtn:
                    Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
                    startActivity(intent);
                    break;
                case R.id.closetNavBtn:
                    Intent closet = new Intent(this, com.closetkeeper.dressy.my_closets.class);
                    startActivity(closet);
                    break;
                case R.id.addNavBtn:
                    Intent items = new Intent(this, com.closetkeeper.dressy.my_items.class);
                    startActivity(items);
                    break;
                case R.id.searchNavBar:
                    break;
                case R.id.outfitsNavBtn:
                    Intent outfit = new Intent(this, com.closetkeeper.dressy.my_outfits.class);
                    startActivity(outfit);
                    break;
            }


            return true;
        });

        ItemsScroll = (LinearLayout) findViewById(R.id.ItemsScroll);



       /* adapterView = (ListView) findViewById(R.id.adapterView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_select_outfit,Items);
        adapterView.setAdapter(adapter); */

        /** Sets up textview on selectCloset page, sets passed data into the textview */
        outfitNameInput = (TextView) findViewById(R.id.outfitNameInput);
        Intent intent = getIntent();
        outfitName = intent.getStringExtra(OUTFITNAME);
        outfitNameInput.setText(outfitName);


        /** setting variables equal to the xml button and creating OnClick listeners */



        outfitFwdBtn = (AppCompatButton) findViewById(R.id.outfitFwdBtn);
        outfitFwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Outfit MyOutfit = new Outfit(); //Create a new outfit on click
                MyOutfit.setName(outfitName);

                for (ImageView view : viewList)
                {
                    if (view.isSelected()){
                        MyOutfit.addItem(Items.get(view.getId()));  //for every item selected, add item to new outfit
                    }
                }
                Outfits.add(MyOutfit); //after loop, add new outfit to list of outfits
                createOutfit(); //open home page
            }
        });




        /** End of OnClick Listeners */


        //For loop to display items
        viewList.removeAll(viewList);
        for (Item image : Items)
        {
            ImageView map = new ImageView(this);/** This code adds a button each time*/
            map.setImageBitmap(image.getImage());
            //map.setClickable(true);
            map.setPadding(18, 18, 18, 18);
            map.setId(Items.indexOf(image));
            map.setSelected(false);
            viewList.add(map);
            ItemsScroll.addView(map);
            createOnLongClick(map);
        }
    }

    /** Empty method to prevent errors */
    public void createItem()
    {
        //MyItem Item = new Item(tag)
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 7); /** this is connected to "OnActivityResult" Method */
    }

    public void createOutfit() {
        Intent intent = new Intent(this, com.closetkeeper.dressy.home.class);
        startActivity(intent);
    }

    /** this method creates an onlongclick listener for every button that calls this method. */
    public void createOnLongClick(ImageView map){
        map.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if (map.isSelected() != true)
                {
                    map.setAlpha(70);
                    map.setSelected(true);

                }
                else { //isSelected() is true
                    map.setAlpha(1000);
                    map.setSelected(false);
                    //map.setBackgroundColor(getResources().getColor(R.color.purple));
                }
                return true;
            }
        });
    }

}