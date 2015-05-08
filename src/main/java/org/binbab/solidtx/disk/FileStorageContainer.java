/*
 * This file is part of the Solid TX project.
 *
 * Copyright (c) 2015. sha1(OWNER) = df334a7237f10846a0ca302bd323e35ee1463931
 * --> See LICENSE.txt for more information.
 *
 * @author BinaryBabel OSS (http://code.binbab.org)
 */

package org.binbab.solidtx.disk;

import org.binbab.solidtx.TxStack;
import org.binbab.solidtx.core.storage.Container;
import org.binbab.solidtx.core.storage.DataStore;
import org.binbab.solidtx.core.storage.SD0A;
import org.binbab.solidtx.core.storage.SD0C;
import org.binbab.solidtx.core.storage.StorageAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileStorageContainer extends Container implements SD0C {

    protected String filepath;

    public FileStorageContainer(StorageAdapter adapter, String filepath) {
        super(adapter);
        this.filepath = filepath;
    }

    @Override
    protected SD0A getAdapter() {
        return (SD0A) adapter;
    }

    @Override
    public void empty() {
        (new File(filepath)).delete();
    }

    @Override
    public void replaceDataStoreAttributes(DataStore input) {
        replaceDataStore(input);
    }

    @Override
    public void retrieveDataStoreAttributes(DataStore output) {
        retrieveDataStore(output);
    }

    public void replaceDataStore(DataStore input) {
        FileOutputStream io = null;
        try {
            io = new FileOutputStream(filepath);
            getAdapter().encodeDataStore(input, io);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TxStack.closeQuietly(io);
        }
    }

    public void retrieveDataStore(DataStore output) {
        FileInputStream io = null;
        try {
            io = new FileInputStream(filepath);
            getAdapter().decodeDataStore(output, io);
        } catch (FileNotFoundException e) {
            // Do not error if data file does not yet exist.
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TxStack.closeQuietly(io);
        }
    }
}
