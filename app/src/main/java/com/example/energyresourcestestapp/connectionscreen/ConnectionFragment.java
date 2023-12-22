package com.example.energyresourcestestapp.connectionscreen;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.energyresourcestestapp.infoserverscreen.InfoFragment;
import com.example.energyresourcestestapp.R;
import com.example.energyresourcestestapp.network.ServerInfo;

public class ConnectionFragment extends Fragment {
    private Button btnConnectionServer;
    private EditText etAddressServer;
    private ConnectionServerViewModel connectionServerViewModel;
    private ProgressBar progressBar;

    public ConnectionFragment() {
        super(R.layout.fragment_connection);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnConnectionServer = view.findViewById(R.id.btnConnection);
        etAddressServer = view.findViewById(R.id.etAddressServer);
        progressBar = view.findViewById(R.id.progressBar);
        connectionServerViewModel = new ConnectionServerViewModel();
        connectionServerViewModel.liveDataServerInfo.observe(
                getViewLifecycleOwner(),
                this::goToInfoFragment);
        connectionServerViewModel.liveDataError.observe(
                getViewLifecycleOwner(),
                this::showError);
        btnConnectionServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                connectionServerViewModel.connectButtonPressed(etAddressServer.getText().toString());
            }
        });
    }

    private void goToInfoFragment(ServerInfo serverInfo) {
        progressBar.setVisibility(View.GONE);
        InfoFragment instanceInfoFragment = InfoFragment.newInstance(serverInfo);
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.container, instanceInfoFragment).addToBackStack(null).commit();
    }

    private void showError(Error error) {
        progressBar.setVisibility(View.GONE);
        switch (error) {
            case ERROR_URL:
                showToast(R.string.msg_error_format_address);
                break;
            case ERROR_NETWORK:
                showToast(R.string.msg_error_network);
                break;
            case ERROR_SERVER:
                showToast(R.string.msg_error_server);
                break;
            case ERROR_METHOD:
                showToast(R.string.msg_error_method);
                break;
        }
    }

    private void showToast(int resIdErrorMsg) {
        Toast.makeText(this.getContext(), resIdErrorMsg, Toast.LENGTH_LONG).show();
    }
}
