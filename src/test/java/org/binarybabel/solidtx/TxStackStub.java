/*
 * This file is part of the Solid TX project.
 *
 * Copyright (c) 2014. sha1(OWNER) = df334a7237f10846a0ca302bd323e35ee1463931
 * --> See LICENSE.txt for more information.
 *
 * @author BinaryBabel OSS (http://code.binbab.org)
 */

package org.binarybabel.solidtx;

import org.binarybabel.solidtx.core.network.NetworkManager;
import org.binarybabel.solidtx.core.object.ObjectManager;
import org.binarybabel.solidtx.core.router.Router;
import org.binarybabel.solidtx.core.storage.StorageManager;

public class TxStackStub extends TxStack {

    public TxStackStub() {
        super();
    }

    public TxStackStub(Router r, ObjectManager om, NetworkManager nm, StorageManager sm) {
        super(r, om, nm, sm);
    }

    @Override
    protected void setupNetwork(NetworkManager nm) {

    }

    @Override
    protected void setupStorage(StorageManager sm) {

    }

    @Override
    public String getVersion() {
        return "0";
    }
}
