package solidtxsample;

import org.binbab.solidtx.TxStack;
import org.binbab.solidtx.core.network.NetworkManager;
import org.binbab.solidtx.core.storage.StorageManager;
import org.binbab.solidtx.disk.FileStorageContainer;
import org.binbab.solidtx.http.HttpGateway;
import org.binbab.solidtx.json.JsonAdapter;
import org.binbab.solidtx.nulled.NullStorageContainer;

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
