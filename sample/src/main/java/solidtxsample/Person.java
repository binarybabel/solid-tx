package solidtxsample;

import org.binarybabel.solidtx.txo.TxBaseObject;
import org.binarybabel.solidtx.txo.TxField;

public class Person extends TxBaseObject {

    @TxField
    public String firstName;

    @TxField
    public String lastName;

    @TxField
    public Place hometown;

    @TxField(optional = true)
    public String[] favorite_colors;

    @TxField(optional = true)
    public Thing[] favorite_things;

}
