/*
 * This file is part of the Solid TX project.
 *
 * Copyright (c) 2015. sha1(OWNER) = df334a7237f10846a0ca302bd323e35ee1463931
 * --> See LICENSE.txt for more information.
 *
 * @author BinaryBabel OSS (http://code.binbab.org)
 */

package org.binarybabel.solidtx.core.storage;

import org.binarybabel.solidtx.core.data.DataIndex;

import java.util.Map;

/**
 * Extension of DataIndex to support additional storage
 * metadata and helper functions.
 */
public class StorageIndex extends DataIndex {
    private Map<String,String> attributes;

    /**
     * Create new StorageIndex from existing DataStore.
     * @param dataStore the data store to import from
     */
    public StorageIndex(DataStore dataStore) {
        super(dataStore);
        attributes = dataStore.getAttributes();
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String getAttribute(String key) {
        return attributes.get(key);
    }
}
