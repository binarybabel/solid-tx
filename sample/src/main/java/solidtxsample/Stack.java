package solidtxsample;

import org.binarybabel.solidtx.TxStack;
import org.binarybabel.solidtx.core.network.NetworkManager;
import org.binarybabel.solidtx.core.storage.StorageManager;
import org.binarybabel.solidtx.disk.FileStorageContainer;
import org.binarybabel.solidtx.http.HttpGateway;
import org.binarybabel.solidtx.json.JsonAdapter;

import java.net.MalformedURLException;
import java.net.URL;

public class Stack extends TxStack {

    public Stack() {
        super();
    }

    static protected JsonAdapter jsonAdapter = new JsonAdapter();

    @Override
    protected void setupNetwork(NetworkManager nm) {
        HttpGateway gw = null;
        try {
            gw = new HttpGateway(new URL("http://localhost:38080/payload/endpoint"), jsonAdapter);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        nm.addGateway(gw);
        addRoute(Person.class, gw, "person");
        addRoute(Place.class, gw, "place");
        addRoute(Thing.class, gw, "thing");
    }

    @Override
    protected void setupStorage(StorageManager sm) {
        sm.addContainer(new FileStorageContainer(
                jsonAdapter,
                "database.json"
        ));
    }

    @Override
    public String getVersion() {
        return "0";
    }

}
