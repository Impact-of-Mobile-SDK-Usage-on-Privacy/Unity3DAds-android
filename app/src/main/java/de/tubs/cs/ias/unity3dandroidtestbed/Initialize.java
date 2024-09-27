package de.tubs.cs.ias.unity3dandroidtestbed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.UnityAds;

import de.tubs.cs.ias.unity3dandroidtestbed.databinding.FragmentInitializeBinding;

public class Initialize extends Fragment {

    private FragmentInitializeBinding binding;
    public static String UNITY_GAME_ID = "UNITY_ID_PLACEHOLDER";
    public static Boolean TEST_MODE = false;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentInitializeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonInitializeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Initialize.this)
                        .navigate(R.id.action_initialize_to_basic_functionality);
            }
        });

        binding.buttonInitialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)Initialize.this.getActivity()).initializeUnity3d(UNITY_GAME_ID,TEST_MODE,
                        new IUnityAdsInitializationListener(){
                            @Override
                            public void onInitializationComplete() {
                                binding.textviewInitialize.setText("Initialize [SUCC]");
                            }

                            @Override
                            public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {
                                binding.textviewInitialize.setText("Initialize [FAIL]");
                            }
                        });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
