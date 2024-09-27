package de.tubs.cs.ias.unity3dandroidtestbed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.unity3d.ads.metadata.MetaData;

import de.tubs.cs.ias.unity3dandroidtestbed.databinding.FragmentConsentBinding;

public class Consent extends Fragment {

    private FragmentConsentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentConsentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonConsentYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MetaData gdprMetaData = new MetaData(Consent.this.getActivity());
                gdprMetaData.set("gdpr.consent", true);
                gdprMetaData.commit();
                binding.textviewConsent.setText("Consent Given");
            }
        });

        binding.buttonConsentNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MetaData gdprMetaData = new MetaData(Consent.this.getActivity());
                gdprMetaData.set("gdpr.consent", false);
                gdprMetaData.commit();
                binding.textviewConsent.setText("Consent Refused");
            }
        });

        binding.buttonConsentNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Consent.this)
                        .navigate(R.id.action_consent_to_initialize);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}