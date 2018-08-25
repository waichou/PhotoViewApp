package com.example.photoview;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 参考实例：ViewPager + PhotoView ==> https://blog.csdn.net/m0_37168878/article/details/78905370
 * 官网地址：https://github.com/chrisbanes/PhotoView
 *
 123-456789
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.big_img_vp)
    ViewPager bigImgVp;
    private int position;
    private ArrayList<String> paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_big_img);
        ButterKnife.bind(this);
//        Intent intent = getIntent();
//        position = intent.getIntExtra("position", 0);
//        paths = intent.getStringArrayListExtra("paths");
//        String title = intent.getStringExtra("title");
//        headerTitle.setText(title);
//        headerLeftImg.setVisibility(View.VISIBLE);
//        headerRightTv.setVisibility(View.VISIBLE);
//        headerRightTv.setText(position + 1 + "/" + paths.size());

        paths = new ArrayList<>();
        paths.addAll(Arrays.asList(randomPic()));

        initView();
    }

    public String[] randomPic() {
        String urlSmall = "http://git.oschina.net/alexyu.yxj/MyTmpFiles/raw/master/kmk_pic_fld/small/";
        String[] pics = new String[]{
                "120.JPG",
                "127.JPG",
                "130.JPG",
                "18.JPG",
                "184.JPG",
                "22.JPG",
                "236.JPG",
                "237.JPG",
                "254.JPG",
                "255.JPG",
                "263.JPG",
                "265.JPG",
                "273.JPG",
                "37.JPG",
                "39.JPG",
                "IMG_2219.JPG",
                "IMG_2270.JPG",
                "IMG_2271.JPG",
                "IMG_2275.JPG",
                "107.JPG"
        };
        int index = 0;
        return new String[]{
                urlSmall + pics[index],
                urlSmall + pics[index + 1],
                urlSmall + pics[index + 2],
                urlSmall + pics[index + 3],
        };
    }

    private void initView() {
        bigImgVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return paths == null ? 0 : paths.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View adView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_big_img, null);
                PhotoView icon = (PhotoView) adView.findViewById(R.id.flaw_img);
//                icon.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                Glide.with(MainActivity.this)
                        .load(paths.get(position))
                        .into(icon);
                container.addView(adView);
                return adView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        bigImgVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                headerRightTv.setText(position + 1 + "/" + paths.size());

                Toast.makeText(MainActivity.this, position + 1 + "/" + paths.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        bigImgVp.setCurrentItem(position, true);
    }

//    @OnClick(R.id.header_left_img)
//    public void onClick() {
//        finish();
//    }
}
