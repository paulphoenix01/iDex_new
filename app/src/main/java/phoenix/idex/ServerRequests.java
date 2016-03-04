package phoenix.idex;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Ravinder on 3/2/16.
 */
public class ServerRequests {
    private ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://www.http://idex.site88.net/";

    public ServerRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait...");
    }
}
