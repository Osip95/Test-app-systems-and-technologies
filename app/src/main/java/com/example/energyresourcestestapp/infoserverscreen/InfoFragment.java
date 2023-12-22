package com.example.energyresourcestestapp.infoserverscreen;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.energyresourcestestapp.R;
import com.example.energyresourcestestapp.network.ServerInfo;

public class InfoFragment extends Fragment {
    private final static String ARGUMENTS_KEY = "key";
    private TextView tvOrganization;
    private TextView tvVersion;
    private TextView tvUpdatePack;
    private Button btnOk;
    private final InfoServerViewModel infoServerViewModel = new InfoServerViewModel();

    public InfoFragment() {
        super(R.layout.fragment_info);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvOrganization = view.findViewById(R.id.tvOrganization);
        tvVersion = view.findViewById(R.id.tvVersion);
        tvUpdatePack = view.findViewById(R.id.tvUpdatePack);
        btnOk = view.findViewById(R.id.btnOk);
        infoServerViewModel.liveDataServerInfo.observe(getViewLifecycleOwner(), this::updateUI);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        if (getArguments() != null) {
            infoServerViewModel.onCreatedFragment(
                    getArguments().getParcelable(ARGUMENTS_KEY));
        }
    }

    public static InfoFragment newInstance(ServerInfo serverInfo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENTS_KEY, serverInfo);
        InfoFragment infoFragment = new InfoFragment();
        infoFragment.setArguments(bundle);
        return infoFragment;
    }

    private void updateUI(ServerInfo serverInfo) {
        tvOrganization.append(" " + serverInfo.organization);
        tvVersion.append(" " + serverInfo.version);
        tvUpdatePack.append(" " + serverInfo.versionUpdatePack);
    }

}

