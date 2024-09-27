package de.tubs.cs.ias.unity3dandroidtestbed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.widget.RelativeLayout;

import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

import de.tubs.cs.ias.unity3dandroidtestbed.databinding.FragmentBasicFuncBinding;

public class BasicFunctionality extends Fragment {

    private FragmentBasicFuncBinding binding;
    public static UnityBannerSize BANNER_SIZE = new UnityBannerSize(320,50);
    public static String PLACEMENT_ID = "Banner_Android";


    private BannerView.IListener bannerListener = new BannerView.IListener() {
        @Override
        public void onBannerLoaded(BannerView bannerAdView) {
            Log.v("UnityAdsExample", "onBannerLoaded: " + bannerAdView.getPlacementId());
            binding.textviewBasicFunctionality.setText("Banner Loaded!");
        }

        @Override
        public void onBannerClick(BannerView bannerAdView) {
            Log.v("UnityAdsExample", "onBannerClick: " + bannerAdView.getPlacementId());
            binding.textviewBasicFunctionality.setText("Banner Click!");
        }

        @Override
        public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
            Log.e("UnityAdsExample", "Unity Ads failed to load banner for " + bannerAdView.getPlacementId() + " with error: [" + errorInfo.errorCode + "] " + errorInfo.errorMessage);
            binding.textviewBasicFunctionality.setText("Banner Error [" + errorInfo.errorCode + "] " + errorInfo.errorMessage);
        }

        @Override
        public void onBannerLeftApplication(BannerView bannerAdView) {
            Log.v("UnityAdsExample", "onBannerLeftApplication: " + bannerAdView.getPlacementId());
            binding.textviewBasicFunctionality.setText("Banner left app!");
        }
    };

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentBasicFuncBinding.inflate(inflater, container, false);

        binding.buttonBanner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                BannerView banner = new BannerView(BasicFunctionality.this.getActivity(),PLACEMENT_ID,BANNER_SIZE);
                RelativeLayout bannerView = BasicFunctionality.this.getActivity().findViewById(R.id.banner_basic_functionality);
                banner.setEnabled(true);
                banner.setListener(bannerListener);
                banner.load();
                bannerView.addView(banner);
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}